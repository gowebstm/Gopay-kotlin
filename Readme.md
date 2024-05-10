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
        implementation 'com.github.gowebstm:Gopay-kotlin:1.0.1'
    }
```

### Maven users

Add this dependency to your project's POM:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.gowebstm</groupId>
        <artifactId>GoPay-kotlin</artifactId>
    <version>1.0.1</version>
</dependency>
```

### Usage

#### For Gopay Verification & Initialization

```groovy
        lifecycleScope.launch {
            val result = Gopay()
                .accessGopay(context)
                .authKey("auth_key")
                .authToken("auth_token")
                .authPackage("package_name")
                .verify()
            result?.let {
                //Toast to Know about the Verification is completed or not
                Toast.makeText(context, "Verification successful!", Toast.LENGTH_SHORT).show()
            }
        }
```

#### For Gopay Payment Initialization


```groovy
    val name = "name"
    val mobile = "mobile"
    val email = "email"
    val package_name = "package name"
    val amount = 1
    val description = "Description"

    lifecycleScope.launch {
        Gopay()
         .PaymentInit(context)
         .createOrder(name, mobile, email, package_name, amount, description)
    }
```

## Response Code
| Code    | Description |
| -------- | ------- |
| 200 | Successful authentication    |
| 400 | Bad request authentication     |
| 401    | Missing required parameters    |
| 500    | Bad request error    |
