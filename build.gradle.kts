plugins {
    `java-library`
    `maven-publish`
    idea

    id("org.teavm.library").version("0.10.2")
    id("dev.vexide.hydrozoa").apply(false)
    id("org.jreleaser") version "1.15.0"
}

group = "dev.vexide"
version = "0.1.0-alpha.2"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = "hydrozoa"
            pom {
                name = "Hydrozoa"
                description = "Create VEX V5 robot programs in Java and Kotlin"
                inceptionYear = "2024"
                url = "https://vexide.dev"
                developers {
                    developer {
                        id = "doinkythederp"
                        email = "doinkythederp@icloud.com"
                        organization = "vexide"
                    }
                }
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
                url = uri(rootProject.layout.buildDirectory.dir("staging-deploy"))
            }
        }
    }

    java {
        withSourcesJar()
        withJavadocJar()

        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    repositories {
        mavenCentral()
    }

    tasks.javadoc {
        source = sourceSets.main.get().allJava
        options
            .windowTitle("Hydrozoa Docs")
            .stylesheetFile(rootProject.layout.projectDirectory.file("docs.css").asFile)
    }
}

subprojects {
    group = project.rootProject.group
    version = project.rootProject.version
}

jreleaser {
    signing {
        setActive("ALWAYS")
        armored = true
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    setActive("ALWAYS")
                    url = "https://central.sonatype.com/api/v1/publisher"
                    stagingRepository("build/staging-deploy")
                }
            }
        }
    }
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.0.0")

    implementation(project(":vex-sdk"))
}

tasks.javadoc {
    title = "Hydrozoa"
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}
