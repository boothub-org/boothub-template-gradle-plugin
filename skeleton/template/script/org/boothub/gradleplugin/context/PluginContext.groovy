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
package org.boothub.gradleplugin.context

import groovy.transform.SelfType
import org.codehaus.groovy.tools.Utilities
import org.boothub.Util
import org.boothub.context.ConfiguredBy
import org.boothub.context.ProjectContext
import org.boothub.context.TextIOConfigurator
import org.beryx.textio.InputReader
import org.beryx.textio.TextIO

@SelfType(ProjectContext)
@ConfiguredBy(PluginContext.Configurator)
trait PluginContext {
    String pluginBasePackage
    String pluginId
    String pluginExtensionName
    String taskName
    String pluginClassName
    String taskClassName

    static class Configurator extends TextIOConfigurator  {
        static final String PLUGIN_ID_REGEX = '[a-zA-Z][a-zA-Z0-9-.]{1,99}'
        static final String PLUGIN_ID_INFO = 'https://docs.gradle.org/current/userguide/custom_plugins.html#sec:creating_a_plugin_id'

        @Override
        void configureWithTextIO(ProjectContext context, TextIO textIO) {
            def ctx = context as PluginContext

            ctx.pluginBasePackage = context.modules[0].basePackage
            def defaultPluginId = (getPluginIdValidationError(ctx.pluginBasePackage) == null) ? ctx.pluginBasePackage : null
            ctx.pluginId = textIO.newStringInputReader()
                    .withDefaultValue(defaultPluginId)
                    .withValueChecker(pluginIdChecker)
                    .read("Plugin id")

            ctx.pluginExtensionName = textIO.newStringInputReader()
                    .withDefaultValue(getDefaultPluginExtensionName(ctx.pluginId))
                    .withValueChecker(extensionChecker)
                    .read("Plugin extension")

            ctx.taskName = textIO.newStringInputReader()
                    .withDefaultValue(ctx.pluginExtensionName)
                    .withValueChecker(taskNameChecker)
                    .read("Choose a name for the main task of your plugin")

            ctx.pluginClassName = textIO.newStringInputReader()
                    .withDefaultValue(getDefaultPluginClassName(ctx.pluginExtensionName))
                    .withValueChecker(Util.classNameChecker)
                    .read('Plugin class name')

            ctx.taskClassName = textIO.newStringInputReader()
                    .withDefaultValue(getDefaultTaskClassName(ctx.taskName))
                    .read('Task class name')
        }

        private static InputReader.ValueChecker<String> pluginIdChecker = {id, prop ->
            def errMsg = getPluginIdValidationError(id)
            errMsg ? [errMsg, "See $PLUGIN_ID_INFO" as String] : null
        }

        private static String getPluginIdValidationError(String id) {
            if(!id || !id.matches(PLUGIN_ID_REGEX)) return 'Not a valid plugin id'
            if(!id.contains('.')) return "The id must contain at least one '.' character"
            if(id.endsWith('.')) return "The id cannot end with a '.' character"
            if(id.contains('..')) return "The id cannot consecutive '.' characters"
            if(id.startsWith('org.gradle.') || id.startsWith('com.gradleware.')) return "org.gradle and com.gradleware namespaces may not be used"
            return null
        }

        private static InputReader.ValueChecker<String> extensionChecker = {name, prop ->
            Utilities.isJavaIdentifier(name) ? null : ['Invalid extension', 'Use a valid java identifier']
        }

        private static InputReader.ValueChecker<String> taskNameChecker = {name, prop ->
            Utilities.isJavaIdentifier(name) ? null : ['Invalid task name', 'Use a valid java identifier']
        }

        private static String getDefaultPluginExtensionName(String pluginId) {
            if(!pluginId) return 'hello'
            def name = pluginId.tokenize('.').last()
            int pos = name.toLowerCase().indexOf('plugin')
            if(pos >= 0) {
                name = name.substring(0, pos) + name.substring(pos + 6)
                if(name.length() < 2) return 'hello'
            }
            return Util.asJavaId(name)
        }

        private static String getDefaultPluginClassName(String extensionName) {
            return Util.asJavaClassName(extensionName) + 'Plugin'
        }

        private static String getDefaultTaskClassName(String taskName) {
            return Util.asJavaClassName(taskName) + 'Task'
        }
    }
}
