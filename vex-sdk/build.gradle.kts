plugins {
    `java-library`
    `maven-publish`
    id("org.teavm.library")
}


tasks.jar {
    manifest {
        attributes("Automatic-Module-Name" to "dev.vexide.hydrozoa.sdk")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJavaVexSdk") {
            pom {
                name = "Hydrozoa VEX SDK"
                description = "Access low-level VEX V5 hardware features"
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


dependencies {
    implementation("org.jetbrains:annotations:24.0.0")

    implementation(teavm.libs.interop)
}
