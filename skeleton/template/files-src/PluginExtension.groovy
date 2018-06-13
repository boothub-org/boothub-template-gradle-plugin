package {{targetPackage}}

import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property

class \{{pluginClassName}}Extension {
    final Property<String> \{{taskName}}InputProperty
    final RegularFileProperty \{{taskName}}OutputProperty

    \{{pluginClassName}}Extension(Project project) {
        \{{taskName}}InputProperty = project.objects.property(String)
        \{{taskName}}InputProperty.set('\{{taskName}}InputProperty-default-val')
        \{{taskName}}OutputProperty = project.layout.fileProperty()
        \{{taskName}}OutputProperty.set(project.file("$project.buildDir/\{{pluginExtensionName}}/\{{taskName}}-out.txt"))
    }
}
