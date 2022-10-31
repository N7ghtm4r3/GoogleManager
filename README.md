**v1.0.0**

This is a Java Based library useful to work with Google API services

## Implementation

Add the JitPack repository to your build file

### Gradle

- Add it in your root build.gradle at the end of repositories

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

- Add the dependency

```gradle
dependencies {
  implementation 'com.github.N7ghtm4r3:GoogleManager:1.0.0'
}
```

### Maven

- Add it in your root build.gradle at the end of repositories

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

- Add the dependency

```xml

<dependency>
    <groupId>com.github.N7ghtm4r3</groupId>
    <artifactId>GoogleManager</artifactId>
    <version>1.0.0</version>
</dependency>
```

## 🛠 Skills

- Java

## Services available

- <a href="https://developers.google.com/gmail/api/reference/rest"><strong>Gmail</strong></a>

The other services will be gradually released

## Initial configuration

Before to start you have to create or authorize, from <a href="https://console.cloud.google.com/apis/"> Google API
Console</a>, the new project that you will use in this library:

- <a href="https://console.cloud.google.com/projectcreate"> Create a project </a> to use in the library if you haven't one yet
- Then you have to choose, and **enable**, the API services that you need
  from <a href="https://console.cloud.google.com/apis/library"> this page</a>
- After chosen the API services that you need, you have
  to <a href="https://console.cloud.google.com/apis/credentials/consent">configure consent screen</a> to be allowed to
  use **OAuth 2.0 Client IDs** service (this library use **only** this as authentication mode)
- After configured the consent screen, you have to create and
  configure <a href="https://console.cloud.google.com/apis/credentials/oauthclient">OAuth client ID</a> to copy the
  **Client ID** and the **Client secret** or download the JSON file with these credentials, in this case you need to
  parse
  by yourself to get the **Client ID** and the **Client secret**. <br> **Note:** based on your choose for **Application
  type** you have to configure by yourself details such as **host**, **port**, **callback path**, etc
- After these operations you and your project are ready to use **GoogleManager** library!

## Usage/Examples

#### Execution

For any library manager you need to instantiate it like this:

```java
// choose the manager for example: Gmail, etc 
GoogleManager manager = new GoogleManager(clientId,clientSecret,userId, /* params of the constructor chosen */);
// and then use it
manager.makeSomething();
```

To avoid re-entering credentials for each manager, you can instantiate managers like this:

```java
// choose the manager for example: Gmail, etc 
GoogleManager firstManager = new GoogleManager(clientId,clientSecret,userId, /* params of the constructor chosen */);
// and then use it 
firstManager.makeSomething();
// you don't need to insert all credentials to make manager work
GoogleManager secondManager = new GoogleManager(); // same credentials used
// and then use it
secondManager.makeSomething();
```

#### Responses

Library give to you the opportunity to customize the return object after a request, the possibilities are:

- **JSON:** return response formatted as **JSON** (**org.json.JSONObject** or **org.json.JSONArray**)
- **STRING:** return response formatted as **String**
- **LIBRARY_OBJECT:** return response formatted as custom object offered by **GoogleManager's library**

You find the constants in **GoogleManager.ReturnFormat enum**

```java
// choose the manager for example: Gmail, etc 
GoogleManager manager = new GoogleManager(clientId,clientSecret,userId, /* params of the constructor chosen */);
// method to return directly a library given by library
manager.someRequest(); // in this case will be returned directly a LIBRARY_OBJECT
// method to customize the format of the return 
manager.someRequest(ReturnFormat.JSON); // in this case will be returned response in JSON format
```

## Authors

- [@N7ghtm4r3](https://www.github.com/N7ghtm4r3)

## Support

If you need help using the library or encounter any problems or bugs, please contact us via the following links:

- Support via <a href="mailto:infotecknobitcompany@gmail.com">email</a>
- Support via <a href="https://github.com/N7ghtm4r3/GoogleManager/issues/new">GitHub</a>

Thank you for your help!

## Badges

[![](https://img.shields.io/badge/Google_Play-414141?style=for-the-badge&logo=google-play&logoColor=white)](https://play.google.com/store/apps/developer?id=Tecknobit)
[![Twitter](https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/tecknobit)

[![](https://img.shields.io/badge/google-4285F4?style=for-the-badge&logo=google&logoColor=white)](https://developers.google.com/apis-explorer/)
[![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)

[![](https://jitpack.io/v/N7ghtm4r3/GoogleManager.svg)](https://jitpack.io/#N7ghtm4r3/GoogleManager)

## Donations

If you want support project and developer: **0x5f63cc6d13b16dcf39cd8083f21d50151efea60e**

![](https://img.shields.io/badge/Bitcoin-000000?style=for-the-badge&logo=bitcoin&logoColor=white)
![](https://img.shields.io/badge/Ethereum-3C3C3D?style=for-the-badge&logo=Ethereum&logoColor=white)

If you want support project and developer with <a href="https://www.paypal.com/donate/?hosted_button_id=5QMN5UQH7LDT4">
PayPal</a>

Copyright © 2022 Tecknobit

