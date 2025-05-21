package dev.vexide.hydrozoa.plugin.bindings.sdk

import com.github.javaparser.StaticJavaParser
import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.type.ClassOrInterfaceType
import com.github.javaparser.ast.type.PrimitiveType
import com.github.javaparser.ast.type.Type
import com.github.javaparser.ast.type.VoidType
import com.github.javaparser.utils.SourceRoot
import com.google.common.base.CaseFormat
import dev.vexide.hydrozoa.plugin.bindings.SdkEnum
import dev.vexide.hydrozoa.plugin.bindings.SdkItem
import dev.vexide.hydrozoa.plugin.bindings.SdkModule
import dev.vexide.hydrozoa.plugin.bindings.addHydrozoaGeneratedAnnotation
import dev.vexide.hydrozoa.plugin.bindings.addHydrozoaGeneratedComment
import dev.vexide.hydrozoa.plugin.bindings.addNotNullAnnotation
import dev.vexide.hydrozoa.plugin.bindings.addPrivateConstructor
import dev.vexide.hydrozoa.plugin.bindings.addStaticInitAnnotation
import kotlin.jvm.optionals.getOrNull

class JavaSdkModule(val sdk: SdkModule) {
    var enums: Map<String, SdkEnum> = sdk.enums.associateBy { it.name }

    fun generate(sourceRoot: SourceRoot): CompilationUnit {
        val className = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, sdk.name) + "Sdk"

        val cu = makeCompilationUnit(className, sourceRoot)
            .addHydrozoaGeneratedComment()

        val bindingsClass = cu.addClass(className, Modifier.Keyword.PUBLIC, Modifier.Keyword.FINAL)
            .addHydrozoaGeneratedAnnotation()
            .addStaticInitAnnotation()
            .addPrivateConstructor()

        val submodules = mutableMapOf<String, JavaSdkSubmodule>()

        for (item in sdk.items) {
            val (submoduleName, trimmedMethodName) = JavaSdkItem.Companion.generateMethodName(item, sdk)

            val submodule = submodules.getOrPut(submoduleName) {
                JavaSdkSubmodule(submoduleName)
            }
            submodule.items.add(JavaSdkItem(item, module = this, methodName = trimmedMethodName))
        }

        for (submodule in submodules.values) {
            submodule.generate(bindingsClass)
        }

        for (enum in sdk.enums) {
            JavaSdkEnum(enum, module = this)
                .generate(sourceRoot)
        }

        return cu
    }

    fun javaTypeFor(type: SdkItem.Type?, useRawTypes: Boolean = false, annotations: Boolean = false): Type {
        return when (type) {
            null -> VoidType()
            is SdkItem.Type.Bool -> PrimitiveType(PrimitiveType.Primitive.BOOLEAN)
            is SdkItem.Type.Int -> PrimitiveType(PrimitiveType.Primitive.INT)
            is SdkItem.Type.Long -> PrimitiveType(PrimitiveType.Primitive.LONG)
            is SdkItem.Type.Float -> PrimitiveType(PrimitiveType.Primitive.FLOAT)
            is SdkItem.Type.Double -> PrimitiveType(PrimitiveType.Primitive.DOUBLE)
            is SdkItem.Type.StringPtr -> StaticJavaParser.parseClassOrInterfaceType("String")
                .addNotNullAnnotation(annotations)
            is SdkItem.Type.Named -> {
                if (useRawTypes) {
                    val underlyingType = enums[type.name]?.underlyingType
                    if (underlyingType != null) return javaTypeFor(underlyingType)
                }

                ClassOrInterfaceType(null, JavaSdkEnum.Companion.generateEnumName(type.name, sdk.name))
                    .addNotNullAnnotation(annotations)
            }
            is SdkItem.Type.Pointer -> {
                ClassOrInterfaceType(null, "org.teavm.interop.Address")
                    .addNotNullAnnotation(annotations)
            }
        }
    }

    fun hasRawType(type: SdkItem.Type): Boolean {
        return when (type) {
            is SdkItem.Type.Named -> enums[type.name]?.underlyingType != null
            else -> false
        }
    }
}

const val PACKAGE_NAME = "dev.vexide.hydrozoa.sdk"
fun makeCompilationUnit(className: String, sourceRoot: SourceRoot): CompilationUnit {
    var filename = sourceRoot.root
    for (component in PACKAGE_NAME.split(".")) {
        filename = filename.resolve(component)
    }

    val cu = CompilationUnit(PACKAGE_NAME)
        .setStorage(filename.resolve("$className.java"))
    print("Filename: ${cu.storage.getOrNull()?.path}")

    sourceRoot.add(cu)

    return cu
}