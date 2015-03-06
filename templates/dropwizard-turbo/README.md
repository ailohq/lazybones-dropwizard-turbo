# Introduction
You have created an dropwizard application using lazybones. This application uses dropwizard 0.8.0 version.

If you are not already familiar with Dropwizard, check out [Getting Started](http://dropwizard.github.io/dropwizard/getting-started.html).

## Project Structure

    <proj>
        +- gradle
        |
        +- newrelic
        |
        +- src
            +- main
            |   +- java
            |   |     +- your.package.structure
            |   |           +- db
            |   |           +- health
            |   |           +- jersey
            |   |           +- model
            |   |           +- resources
            |   +- resources
            |
            +- test
                +- java
                |     +- // junit tests in here!
                +- resources
                      +- fixtures

### New Relic Setup
Update `newrelic/newrelic.yml` with your api key and application name.

Alternatively you can set ENV variables for `NEW_RELIC_APP_NAME` and `NEW_RELIC_API_KEY` which will replace your newrelic.yml config

### BinTray Setup
To upload your fatjar into bintray, update `gradle/bintray.gradle` file with your bintray repository information.

Also update `~/.gradle/gradle.properties` with your `bintrayUserName` and `bintrayApiKey`

Then run:

        gradle bintrayUpload

## Running The Application
To test the example application run the following commands.

* To run the tests run

        gradle test

* To package the example run.

        gradle shadowJar


* To run the application run.

        gradle run