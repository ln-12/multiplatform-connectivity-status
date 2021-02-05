plugins {
    id("com.android.library")
    kotlin("multiplatform") version "1.4.21"
    kotlin("native.cocoapods") version "1.4.21"
    id("maven-publish")
}

group = "com.github.ln-12"
version = "1.0"

repositories {
    google()
    mavenCentral()
    jcenter()
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

// taken from: https://serpro69.medium.com/publishing-a-kotlin-library-to-your-bintray-repo-using-gradle-kotlin-dsl-bdeaed54571a
val artifactName = project.name
val artifactGroup = project.group.toString()
val artifactVersion = project.version.toString()

val pomUrl = "https://github.com/ln-12/multiplatform-connectivity-status"
val pomScmUrl = "https://github.com/ln-12/multiplatform-connectivity-status"
val pomIssueUrl = "https://github.com/ln-12/multiplatform-connectivity-status/issues"
val pomDesc = "A Kotlin multiplatform mobile library to monitor the connectivity status of the device"

val githubRepoName = "ln-12/multiplatform-connectivity-status"
val githubReadme = "README.md"

val pomLicenseName = "Apache-2.0"
val pomLicenseUrl = "http://www.apache.org/licenses/LICENSE-2.0"
val pomLicenseDist = "repo"

val pomDeveloperId = "ln-12"
val pomDeveloperName = "Lorenzo Neumann"

publishing {
    publications {
        publications.withType<MavenPublication>().all {
            groupId = artifactGroup
            artifactId = artifactName
            version = artifactVersion

            pom.withXml {
                asNode().apply {
                    appendNode("description", pomDesc)
                    appendNode("name", rootProject.name)
                    appendNode("url", pomUrl)
                    appendNode("licenses").appendNode("license").apply {
                        appendNode("name", pomLicenseName)
                        appendNode("url", pomLicenseUrl)
                        appendNode("distribution", pomLicenseDist)
                    }
                    appendNode("developers").appendNode("developer").apply {
                        appendNode("id", pomDeveloperId)
                        appendNode("name", pomDeveloperName)
                    }
                    appendNode("scm").apply {
                        appendNode("url", pomScmUrl)
                    }
                }
            }
        }
    }
}

