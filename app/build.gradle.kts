import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  id ("com.android.application")
  id ("kotlin-android")
}

android {
  namespace = "dev.trindadedev.thecatapi"
  compileSdk = 34

  defaultConfig {
    applicationId = "dev.trindadedev.thecatapi"
    minSdk = 24
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"

    vectorDrawables.useSupportLibrary = true
  }
    
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles (getDefaultProguardFile ("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  buildFeatures {
    viewBinding = true
  }
}

tasks.withType<KotlinJvmCompile>().configureEach {
  compilerOptions.jvmTarget.set (JvmTarget.JVM_11)
}

dependencies {
  implementation ("androidx.appcompat:appcompat:1.7.1")
  implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
  implementation ("com.google.android.material:material:1.12.0")
  
  val fragment_version = "1.8.9"
  implementation ("androidx.fragment:fragment:$fragment_version")
  implementation ("androidx.fragment:fragment-ktx:$fragment_version")

  val retrofit_version = "2.11.0"
  implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
  implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")

  val coroutines_version = "1.9.0"
  implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
  implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

  val lifecycle_version = "2.8.4"
  implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
  implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")

  val coil_version = "2.6.0"
  implementation ("io.coil-kt:coil:$coil_version")
  implementation ("io.coil-kt:coil-base:$coil_version")
}
