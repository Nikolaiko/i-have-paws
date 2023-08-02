pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    }

    plugins {
        id("org.jetbrains.compose").version("1.4.3")
    }
}

rootProject.name = "IHavePaws"
include(":androidHavePawsApp")
include(":shared")