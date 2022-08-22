# Multiplatform connectivity status

This Kotlin multiplatform mobile utility library allows monitoring the internet connection status of the device. You can use it from shared code as well as directly from Android or iOS code.

The project structure is taken from [this well explained and detailed tutorial](https://dev.to/kotlin/how-to-build-and-publish-a-kotlin-multiplatform-library-creating-your-first-library-1bp8
). 
## Installation 

Add `mavenCentral()` to your repositories:
``` kotlin
buildscript {
    repositories {
        // ... other repositories
        mavenCentral()
    }
}
```

Add the library to your project:
``` kotlin
kotlin {
    android()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.github.ln_12:multiplatform-connectivity-status:1.2.0")
            }
        }
    }
}
```

On iOS, you also have to add the [Reachability](https://github.com/tonymillion/Reachability) pod to your Podfile:
```
target 'iosApp' do
    pod 'Reachability'
end 
```

## Usage 

``` kotlin
// get the satus object, on Android provide a context
val connectivityStatus = ConnectivityStatus()

// output changes
connectivityStatus.isNetworkConnected.collect {
    printnl("New status $it")
}

// start monitoring
connectivityStatus.start()

// some code from your project

// when you are done, stop it
connectivityStatus.stop()
```

## Sample

You can find a sample Android and iOS app inside the [sample](./sample) directory.

## License

Copyright 2022 Lorenzo Neumann

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

