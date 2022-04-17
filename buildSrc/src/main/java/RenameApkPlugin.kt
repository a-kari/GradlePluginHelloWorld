import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import tasks.LogDependenciesTask
import tasks.RenameApkTask

class RenameApkPlugin : Plugin<Project> {

    // Tasks execution order:
    // build -> renameApk -> logDependencies -> createBuild
    // I.e. every dependent task firstly executes its dependencies.
    override fun apply(project: Project) {
        project.tasks.register<RenameApkTask>(RENAME_APK_TASK_NAME) {
            println("Configuring $RENAME_APK_TASK_NAME task...")
            dependsOn(BUILD_TASK_NAME)
        }

        project.tasks.register<LogDependenciesTask>(LOG_DEPENDENCIES_TASK_NAME) {
            println("Configuring $LOG_DEPENDENCIES_TASK_NAME task...")
            dependsOn(RENAME_APK_TASK_NAME)
        }

        project.task(CREATE_BUILD_TASK_NAME) {
            println("Configuring $CREATE_BUILD_TASK_NAME task...")
            dependsOn(LOG_DEPENDENCIES_TASK_NAME)
        }
    }

    companion object {
        private const val RENAME_APK_TASK_NAME = "renameApk"
        private const val LOG_DEPENDENCIES_TASK_NAME = "logDependencies"
        private const val CREATE_BUILD_TASK_NAME = "createRenamedBuild"
        private const val BUILD_TASK_NAME = "build"
    }
}
