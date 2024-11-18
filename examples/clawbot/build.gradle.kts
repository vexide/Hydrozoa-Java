hydrozoa {
    entrypoint = "dev.vexide.hydrozoa.clawbot.Main"
    runtime = project.parent!!.parent!!.layout.projectDirectory.file("runtime.bin")
}
