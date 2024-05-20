plugins {
    alias(libs.plugins.ppasic.android.library)
    alias(libs.plugins.ppasic.android.library.jacoco)
    alias(libs.plugins.ppasic.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.hodak.ppasic.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}