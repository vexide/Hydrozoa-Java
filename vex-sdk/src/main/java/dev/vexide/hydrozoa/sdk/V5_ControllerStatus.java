package dev.vexide.hydrozoa.sdk;

@SuppressWarnings("HungarianNotationConstants")
public record V5_ControllerStatus(byte value) {
    public static final V5_ControllerStatus kV5ControllerOffline = new V5_ControllerStatus((byte) 0);
    public static final V5_ControllerStatus kV5ControllerTethered = new V5_ControllerStatus((byte) 1);
    public static final V5_ControllerStatus kV5ControllerVexnet = new V5_ControllerStatus((byte) 2);
}
