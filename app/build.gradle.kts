import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("dagger.hilt.android.plugin")
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
    buildFeatures.dataBinding = true

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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    packagingOptions {
        // kapt
        exclude("META-INF/gradle/incremental.annotation.processors")
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
    val coroutinesVersion: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    // endregion

    // region Hilt
    val hiltVersion: String by project
    val hiltJetpackVersion: String by project
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    implementation("androidx.hilt:hilt-lifecycle-viewmodel:$hiltJetpackVersion")
    kapt("androidx.hilt:hilt-compiler:$hiltJetpackVersion")
    implementation("androidx.fragment:fragment-ktx:1.3.0-rc01")

    androidTestImplementation("com.google.dagger:hilt-android-testing:$hiltVersion")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:$hiltVersion")
    kaptAndroidTest("androidx.hilt:hilt-compiler:$hiltJetpackVersion")
    // endregion

    // region Room
    val roomVersion: String by project
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    // endregion

    // region AndroidX
    val lifecycleVersion: String by project
    implementation("androidx.lifecycle:lifecycle-livedata:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-extensions:$lifecycleVersion")
    val navigationVersion: String by project
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    val preferenceVersion: String by project
    implementation("androidx.preference:preference:$preferenceVersion")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    val espressoVersion: String by project
    implementation("androidx.test.espresso:espresso-idling-resource:$espressoVersion")
    // endregion

    // region time
    val jodaTimeVersion: String by project
    implementation("joda-time:joda-time:$jodaTimeVersion")
    // endregion

    // region UI
    val materialVersion: String by project
    implementation("com.google.android.material:material:$materialVersion")
    val constraintVersion: String by project
    implementation("androidx.constraintlayout:constraintlayout:$constraintVersion")
    val horizontalCalendarVersion: String by project
    implementation("devs.mulham.horizontalcalendar:horizontalcalendar:$horizontalCalendarVersion")
    // endregion

    // region Timber
    val timberVersion: String by project
    implementation("com.jakewharton.timber:timber:$timberVersion")
    // endregion
}
