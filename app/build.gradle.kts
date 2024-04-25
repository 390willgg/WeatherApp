plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.weatherappsample1yt"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.weatherappsample1yt"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
    }
}

kapt {
    correctErrorTypes = true
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
        arg("kapt.module.generated", "$projectDir/generated/kaptKotlin/")
    }
}

kapt {
    arguments {
        arg("AROUTER_MODULE_NAME", project.name)
    }
}
//
dependencies{
    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //okHttp
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    //gson
    implementation (libs.gson)

    implementation(libs.glide)
    implementation (libs.blurview)
    implementation (libs.weatherview)

    implementation(libs.symbol.processing.api)

    implementation (libs.play.services.location)
    implementation (libs.androidx.viewpager2)
    implementation (libs.androidx.activity.ktx)
    implementation (libs.androidx.fragment.ktx)

    ksp(libs.dagger.compiler)
    implementation(libs.dagger)
    implementation(libs.androidx.databinding.runtime)
    ksp(libs.dagger.compiler)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support) // if you use the support libraries
    ksp(libs.dagger.android.processor)

    implementation (libs.lottie)
    implementation (libs.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

