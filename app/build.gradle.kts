import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "org.helllynx.smartbody"
        versionCode = 1
        versionName = "0.0.0"

        minSdkVersion(23)
        targetSdkVersion(30)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures.viewBinding = true

    signingConfigs {
        val localProperties = File("${rootDir.path}/local.properties").run {
            if (exists()) Properties().apply { load(inputStream()) } else null
        }
        val environment = System.getenv()
        fun get(env: String, local: String) = environment[env] ?: run {
            project.logger.warn("WARNING: No $env environmental variable")
            localProperties?.getProperty(local) ?: run {
                project.logger.warn("WARNING: No $local local property")
                null
            }
        }

        data class Keystore(
            val storeFile: File,
            val storePassword: String,
            val keyAlias: String,
            val keyPassword: String
        )

        fun getDebugKeystore(): Keystore? {
            return Keystore(
                rootProject.file("signing/debug.jks"),
                get("DEBUG_KEYSTORE_PASSWORD", "signing.debugKeystorePassword") ?: return null,
                get("DEBUG_KEY_ALIAS", "signing.debugKeystoreAlias") ?: return null,
                get("DEBUG_KEY_PASSWORD", "signing.debugKeyPassword") ?: return null
            )
        }

        getDebugKeystore()?.let { keystore ->
            getByName("debug") {
                storeFile = keystore.storeFile
                storePassword = keystore.storePassword
                keyAlias = keystore.keyAlias
                keyPassword = keystore.keyPassword
            }
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = checkNotNull(signingConfigs.findByName("debug"))
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
        getByName("androidTest").java.srcDirs("src/androidTest/kotlin")
    }
}

dependencies {
    // region Kotlin
    val kotlinVersion: String by project
    implementation(kotlin("stdlib-jdk8", kotlinVersion))
    // endregion

    // region AndroidX
    val navigationVersion: String by project
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    implementation("androidx.preference:preference:1.1.1")
    implementation("androidx.preference:preference:1.1.1")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("com.google.android.material:material:1.3.0-beta01")
    // endregion
}
