plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("native.cocoapods")
}

kotlin {
    android()
    ios()

    version = "1.1"

    cocoapods {
        summary = "Common library"
        homepage = "https://github.com/ln-12/multiplatform-connectivity-status"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.github.ln_12:multiplatform-connectivity-status:1.2.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {}
        }
        val androidTest by getting {
            dependencies {}
        }
        val iosMain by getting {
            dependencies {}
        }
        val iosTest by getting {
            dependencies {}
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