plugins {
    alias(libs.plugins.ppasic.android.library)
    alias(libs.plugins.ppasic.android.library.compose)
    alias(libs.plugins.ppasic.android.library.jacoco)
}

android {
    namespace = "com.hodak.ppasic.core.designsystem"
}

dependencies{
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    implementation(libs.coil.kt.compose)
}