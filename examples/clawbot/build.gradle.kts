plugins {
    id("dev.vexide.hydrozoa")
}

hydrozoa {
    entrypoint = "dev.vexide.hydrozoa.clawbot.Main"
    runtime = project.rootProject.layout.projectDirectory.file("runtime.bin")
}
