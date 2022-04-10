plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("RenameApkPlugin") {
            id = "jp.neechan.akari.rename-apk-plugin"
            implementationClass = "RenameApkPlugin"
            version = "1.0.0"
        }
    }
}
