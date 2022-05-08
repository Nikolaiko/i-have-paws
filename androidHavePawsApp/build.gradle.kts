plugins {
    id("com.android.application")
    kotlin("android")

    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}
val var1 by extra("sergeantN1kolai")
val var2 by extra("key0")
val var3 by extra("sergeantN1kolai")

android {
    signingConfigs {
        create("release") {
            storeFile =
                file("/Users/nikolai/Projects/IHavePaws/androidHavePawsApp/certs/PawsCertsStorage")
            storePassword = "uWie3ii3"
            keyAlias = "key0"
            keyPassword = "uWie3ii3"
        }
    }
    compileSdk = 31
    defaultConfig {
        applicationId = "com.nikolai.ihavepaws.android"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            //signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.1"
    }
}

dependencies {
    val koin_version= "3.2.0-beta-1"

    implementation(project(":shared"))

    //Compose
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.material:material:1.1.1")
    implementation("androidx.compose.ui:ui-tooling:1.1.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.1.1")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.4.2")

    //Hilt
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.dagger:hilt-android:2.41")
    kapt("com.google.dagger:hilt-android-compiler:2.41")

    //Koin
    implementation("io.insert-koin:koin-android:$koin_version")
    implementation("io.insert-koin:koin-androidx-navigation:$koin_version")
    implementation("io.insert-koin:koin-androidx-compose:$koin_version")


    //ViewModel LifeCycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-beta01")

    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
}

kapt {
    correctErrorTypes = true
}