import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.serialization)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.androidKmpLibrary)
}

kotlin {
    androidLibrary {
        compileSdk = 36
        minSdk = 26
        namespace = "com.example.to_do_cmp"
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }

    jvm()

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)

            implementation(libs.sqldelight.android)

            implementation(libs.navigation3.runtime)
            implementation(libs.navigation3.ui)
            implementation(libs.navigation3.viewmodel)
            implementation(libs.navigation3.adaptive)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.kotlinx.serialization)
            implementation(libs.kotlinx.date.time)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.navigation3)

            implementation(libs.swipeable.kmp)

            implementation(libs.sqldelight.coroutines)
        }
        iosMain.dependencies {
            implementation(libs.navigation2)
            implementation(libs.sqldelight.ios)
        }
        jvmMain.dependencies {
            implementation(libs.navigation2)
            implementation(libs.sqldelight.driver)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.example.to_do_cmp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.example.to_do_cmp"
            packageVersion = "1.0.0"
        }
    }
}

sqldelight {
    databases {
        create("TaskDatabase") {
            packageName.set("com.example.to_do_cmp.database")
        }
    }
}
