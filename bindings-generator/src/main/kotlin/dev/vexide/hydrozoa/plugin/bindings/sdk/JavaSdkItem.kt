package dev.vexide.hydrozoa.plugin.bindings.sdk

import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration
import com.github.javaparser.ast.body.MethodDeclaration
import com.github.javaparser.ast.expr.Expression
import com.github.javaparser.ast.expr.MethodCallExpr
import com.github.javaparser.ast.expr.NameExpr
import com.github.javaparser.ast.expr.ObjectCreationExpr
import com.github.javaparser.ast.expr.StringLiteralExpr
import com.github.javaparser.ast.stmt.ExpressionStmt
import com.github.javaparser.ast.stmt.ReturnStmt
import com.github.javaparser.ast.type.ClassOrInterfaceType
import com.google.common.base.CaseFormat
import dev.vexide.hydrozoa.plugin.bindings.SdkItem
import dev.vexide.hydrozoa.plugin.bindings.SdkModule
import dev.vexide.hydrozoa.plugin.bindings.nodeListOf

class JavaSdkItem(val sdk: SdkItem, val module: JavaSdkModule, val methodName: String = sdk.name) {
    fun generate(bindingsClass: ClassOrInterfaceDeclaration) {
        val rawMethod = bindingsClass
            .addMethod(sdk.name + "_raw", Modifier.Keyword.PRIVATE, Modifier.Keyword.STATIC, Modifier.Keyword.NATIVE)
            .setType(module.javaTypeFor(sdk.returns, useRawTypes = true, annotations = true))
            .removeBody()

        rawMethod.addAndGetAnnotation("org.teavm.interop.Import")
            .addPair("module", StringLiteralExpr(module.sdk.name))
            .addPair("name", StringLiteralExpr(sdk.name))

        generateParams(sdk.params, rawMethod, true)

        val method = bindingsClass
            .addMethod(methodName, Modifier.Keyword.PUBLIC, Modifier.Keyword.STATIC)
            .setType(module.javaTypeFor(sdk.returns, annotations = true))
            .setJavadocComment(sdk.name)

        generateParams(sdk.params, method)
        generateBody(method, rawMethod)
    }

    private fun generateParams(
        params: Iterable<SdkItem.Param>,
        method: MethodDeclaration,
        useUnderlyingTypes: Boolean = false
    ) {
        for (param in params) {
            val paramType = module.javaTypeFor(param.type, useUnderlyingTypes)
            method.addAndGetParameter(paramType, param.name)
        }
    }

    private fun generateBody(method: MethodDeclaration, rawMethod: MethodDeclaration) {
        val body = method.createBody()

        val nativeMethodCall = MethodCallExpr(null, rawMethod.name)
        for (param in sdk.params) {
            var arg: Expression = NameExpr(param.name)

            if (module.hasRawType(param.type)) {
                arg = MethodCallExpr(arg, "getRawValue")
            }

            nativeMethodCall.addArgument(arg)
        }

        var expr: Expression = nativeMethodCall

        if (sdk.returns != null && module.hasRawType(sdk.returns)) {
            val wrapper = module.javaTypeFor(sdk.returns) as ClassOrInterfaceType
            expr = ObjectCreationExpr(null, wrapper, nodeListOf(nativeMethodCall))
        }

        body.addStatement(
            if (method.type.isVoidType) {
                ExpressionStmt(expr)
            } else {
                ReturnStmt(expr)
            }
        )
    }

    companion object {
        private var actionVerbs =
            setOf("get", "set", "request", "update", "reset", "fill", "draw", "clear", "disable", "enable")

        fun generateMethodName(item: SdkItem, module: SdkModule): Pair<String, String> {
            val nameComponents = ArrayDeque(
                CaseFormat.LOWER_CAMEL
                    .to(CaseFormat.LOWER_UNDERSCORE, item.name)
                    .removePrefix(module.name + "_")
                    .split('_')
            )

            val moduleName = nameComponents.removeFirst()

            // Move verbs to front of method name
            val lastComponent = nameComponents.lastOrNull()
            if (actionVerbs.contains(lastComponent)) {
                nameComponents.add(0, nameComponents.removeLast())
            }

            val submoduleName = CaseFormat.LOWER_UNDERSCORE
                .to(CaseFormat.UPPER_CAMEL, moduleName)
            val trimmedMethodName = CaseFormat.LOWER_UNDERSCORE
                .to(CaseFormat.LOWER_CAMEL, nameComponents.joinToString("_"))

            return Pair(submoduleName, trimmedMethodName)
        }
    }
}