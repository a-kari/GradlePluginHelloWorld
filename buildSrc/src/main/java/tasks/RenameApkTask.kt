package tasks

import AppDetail.defaultApkDirectoryPath
import AppDetail.defaultApkName
import AppDetail.newApkDirectoryPath
import AppDetail.newApkName
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import tasks.extensions.buildDirectoryPath
import java.io.File

open class RenameApkTask : DefaultTask() {

    private val oldApkPath
        get() = "$buildDirectoryPath/$defaultApkDirectoryPath/$defaultApkName"

    private val renamedApkPath
        get() = "$buildDirectoryPath/$defaultApkDirectoryPath/$newApkName"

    private val movedApkPath
        get() = "$buildDirectoryPath/$newApkDirectoryPath/$newApkName"

    @TaskAction
    fun renameAndMoveApk() {
        renameApk()
        moveApk()
    }

    private fun renameApk() {
        val apkFile = File(oldApkPath)
        val renamedApkFile = File(renamedApkPath)

        if (apkFile.exists()) {
            apkFile.renameTo(renamedApkFile)
        } else {
            println("Cannot rename apk due to path does not exist: ${apkFile.path}")
        }
    }

    private fun moveApk() {
        val apkFile = File(renamedApkPath)
        val movedApkFile = File(movedApkPath)

        try {
            apkFile.apply {
                copyTo(movedApkFile)
                delete()
            }
        } catch (e: Exception) {
            println("Cannot move apk ${apkFile.path} due to:")
            e.printStackTrace()

            val newApkDirectory = File(newApkDirectoryPath)
            newApkDirectory.mkdir()
        }
    }
}
