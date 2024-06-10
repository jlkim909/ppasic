import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import com.hodak.ppasic.configureBadgingTasks
import com.hodak.ppasic.configureGradleManagedDevices
import com.hodak.ppasic.configureKotlinAndroid
import com.hodak.ppasic.configurePrintApksTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("ppasic.android.lint")
                apply("com.dropbox.dependency-guard")
            }

            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = 34
                // @Suppress("UnstableApiUsage") : 안정성이 보장되지 않는 API 사용에 관련된 경고를 컴파일러가 표시하지 않도록 하는 역할
                @Suppress("UnstableApiUsage")
                // 테스트 옵션 중 애니메이션을 비활성화합니다.
                // 테스트 실행 시 애니메이션이 비활성화되어 더 안정적인 테스트 환경을 제공
                testOptions.animationsDisabled = true
                configureGradleManagedDevices(this)
            }

            extensions.configure<ApplicationAndroidComponentsExtension> {
                // test APK 파일의 위치를 확인하고, 해당 위치를 출력하는 Gradle 작업(Task)을 설정한다는 것을 의미
                configurePrintApksTask(this)
                // Badging은 Manifest 정보를 분석하여 APK 파일의 속성을 확인하는 프로세스
                configureBadgingTasks(extensions.getByType<BaseExtension>(), this)
            }
        }
    }
}