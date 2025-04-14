import dev.vexide.hydrozoa.gradle.ProgramIcon

plugins {
    kotlin("jvm") version "2.0.20"
    id("dev.vexide.hydrozoa")
}

hydrozoa {
    entrypoint = "dev.vexide.hydrozoa.examples.clawbotkt.MainKt"
    runtime = rootProject.layout.projectDirectory.file("hydrozoa.bin")
    name = "Clawbot Kotlin"
    icon = ProgramIcon.ROBOT
}

kotlin {
    jvmToolchain(21)
}
