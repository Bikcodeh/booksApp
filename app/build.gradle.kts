plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.bikcode.booksapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bikcode.booksapp"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime)
    implementation(libs.runtime.compose)
    implementation(libs.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.compose.navigation)
    implementation(libs.compose.constraint.layout)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.tooling.preview)
    implementation(libs.material3.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.android)
    implementation(libs.timber)
    kapt(libs.hilt.compiler)
    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseAuthentication)
    implementation(libs.firebaseDatabase)

    /** Network */
    implementation(libs.retrofit)
    implementation(libs.okHttp)
    implementation(libs.okhttplogginginterceptor)
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
}