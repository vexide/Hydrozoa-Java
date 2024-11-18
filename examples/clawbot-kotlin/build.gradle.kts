plugins {
    kotlin("jvm") version "1.9.20"
}

hydrozoa {
    entrypoint = "dev.vexide.hydrozoa.clawbotkt.Main"
}

kotlin {
    jvmToolchain(21)
}
