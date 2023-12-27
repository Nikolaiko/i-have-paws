val jetpackComposeVersion = extra["jetpackcompose.version"] as String
val resourceVersion = extra["icerock.resources"] as String
val koinVersion = extra["koin.version"] as String
val decomposeVersion = extra["decompose.version"] as String
val koinCompose = extra["koin.compose"] as String

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")

    id("org.jetbrains.compose")
    id("com.android.library")

    id("io.gitlab.arturbosch.detekt")
    id("dev.icerock.mobile.multiplatform-resources")

    id("app.cash.sqldelight") version "2.0.0-rc02"
    id("io.realm.kotlin") version "1.10.1"
}

kotlin {
    //androidTarget()

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

            export("com.arkivanov.decompose:decompose:$decomposeVersion")
            export("com.arkivanov.essenty:lifecycle:2.0.0-alpha02")
            export("com.arkivanov.essenty:state-keeper:2.0.0-alpha02")
            export("dev.icerock.moko:mvvm-core:0.16.1")
        }
    }

//    targets
//        .filterIsInstance<KotlinNativeTarget>()
//        .filter { it.konanTarget.family == org.jetbrains.kotlin.konan.target.Family.IOS }
//        .forEach {
//            it.binaries.framework {
//                export("com.arkivanov.decompose:decompose:$decomposeVersion")
//                export("com.arkivanov.essenty:lifecycle:2.0.0-alpha02")
//                export("com.arkivanov.essenty:state-keeper:2.0.0-alpha02")
//                export("dev.icerock.moko:mvvm-core:0.16.1")
//            }
//        }

    sourceSets {
        val commonMain by getting {
            dependencies {

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

                //Navigation
                implementation("com.arkivanov.decompose:decompose:$decomposeVersion")
                implementation("com.arkivanov.decompose:extensions-compose:$decomposeVersion")

                //DI
                implementation("io.insert-koin:koin-core:$koinVersion")
                implementation("io.insert-koin:koin-compose:$koinCompose")

                //Realm
                implementation("io.realm.kotlin:library-base:1.10.1")

                //Compose multiplatform
                implementation(compose.runtime)
                implementation(compose.foundation)

                //Resources and UI
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation("dev.icerock.moko:resources:$resourceVersion")
                implementation("dev.icerock.moko:resources-compose:$resourceVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))

                //Coroutines test
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

                //Flow test
                implementation("app.cash.turbine:turbine:1.0.0")

                //Resource test
                implementation("dev.icerock.moko:resources-test:$resourceVersion")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
                implementation("app.cash.sqldelight:android-driver:2.0.0-rc02")

                //Navigation
                implementation("com.arkivanov.decompose:extensions-android:$decomposeVersion")

                //Compose multiplatform
                api("androidx.activity:activity-compose:1.8.2")

                //DI
                implementation("io.insert-koin:koin-android:$koinVersion")

                //Preview
                implementation("org.jetbrains.compose.ui:ui-tooling-preview:${jetpackComposeVersion}")
                implementation("androidx.compose.ui:ui-tooling:${jetpackComposeVersion}")
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

sqldelight {
    databases {
        create("GroupsDatabase") {
            srcDirs.setFrom("src/commonMain/sqldelight")
            packageName.set("com.nikolai")
            schemaOutputDirectory.set(file("build/dbs"))
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.nikolai.ihavepaws"
    multiplatformResourcesClassName = "sharedRes"
    multiplatformResourcesVisibility = dev.icerock.gradle.MRVisibility.Internal
    iosBaseLocalizationRegion = "ru"
}

android {
    namespace = "com.nikolai.ihavepaws.android"
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

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
}

dependencies {
    implementation("androidx.core:core:1.10.1")
    implementation("androidx.core:core-ktx:+")
    commonMainApi("dev.icerock.moko:mvvm-core:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-compose:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow-compose:0.16.1")
}