import org.jetbrains.kotlin.gradle.plugin.KaptExtension

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.kapt") // Optional to alias if you like
    id("androidx.room") version "2.7.0"
}

android {
    namespace = "com.example.welcome"
    compileSdk = 35



    defaultConfig {
        applicationId = "com.example.welcome"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true

        dataBinding = true
    }
}



kapt {
    includeCompileClasspath = false


}

room{
    schemaDirectory("$projectDir/Schemas")
}

dependencies {
    // Compose BOM
    implementation(platform(libs.androidx.compose.bom))

    // Compose UI and Material
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)

    // Lifecycle + Activity
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0") // manually managed
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")
    implementation(libs.androidx.activity.compose)

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Room
    implementation("androidx.room:room-runtime:2.7.0")
    implementation(libs.androidx.compiler)
    implementation(libs.androidx.room.runtime.android)
    kapt("androidx.room:room-compiler:2.7.0")

    //icons
    implementation(libs.androidx.material.icons.extended)


    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debug tools
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    configurations.all {
        exclude(group = "com.google.guava", module = "listenablefuture")
        exclude(group = "androidx.databinding", module = "baseLibrary")
    }




}
