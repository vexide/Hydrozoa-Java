package dev.vexide.hydrozoa.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.property
import org.gradle.kotlin.dsl.register
import org.teavm.gradle.api.TeaVMExtension


/**
 * Available icons for Hydrozoa programs
 */
enum class ProgramIcon(val value: String) {
    VEX_CODING_STUDIO("vex-coding-studio"),
    COOL_X("cool-x"),
    QUESTION_MARK("question-mark"),
    PIZZA("pizza"),
    CLAWBOT("clawbot"),
    ROBOT("robot"),
    POWER_BUTTON("power-button"),
    PLANETS("planets"),
    ALIEN("alien"),
    ALIEN_IN_UFO("alien-in-ufo"),
    CUP_IN_FIELD("cup-in-field"),
    CUP_AND_BALL("cup-and-ball"),
    MATLAB("matlab"),
    PROS("pros"),
    ROBOT_MESH("robot-mesh"),
    ROBOT_MESH_CPP("robot-mesh-cpp"),
    ROBOT_MESH_BLOCKLY("robot-mesh-blockly"),
    ROBOT_MESH_FLOWOL("robot-mesh-flowol"),
    ROBOT_MESH_JS("robot-mesh-js"),
    ROBOT_MESH_PY("robot-mesh-py"),
    CODE_FILE("code-file"),
    VEXCODE_BRACKETS("vexcode-brackets"),
    VEXCODE_BLOCKS("vexcode-blocks"),
    VEXCODE_PYTHON("vexcode-python"),
    VEXCODE_CPP("vexcode-cpp"),
}

interface HydrozoaPluginExtension {
    val slot: Property<Int>
    val name: Property<String>
    val icon: Property<ProgramIcon>
    val outputFile: Property<String>
    val runtime: RegularFileProperty
    val entrypoint: Property<String>
}

abstract class UploadTask : Exec() {
    @Input
    val slot = project.objects.property<Int>()

    @Input
    val name = project.objects.property<String>()

    @Input
    val icon = project.objects.property<ProgramIcon>()

    @InputFile
    val file = project.objects.fileProperty()

    @InputFile
    val runtime = project.objects.fileProperty()

    override fun exec() {
        commandLine = listOf(
            "hydrozoa",
            "upload",
            file.get().asFile.toString(),
            "--name",
            name.get(),
            "--slot",
            slot.get().toString(),
            "--icon",
            icon.get().value,
            "--runtime",
            runtime.get().asFile.toString(),
            "--version",
            "0.1.0.0"
        )

        super.exec()
    }
}

class HydrozoaPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            plugins.apply("java")

            // Apply the TeaVM plugin if it exists
            if (plugins.hasPlugin("org.teavm")) {
                // TeaVM plugin already applied
            } else {
                plugins.apply("org.teavm")
            }

            val extension = extensions.create<HydrozoaPluginExtension>("hydrozoa")

            extension.name.convention("MyJavaProgram")
            extension.slot.convention(1)
            extension.icon.convention(ProgramIcon.ALIEN_IN_UFO)
            extension.outputFile.convention("robot.wasm")
            extension.runtime.convention(layout.projectDirectory.file("hydrozoa.bin"))

            tasks.register<UploadTask>("hydrozoaUpload") {
                group = "build"
                dependsOn("assemble")
                name.convention(extension.name)
                slot.convention(extension.slot)
                icon.convention(extension.icon)
                file.convention(extension.outputFile.flatMap { layout.buildDirectory.file("generated/teavm/wasm/$it") })
                runtime.convention(extension.runtime)
            }

            // Configure TeaVM
            (extensions.findByName("teavm") as? TeaVMExtension)?.apply {
                wasm {
                    mainClass.set(extension.entrypoint)
                    targetFileName.set(extension.outputFile)
                }
            }

            tasks.named("assemble") {
                dependsOn("generateWasm")
            }
        }
    }
}
