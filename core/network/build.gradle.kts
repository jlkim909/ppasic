plugins {
    alias(libs.plugins.ppasic.android.library)
    alias(libs.plugins.ppasic.android.library.jacoco)
    alias(libs.plugins.ppasic.android.hilt)
}

android {
    buildFeatures {
        buildConfig = true
    }
    namespace = "com.hodak.ppasic.core.network"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}