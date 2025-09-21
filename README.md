# Hydrozoa Java SDK

> Create VEX V5 robot programs in Java and Kotlin

## About

The Hydrozoa Java SDK is a software library that allows Java and Kotlin programs to access the peripherals on VEX V5
robots. It
also includes a Gradle plugin to make it easier to deploy Hydrozoa programs to robots.

## Running Examples

You'll need [Hydrozoa CLI](https://github.com/vexide/hydrozoa-cli) installed.

Run the following command with a VEX V5 robot connected via USB.

```shell
./gradlew examples:clawbot:upload
```

Other examples can be run similarly:

```shell
./gradlew examples:clawbot-kotlin:upload
```

If you want to make your own Hydrozoa Java project, use this [template](https://github.com/vexide/hydrozoa-java-template).
