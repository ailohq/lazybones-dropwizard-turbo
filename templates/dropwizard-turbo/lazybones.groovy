import org.apache.commons.io.FileUtils

def props = [:]
props.packageName = ask("Define 'package' in lowercase [com.example]: ", "com.example", "group")

props.applicationName = ask("Define 'application' name in camel case: [PetStore]", "PetStore", "applicationName").capitalize()
props.applicationDashName = props.applicationName.replaceAll(/\B[A-Z]/) { '-' + it }.toLowerCase()

props.domainName = ask("Define 'domain' name in camel case [Dog]:", "Dog", "applicationName").capitalize()
props.domainSnakeName=props.domainName.replaceAll( /([A-Z])/, /_$1/ ).toLowerCase().replaceAll( /^_/, '' )
props.domainLowercaseName=props.domainName.toLowerCase()

processTemplates "gradle.properties", props
processTemplates "Dockerfile", props
processTemplates "src/main/java/packageName/**/*.java", props
processTemplates "src/test/java/packageName/**/*.java", props

// new package structure
def packageDirectoryStructure = props.packageName.replace('.', '/')

String targetDir = projectDir.path
def existingDirectory = new File("${targetDir}/src/main/java/packageName")

// Java classes
def dbFile = new File("${targetDir}/src/main/java/packageName/db/DomainDAO.java")
FileUtils.moveFile(dbFile, new File("${targetDir}/src/main/java/${packageDirectoryStructure}/db/${props.domainName}DAO.java"))

def healthCheckFile = new File("${targetDir}/src/main/java/packageName/health/DomainHealthCheck.java")
FileUtils.moveFile(healthCheckFile, new File("${targetDir}/src/main/java/${packageDirectoryStructure}/health/${props.domainName}HealthCheck.java"))

def modelFile = new File("${targetDir}/src/main/java/packageName/model/Domain.java")
FileUtils.moveFile(modelFile, new File("${targetDir}/src/main/java/${packageDirectoryStructure}/model/${props.domainName}.java"))

def resourceFile = new File("${targetDir}/src/main/java/packageName/resources/DomainResource.java")
FileUtils.moveFile(resourceFile, new File("${targetDir}/src/main/java/${packageDirectoryStructure}/resources/${props.domainName}Resource.java"))

def moduleFile = new File("${targetDir}/src/main/java/packageName/ApplicationModule.java")
FileUtils.moveFile(moduleFile, new File("${targetDir}/src/main/java/${packageDirectoryStructure}/${props.applicationName}Module.java"))

def appFile = new File("${targetDir}/src/main/java/packageName/Application.java")
FileUtils.moveFile(appFile, new File("${targetDir}/src/main/java/${packageDirectoryStructure}/${props.applicationName}.java"))

def configurationFile = new File("${targetDir}/src/main/java/packageName/ApplicationConfiguration.java")
FileUtils.moveFile(configurationFile, new File("${targetDir}/src/main/java/${packageDirectoryStructure}/${props.applicationName}Configuration.java"))

// Test Classes
def resourceTestFile = new File("${targetDir}/src/test/java/packageName/resources/DomainResourceTest.java")
FileUtils.moveFile(resourceTestFile, new File("${targetDir}/src/test/java/${packageDirectoryStructure}/resources/${props.domainName}ResourceTest.java"))

def integrationTestFile = new File("${targetDir}/src/test/java/packageName/resources/DomainIntegrationTest.java")
FileUtils.moveFile(integrationTestFile, new File("${targetDir}/src/test/java/${packageDirectoryStructure}/resources/${props.domainName}IntegrationTest.java"))

def jsonFixtureTestFile = new File("${targetDir}/src/test/java/packageName/JsonFixtureTest.java")
FileUtils.moveFile(jsonFixtureTestFile, new File("${targetDir}/src/test/java/${packageDirectoryStructure}/JsonFixtureTest.java"))

def absIntegrationTestFile = new File("${targetDir}/src/test/java/packageName/IntegrationTest.java")
FileUtils.moveFile(absIntegrationTestFile, new File("${targetDir}/src/test/java/${packageDirectoryStructure}/IntegrationTest.java"))

// Config YAML
def configYaml = new File("${targetDir}/application.yml")
FileUtils.moveFile(configYaml, new File("${targetDir}/${props.applicationDashName}.yml"))

def configTestYaml = new File("${targetDir}/src/test/resources/application-test.yml")
FileUtils.moveFile(configTestYaml, new File("${targetDir}/src/test/resources/${props.applicationDashName}-test.yml"))

FileUtils.deleteDirectory(existingDirectory)
