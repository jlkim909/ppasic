import com.hodak.ppasic.HoBuildType

plugins {
    alias(libs.plugins.ppasic.android.application)
    alias(libs.plugins.ppasic.android.application.compose)
    alias(libs.plugins.ppasic.android.application.flavors)
    alias(libs.plugins.ppasic.android.application.jacoco)
    alias(libs.plugins.ppasic.android.hilt)
}

android {
    namespace = "com.hodak.ppasic"

    defaultConfig {
        applicationId = "com.hodak.ppasic"
        versionCode = 1
        versionName = "0.1.0" // X.Y.Z; X = Major, Y = minor, Z = Patch level

        testInstrumentationRunner = "com.hodak.ppasic.core.testing.HoTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = HoBuildType.DEBUG.applicationIdSuffix
        }
        release {
            isMinifyEnabled = true
            applicationIdSuffix = HoBuildType.RELEASE.applicationIdSuffix
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )

            // To publish on the Play store a private signing key is required, but to allow anyone
            // who clones the code to sign and run the release variant, use the debug signing key.
            // TODO: Abstract the signing configuration to a separate file to avoid hardcoding this.
            signingConfig = signingConfigs.named("debug").get()
            // Ensure Baseline Profile is fresh for release builds.
            //baselineProfile.automaticGenerationDuringBuild = true
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.designsystem)

    implementation(projects.feature.cart)
    implementation(projects.feature.fridge)
    implementation(projects.feature.home)
    implementation(projects.feature.recipe)

    implementation(libs.androidx.activity.compose)
}