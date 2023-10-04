import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.kotlin.gradle.plugin.extraProperties
import org.jetbrains.kotlin.library.impl.extract
import java.util.Properties

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.konfig)
    alias(libs.plugins.apolloGraphQL)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.animation)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // Icons
                api(compose.materialIconsExtended)

                // Precompose
                api(libs.precompose)
                api(libs.precompose.viewmodel)
                api(libs.precompose.koin)

                // Koin
                api(libs.koin.core)
                api(libs.koin.compose)

                // Ktor
                api(libs.ktor.client.core)
                api(libs.ktor.client.logging)
                api(libs.ktor.serialization.kotlinx.json)
                api(libs.ktor.client.content.negotiation)

                // Apollo3
                api(libs.apollo.runtime)
                api(libs.apollo.normalized.cache)

                // fix of Could not find ".../commonMain/org.jetbrains.kotlinx-atomicfu-0.17.3-nativeInterop-8G5yng.klib"
                api(libs.atomicfu)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                api(libs.activity.compose)
                api(libs.appcompat)
                api(libs.core.ktx)
                api(libs.androidx.foundation)

                // Koin
                api(libs.koin.android)

                // Ktor
                api(libs.ktor.okhttp)
                api(libs.ktor.client.android)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>()
    .configureEach {
        kotlinOptions {
            freeCompilerArgs += listOf("-Xskip-prerelease-check")
        }
    }

tasks
    .withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>>()
    .configureEach {
        compilerOptions
            .languageVersion
            .set(
                org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_1_9
            )
    }

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "ca.josuelubaki.countryapp.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    sourceSets["main"].resources.exclude("src/commonMain/resources/MR")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

buildkonfig {
    packageName = "ca.josuelubaki.common"

    val properties = Properties()

    val localPropertiesFile = project.rootProject.file("local.properties")
    if(localPropertiesFile.exists()){
        localPropertiesFile.inputStream().use { properties.load(it) }
    }

    defaultConfigs {
        buildConfigField(STRING, "BASE_URL", properties.getProperty("BASE_URL"))
    }
}

apollo {
    service("service") {
        packageName.set("ca.josuelubaki.countryapp.common")
    }
}