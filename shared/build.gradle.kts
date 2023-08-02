plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")

    id("com.android.library")
    id("app.cash.sqldelight") version "2.0.0-alpha05"
    id("io.gitlab.arturbosch.detekt")
}

sqldelight {
    databases {
        create("GroupsDatabase") {
            packageName.set("com.nikolai")
            sourceFolders.set(listOf("database"))
        }
    }
}

//sqldelight {
//
//    database("GroupsDatabase") {
//        packageName = "com.nikolai"
//        sourceFolders = listOf("database")
//        schemaOutputDirectory = file("build/dbs")
//        migrationOutputFileFormat = ".sqm"
//        verifyMigrations = true
//    }
//    linkSqlite = true
//}


kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64() //sure all ios dependencies support this target
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("io.realm.kotlin:library-base:0.9.0")
                implementation("io.insert-koin:koin-core:3.4.0")

                //Compose multiplatform
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
                implementation("app.cash.sqldelight:android-driver:2.0.0-alpha05")

                //Compose multiplatform
                api("androidx.activity:activity-compose:1.6.1")
            }
        }
        val androidTest by getting {
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
                implementation("app.cash.sqldelight:native-driver:2.0.0-alpha05")
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
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 31
    }
}