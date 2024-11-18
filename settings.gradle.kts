rootProject.name = "vex-java-project"

includeBuild("gradle-plugin")

include("vex-sdk")

include("examples:clawbot")
findProject(":examples:clawbot")?.name = "clawbot"
