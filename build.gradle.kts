plugins {
//    id("io.gitlab.arturbosch.detekt") version "1.12.0"
    kotlin("plugin.serialization") version "1.4.20"
}

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        val kotlinVersion: String by project
        classpath("com.android.tools.build:gradle:4.2.0-beta03")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.2")

        val hiltVersion: String by project
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects.forEach { module ->
    module.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            @Suppress("SuspiciousCollectionReassignment")
            freeCompilerArgs += listOf(
                //"-XXLanguage:+InlineClasses",
                "-Xnew-inference"
            )
        }
    }
}

//detekt {
//    config = files("detekt-config.yml")
//
//    input = files(*subprojects.map { subproject ->
//        if (subproject.subprojects.isEmpty())
//            "${subproject.name}/src"
//        else
//            subproject.subprojects.map {
//                "${subproject.name}/${it.name}/src"
//            }
//    }.toTypedArray())
//
//    reports { xml { enabled = false } }
//    failFast = false
//}
//
//dependencies {
//    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.12.0")
//}
