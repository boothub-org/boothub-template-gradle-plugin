package {{targetPackage}}

class \{{taskClassName}}Impl {
    String \{{taskName}}InputProperty
    File \{{taskName}}OutputProperty

    \{{taskClassName}}Impl(String sampleInputProperty, File \{{taskName}}OutputProperty) {
        this.\{{taskName}}InputProperty = sampleInputProperty
        this.\{{taskName}}OutputProperty = \{{taskName}}OutputProperty
    }

    void execute() {
        \{{taskName}}OutputProperty.text = "\{{taskName}}: \{{taskName}}InputProperty = $\{{taskName}}InputProperty"
    }
}
