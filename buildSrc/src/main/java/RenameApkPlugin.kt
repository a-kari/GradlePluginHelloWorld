import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class RenameApkPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.task(RENAME_APK_TASK_NAME) {
            println("Configuring renameApk...")

            doLast {
                val apkFile = File(project.defaultReleaseArtefactPath)
                val newApkFile = File("${project.releaseArtefactsDirectoryPath}/$NEW_APK_NAME")

                if (apkFile.exists()) {
                    apkFile.renameTo(newApkFile)
                } else {
                    println("Cannot rename apk due to path does not exist: ${apkFile.path}")
                }
            }
        }.dependsOn(BUILD_TASK_NAME)
    }

    private val Project.defaultReleaseArtefactPath: String
        get() = "$releaseArtefactsDirectoryPath/$DEFAULT_APK_NAME"

    private val Project.releaseArtefactsDirectoryPath: String
        get() = "${this.buildDir.absoluteFile}/outputs/apk/release"

    companion object {
        private const val RENAME_APK_TASK_NAME = "renameApk"
        private const val BUILD_TASK_NAME = "build"
        private const val DEFAULT_APK_NAME = "app-release-unsigned.apk"
        private const val NEW_APK_NAME = "renamed-apk.apk"
    }
}
