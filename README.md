# lastminute-exercise
**Continuous Integration:** [![Build Status](https://travis-ci.org/fastluca/lastminute-exercise.svg?branch=master)](https://travis-ci.org/fastluca/lastminute-exercise)   
**Test coverage:** [![Coverage Status](https://coveralls.io/repos/github/fastluca/lastminute-exercise/badge.svg?branch=master)](https://coveralls.io/github/fastluca/lastminute-exercise?branch=master)   

Requirements
============
* Maven 3.5 (older versions might work too)
* Java Developer Kit 8 with at least Update 40

Build Instruction
============
To create your executable file, call `mvn jfx:jar`. The jar-file will be placed at `target/jfx/app`.  
If you would create your executable and some installers (please see official oracle-documentation which applications are required for this), call `mvn jfx:native`. The native launchers or installers will be placed at `target/jfx/native`.  
Others details about the build process are available at https://github.com/javafx-maven-plugin/javafx-maven-plugin.

Run Tests
============
To run tests, call `mvn test`.