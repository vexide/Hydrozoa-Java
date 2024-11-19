plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "hydrozoa"

includeBuild("gradle-plugin")

include("vex-sdk")
findProject("vex-sdk")?.name = "vex-sdk"

include("examples:clawbot")
findProject(":examples:clawbot")?.name = "clawbot"
include("examples:clawbot-kotlin")
findProject(":examples:clawbot-kotlin")?.name = "clawbot-kotlin"
