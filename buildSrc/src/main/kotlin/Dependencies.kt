@file:Suppress("Unused", "MayBeConstant", "MemberVisibilityCanBePrivate")

internal object Version {
    const val GRADLE_ANDROID = "7.0.0"
    const val GRADLE_KTLINT = "10.1.0"
    const val GRADLE_VERSIONS = "0.39.0"

    const val KOTLIN = "1.5.21"
    const val COROUTINES = "1.5.1"
    const val SERIALIZATION = "1.2.2"
    const val KTOR = "1.6.2"

    const val APP_COMPAT = "1.3.1"
    const val LIFECYCLE = "2.3.1"
    const val ACTIVITY = "1.3.1"

    const val TEST_KOTEST_RUNNER = "4.6.1"
}

object ProjectLib {
    const val ANDROID = "com.android.tools.build:gradle:${Version.GRADLE_ANDROID}"
    const val KOTLIN_GRADLE = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlin:kotlin-serialization:${Version.KOTLIN}"
    const val KTLINT = "org.jlleitschuh.gradle:ktlint-gradle:${Version.GRADLE_KTLINT}"
    const val VERSIONS = "com.github.ben-manes:gradle-versions-plugin:${Version.GRADLE_VERSIONS}"

    val all = setOf(ANDROID, KOTLIN_GRADLE, KOTLIN_SERIALIZATION, KTLINT, VERSIONS)
}

object ModuleLib {
    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.KOTLIN}"
    const val COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINES}"
    const val COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINES}"
    const val SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.SERIALIZATION}"
    const val KTOR = "io.ktor:ktor-client-android:${Version.KTOR}"
    const val KTOR_SERIALIZATION = "io.ktor:ktor-client-serialization-jvm:${Version.KTOR}"

    const val APP_COMPAT = "androidx.appcompat:appcompat:${Version.APP_COMPAT}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Version.ACTIVITY}"
    const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"

    val dalek = setOf(KOTLIN, COROUTINES_CORE)

    val sample = setOf(KOTLIN, COROUTINES_ANDROID, SERIALIZATION, KTOR, KTOR_SERIALIZATION,
        APP_COMPAT, ACTIVITY_KTX, LIFECYCLE_VIEWMODEL_KTX)
}

object TestLib {
    const val KOTEST_RUNNER = "io.kotest:kotest-runner-junit5:${Version.TEST_KOTEST_RUNNER}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES}"

    val all = setOf(KOTEST_RUNNER, COROUTINES)
}