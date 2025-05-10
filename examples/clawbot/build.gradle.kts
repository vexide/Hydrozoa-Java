import dev.vexide.hydrozoa.gradle.ProgramIcon

plugins {
    id("dev.vexide.hydrozoa")
}

hydrozoa {
    entrypoint = "dev.vexide.hydrozoa.examples.clawbot.Main"
    runtime = rootProject.layout.projectDirectory.file("hydrozoa.bin")
    name = "Clawbot Java"
    icon = ProgramIcon.COOL_X
}
