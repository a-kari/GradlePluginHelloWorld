package tasks

import AppDetail
import AppDetail.dependencyFileName
import AppDetail.newApkDirectoryPath
import getFormattedDateTime
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import tasks.extensions.buildDirectoryPath
import java.io.File

open class LogDependenciesTask : DefaultTask() {

    private val dependenciesDirectoryPath
        get() = "$buildDirectoryPath/$newApkDirectoryPath"

    private val dependenciesFilePath
        get() = "$dependenciesDirectoryPath/$dependencyFileName"

    @TaskAction
    fun createFileAndLogDependencies() {
        val dependenciesFile = getDependenciesFile()
        logDependencies(dependenciesFile)
    }

    private fun getDependenciesFile(): File {
        val dependenciesFile = File(dependenciesFilePath)

        if (!dependenciesFile.exists()) {
            println("$dependenciesFilePath does not exist. Creating folder...")

            val dependenciesDirectory = File(dependenciesDirectoryPath)
            if (!dependenciesDirectory.exists()) {
                dependenciesDirectory.mkdir()
            }
            dependenciesFile.createNewFile()
        }
        return dependenciesFile
    }

    private fun logDependencies(file: File) {
        file.writeText(getFormattedDateTime(), Charsets.UTF_8)
        file.appendText(
            "\n${AppDetail.appName} - ${AppDetail.versionName}(${AppDetail.versionCode})\n",
            Charsets.UTF_8
        )
        file.appendText("====Dependencies====\n", Charsets.UTF_8)

        project.configurations.asMap.forEach {
            if (it.key.contains("implementation")) {
                it.value.dependencies.forEach { dependency ->
                    file.appendText(
                        "${dependency.group}:${dependency.name}:${dependency.version}\n",
                        Charsets.UTF_8
                    )
                }
            }
        }

        file.appendText("====================", Charsets.UTF_8)
        file.appendText("\n\n\n", Charsets.UTF_8)
    }
}
