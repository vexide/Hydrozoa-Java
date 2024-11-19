# Hydrozoa Gradle Plugin

The Hydrozoa Gradle plugin automatically manages the `hydrozoa` CLI command to make it easy to upload Gradle projects
to the VEX V5 brain.

## Installation

To use the plugin, first install the command line tool:

```shell
cargo install --git https://github.com/vexide/hydrozoa-cli.git
```

Then add the plugin to `build.gradle.kts`:

```kt
plugins {
    id("dev.vexide.hydrozoa")
}

hydrozoa {
    entrypoint = "com.vrcteam1234.vrc2024.Main"
}
```

Copy the runtime (`hydrozoa.bin`) from the repository root to your project root.
You can also build it manually at <https://github.com/vexide/hydrozoa>.

## Usage

The Hydrozoa plugin adds an `upload` task you can use to upload your robot code to the VEX V5 robot.

Connect to the robot via a USB cable, then run:

```shell
./gradlew upload
```
