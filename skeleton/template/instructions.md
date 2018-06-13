### Getting started

{{#if ghApiUsed}}
```
git clone https://github.com/{{ghProjectOwner}}/{{ghProjectId}}.git
cd {{ghProjectId}}
```
{{else}}
Download the generated zip file and unpack it. In the {{ghProjectId}} directory execute:
{{/if}}


&#8226; *On Linux or Mac OS:*
```
./gradlew build
```

&#8226; *On Windows:*
```
gradlew build
```

The generated plugin provides a `{{taskName}}` task, which can be configured using the `{{pluginExtensionName}}` extension.

The sample task implementation reads the value of an input property and writes it into the file specified by an output property.
You should replace it with your own implementation.

See the [template documentation](https://github.com/boothub-org/boothub-template-gradle-plugin/blob/master/README.md) for more info.
