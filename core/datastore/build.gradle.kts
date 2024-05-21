plugins {
    alias(libs.plugins.ppasic.android.library)
    alias(libs.plugins.ppasic.android.library.jacoco)
    alias(libs.plugins.ppasic.android.hilt)
}

android {
    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }
    namespace = "com.hodak.ppasic.core.datastore"
    testOptions {
        unitTests {
            isReturnDefaultValues = true
        }
    }
}