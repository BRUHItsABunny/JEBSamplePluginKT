# JEB Sample Plugin KT
This is an example for setting up a JEB Decompiler plug-in codebase in Intellij IDEA with Gradle and Kotlin

## Motivation
I make some JEB Decompiler plugins from time to time, but it can be quite a hassle to set this up for the first time after a while.

You can find the original documentation around JEB plugins over [here](https://github.com/pnfsoftware/jeb-samplecode) on PNF Software's GitHub.

As you may be able to tell this documentation tells you how to set up a JEB plugin with Eclipse, Java and Ant.

In order for us to enjoy Intellij IDEA, Gradle and Kotlin there are some undocumented and not-so-straightforward (for beginners) steps required.

## Project Set-up

### 1. Create the project in Intellij IDEA
This step is pretty easy.
Fire up Intellij IDEA, click on 'Create Project' and start populating the fields.

The screenshot below will show you what the settings are that I went with to get the project as it shows in the initial commit.

* Language: Kotlin
* Build System: Gradle
* Gradle DSL: Kotlin

![Intellij Settings](https://github.com/BRUHItsABunny/JEBSamplePluginKT/raw/ce666d642146c1d64fba5383863c01cdaee18de2/_resources/media/1_create_project_dialog.png)

### 2. Add dependencies
JEB plugins have some dependencies that we have to manually add, otherwise the entire plugin will be a no-go.

In my second commit I created the ``libs`` directory, added all jars in it to my `.gitignore` and lastly you want to add the `jeb.jar` and `swt.jar` from you JEB installation (these jars can be found in the `bin/app` subdirectory of the installation).

### 3. Configure Gradle
In order for us to properly compile our plugin for JEB we need to set up Gradle to have the proper dependencies and to output a fat jar upon build.

For this step you can mirror the changes to `build.gradle.kts`, some key takeaways are:
* Add the proper dependencies
* Setup a manifest (optional?)
* Set a Duplication Strategy
* Build the fat jar
* Exclude signature from signed jars because it will corrupt the fat jar with invalid signatures
* Build/Reload Gradle to be up-to-date with your changes

### 4. Start coding
At this point you can start coding and your IntelliSense code completion, Gradle, etc should all work as expected.

In this project I just have a very simple example class, only meant to show that the code actually executes.

### 5. Build and configure JEB to find it
Now you want to execute: `./gradlew jar` and it should create a directory called `build`.

After this build finished successfully you want to do this once (subsequent builds do not need this step anymore).

You want to open JEB and under `Edit` click on `Options`, make sure you are in the `Simple Options`.

Go to the `Development` tab and then add a jar and navigate to that aforementioned `build` directory and select the jar found in the `libs` subdirectory. (by default it will end with `-SNAPSHOT.jar`)

Finally, you want to add the plugin classname, which is essentially the full package and class name of whatever class extends `AbstractEnginesPlugin` (so in this example it is: `com.bunnytechsolutions.jebsamplepluginkt.PluginEntry`)

A pop-up should show up saying the class was found, and you can restart JEB and enjoy developing your plugin.

Upon restart your plugin can be found here: `File>Plugins>Execute an Engines plugin`

Here is the output after a successful run: (it looks cluttered due to debugging being on)
![Result](https://github.com/BRUHItsABunny/JEBSamplePluginKT/raw/ce666d642146c1d64fba5383863c01cdaee18de2/_resources/media/5_result.png)