package dev.vexide.hydrozoa.sdk;

@SuppressWarnings("HungarianNotationConstants")
public record V5_ControllerId(byte value) {
    public static final V5_ControllerId kControllerMaster = new V5_ControllerId((byte) 0);
    public static final V5_ControllerId kControllerPartner = new V5_ControllerId((byte) 1);
}
