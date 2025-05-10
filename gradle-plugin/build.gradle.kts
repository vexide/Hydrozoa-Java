plugins {
    `kotlin-dsl`
    id("com.gradle.plugin-publish") version "1.2.1"
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
        create("hydrozoaPlugin") {
            id = "dev.vexide.hydrozoa"
            displayName = "Hydrozoa Gradle plugin"
            implementationClass = "dev.vexide.hydrozoa.gradle.HydrozoaPlugin"
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
