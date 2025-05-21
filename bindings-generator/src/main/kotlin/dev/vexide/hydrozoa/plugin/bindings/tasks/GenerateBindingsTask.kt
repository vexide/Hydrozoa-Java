package dev.vexide.hydrozoa.plugin.bindings.tasks

import com.github.javaparser.utils.SourceRoot
import dev.vexide.hydrozoa.plugin.bindings.sdk.JavaSdkModule
import dev.vexide.hydrozoa.plugin.bindings.SdkModule
import kotlinx.serialization.json.Json
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.createDirectory
import kotlin.io.path.deleteRecursively
import kotlin.io.readText

abstract class GenerateBindingsTask : DefaultTask() {
    @get:InputFile
    abstract val apiFile: RegularFileProperty

    @get:OutputDirectory
    abstract val outputDirectory: DirectoryProperty

    @OptIn(ExperimentalPathApi::class)
    @TaskAction
    fun generateBindings() {
        val apiFile = apiFile.asFile.get()
        val api = Json.decodeFromString<SdkModule>(apiFile.readText())

        val outDir = outputDirectory.asFile.get().toPath()
        print("Out dir: $outDir")

        outDir.deleteRecursively()
        outDir.createDirectory()

        val sourceRoot = SourceRoot(outDir)
        JavaSdkModule(api).generate(sourceRoot)
        sourceRoot.saveAll()
    }
}