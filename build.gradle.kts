buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.konfigPlugin)
    }
}

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform").version(libs.versions.kotlin).apply(false)
    kotlin("jvm").version(libs.versions.kotlin).apply(false)
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.library) apply false
    alias(libs.plugins.compose) apply false
    alias(libs.plugins.konfig) apply false
    alias(libs.plugins.apolloGraphQL) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}