plugins {

    id("com.android.application")

    id("kotlin-android")

    id("kotlin-kapt")

    id("dagger.hilt.android.plugin")

}


android {
    namespace = "com.example.basketball"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.basketball"
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
    // Testing

    testImplementation("junit:junit:4.13.2")

    testImplementation("org.mockito:mockito-core:5.3.1") // Mockito for unit testing

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3") // Coroutine testing

    testImplementation("androidx.arch.core:core-testing:2.2.0") // ViewModel and LiveData testing

    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")

    testImplementation("org.mockito:mockito-inline:5.3.1")



    androidTestImplementation("androidx.test.ext:junit:1.2.1")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")

    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("androidx.compose.ui:ui:1.4.3")

    implementation("androidx.compose.material3:material3:1.1.0")

    implementation("androidx.compose.ui:ui-tooling-preview:1.4.3")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    implementation("com.google.dagger:hilt-android:2.44")

    kapt("com.google.dagger:hilt-compiler:2.44")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    implementation("io.coil-kt:coil-compose:2.4.0")

    // Lifecycle ViewModel + Compose integration

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5") // Ensure it's the latest



    // Hilt

    implementation("com.google.dagger:hilt-android:2.49")

    kapt("com.google.dagger:hilt-compiler:2.49")



    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")



    // Retrofit & Gson

    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    implementation("com.squareup.retrofit2:converter-gson:2.11.0")



    // Room

    implementation("androidx.room:room-runtime:2.6.1")

    kapt("androidx.room:room-compiler:2.6.1")

    implementation("androidx.room:room-ktx:2.6.1")



    // Kotlin Coroutines

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")



    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")



    // Lifecycle (LiveData + ViewModel)

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.5")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")

    implementation("androidx.compose.runtime:runtime-livedata")



    implementation("io.coil-kt:coil-compose:2.3.0")



    implementation("androidx.navigation:navigation-compose:2.7.3")



//    // Testing

//    testImplementation("junit:junit:4.13.2")

//    androidTestImplementation("androidx.test.ext:junit:1.2.1")

//    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

//    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

//    debugImplementation("androidx.compose.ui:ui-tooling")

//    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // Core libraries

    implementation("androidx.core:core-ktx:1.13.1")

    implementation("androidx.activity:activity-compose:1.9.2")



    // Jetpack Compose (Update to latest stable version)

    implementation(platform("androidx.compose:compose-bom:2024.01.00")) // Updated BOM

    implementation("androidx.compose.ui:ui")

    implementation("androidx.compose.ui:ui-tooling-preview")

    implementation("androidx.compose.material3:material3")




}