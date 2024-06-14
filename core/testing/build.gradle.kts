plugins {
    alias(libs.plugins.ppasic.android.library)
    alias(libs.plugins.ppasic.android.library.compose)
    alias(libs.plugins.ppasic.android.hilt)
}

android {
    namespace = "com.hodak.ppasic.core.testing"
}

dependencies{
    implementation(libs.androidx.test.rules)
    implementation(libs.hilt.android.testing)
}