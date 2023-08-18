plugins {
    id("com.android.application")
    kotlin("android")

    kotlin("kapt")
    id("io.gitlab.arturbosch.detekt")
}

android {
    signingConfigs {
        create("release") {
            storeFile =
                file("./certs/PawsCertsStorage")
            storePassword = "N1kolaiko"
            keyAlias = "key0"
            keyPassword = "N1kolaiko"
        }
    }
    compileSdk = 33
    defaultConfig {
        applicationId = "com.nikolai.ihavepaws.android"
        minSdk = 31
        targetSdk = 33
        versionCode = 5
        versionName = "5.0"
    }
    buildFeatures {
        compose = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
}

dependencies {
    val koin_version= "3.2.0"

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
    implementation("io.insert-koin:koin-android:3.4.0")
    implementation("io.insert-koin:koin-androidx-navigation:3.4.0")
    implementation("io.insert-koin:koin-androidx-compose:3.4.1")


    //ViewModel LifeCycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-beta01")

    //Compose tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.1.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.1.1")

    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
}

kapt {
    correctErrorTypes = true
}