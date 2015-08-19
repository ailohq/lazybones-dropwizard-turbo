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

## Usage
To test the example application run the following commands.

* Create a IntelliJ project:

        ./gradlew idea

* Run the tests:

        ./gradlew test

* Package the application and run it (see `gradle/shadow.gradle`):

        ./gradlew shadowJar
        ./gradlew shadowRun

* Upload package to bintray (see `gradle/bintray.gradle`):

        ./gradlew bintrayUpload

* Run DB Migrations (see `gradle/migrations.gradle`):

        ./gradlew dbDropAll
        ./gradlew dbMigrate
        ./gradlew dbTag

* Run Pact tests (see `gradle/pact.gradle`);

        ./gradlew pactVerify

## Usage
Use either a browser or a REST client such as [PostMan](https://www.getpostman.com)

* You RESTful APIs are available here:

    http://localhost:8080/*
    http://localhost:8080/admin

* [Swagger](http://swagger.io/) API are here:

    http://localhost:8080/api/api-docs