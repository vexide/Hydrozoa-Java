plugins {
    kotlin("jvm") version "1.9.20"
        id("dev.vexide.hydrozoa")
}

hydrozoa {
    entrypoint = "dev.vexide.hydrozoa.clawbotkt.Main"
    runtime = project.rootProject.layout.projectDirectory.file("runtime.bin")
}

kotlin {
    jvmToolchain(21)
}
