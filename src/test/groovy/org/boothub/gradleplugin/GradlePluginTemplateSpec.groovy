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
package org.boothub.gradleplugin

import java.nio.file.Paths
import org.boothub.gradle.*
import spock.lang.Specification

class GradlePluginTemplateSpec extends Specification {
    private static final String TEMPLATE_DIR = getPath("/template")
    private static final String CONTEXT_SINGLE = getPath("/base-context-single.yml")

    private static String getPath(String resourcePath) {
        def resource = GradlePluginTemplateSpec.class.getResource(resourcePath)
        assert resource : "Resource not available: $resourcePath"
        Paths.get(resource.toURI()).toAbsolutePath().toString()
    }

def "should create a valid plugin using base-context-single.yml"() {
        when:
        def artifacts = new GradleTemplateBuilder(TEMPLATE_DIR)
                .withContextFile(CONTEXT_SINGLE)
                .runGradleBuild()
                .artifacts
        def jars = artifacts['jar']

        then:
        jars.size() == 1
    }
}
