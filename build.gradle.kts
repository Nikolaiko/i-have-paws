buildscript {
    val kotlin_version = extra["kotlin.version"] as String
    val resourceVersion = extra["icerock.resources"] as String

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.44")
        classpath( "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.20.0")
        classpath("dev.icerock.moko:resources-generator:$resourceVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("multiplatform").apply(false)
    kotlin("plugin.serialization").apply(false)

    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}