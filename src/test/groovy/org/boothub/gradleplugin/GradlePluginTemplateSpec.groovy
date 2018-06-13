package org.boothub.gradleplugin

import java.nio.file.Paths
import org.boothub.Initializr
import org.boothub.gradle.*
import spock.lang.Specification
import spock.lang.Unroll

class GradlePluginTemplateSpec extends Specification {

    private static final String TEMPLATE_DIR = getPath("/template")



//    private static final String BASE_PATH = 'org/bizarre_soft/weird_app'
    private static final String BASE_PATH = 'org/bizarre_soft'
    private static final APP_MAIN_CLASS = 'WeirdAppMain'
//    private static final APP_MAIN_CLASS_PATH = "$BASE_PATH/${APP_MAIN_CLASS}.class"
    private static final APP_MAIN_CLASS_PATH = "$BASE_PATH/weird_app/${APP_MAIN_CLASS}.class"

private static final String CONTEXT_SINGLE = getPath("/base-context-single.yml")


    private static String getPath(String resourcePath) {
        def resource = GradlePluginTemplateSpec.class.getResource(resourcePath)
        assert resource : "Resource not available: $resourcePath"
        Paths.get(resource.toURI()).toAbsolutePath().toString()
    }

def "should create a valid artifact using base-context-single.yml"() {
        when:
        def artifacts = new GradleTemplateBuilder(TEMPLATE_DIR)
                .withContextFile(CONTEXT_SINGLE)
                .runGradleBuild()
                .artifacts
        def jars = artifacts['jar']

        then:
        jars.size() == 1
        jars[0].getEntry(APP_MAIN_CLASS_PATH) != null
    }

def "should create a valid application using base-context-single.yml"() {
        when:
        def context = new Initializr(TEMPLATE_DIR).createContext(CONTEXT_SINGLE)

        then:
        new OutputChecker(TEMPLATE_DIR, context)
                .checkOutput("Hello from $context.appMainClass!")
    }


}
