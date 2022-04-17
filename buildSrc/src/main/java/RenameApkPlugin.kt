import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import tasks.RenameApkTask

class RenameApkPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.register<RenameApkTask>(RENAME_APK_TASK_NAME) {
            println("Configuring renameApk task...")
            dependsOn(BUILD_TASK_NAME)
        }
    }

    companion object {
        private const val RENAME_APK_TASK_NAME = "renameApk"
        private const val BUILD_TASK_NAME = "build"
    }
}
