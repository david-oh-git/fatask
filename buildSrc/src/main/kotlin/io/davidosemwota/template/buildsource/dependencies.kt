@file:Suppress("SpellCheckingInspection")
package io.davidosemwota.template.buildsource

object Libs {

    object Versions {
        const val ktlint = "0.40.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:4.1.2"
    const val jdkDesugar = "com.android.tools:desugar_jdk_libs:1.0.9"
    const val benManesUpdatePlugin = "com.github.ben-manes:gradle-versions-plugin:0.36.0"
    const val spotlessPlugin = "com.diffplug.spotless:spotless-plugin-gradle:5.11.0"
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"

    const val material = "com.google.android.material:material:1.3.0"

    object Detekt {
        const val version = "1.0.0-RC16"
        const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$version"
    }

    object Kotlin {
        const val version = "1.4.31"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val allOpen = "org.jetbrains.kotlin:kotlin-allopen:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.4.2"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object OkHttp {
        private const val version = "4.9.0"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object JUnit {
        private const val version = "4.13.2"
        const val junit = "junit:junit:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val palette = "androidx.palette:palette:1.0.0"

        const val coreKtx = "androidx.core:core-ktx:1.3.2"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"

        object Lifecycle {
            private const val version = "2.3.0"
            const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha02"
            const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object Test {
            private const val version = "1.2.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
            
        }

        object Room {
            private const val version = "2.2.5"
            const val runtime = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }
    }

}