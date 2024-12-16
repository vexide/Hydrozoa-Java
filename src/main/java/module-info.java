/**
 * The Hydrozoa Java SDK is a software library that allows Java and Kotlin programs to control VEX V5 robots.
 */
module dev.vexide.hydrozoa {
    requires org.jetbrains.annotations;
    requires dev.vexide.hydrozoa.sdk;
    requires measured.jvm;

    exports dev.vexide.hydrozoa;
    exports dev.vexide.hydrozoa.display;
    exports dev.vexide.hydrozoa.devices.smart;
    exports dev.vexide.hydrozoa.devices;
}
