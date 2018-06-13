A template for creating Gradle plugins

The generated project contains:
 - the plugin main class
 - the plugin extension class
 - the class defining the main task of the plugin 
 - the implementation class of the main task
 - Spock unit tests for the task implementation 
 - Spock integration tests (using the Gradle TestKit)


The template lets you customize:
 - the plugin id
 - the name of the main task provided by your plugin
 - the name of the plugin class
 - the name of the class defining the main task
 - the extension used to configure the plugin
