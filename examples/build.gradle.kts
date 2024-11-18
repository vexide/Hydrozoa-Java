subprojects {
    dependencies {
        // Add Hydrozoa SDK from project root
        implementation(project(":"))
    }

    hydrozoa {
        runtime = project.parent!!.parent!!.layout.projectDirectory.file("runtime.bin")
    }
}
