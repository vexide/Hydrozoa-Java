plugins {
    kotlin("jvm") version "1.9.20"
        id("dev.vexide.hydrozoa")
}

hydrozoa {
    entrypoint = "dev.vexide.hydrozoa.examples.clawbotkt.MainKt"
    runtime = rootProject.layout.projectDirectory.file("hydrozoa.bin")
}

kotlin {
    jvmToolchain(21)
}
