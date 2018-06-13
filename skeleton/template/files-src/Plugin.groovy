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

import org.gradle.api.Plugin
import org.gradle.api.Project

class \{{pluginClassName}} implements Plugin<Project> {
    final static TASK_NAME = '\{{taskName}}'

    @Override
    void apply(Project project) {
        def extension = project.extensions.create('\{{pluginExtensionName}}', \{{pluginClassName}}Extension, project)
        project.getTasks().create(TASK_NAME, \{{taskClassName}}, { \{{taskClassName}} task ->
            task.\{{taskName}}InputProperty = extension.\{{taskName}}InputProperty
            task.\{{taskName}}OutputProperty = extension.\{{taskName}}OutputProperty
        })
    }
}
