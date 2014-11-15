[Unit Tests Google Doc](https://docs.google.com/document/d/1IuC2zztAx2cJOPHNSCv2N60atLTbsYD412TMBkFZJvk/edit?usp=sharing)

# Background

The goal of this project is to convert a simple Java stopwatch to an
Android application.  The original java code can be found
[here](https://github.com/concurrency-cs-luc-edu/simplestopwatch-java).

# Learning Objectives

## Modeling

* Modeling state-dependent behavior with state machine diagrams
  (see also [here](/loyolachicagocs_comp313/stopwatch-android-java/src/default/doc))
* Distinguishing between view states and (behavioral) model states

## Semantics

* Event-driven/asynchronous program execution
* User-triggered input events
* Internal events from background timers
* Concurrency issues: single-thread rule of accessing/updating the view in the GUI thread

## Architecture and Design

* Key architectural issues and patterns
    * Simple dependency injection (DI)
    * Model-view-adapter (MVA) architectural pattern
    * Mapping MVA to Android
    * Difference between MVA and model-view-controller (MVC)
    * Distinguishing among dumb, reactive, and autonomous model components
* Key design patterns
    * Implementing event-driven behavior using the Observer pattern
    * Implementing state-dependent behavior using the State pattern
    * Command pattern for representing tasks as objects
    * Façade pattern for hiding complexity in the model from the adapter
* Relevant class-level design principles
    * Dependency Inversion Principle (DIP)
    * Single Responsibility Principle (SRP)
    * Interface Segregation Principle (ISP)
* Package-level architecture and relevant principles
    * Dependency graph
      (see also [here](/loyolachicagocs_comp313/stopwatch-android-java/src/default/doc))
    * Stable Dependencies Principle (SDP)
    * Acyclic Dependencies Principle (ADP)
* [Architectural journey](/stopwatch-android-java/commits)

## Testing

* Different types of testing
    * Component-level unit testing
    * System testing
    * Instrumentation testing
* Mock-based testing
* Testcase Superclass pattern
* Test coverage

# Setting up the Environment

Check out the project using Android Studio. This creates the `local.properties` file
with the required line

    sdk.dir=<root folder of Android Studio's Android SDK installation>

# Running the Application

In Android Studio: `Run > Run Stopwatch`

# Running the Tests

## Unit tests including out-of-emulator system tests using Robolectric

    $ gradle --info unitTest

You can view the resulting test reports in HTML by opening this file in your browser:

    Stopwatch/build/reports/tests/index.html

## Android instrumentation tests (in-emulator/device system tests)

In Android Studio, right-click on `Stopwatch/src/instrumentTest/java/.../StopwatchActivityTest`,
then choose `Run StopwatchActivityTest`

You can also use Gradle:

    $ gradle connectedAndroidTest

## TODO

* add external links to learning outcomes
* add lifecycle methods, esp. save/restore app state
* add Mockito
* review wrt DIP following
  [this post](http://lostechies.com/derickbailey/2011/09/22/dependency-injection-is-not-the-same-as-the-dependency-inversion-principle/)
* add Gradle code coverage plugin
* add Gradle dependency/stability metrics plugin
* consider other useful Gradle code quality plugins (checkstyle etc.)