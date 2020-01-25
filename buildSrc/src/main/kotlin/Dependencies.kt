@file:Suppress("Unused", "MayBeConstant", "MemberVisibilityCanBePrivate")

internal object Version {
    const val GRADLE_ANDROID = "3.5.2"
    const val GRADLE_KTLINT = "9.1.1"
    const val GRADLE_VERSIONS = "0.27.0"

    const val KOTLIN = "1.3.61"
    const val COROUTINES = "1.3.3"
    const val SERIALIZATION = "0.14.0"

    const val APP_COMPAT = "1.1.0"
    const val LIFECYCLE = "2.2.0"
    const val ACTIVITY = "1.1.0"
    const val FUEL = "2.2.1"

    const val TEST_KOTLIN_RUNNER = "3.4.2"
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
    const val SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Version.SERIALIZATION}"

    const val APP_COMPAT = "androidx.appcompat:appcompat:${Version.APP_COMPAT}"
    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Version.ACTIVITY}"
    const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.LIFECYCLE}"
    const val FUEL_CORE = "com.github.kittinunf.fuel:fuel:${Version.FUEL}"
    const val FUEL_COROUTINES = "com.github.kittinunf.fuel:fuel-coroutines:${Version.FUEL}"
    const val FUEL_SERIALIZATION = "com.github.kittinunf.fuel:fuel-kotlinx-serialization:${Version.FUEL}"

    val dalek = setOf(KOTLIN, COROUTINES_CORE)

    val sample = setOf(KOTLIN, COROUTINES_ANDROID, SERIALIZATION, APP_COMPAT, ACTIVITY_KTX,
        LIFECYCLE_VIEWMODEL_KTX, FUEL_CORE, FUEL_COROUTINES, FUEL_SERIALIZATION)
}

object TestLib {
    const val KOTLIN_RUNNER = "io.kotlintest:kotlintest-runner-junit5:${Version.TEST_KOTLIN_RUNNER}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINES}"

    val all = setOf(KOTLIN_RUNNER, COROUTINES)
}