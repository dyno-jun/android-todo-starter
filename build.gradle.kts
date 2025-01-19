// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.0" apply false
    id("com.google.dagger.hilt.android") version "2.53.1" apply false
    id("com.dropbox.dependency-guard") version "0.5.0" apply false
    alias(libs.plugins.roborazzi) apply false
}
