plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "hydrozoa"

includeBuild("gradle-plugin")
includeBuild("bindings-generator")

include("vex-sdk")
findProject("vex-sdk")?.name = "vex-sdk"

val examples = file("examples").listFiles()
for (exampleFile in examples) {
    includeBuild(exampleFile) {
        name = "examples.${exampleFile.name}"
        dependencySubstitution {
            substitute(module("dev.vexide.hydrozoa:hydrozoa")).using(project(":"))
        }
    }
}
