plugins {
    `kotlin-dsl`
    alias(libs.plugins.publish)
}

group = "dev.vexide"
version = "0.1.0-alpha.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.teavm:org.teavm.gradle.plugin:0.10.2")
}

gradlePlugin {
    website = "https://github.com/vexide/Hydrozoa-Java/tree/main/gradle-plugin"
    vcsUrl = "https://github.com/vexide/Hydrozoa-Java.git"
    plugins {
        matching { it.name == "dev.vexide.hydrozoa" }.configureEach {
            id = "dev.vexide.hydrozoa"
            displayName = "Hydrozoa Gradle plugin"
            description = "Build & upload Gradle projects to VEX V5 robots"
            tags = listOf("vex", "robot", "robotics", "v5", "vexide", "hydrozoa")
        }
    }
}

publishing {
    repositories {
        maven {
            name = "staging"
            url = uri(layout.buildDirectory.dir("staging-deploy"))
        }
    }
}
