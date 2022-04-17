import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Dependencies {
    const val kotlinCore = "androidx.core:core-ktx:${Versions.kotlinCoreVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraint =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintVersion}"

    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExtVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"

    const val buildToolGradle = "com.android.tools.build:gradle:${Versions.buildToolGradleVersion}"
    const val kotlinGradlePlugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinStdLibVersion}"
}

object Versions {
    const val compileSdkVersion = 32
    const val minSdkVersion = 21
    const val targetSdkVersion = 32

    const val kotlinStdLibVersion = "1.5.30"
    const val buildToolGradleVersion = "7.0.4"

    const val kotlinCoreVersion = "1.7.0"
    const val appCompatVersion = "1.4.1"
    const val materialVersion = "1.5.0"
    const val constraintVersion = "2.1.3"
    const val jUnitVersion = "4.13.2"
    const val jUnitExtVersion = "1.1.3"
    const val espressoVersion = "3.4.0"
}

object AppDetail {
    const val applicationId = "jp.neechan.akari.project_tracker"
    const val appName = "ProjectTracker"
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val defaultApkDirectoryPath = "/outputs/apk/release"
    const val newApkDirectoryPath = "newApkDirectory"

    const val defaultApkName = "app-release-unsigned.apk"
    val newApkName = "$appName-${getDate(false)}($versionCode).apk"

    const val dependencyFileName = "Dependencies.txt"
}

fun getDate(forTxtFile: Boolean): String {
    val current = LocalDateTime.now()
    val formatter = if (forTxtFile) {
        DateTimeFormatter.ofPattern("dd MMM, yyy HH:mm:ss")
    } else {
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    }
    return current.format(formatter)
}
