plugins {
    java
    id("org.teavm")
}

repositories {
    mavenCentral()
}

interface HydrozoaPluginExtension {
    val slot: Property<Int>
    val outputFile: Property<String>
    val runtime: RegularFileProperty
    val entrypoint: Property<String>
}

val extension = extensions.create<HydrozoaPluginExtension>("hydrozoa")

extension.slot.convention(1)
extension.outputFile.convention("robot.wasm")
extension.runtime.convention(layout.projectDirectory.file("runtime.bin"))

abstract class UploadTask : Exec() {
    @Input
    val slot = project.objects.property<Int>()

    @InputFile
    val file = project.objects.fileProperty()

    @InputFile
    val runtime = project.objects.fileProperty()

    override fun exec() {
        commandLine = listOf(
            "hydrozoa",
            "upload",
            file.get().asFile.toString(),
            "--slot",
            slot.get().toString(),
            "--runtime",
            runtime.get().asFile.toString(),
        )

        super.exec()
    }
}

tasks.register<UploadTask>("upload") {
    dependsOn("assemble")
    slot.convention(extension.slot)
    file.convention(extension.outputFile.flatMap { layout.buildDirectory.file("generated/teavm/wasm/$it") })
    runtime.convention(extension.runtime)
}

teavm.wasm {
    mainClass = extension.entrypoint
    targetFileName = extension.outputFile
}

tasks.assemble {
    dependsOn(tasks.generateWasm)
}
