package tasks.extensions

import org.gradle.api.DefaultTask

val DefaultTask.buildDirectoryPath: String
    get() = project.buildDir.absoluteFile.path
