<<<<<<< HEAD
# Lazybone Dropwizard Turbo template

Turbo charge your [Dropwizard](dropwizard.io) 0.8.0 Microservice using this lazybones template, inspired by KyleBoon's
[Lazybones Template for Dropwizard 0.7.0](https://github.com/kyleboon/lazybones-dropwizard-template)

For more instructions on lazybones( Project Creation tool) and how to build this project refer to:
[Lazy Bones](https://github.com/pledbrook/lazybones)
and [Template developer guide](https://github.com/pledbrook/lazybones/wiki/Template-developers-guide)

## Purpose

This project is tailored towards the following usages:

 * containerised deployment - Docker or Heroku containers with single port and ENV variables available during runtime only
 * continuous delivery - application version is not hardcoded but passed in at build time via gradle property: `-Papp_version`
 * dependency injection - use Guice and Governator.

### Official Modules:

 * [Dropwizard Hibernate](http://dropwizard.github.io/dropwizard/manual/hibernate.html) - database ORM
 * [Dropwizard Migrations](http://dropwizard.github.io/dropwizard/manual/migrations.html) - database migration. Also see `gradle/migrations.gradle`
 * [Dropwizard Liquibase]() - for migrations
 * [Dropwizard Jersey Client]() - to connect with other REST API endpoints.
 * [Dropwizard Authentication](http://dropwizard.github.io/dropwizard/manual/auth.html) - to authenticate users

### Thrid Party Modules:

 * [Dropwizard Guice](https://github.com/HubSpot/dropwizard-guice) - enables AutoConfig of resources, healthcheck and
 * [Governator](https://github.com/Netflix/governator) - lazy singleton and life cycle managment.
 * [Dropwizard Template Configuration](https://github.com/tkrille/dropwizard-template-config) - swaps in environment variables using freemarker templating.
 * [Swagger](http://swagger.io/) - interactive documentation

### Helpers:

 * [Pact Gradle](https://github.com/DiUS/pact-jvm/tree/master/pact-jvm-provider-gradle) - consumer driven contract testing using [Pact](https://github.com/realestate-com-au/pact). See `gradle/pact.gradle`
 * [NewRelic](http://newrelic.com/) - for monitoring. See `gradle/newrelic.gradle`
 * [Docker](https://www.docker.com/) - Includes sample Dockerfile.
 * [Dropwizard Testing](https://dropwizard.github.io/dropwizard/manual/testing.html) - sample Resource and Integration tests provided
 * [Shadow]() - building exectutable fatjar. See `gradle/shadow.gradle`

## Usage

Install lazybones using [gvm](http://gvmtool.net/)

    gvm install lazybones

Update lazybones config to point to trunkplatform lazybones repository:

    lazybones config set bintrayRepositories "pledbrook/lazybones-templates", "trunkplatform/lazybones"
=======
Lazybone Dropwizard Turbo template
----------------------------------

This is a template project for [Dropwizard](dropwizard.io) 0.8.0 applications, turbo charged with a suite of add-ons to
produce a containerised Microservice that can be continuously delivered.

Thrid Party Modules:

 * Guice - enables AutoConfig of resources, healthcheck and
 * Governator - lazy singleton and life cycle managment.
 * Template Configuration - swaps in environment variables using freemarker templating. Useful for Docker or Heroku where
 environment variables are only available during RUN phase.
 * Swagger-Lite - interactive documentation

Helpers:

 * Pact - testing consumer driven contracts
 * NewRelic - for monitoring
 * Docker - Includes sample Dockerfile.
 * Tests - resource and integration tests samples with H2 database

For more instructions on lazybones( Project Creation tool) and how to build this project refer to:  [Lazy Bones](https://github.com/pledbrook/lazybones)
and [Template developer guide](https://github.com/pledbrook/lazybones/wiki/Template-developers-guide)

### Build from source

    gradle packageTemplateDropwizardTurbo
    gradle installAllTemplates

That will create the template and install it to your local lazybones cache. After that you can use the template as you would normally.
>>>>>>> f7b059e3cf4f7698120962b3b7889442bfca2cff

### Create new Project

Make sure you are using lazybones 0.6+

<<<<<<< HEAD
    lazybones create dropwizard 0.1.1 destination

### Build from source

If you wish to build from source or make contributions to this project:

    gradle packageAllTemplates
    gradle installAllTemplates

That will create the template and install it to your local lazybones cache. After that you can use the template as you would normally.
=======
    lazybones create dropwizard 0.1 destination
>>>>>>> f7b059e3cf4f7698120962b3b7889442bfca2cff
