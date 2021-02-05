# Multiplatform connectivity status

This small Kotlin multiplatform mobile library allows monitoring the internet connection of the device. You can use it from shared code as well as directly from Android or iOS code.

## Installation 

Clone the repo and run the following command:
```
./gradlew publishToMavenLocal
```

With that, you publish the library locally on your machine. Afterwards you need to add `mavenLocal` to your repositories:
``` kotlin
buildscript {
    repositories {
        // ... other repositories
        mavenCentral()
        mavenLocal()
    }
}
```

Then you can add the library to your project:
``` kotlin
kotlin {
    android()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.github.ln-12:multiplatform-connectivity-status:1.0")
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
// get the satus object
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

To run the sample you need to clone the repo and run
```
./gradlew publishToMavenLocal
```

Then run the sample apps inside the [sample](./sample) directory.

## License

Copyright 2021 Lorenzo Neumann

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

