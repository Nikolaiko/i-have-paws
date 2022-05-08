buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        mavenLocal()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.41")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("io.realm.kotlin:gradle-plugin:0.9.0")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")
        classpath( "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.20.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}