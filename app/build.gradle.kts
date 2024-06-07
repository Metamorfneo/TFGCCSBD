plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.tfgccsbd"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tfgccsbd"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //ARQ Components
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1")


    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.1")
    implementation("androidx.lifecycle:lifecycle-runtime:2.8.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")


    //Material Desing
    implementation("com.google.android.material:material:1.12.0")

    //Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.2")
    implementation("androidx.navigation:navigation-ui:2.7.7")


    //Corutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")

    //Corutines lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    kapt("com.github.bumptech.glide:compiler:4.15.1")


    //Dagger - hilt
    implementation("com.google.dagger:hilt-android:2.47")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    kapt("com.google.dagger:hilt-android-compiler:2.28-alpha")


    //KTX
    implementation("androidx.activity:activity-ktx:1.7.2")

    //Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    //Firebase firestore KTX
    implementation("com.google.firebase:firebase-firestore-ktx:24.8.1")

    //Firebase firestore
    implementation("com.google.firebase:firebase-firestore:24.8.1")

    //Firebase Corutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3")


    //Exoplayer
    api("com.google.android.exoplayer:exoplayer:2.19.1")
    api("com.google.android.exoplayer:extension-mediasession:2.19.1")
    api("com.google.android.exoplayer:exoplayer-ui:2.19.1")

}