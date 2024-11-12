plugins {
    id("java")
    id("org.teavm").version("0.10.2")
}

group = "dev.vexide"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

teavm.wasm {
    addedToWebApp = true
    mainClass = "dev.vexide.Main"
    targetFileName = "robot.wasm"
}
tasks.assemble.get().dependsOn(tasks.generateWasm)

// make an upload task that builds and then runs  multiv upload ./build/generated/teavm/robot.wasm --slot 1 --runtime ./runtime.bin
// relative to the project root
tasks.register("upload", Exec::class) {
    dependsOn("assemble")
    commandLine("multiv", "upload", "./build/generated/teavm/wasm/robot.wasm", "--slot", "1", "--runtime", "./runtime.bin")
}
