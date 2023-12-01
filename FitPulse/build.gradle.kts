// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.21" apply false
    id("com.google.devtools.ksp") version "1.9.21-1.0.15" apply false
}

buildscript {
    repositories {
        google()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")

        //navigation
        val nav_version = "2.7.5"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")

    }
}