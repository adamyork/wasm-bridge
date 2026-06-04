@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.3.20"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.3.20"
    id("com.google.devtools.ksp") version "2.3.9"
}

group = "com.github.adamyork"
version = "0.0.1"

kotlin {
    wasmJs {
        binaries.executable()
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            implementation("me.tatarka.inject:kotlin-inject-runtime:0.7.2")
            implementation("io.github.reactivecircus.cache4k:cache4k:0.14.0")
        }
        wasmJsMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-browser:0.3")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.11.0")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
        }
    }
}

dependencies {
    add("kspWasmJs", "me.tatarka.inject:kotlin-inject-compiler-ksp:0.7.2")
}