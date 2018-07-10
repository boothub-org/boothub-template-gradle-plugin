[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/boothub-org/boothub-template-gradle-plugin/blob/master/LICENSE)
[![Build Status](https://img.shields.io/travis/boothub-org/boothub-template-gradle-plugin/master.svg?label=Build)](https://travis-ci.org/boothub-org/boothub-template-gradle-plugin)


## Gradle-Plugin template ##


A BootHub template for Gradle plugins.


To start using the template, 
[click here](https://boothub.org/goto/org.boothub.gradle-plugin).

Click on the image below for an animation showing the template at work.

<a href="https://github.com/boothub-org/boothub-template-gradle-plugin/raw/master/doc/img/plugin.gif"><img src="https://github.com/boothub-org/boothub-template-gradle-plugin/raw/master/doc/img/plugin-thumb.png"></a>


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

