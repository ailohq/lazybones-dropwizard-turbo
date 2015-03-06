# Lazybone Dropwizard Turbo template

[ ![Download](https://api.bintray.com/packages/trunkplatform/lazybones/dropwizard-turbo-template/images/download.svg) ](https://bintray.com/trunkplatform/lazybones/dropwizard-turbo-template/_latestVersion)

Turbo charge your [Dropwizard](dropwizard.io) 0.8.0 Microservice using this lazybones template, inspired by KyleBoon's
[Lazybones Template for Dropwizard 0.7.0](https://github.com/kyleboon/lazybones-dropwizard-template)

For more instructions on lazybones( Project Creation tool) and how to build this project refer to:
[Lazy Bones](https://github.com/pledbrook/lazybones)
and [Template developer guide](https://github.com/pledbrook/lazybones/wiki/Template-developers-guide)

## Purpose
This template is a lot more comprehensive and is tailored towards the following usages (see Tech Stack below for more details):

 * dependency injection - use Guice auto config to avoid boilerplate code.
 * containerised deployment - Docker or Heroku containers: single port and ENV variables available during runtime only
 * continuous delivery - application version is not hardcoded but passed in at build time via gradle property: `-Papp_version`
 * Java8 - uses lambdas and Java8 module will be added in the future

## Usage
Install lazybones using [gvm](http://gvmtool.net/)

    gvm install lazybones

Update lazybones config to point to trunkplatform lazybones repository:

    lazybones config set bintrayRepositories "pledbrook/lazybones-templates", "trunkplatform/lazybones"

### Create new Project
Make sure you are using lazybones 0.6+

    lazybones create dropwizard 0.1.1 destination

Make sure you set your IDE to Java1.8 when importing this project.

### Build from source
If you wish to build from source or make contributions to this project:

    gradle packageTemplateDropwizardTurbo
    gradle installAllTemplates

That will create the template and install it to your local lazybones cache. After that you can use the template as you would normally.

## Tech Stack
Most of the following dependencies are optional. Mix and match as you see fit.

### Official Modules:

 * [Dropwizard Hibernate](http://dropwizard.github.io/dropwizard/manual/hibernate.html) - database ORM
 * [Dropwizard Migrations](http://dropwizard.github.io/dropwizard/manual/migrations.html) - liquibase migration. Also see `gradle/migrations.gradle`
 * [Dropwizard Jersey Client]() - to connect with other REST API endpoints.
 * [Dropwizard Authentication](http://dropwizard.github.io/dropwizard/manual/auth.html) - support Basic and OAuth authentication

### Thrid Party Modules:

 * [Dropwizard Guice](https://github.com/HubSpot/dropwizard-guice) - DI and auto configures resources, healthcheck, tasks, managed, jersey providers.
 * [Governator](https://github.com/Netflix/governator) - lazy singleton and life cycle management.
 * [Dropwizard Template Configuration](https://github.com/tkrille/dropwizard-template-config) - swaps in environment variables using freemarker templating.
 * [Swagger](http://swagger.io/) - interactive documentation

### Helpers:

 * [Pact Gradle](https://github.com/DiUS/pact-jvm/tree/master/pact-jvm-provider-gradle) - consumer driven contract testing using [Pact](https://github.com/realestate-com-au/pact). See `gradle/pact.gradle`
 * [NewRelic](http://newrelic.com/) - for monitoring. See `gradle/newrelic.gradle`
 * [Docker](https://www.docker.com/) - Includes sample Dockerfile.
 * [Dropwizard Testing](https://dropwizard.github.io/dropwizard/manual/testing.html) - sample Resource and Integration tests provided
 * [Shadow]() - building exectutable fatjar. See `gradle/shadow.gradle`
 * CORS Filter - CORS implementation using Jersey `ContainerResponseFilter`


