# lastminute-exercise
[![Build Status](https://travis-ci.org/fastluca/lastminute-exercise.svg?branch=master)](https://travis-ci.org/fastluca/lastminute-exercise) 
[![Coverage Status](https://sonarcloud.io/api/project_badges/measure?project=com.lastminute%3Aexercise&metric=coverage)](https://sonarcloud.io/api/project_badges/measure?project=com.lastminute%3Aexercise&metric=coverage)  
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=com.lastminute%3Aexercise&metric=alert_status)](https://sonarcloud.io/api/project_badges/measure?project=com.lastminute%3Aexercise&metric=alert_status)   

Exercise solution for technical interview.   
The project is configured to use Travis CI as build service and SonarCloud as code quality and code test coverage analyzer.
 
Requirements
============
* Maven 3.5 (older versions might work too)
* Java Developer Kit 8 with at least Update 40

Build Instruction
============
To create your executable file, call `mvn jfx:jar`. The jar-file will be placed at `target/jfx/app`.  
If you would create your executable and some installers (please see official oracle-documentation which applications are required for this), call `mvn jfx:native`. The native launchers or installers will be placed at `target/jfx/native`.  
Others details about the build process are available at [javafx-maven-plugin project page](https://github.com/javafx-maven-plugin/javafx-maven-plugin).

Run Tests
============
To run tests, call `mvn test`.   
The class `CartServiceUnitTest.java` contains the three test cases provided in requirements.