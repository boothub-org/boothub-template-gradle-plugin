## {{projectName}} ##

A Gradle plugin that provides a `{{taskName}}` task, which can be configured using the `{{pluginExtensionName}}` extension.

The current task implementation reads the value of `{{taskName}}InputProperty` and writes it into the file specified by `{{taskName}}OutputProperty`.

Example `build.gradle` configuration:

```
plugins {
    id '{{pluginId}}' version '{{versionMajor}}.{{versionMinor}}.{{versionPatch}}'
}

{{pluginExtensionName}} {
    {{taskName}}InputProperty = 'my-input-value'
    {{taskName}}OutputProperty = file('my-output-file.txt')
}
```
