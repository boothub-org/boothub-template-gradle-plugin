/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package {{targetPackage}}

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import spock.lang.Unroll

class \{{pluginClassName}}Spec extends Specification {
    String createBuildContent(String input, String output) {
        def inputCfg = input ? "\{{taskName}}InputProperty = \"$input\"" : ''
        def outputCfg = output ? "\{{taskName}}OutputProperty = file(\"$output\")" : ''
        def extension = (inputCfg || outputCfg) ?
            """
            \{{pluginExtensionName}} {
                $inputCfg
                $outputCfg
            }
            """ : ''
        return """
            plugins {
                id '\{{pluginId}}'
            }
            $extension
        """.stripIndent()
    }

    def setUpBuild(String input = null, String output = null) {
        File buildFile = testProjectDir.newFile("build.gradle")
        buildFile.text = createBuildContent(input, output)
        println "Executing build script:\n${buildFile.text}"
    }

    @Rule final TemporaryFolder testProjectDir = new TemporaryFolder()

    @Unroll
    def "should execute task with input=#input and output=#output"() {
        when:
        setUpBuild(input, output)
        BuildResult result = GradleRunner.create()
                .withProjectDir(testProjectDir.root)
                .withPluginClasspath()
                .withArguments(\{{pluginClassName}}.TASK_NAME, "-is")
                .build();
        def expectedOutputFile = new File("$testProjectDir.root.path/build/$expectedOutputFilePath")

        then:
        result.task(":$\{{pluginClassName}}.TASK_NAME").outcome == TaskOutcome.SUCCESS
        expectedOutputFile.text == "\{{taskName}}: \{{taskName}}InputProperty = $expectedInputVal"

        where:
        input  | output                    || expectedInputVal                             | expectedOutputFilePath
        null   | null                      || '\{{taskName}}InputProperty-default-val' | "\{{pluginExtensionName}}/\{{taskName}}-out.txt"
        'val1' | null                      || 'val1'                                       | "\{{pluginExtensionName}}/\{{taskName}}-out.txt"
        null   | '$buildDir/dir2/out2.txt' || '\{{taskName}}InputProperty-default-val' | "dir2/out2.txt"
        'val3' | '$buildDir/dir3/out3.txt' || 'val3'                                       | "dir3/out3.txt"
    }

}
