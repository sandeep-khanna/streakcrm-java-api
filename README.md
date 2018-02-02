# streakcrm-java-api

A Java &amp; Spring Boot based reusable library for integrating Streak CRM with external systems and applications

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>com.streak</groupId>
    <artifactId>streakcrm-java-api</artifactId>
    <version>0.1.0-SNAPSHOT</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.streak:streakcrm-java-api:0.1.0-SNAPSHOT"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/streakcrm-java-api-0.1.0-SNAPSHOT.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instructions.

Examples coming soon!

## Documentation for Authorization

The Streak API endpoint is https://www.streak.com/api/v1 and all paths below are relative to that. Streak uses HTTP Basic Auth for authentication and all calls must be over HTTPS.

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

Sandeep Khanna 