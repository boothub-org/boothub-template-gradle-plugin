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

import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import spock.lang.Unroll
import {{targetPackage}}.impl.\{{taskClassName}}Impl

class \{{taskClassName}}Spec extends Specification {
    @Rule final TemporaryFolder testProjectDir = new TemporaryFolder()

    @Unroll
    def "should create the correct output"() {
        given:
        def input = 'my-input'
        def output = testProjectDir.newFile('my-output.txt')

        when:
        new \{{taskClassName}}Impl(input, output).execute()

        then:
        output.text == "$\{{pluginClassName}}.TASK_NAME: \{{taskName}}InputProperty = $input"
    }
}
