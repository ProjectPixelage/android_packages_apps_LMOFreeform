import com.github.kr328.gradle.zygote.ZygoteLoader

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.zygoteLoader)
    alias(libs.plugins.hiddenApiRefine)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.libremobileos.freeform.server"

    buildFeatures {
        aidl = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.magic.library)
    implementation(libs.gson)
    implementation(projects.service)
    implementation(libs.hiddenapirefineruntime)
    compileOnly(projects.hiddenApi)
}

zygote {
    val moduleId = "mi-freeform"
    val moduleName = "Mi-Freeform"
    val moduleDescription = "Mi-Freeform"
    val moduleAuthor = "KindBrave"
    val moduleEntrypoint = "com.libremobileos.freeform.server.ZygoteMain"

    packages(ZygoteLoader.PACKAGE_SYSTEM_SERVER)

    riru {
        id = "riru-$moduleId".replace('-', '_')
        name = "Riru - $moduleName"
    }

    zygisk {
        id = "zygisk-$moduleId".replace('-', '_')
        name = "Zygisk - $moduleName"
    }

    all {
        author = moduleAuthor
        description = moduleDescription
        entrypoint = moduleEntrypoint
    }
}
