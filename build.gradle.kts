plugins {
    id("com.android.library")
    kotlin("native.cocoapods") version "1.6.10"
    kotlin("multiplatform") version "1.6.10"
    id("convention.publication")
}

group = "com.github.ln-12"
version = "1.1.0"

repositories {
    google()
    mavenCentral()
}

kotlin {
    android {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }

        publishLibraryVariants("release", "debug")
    }
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
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
                implementation("junit:junit:4.13.2")
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
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
}

// metadata is currently not supported for iOS
// https://youtrack.jetbrains.com/issue/KT-44459#focus=Comments-27-4645829.0-0
kotlin.metadata {
    compilations.matching { it.name == "iosMain" }.all {
        compileKotlinTaskProvider.configure { enabled = false }
    }
}