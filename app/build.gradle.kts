plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("io.gitlab.arturbosch.detekt")
    id("com.diffplug.spotless") version "6.21.0"
    id("com.google.dagger.hilt.android") // Hilt
    kotlin("kapt") // For annotation processing
    id("com.dropbox.dependency-guard") version "0.5.0"
    id("jacoco")
}

android {
    // Your existing configuration
}

tasks.register<JacocoReport>("createDebugCoverageReport") {
    dependsOn("testDebugUnitTest") // Ensure unit tests are run first

    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*")
    val debugTree = fileTree("${buildDir}/intermediates/javac/debug") {
        exclude(fileFilter)
    }

    sourceDirectories.setFrom(files(listOf("src/main/java")))
    classDirectories.setFrom(files(debugTree))
    executionData.setFrom(files("${buildDir}/jacoco/testDebugUnitTest.exec"))
}

dependencyGuard {
    configuration("debugRuntimeClasspath")
    configuration("releaseRuntimeClasspath")

}

detekt {
//    config.from("$rootDir/config/detekt/detekt.yml")
}

spotless {
    kotlin {
        target("**/*.kt") // 대상 파일
        ktlint("0.48.2") // Ktlint 버전
    }
}

android {
    namespace = "com.dyno.android_todo_starter"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dyno.android_todo_starter"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.github.takahirom.roborazzi.RoborazziTestRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // detekt
    detektPlugins(libs.detekt.formatting) // 포맷팅 검사 추가

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Ktor
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.json)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Paging
    implementation(libs.androidx.paging.runtime.ktx)

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
    androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.roborazzi)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

tasks.named("spotlessKotlin") {
    dependsOn("dependencyGuard")
}