plugins {
    kotlin("jvm") version "1.9.20"
    id("dev.vexide.hydrozoa")
}

kotlin {
    jvmToolchain(21)
}

hydrozoa {
    entrypoint = "dev.vexide.hydrozoa.examples.screen.MainKt"
    runtime = rootProject.layout.projectDirectory.file("hydrozoa.bin")
}
