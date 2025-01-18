plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("io.gitlab.arturbosch.detekt")
    id("com.diffplug.spotless") version "6.21.0"
}

detekt {
    config.from("$rootDir/config/detekt/detekt.yml")
    allRules = false // 모든 규칙을 실행하지 않고 필요한 것만 사용
}

spotless {
    kotlin {
        target("**/*.kt") // 대상 파일
        ktlint("0.48.2") // Ktlint 버전
        licenseHeader("/* License Header */") // 라이선스 헤더 추가 (옵션)
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
    detektPlugins(libs.detekt.formatting) // 포맷팅 검사 추가

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
}
