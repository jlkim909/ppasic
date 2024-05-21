plugins{
    alias(libs.plugins.ppasic.android.library)
    alias(libs.plugins.ppasic.android.library.jacoco)
    id("com.google.devtools.ksp")
}

android{
    namespace = "com.hodak.ppasic.core.domain"
}