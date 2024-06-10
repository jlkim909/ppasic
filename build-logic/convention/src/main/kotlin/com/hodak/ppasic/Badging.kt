package com.hodak.ppasic

import com.android.SdkConstants
import com.android.build.api.artifact.SingleArtifact
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import com.google.common.truth.Truth.assertWithMessage
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.CacheableTask
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
import org.gradle.configurationcache.extensions.capitalized
import org.gradle.kotlin.dsl.register
import org.gradle.language.base.plugins.LifecycleBasePlugin
import org.gradle.process.ExecOperations
import java.io.File
import javax.inject.Inject

@CacheableTask
abstract class GenerateBadgingTask : DefaultTask() {
    @get:OutputFile
    abstract val badging: RegularFileProperty

    @get:PathSensitive(PathSensitivity.NONE)
    @get:InputFile
    abstract val apk: RegularFileProperty

    @get:PathSensitive(PathSensitivity.NONE)
    @get:InputFile
    abstract val aapt2Executable: RegularFileProperty

    @get:Inject
    abstract val execOperations: ExecOperations

    @TaskAction
    fun taskAction() {
        execOperations.exec {
            commandLine(
                aapt2Executable.get().asFile.absolutePath,
                "dump",
                "badging",
                apk.get().asFile.absolutePath,
            )
            standardOutput = badging.asFile.get().outputStream()
        }
    }
}

@CacheableTask
abstract class CheckBadgingTask : DefaultTask() {

    // 입력이 변경되지 않았을 때 작업이 최신 상태가 되도록 하기 위해,
    // 작업은 사용되지 않더라도 출력을 선언해야 합니다.
    // 출력은 입력이 변경되었는지 여부에 관계없이 항상 실행됩니다

    @get:OutputDirectory
    abstract val output: DirectoryProperty

    @get:PathSensitive(PathSensitivity.NONE)
    @get:InputFile
    abstract val goldenBadging: RegularFileProperty

    @get:PathSensitive(PathSensitivity.NONE)
    @get:InputFile
    abstract val generatedBadging: RegularFileProperty

    @get:Input
    abstract val updateBadgingTaskName: Property<String>

    override fun getGroup(): String = LifecycleBasePlugin.VERIFICATION_GROUP

    @TaskAction
    fun taskAction() {
        assertWithMessage(
            "Generated badging is different from golden badging! " +
                    "If this change is intended, run ./gradlew ${updateBadgingTaskName.get()}",
        )
            .that(generatedBadging.get().asFile.readText())
            .isEqualTo(goldenBadging.get().asFile.readText())
    }
}

fun Project.configureBadgingTasks(
    baseException: BaseExtension,
    componentsException: ApplicationAndroidComponentsExtension
) {
    componentsException.onVariants { variant ->
        val capitalizedVariantName = variant.name.capitalized()
        val generateBadgingTaskName = "generate${capitalizedVariantName}Badging"
        val generateBadging =
            tasks.register<GenerateBadgingTask>(generateBadgingTaskName) {
                apk.set(
                    variant.artifacts.get(SingleArtifact.APK_FROM_BUNDLE),
                )
                aapt2Executable.set(
                    File(
                        baseException.sdkDirectory,
                        "${SdkConstants.FD_BUILD_TOOLS}/" +
                                "${baseException.buildToolsVersion}/" +
                                SdkConstants.FN_AAPT2
                    ),
                )

                badging.set(
                    project.layout.buildDirectory.file(
                        "outputs/apk_from_bundle/${variant.name}/${variant.name}-badging.txt"
                    ),
                )
            }

        val updateBadgingTaskName = "update${capitalizedVariantName}Badging"
        tasks.register<Copy>(updateBadgingTaskName) {
            from(generateBadging.get().badging)
            into(project.layout.projectDirectory)
        }

        val checkBadgingTaskName = "check${capitalizedVariantName}Badging"
        tasks.register<CheckBadgingTask>(checkBadgingTaskName) {
            goldenBadging.set(
                project.layout.projectDirectory.file("${variant.name}-badging.txt"),
            )
            generatedBadging.set(
                generateBadging.get().badging,
            )
            this.updateBadgingTaskName.set(updateBadgingTaskName)

            output.set(
                project.layout.buildDirectory.dir("intermediates/$checkBadgingTaskName"),
            )
        }
    }
}