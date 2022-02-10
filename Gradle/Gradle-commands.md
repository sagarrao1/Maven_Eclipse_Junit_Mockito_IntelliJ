Code we can write in build.grade
	apply plugin : 'java'
	apply plugin : 'application'

Gradle commands
    gradle clean  -- will do clean
    gradle clean build  -- will do clean and build
   
    gradle tasks  -- will list of task 
 in project
    gradle clean build  --info   -- will provide list of steps executing
    

Project represents a thing to be done like deploying app to staging env
       gradle project requires a set tasks to execute
Task refers to piece of work performed by build, it can be compiling classes, creating jar file, making javadoc,
What is the task : Code that gradle executed for us

Build script is known as build.gradle and located in root directory of project. Every gradle build consists of one or more projects

We can integrate functional test cases


Screen clipping taken: 31-01-2022 09:31 PM



Always better command to run build gradle wrapper it will build with same version
Lets say you build project with gradle versio 7.2 and pushed changes to git
Other persin installed gradle 4.0 version and try to run build, we never know what issue we may get to avoid that if run build gradle wrapper . It uses same version that project has build
One more this , you don't need to have gradle in your system, wrapper download correct version zip file from run the build with version
Advatage of this , we carry the version of gradle along with gradle file and check all this into git (source control)
It's very nice feature of gradle. It has ability to carry the version of gradle along with gradle file and use the correct version to built it


 gradlew build    -> windows
./gradlew build   - > unix

gradle tasks --all
gradle Task6
gradle -q Task6  --> q : quiet

task taskA doLast {println "task A"}
task taskB doLast {println "task B"}

taskB.mustRunAfter taskA

// it is like if we run taskB and taskA , it should run taskA first and taskB 2nd. gradle taskB taskA if // we run only taskB it will execute only taskB

Gradle taskB taskA


Apply plugin : 'java'

gradle init

We can see what tasks are executed using -i  it shows more info of tasks
gradle -i build

To ru build faster. Daemon uses existing jvm process to execute java. 
gradle --daemon build

To see dependencies
gradle -q dependencies

To gerenerate wrapper for project 
gradle wrapper   => it generate gradle/wrapper folder and it contains gradle-wrapper.properties file which tells with what gradle version we have build the project. Then we can use below gradlew command to build the project. It downloads the that version and build with  that. For this we don't need to have gradle installed on our machine

gradlew build  -> windows
./gradlew build  -> unix

It will generate java docs in build/libs/doc/javadoc/index-all.html with build command
gradle javaDoc

To see dependencies
gradle -q dependencies
gradle -q dependencies --configuration implementation
gradle -q dependencies --configuration testImplementation
gradle -q dependencies --configuration compileOnly
gradle -q dependencies --configuration runtimeOnly

gradle compileJava
gradle test

In groovy when we want to use string interpolation we need to use double quotes
buildscript {
  ext {
    log4j_version = '1.2.17'
  }
}

implementation 'log4j:log4j:2.17.0'
implementation "log4j:log4j:$log4j_version"

To see list of task for build will execute
gradle build --console plain


To add to see whether test are failed or passed, we need to this test to build.gradle

import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

test {
  testLogging {
    events TestLogEvent.FAILED,           
           TestLogEvent.SKIPPED,
		   TestLogEvent.PASSED
  }
}


Running gradle wrapper perticular version
gradle wrapper
gradle wrapper --gradle-version 6.3  -> will create wrapper for 6.3 and when run build using gradlw build it download 6.3 and run  witj it
gradlew build

gradle wrapper --gradle-version 6.3 --distribution-type all  -> by default it download bin (binary) version 


If we need to refresh dependencies in local cache with remote
gradle --refresh dependencies

