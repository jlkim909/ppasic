import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.android.build.gradle.LibraryExtension
import com.hodak.ppasic.configureFlavors
import com.hodak.ppasic.configureGradleManagedDevices
import com.hodak.ppasic.configureKotlinAndroid
import com.hodak.ppasic.configurePrintApksTask
import com.hodak.ppasic.disableUnnecessaryAndroidTests
import com.hodak.ppasic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
                apply("ppasic.android.lint")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
                testOptions.animationsDisabled = true
                configureFlavors(this)
                configureGradleManagedDevices(this)
                // 리소스 접두사는 모듈 이름에서 파생되었습니다,
                // 따라서 ":core:module1" 내부의 리소스는 "core_module1_" 앞에 붙어야 합니다
                resourcePrefix =
                    path.split("""\W""".toRegex()).drop(1).distinct().joinToString(separator = "_")
                        .lowercase() + "_"
            }

            extensions.configure<LibraryAndroidComponentsExtension> {
                configurePrintApksTask(this)
                disableUnnecessaryAndroidTests(target)
            }

            dependencies {
                add("testImplementation", kotlin("test"))
                add("implementation", libs.findLibrary("androidx.tracing.ktx").get())
            }
        }
    }
}