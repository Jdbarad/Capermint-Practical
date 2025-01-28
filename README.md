# Jaypalsinh Barad

## Dependency 
### Ktor
A type-safe HTTP client for Android and the JVM
```yml
[versions]
ktor = "3.0.0-beta-2"

[libraries]
ktor-client-core = { module = "io.ktor:ktor-client-core", version.ref = "ktor" }
ktor-client-android = { module = "io.ktor:ktor-client-android", version.ref = "ktor" }
ktor-client-logging = { module = "io.ktor:ktor-client-logging", version.ref = "ktor" }
ktor-contentNegotiation = { module = "io.ktor:ktor-client-content-negotiation", version.ref = "ktor" }
ktor-serialization-json = { module = "io.ktor:ktor-serialization-kotlinx-json", version.ref = "ktor" }
```

```kotlin
implementation(libs.ktor.client.core)
implementation(libs.ktor.client.android)
implementation(libs.ktor.client.logging)
implementation(libs.ktor.contentNegotiation)
implementation(libs.ktor.serialization.json)
```

### Navigation & ViewModel
```yml
[versions]
navigation = "2.8.5"
viewModel = "2.8.5"

[libraries]
androidx-navigation = { module = "androidx.navigation:navigation-compose", version.ref = "navigation" }
androidx-viewModel = { module = "androidx.lifecycle:lifecycle-viewmodel-compose", version.ref = "viewModel" }
```

```kotlin
implementation(libs.androidx.navigation)
implementation(libs.androidx.viewModel)
```

### Hilt: Dependency Injection
```yml
[versions]
ksp = "2.1.0-1.0.29"
hilt = "2.51.1"
hilt-compose = "1.2.0"

[libraries]
hilt = { module = "com.google.dagger:hilt-android", version.ref = "hilt" }
hilt-compose = { module = "androidx.hilt:hilt-navigation-compose", version.ref = "hilt-compose" }
hilt-compiler = { module = "com.google.dagger:hilt-android-compiler", version.ref = "hilt" }

[plugins]
ksp = { id = "com.google.devtools.ksp", version.ref = "ksp" }
hilt = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
```

```kotlin
implementation(libs.hilt)
implementation(libs.hilt.compose)
ksp(libs.hilt.compiler)
```

### Other
```yml
[versions]
serialization-json = "1.6.3"

[libraries]
serialization-json = { module = "org.jetbrains.kotlinx:kotlinx-serialization-json", version.ref = "serialization-json" }
```

```kotlin
implementation(libs.serialization.json)
```

### Coin: Async Image
```yml
[versions]
coin = "3.0.0-alpha10"

[libraries]
coin = { module = "io.coil-kt.coil3:coil-compose", version.ref = "coin" }
coin-network = { module = "io.coil-kt.coil3:coil-network-ktor3", version.ref = "coin" }
```
```kotlin
implementation(libs.coin)
implementation(libs.coin.network)
```

## Screenshots
<table>
    <tr>
        <td width="33%">
            <h3 align="center">Splash Screen</h3>
            <hr>          
            <img src="https://github.com/user-attachments/assets/adff21ce-27eb-4486-a29c-9e9577a4badc" alt="Splash screen">           
        </td>
       <td width="33%">
            <h3 align="center">Login Screen</h3>
            <hr>          
            <img src="https://github.com/user-attachments/assets/9a2cf3a8-1215-4e0d-8214-92311671b6db" alt="Progress screen">           
       </td>
    </tr>
  <tr>
        <td width="33%">
            <h3 align="center">Part 1 Screen</h3>
            <hr>          
            <img src="https://github.com/user-attachments/assets/6f670dac-ab4d-4329-88ba-7fa7bd23e54a" alt="Splash screen">           
        </td>
       <td width="33%">
            <h3 align="center">Part 2 Screen</h3>
            <hr>          
            <img src="https://github.com/user-attachments/assets/c02ca06d-0f16-4e1e-a974-e75cd3af19ad" alt="Progress screen">           
        </td>
    </tr>
</table> 

## App Link
- <a href="https://drive.google.com/file/d/1hsIFwXg-_pRYgYZ_y-WpJBKJEohO6C2K/view?usp=sharing">Download</a>

## Other Projects 
- <a href="https://github.com/Jdbarad/deonde-api">deonde-api</a>
- <a href="https://github.com/Jdbarad/Play-with-Dots">Play with Dots</a>
- <a href="https://github.com/Jdbarad/Api-App-with-Search">Elsnerdev-Api with Search</a>
- <a href="https://github.com/Jdbarad/News-App-Android">News-Api with Social Sign-in</a>
- <a href="https://github.com/Jdbarad/QfonApp/">Api With Local Database & Search</a>
- <a href="https://github.com/Jdbarad/CountApp">Count Appp</a>
- <a href="https://github.com/Jdbarad/MindMyScape">MindMyScape</a>

## Developed By
### [Jaypalsinh Barad](https://jdbarad.live/)
- <a href="mailto:jdbarad1010@gmail.com">Mail</a>
- <a href="https://www.github.com/Jdbarad">GitHub</a>
- <a href="https://www.linkedin.com/in/jdbarad">LinkedIn</a>
- <a href="https://twitter.com/jdbarad1010">Tweeter</a>
