package {{targetPackage}}

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import {{targetPackage}}.impl.\{{taskClassName}}Impl

class \{{taskClassName}} extends DefaultTask {
    @Input @Optional
    Property<String> \{{taskName}}InputProperty

    @OutputFile @Optional
    RegularFileProperty \{{taskName}}OutputProperty

    @TaskAction
    void \{{taskName}}TaskAction() {
        def input = \{{taskName}}InputProperty.get()
        def output = \{{taskName}}OutputProperty.get().asFile
        new \{{taskClassName}}Impl(input, output).execute()
    }
}
