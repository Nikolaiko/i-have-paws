import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.extraProperties
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("com.android.library")
    id("io.gitlab.arturbosch.detekt")

    id("app.cash.sqldelight") version "2.0.0-rc02"
    id("io.realm.kotlin") version "1.10.1"
}

sqldelight {
    databases {
        create("GroupsDatabase") {
            srcDirs.setFrom("src/commonMain/sqldelight")
            packageName.set("com.nikolai")
            schemaOutputDirectory.set(file("build/dbs"))
        }
    }
}

kotlin {

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    jvmToolchain(11)

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64() //sure all ios dependencies support this target
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true

        }
    }

    targets.withType(KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export("dev.icerock.moko:mvvm-core:0.16.1")
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
                implementation("io.insert-koin:koin-core:3.4.0")

                //DI
                implementation("org.kodein.di:kodein-di:7.19.0")
                implementation("org.kodein.di:kodein-di-framework-compose:7.19.0")

                //Realm
                implementation("io.realm.kotlin:library-base:1.10.1")

                //Compose multiplatform
                implementation(compose.runtime)
                implementation(compose.foundation)

                //Resources and UI
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
                implementation("app.cash.turbine:turbine:1.0.0")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
                implementation("app.cash.sqldelight:android-driver:2.0.0-rc02")

                //Compose multiplatform
                api("androidx.activity:activity-compose:1.6.1")
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("app.cash.sqldelight:native-driver:2.0.0-rc02")
            }

            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    signingConfigs {
        create("release") {
        }
    }
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 33
    }
}

dependencies {
    implementation("androidx.core:core:1.10.1")
    commonMainApi("dev.icerock.moko:mvvm-core:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-compose:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow-compose:0.16.1")
}