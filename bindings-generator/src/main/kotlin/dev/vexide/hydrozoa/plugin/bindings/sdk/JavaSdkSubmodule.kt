package dev.vexide.hydrozoa.plugin.bindings.sdk

import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import dev.vexide.hydrozoa.plugin.bindings.addPrivateConstructor
import dev.vexide.hydrozoa.plugin.bindings.addStaticInitAnnotation
import dev.vexide.hydrozoa.plugin.bindings.modifierListOf

class JavaSdkSubmodule(var submoduleName: String) {
    var items = mutableListOf<JavaSdkItem>()

    fun generate(parentClass: ClassOrInterfaceDeclaration) {
        val submoduleClass = ClassOrInterfaceDeclaration(
            modifierListOf(Modifier.Keyword.PUBLIC, Modifier.Keyword.STATIC, Modifier.Keyword.FINAL),
            false,
            submoduleName
        )
            .addStaticInitAnnotation()
            .addPrivateConstructor()

        parentClass.addMember(submoduleClass)

        for (item in items) {
            item.generate(submoduleClass)
        }
    }
}