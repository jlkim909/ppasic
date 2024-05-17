package com.hodak.ppasic

import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Project

internal fun LibraryAndroidComponentsExtension.disableUnnecessaryAndroidTests(
    project: Project
) = beforeVariants {
    it.enableAndroidTest = it.enableUnitTest
            && project.projectDir.resolve("src/androidTest").exists()
}