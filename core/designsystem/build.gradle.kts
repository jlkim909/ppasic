plugins {
    alias(libs.plugins.ppasic.android.library)
    alias(libs.plugins.ppasic.android.library.compose)
    alias(libs.plugins.ppasic.android.library.jacoco)
}

android {
    namespace = "com.hodak.ppasic.core.designsystem"
}