plugins {
    id("com.android.library")
    kotlin("multiplatform") version "1.4.21"
    kotlin("native.cocoapods") version "1.4.21"
    id("maven-publish")
}

group = "com.github.ln12"
version = "1.0"

repositories {
    google()
    jcenter()
    mavenCentral()
}

kotlin {
    android {
        publishAllLibraryVariants()
    }

    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2-native-mt")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.1")
            }
        }
        val iosMain by getting
        val iosTest by getting
    }

    cocoapods {
        summary = "A Kotlin multiplatform mobile library to monitor the connectivity status of the device"
        homepage = "https://github.com/ln-12/multiplatform-connectivity-status"

        // https://github.com/tonymillion/Reachability
        pod("Reachability") {
            version = "3.2"
        }
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24) // API >= 24 needed for ConnectivityManager.registerDefaultNetworkCallback()
        targetSdkVersion(30)
    }
}

// metadata is currently not supported for iOS
// https://youtrack.jetbrains.com/issue/KT-44459#focus=Comments-27-4645829.0-0
kotlin.metadata {
    compilations.matching { it.name == "iosMain" }.all {
        compileKotlinTaskProvider.configure { enabled = false }
    }
}