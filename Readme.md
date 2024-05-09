# GoPay SDK

The GoPay SDK is a powerful tool for integrating GoPay payment functionality into your Android applications. This SDK allows seamless integration with GoPay's payment gateway, providing a hassle-free experience for both developers and users.

## Requirements

Java 1.8 or later

## Installation

### Gradle

Add this dependency to your project's build file:

```groovy
    // Specify repository settings for dependency resolution
    dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
            // Add Maven Central repository
            mavenCentral()
            // Add JitPack repository
            maven { url 'https://jitpack.io' }
        }
    }
```

```groovy
// Add dependencies
    dependencies {
        // Add implementation dependency for GoPay SDK
        implementation 'com.github.gowebstm:Gopay-kotlin:1.0.0'
    }
```

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>com.github.gowebstm</groupId>
        <artifactId>GoPay-kotlin</artifactId>
    <version>1.0.0</version>
</dependency>
```
