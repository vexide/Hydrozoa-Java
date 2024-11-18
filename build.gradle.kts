plugins {
    `java-library`
    `maven-publish`
    id("org.teavm.library").version("0.10.2")
    id("hydrozoa").apply(false)
}

group = "dev.vexide"
version = "0.1.0"

subprojects {
    plugins.apply("hydrozoa")
    plugins.apply("java")

    group = project.group
    version = project.version

    repositories {
        mavenCentral()
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.0")

    implementation(project(":vex-sdk"))
}

java {
    withSourcesJar()
    withJavadocJar()

    sourceCompatibility = JavaVersion.VERSION_21
}
