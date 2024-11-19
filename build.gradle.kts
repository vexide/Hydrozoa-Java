plugins {
    `java-library`
    `maven-publish`
    id("org.teavm.library").version("0.10.2")
    id("dev.vexide.hydrozoa").apply(false)
}

group = "dev.vexide"
version = "0.1.0"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            pom {
                name = "Hydrozoa"
                description = "Create VEX V5 robot programs in Java and Kotlin"
                inceptionYear = "2024"
                url = "https://vexide.dev"
                licenses {
                    license {
                        name = "The MIT License"
                        url = "https://opensource.org/licenses/MIT"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/vexide/Hydrozoa-Java.git"
                    developerConnection = "scm:git:ssh://github.com/vexide/Hydrozoa-Java.git"
                    url = "https://github.com/vexide/Hydrozoa-Java"
                }
            }
            from(components["java"])
        }
    }
}

allprojects {
    plugins.apply("maven-publish")
    plugins.apply("java")
    publishing {
        repositories {
            maven {
                name = "localMavenRepository"
                url = project.rootProject.uri("../local-maven-repository")
            }
        }
    }

    java {
        withSourcesJar()
        withJavadocJar()

        sourceCompatibility = JavaVersion.VERSION_21
    }

    repositories {
        mavenCentral()
    }
}

subprojects {
    group = project.rootProject.group
    version = project.rootProject.version
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.0")

    implementation(project(":vex-sdk"))
}
