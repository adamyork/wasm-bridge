@file:OptIn(org.jetbrains.kotlin.gradle.ExperimentalWasmDsl::class)

plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.3.0"
}

group = "com.github.adamyork"
version = "0.0.1"

kotlin {
    wasmJs {
        binaries.executable()
        browser()
    }

    sourceSets {
        commonTest.dependencies {
            implementation("org.jetbrains.kotlin:kotlin-test:2.3.0")
        }
        wasmJsMain.dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-browser:0.3")
        }
    }
}

// 1. Register your custom copy task
val assembleOutput = tasks.register<Copy>("assembleOutput") {
    group = "build"
    description = "Copies distribution and static resource files to the build/output directory."

    from(layout.projectDirectory.dir("build/dist/wasmJs/productionExecutable"))
    from(layout.projectDirectory.dir("src/main/resources/static"))
    into(layout.buildDirectory.dir("output"))

    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    // Make this task wait until the WebAssembly distribution is generated
    dependsOn("wasmJsBrowserDistribution")
}

// 2. Hook your task into the standard build lifecycle
tasks.named("build") {
    dependsOn(assembleOutput)
}