--build-cache                      Enables the Gradle build cache. Gradle will try to reuse outputs from previous builds.
-c, --settings-file                Specify the settings file. (deprecated)
--configuration-cache              Enables the configuration cache. Gradle will try to reuse the build configuration from previous builds. [incubating]
--configuration-cache-problems     Configures how the configuration cache handles problems (fail or warn). Defaults to fail. [incubating]
--configure-on-demand              Configure necessary projects only. Gradle will attempt to reduce configuration time for large multi-project builds. [incubating]
--console                          Specifies which type of console output to generate. Values are 'plain', 'auto' (default), 'rich' or 'verbose'.
--continue                         Continue task execution after a task failure.
-D, --system-prop                  Set system property of the JVM (e.g. -Dmyprop=myvalue).
-d, --debug                        Log in debug mode (includes normal stacktrace).
--daemon                           Uses the Gradle Daemon to run the build. Starts the Daemon if not running.
--export-keys                      Exports the public keys used for dependency verification.
-F, --dependency-verification      Configures the dependency verification mode (strict, lenient or off)
--foreground                       Starts the Gradle Daemon in the foreground.
-g, --gradle-user-home             Specifies the gradle user home directory.
-I, --init-script                  Specify an initialization script.
-i, --info                         Set log level to info.
--include-build                    Include the specified build in the composite.
-M, --write-verification-metadata  Generates checksums for dependencies used in the project (comma-separated list)
-m, --dry-run                      Run the builds with all task actions disabled.
--max-workers                      Configure the number of concurrent workers Gradle is allowed to use.
--no-build-cache                   Disables the Gradle build cache.
--no-configuration-cache           Disables the configuration cache. [incubating]
--no-configure-on-demand           Disables the use of configuration on demand. [incubating]
--no-daemon                        Do not use the Gradle daemon to run the build. Useful occasionally if you have configured Gradle to always run with the daemon by default.
--no-parallel                      Disables parallel execution to build projects.
--no-scan                          Disables the creation of a build scan. For more information about build scans, please visit https://gradle.com/build-scans.
--no-watch-fs                      Disables watching the file system.
--offline                          Execute the build without accessing network resources.
-P, --project-prop                 Set project property for the build script (e.g. -Pmyprop=myvalue).
-p, --project-dir                  Specifies the start directory for Gradle. Defaults to current directory.
--parallel                         Build projects in parallel. Gradle will attempt to determine the optimal number of executor threads to use.
--priority                         Specifies the scheduling priority for the Gradle daemon and all processes launched by it. Values are 'normal' (default) or 'low'
--profile                          Profile build execution time and generates a report in the <build_dir>/reports/profile directory.
--project-cache-dir                Specify the project-specific cache directory. Defaults to .gradle in the root project directory.
-q, --quiet                        Log errors only.
--refresh-dependencies             Refresh the state of dependencies.
--refresh-keys                     Refresh the public keys used for dependency verification.
--rerun-tasks                      Ignore previously cached task results.
-S, --full-stacktrace              Print out the full (very verbose) stacktrace for all exceptions.
-s, --stacktrace                   Print out the stacktrace for all exceptions.
--scan                             Creates a build scan. Gradle will emit a warning if the build scan plugin has not been applied. (https://gradle.com/build-scans)
--status                           Shows status of running and recently stopped Gradle Daemon(s).
--stop                             Stops the Gradle Daemon if it is running.
-t, --continuous                   Enables continuous build. Gradle does not exit and will re-execute tasks when task file inputs change.
--update-locks                     Perform a partial update of the dependency lock, letting passed in module notations change version. [incubating]
-v, --version                      Print version info.
-w, --warn                         Set log level to warn.
--warning-mode                     Specifies which mode of warnings to generate. Values are 'all', 'fail', 'summary'(default) or 'none'
--watch-fs                         Enables watching the file system for changes, allowing data about the file system to be re-used for the next build.
--write-locks                      Persists dependency resolution for locked configurations, ignoring existing locking information if it exists
-x, --exclude-task                 Specify a task to be excluded from execution.

build.gradle file

plugins {
	id 'java'
}

version ='1.0_SNAPSHOT'

//sourceCompatibility = JavaVersion.VERSION_1_8
//targetCompatibility = JavaVersion.VERSION_1_8
sourceCompatibility = 11
targetCompatibility = 11
//withJavadocJar()
//withSourcesJar()

//mainClassName = 'com.pluralsight.security.Hash'

jar {
	manifest{
		attributes('Main-Class': 'com.sagar.SayHello')
	}
}

dependencies {
	testImplementation 'org.junit.jupiter:junit-jupiter:5.6.2'
	
	// https://mvnrepository.com/artifact/org.mockito/mockito-junit-jupiter
	testImplementation group: 'org.mockito', name: 'mockito-junit-jupiter', version: '4.2.0'
	
}

repositories {
	mavenCentral()
}

test {
	useJUnitPlatform()
}

// Add groovy script like below

def namesGroovy = ['sagar','bobby','ram']
println namesGroovy.sort()

def amazingCalculator = {a, b -> a+b }
println amazingCalculator(2,3)






Open the project you built in the Creating your first Gradle project lesson. Use a command-line, text editor, or an IDE as you prefer.
Adding the Java code
Add a directory src/main/java for the main application code.
Create directories for the Java package com/tomgregory/languageapp.
Create a new file SayHello.java and open it for editing.
Paste in the following Java code:
package com.tomgregory.languageapp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
public class SayHello {
    public static void main(String[] args) throws IOException {
        String language = args[0];
InputStream resourceStream = SayHello.class.getClassLoader().getResourceAsStream(language + ".txt");
        assert resourceStream != null;
        BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8));
System.out.println(bufferedInputStream.readLine());
    }
}
Adding the resources
Create a new directory under src/main called resources.
Add a file en.txt with contents Hello!.
Add a file es.txt with contents Hola!.
Building the application
Open build.gradle and delete the comment.
Insert this plugins configuration:
plugins {
    id 'java'
}
See what Gradle tasks we have available:
 ./gradlew tasks (Linux/Mac)
