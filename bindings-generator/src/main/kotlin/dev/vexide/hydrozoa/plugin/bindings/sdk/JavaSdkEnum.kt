package dev.vexide.hydrozoa.plugin.bindings.sdk

import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.body.RecordDeclaration
import com.github.javaparser.ast.expr.CastExpr
import com.github.javaparser.ast.expr.DoubleLiteralExpr
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.expr.IntegerLiteralExpr
import com.github.javaparser.ast.expr.NameExpr
import com.github.javaparser.ast.expr.ObjectCreationExpr
import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.ReturnStmt
import com.github.javaparser.ast.type.ClassOrInterfaceType
import com.github.javaparser.utils.SourceRoot
import com.google.common.base.CaseFormat
import dev.vexide.hydrozoa.plugin.bindings.SdkEnum
import dev.vexide.hydrozoa.plugin.bindings.addHydrozoaGeneratedAnnotation
import dev.vexide.hydrozoa.plugin.bindings.addHydrozoaGeneratedComment
import dev.vexide.hydrozoa.plugin.bindings.modifierListOf
import dev.vexide.hydrozoa.plugin.bindings.nodeListOf
import kotlin.collections.iterator

class JavaSdkEnum(val sdk: SdkEnum, val module: JavaSdkModule) {
    fun generate(sourceRoot: SourceRoot) {
        val name = generateEnumName(sdk.name, module.sdk.name)

        val cu = makeCompilationUnit(name, sourceRoot)
            .addHydrozoaGeneratedComment()

        val underlyingType = module.javaTypeFor(sdk.underlyingType)
        val record = RecordDeclaration(
            modifierListOf(Modifier.Keyword.PUBLIC),
            name,
        )
            .apply { cu.addType(this) }
            .addHydrozoaGeneratedAnnotation()
            .setJavadocComment(sdk.name)
            .addParameter(underlyingType, "value")

        val recordType = ClassOrInterfaceType(null, record.name, null)

        for ((variantName, variantValue) in sdk.variants) {
            val literal: Expression = if (variantValue % 1.0 == 0.0) {
                IntegerLiteralExpr(variantValue.toInt().toString())
            } else {
                DoubleLiteralExpr(variantValue)
            }

            record.addFieldWithInitializer(
                underlyingType,
                variantName,
                CastExpr(underlyingType, literal),
                Modifier.Keyword.PUBLIC,
                Modifier.Keyword.STATIC,
                Modifier.Keyword.FINAL,
            )

            record
                .addFieldWithInitializer(
                    recordType,
                    generateMemberName(variantName, name),
                    ObjectCreationExpr(null, recordType, nodeListOf(NameExpr(variantName))),
                    Modifier.Keyword.PUBLIC,
                    Modifier.Keyword.STATIC,
                    Modifier.Keyword.FINAL,
                )
                .setJavadocComment(variantName)
        }

        record.addMethod("getRawValue", Modifier.Keyword.PUBLIC)
            .setType(underlyingType)
            .setBody(
                BlockStmt(
                    nodeListOf(ReturnStmt(NameExpr("value")))
                )
            )
    }

    companion object {
        fun generateEnumName(name: String, moduleName: String): String {
            val name = name
                .removePrefix("V5_")
                .removePrefix("V5")

            val components = ArrayDeque(
                CaseFormat.LOWER_CAMEL
                    .to(CaseFormat.LOWER_UNDERSCORE, name)
                    .split('_')
            )

            components.addFirst(moduleName)

            return CaseFormat.LOWER_UNDERSCORE
                .to(CaseFormat.UPPER_CAMEL, components.joinToString("_"))
        }

        fun generateMemberName(name: String, enumName: String): String {
            val components = ArrayDeque(
                CaseFormat.LOWER_CAMEL
                .to(CaseFormat.LOWER_UNDERSCORE, name)
                .split('_'))
            val enumComponents = ArrayDeque(
                CaseFormat.LOWER_CAMEL
                .to(CaseFormat.LOWER_UNDERSCORE, enumName)
                .split('_'))

            // Remove module prefix from enum name
            enumComponents.removeFirst()

            for (redundantComponent in arrayOf("k", "v5")) {
                if (components.firstOrNull() == redundantComponent) {
                    components.removeFirst()
                } else {
                    break
                }
            }

            for (typeNameComponent in enumComponents) {
                if (components.firstOrNull() == typeNameComponent) {
                    components.removeFirst()
                } else {
                    break
                }
            }

            return components.joinToString("_").uppercase()
        }
    }
}