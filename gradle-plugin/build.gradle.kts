plugins {
    `kotlin-dsl`
}

group = "dev.vexide"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.teavm:org.teavm.gradle.plugin:0.10.2")
}

gradlePlugin {
    plugins {
        create("HydrozoaPlugin") {
            id = "hydrozoa"
            implementationClass = "dev.vexide.hydrozoa.HydrozoaPlugin"
        }
    }
}