gradlew.bat tasks (Windows)
All the tasks listed under Build tasks have been added to the project by the Java plugin.
To see all Gradle tasks, including compileJava and processResources run:
./gradlew tasks --all (Linux/Mac)
gradlew.bat tasks --all (Windows)
These tasks appear under Other tasks.
Compile the Java classes:
./gradlew compileJava  (Linux/Mac)
gradlew.bat compileJava (Windows)
Look for output in build/classes/java/main.
Process resources:
./gradlew processResources (Linux/Mac)
gradlew.bat processResources (Windows)
Look for output in build/resources/main.
Generate a jar file:
./gradlew jar (Linux/Mac)
gradlew.bat jar (Windows)
Look for the jar file in build/libs.
Running the application
Run the following Java command:
java -jar build/libs/get-going-with-gradle.jar en (Linux/Mac/Windows)
You’ll see an error which says no main manifest attribute.
Add this jar configuration to the end of build.gradle:
jar {
    manifest {
        attributes('Main-Class': 'com.tomgregory.languageapp.SayHello')
    }
}
Build the jar file again:

	./gradlew jar (Linux/Mac)
gradlew.bat jar (Windows)
Rerun the Java command:
java -jar build/libs/get-going-with-gradle.jar en (Linux/Mac/Windows)
You should see the text Hello! output.
Run the same Java command but replace en with es for Spanish
java -jar build/libs/get-going-with-gradle.jar es (Linux/Mac/Windows)
You should see the text Hola! output.
Awesome! You’ve just built your first Java application with Gradle. 
Testing the application
Tests live in src/test/java, so under src create a new directory test/java.
Within this directory create the same package structure. Create directories com/tomgregory/languageapp.
Add a new file SayHelloTest.java and open it for editing.
Paste in the following Java code:
package com.tomgregory.languageapp;
import org.junit.jupiter.api.Test;
import java.io.IOException;
public class SayHelloTest {
    @Test
    public void testSayHello() throws IOException {
        SayHello.main(new String[]{"en"});
    }
}
Try running the tests:

	./gradlew test (Linux/Mac)
gradlew.bat test (Windows)
You'll get an error here saying that it can’t find the org.junit.jupiter.api package.
Add this dependency configuration block to build.gradle, after the plugins:
dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter:5.6.3'
}
Add this repositories configuration block, putting it just before the dependencies:
repositories {
    mavenCentral()
}
Rerun the test command:

	./gradlew test (Linux/Mac)
gradlew.bat test (Windows)
The build should be successful.
Open the test report in a browser, located in build/reports/tests/test/index.html.
You'll see 0 tests have been run.
Add this test configuration block below the dependencies block:
test {
    useJUnitPlatform()
}
This configures tests to use the Junit 5 platform.
Run the tests again:

	./gradlew test (Linux/Mac)
gradlew.bat test (Windows)
Open the test report again in a browser, located in build/reports/tests/test/index.html.
You should now see that 1 test was run, with a 100% success rate.
Commit your changes
Get all the files ready to be committed:
git add .
See what files are staged for commit:
git status
Commit everything:
 git commit -m "Create Java project for language app."
Good work you’ve just built, run, and tested your first Java project with Gradle!
From <https://learn.tomgregory.com/courses/take/get-going-with-gradle/lessons/20143835-building-a-java-project-with-gradle-practical> 




Gradle task diagram




Screen clipping taken: 08-02-2022 09:46 AM

![image](https://user-images.githubusercontent.com/50944453/152917671-29e5ffb7-afb1-4fc3-983b-e03eb3dfd53c.png)




To see in list of dependent task in tree , add below plugin
There is a great plugin which visualize dependencies between tasks:
https://plugins.gradle.org/plugin/org.barfuin.gradle.taskinfo
After adding it to your project run e.g. ./gradlew tiTree build

plugins {id "org.barfuin.gradle.taskinfo"version "1.3.1"}

gradlew tiTree build

Screen clipping taken: 08-02-2022 09:46 AM

https://tomgregory.com/gradle-dependency-tree/
https://tomgregory.com/how-to-use-gradle-api-vs-implementation-dependencies-with-the-java-library-plugin/
https://tomgregory.com/gradle-implementation-vs-compile-dependencies/


dependecy tree
gradle  dependencies --configuration compileClasspath

gradle my-subproject:dependencies --configuration compileClasspath
 

configuration
api =compileTime classpath
Implementation  = runtime classpath




