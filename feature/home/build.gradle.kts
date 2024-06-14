plugins {
    alias(libs.plugins.ppasic.android.feature)
    alias(libs.plugins.ppasic.android.library.compose)
    alias(libs.plugins.ppasic.android.library.jacoco)
}

android {
    namespace = "com.hodak.ppasic.feature.home"
}