import org.apache.commons.io.FileUtils

def props = [:]
props.packageName = ask("Define 'package' in lowercase [com.example]: ", "com.example", "group")

props.applicationName = ask("Define 'application' name in camel case: [PetStore]", "PetStore", "applicationName").capitalize()
props.applicationDashName = props.applicationName.replaceAll(/\B[A-Z]/) { '-' + it }.toLowerCase()

props.domainName = ask("Define 'domain' object name in camel case [Dog]:", "Dog", "applicationName").capitalize()
props.domainSnakeName=props.domainName.replaceAll( /([A-Z])/, /_$1/ ).toLowerCase().replaceAll( /^_/, '' )
props.domainLowercaseName=props.domainName.toLowerCase()
props.domainUppercaseName=props.domainName.toUpperCase()

processTemplates "gradle.properties", props
processTemplates "Dockerfile", props
processTemplates "src/main/java/packageName/**/*.java", props
processTemplates "src/main/resources/**/*.xml", props
processTemplates "src/test/java/packageName/**/*.java", props

// new package structure
def packageDirectoryStructure = props.packageName.replace('.', '/')

String targetDir = projectDir.path

// Config YAML
def configYaml = new File("${targetDir}/application.yml")
FileUtils.moveFile(configYaml, new File("${targetDir}/${props.applicationDashName}.yml"))

def configTestYaml = new File("${targetDir}/src/test/resources/application-test.yml")
FileUtils.moveFile(configTestYaml, new File("${targetDir}/src/test/resources/${props.applicationDashName}-test.yml"))

def fixtureFile = new File("${targetDir}/src/test/resources/fixtures/domain.json")
FileUtils.moveFile(fixtureFile, new File("${targetDir}/src/test/resources/fixtures/${props.domainSnakeName}.json"))

// Java classes
def existingSrcDirectory = "${targetDir}/src/main/java/packageName"
def targetSrcDirectory = "${targetDir}/src/main/java/${packageDirectoryStructure}"

def dbFile = new File("${existingSrcDirectory}/db/DomainDAO.java")
FileUtils.moveFile(dbFile, new File("${targetSrcDirectory}/db/${props.domainName}DAO.java"))

def healthCheckFile = new File("${existingSrcDirectory}/health/DomainHealthCheck.java")
FileUtils.moveFile(healthCheckFile, new File("${targetSrcDirectory}/health/${props.domainName}HealthCheck.java"))

def corsFilterFile = new File("${existingSrcDirectory}/jersey/CORSFilter.java")
FileUtils.moveFile(corsFilterFile, new File("${targetSrcDirectory}/jersey/CORSFilter.java"))


def modelFile = new File("${existingSrcDirectory}/model/Domain.java")
FileUtils.moveFile(modelFile, new File("${targetSrcDirectory}/model/${props.domainName}.java"))

def resourceFile = new File("${existingSrcDirectory}/resources/DomainResource.java")
FileUtils.moveFile(resourceFile, new File("${targetSrcDirectory}/resources/${props.domainName}Resource.java"))

def moduleFile = new File("${existingSrcDirectory}/ApplicationModule.java")
FileUtils.moveFile(moduleFile, new File("${targetSrcDirectory}/${props.applicationName}Module.java"))

def appFile = new File("${existingSrcDirectory}/Application.java")
FileUtils.moveFile(appFile, new File("${targetSrcDirectory}/${props.applicationName}.java"))

def configurationFile = new File("${existingSrcDirectory}/ApplicationConfiguration.java")
FileUtils.moveFile(configurationFile, new File("${targetSrcDirectory}/${props.applicationName}Configuration.java"))

FileUtils.deleteDirectory(new File(existingSrcDirectory))

// Test Classes
def existingTestDirectory = "${targetDir}/src/test/java/packageName"
def targetTestDirectory = "${targetDir}/src/test/java/${packageDirectoryStructure}"

def resourceTestFile = new File("${existingTestDirectory}/resources/DomainResourceTest.java")
FileUtils.moveFile(resourceTestFile, new File("${targetTestDirectory}/resources/${props.domainName}ResourceTest.java"))

def integrationTestFile = new File("${existingTestDirectory}/resources/DomainIntegrationTest.java")
FileUtils.moveFile(integrationTestFile, new File("${targetTestDirectory}/resources/${props.domainName}IntegrationTest.java"))

def jsonFixtureTestFile = new File("${existingTestDirectory}/JsonFixtureTest.java")
FileUtils.moveFile(jsonFixtureTestFile, new File("${targetTestDirectory}/JsonFixtureTest.java"))

def absIntegrationTestFile = new File("${existingTestDirectory}/IntegrationTest.java")
FileUtils.moveFile(absIntegrationTestFile, new File("${targetTestDirectory}/IntegrationTest.java"))

FileUtils.deleteDirectory(new File(existingTestDirectory))

// Git Ignore
def gitIgnoreString = "# ignore build\n" +
        ".gradle\n" +
        "logs\n" +
        "build\n" +
        "\n" +
        "#ignore intellij\n" +
        "atlassian-ide-plugin.xml\n" +
        ".idea/\n" +
        "*.iml\n" +
        "*.ipr\n" +
        "*.iws\n" +
        "\n" +
        "#ignore misc\n" +
        ".ruby-version\n" +
        ".wgetrc\n" +
        "contract.zip\n" +
        "contract\n" +
        "newrelic/*.jar\n" +
        "newrelic/logs\n" +
        "scripts"

def gitIgnoreFile = new File ("${targetDir}/.gitignore");
FileUtils.write(gitIgnoreFile, gitIgnoreString);