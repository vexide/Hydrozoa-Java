package dev.vexide.hydrozoa.sdk;

public record V5_ControllerIndex(byte value) {
    public static final V5_ControllerIndex AnaLeftX = new V5_ControllerIndex((byte) 0);
    public static final V5_ControllerIndex AnaLeftY = new V5_ControllerIndex((byte) 1);
    public static final V5_ControllerIndex AnaRightX = new V5_ControllerIndex((byte) 2);
    public static final V5_ControllerIndex AnaRightY = new V5_ControllerIndex((byte) 3);
    public static final V5_ControllerIndex AnaSpare1 = new V5_ControllerIndex((byte) 4);
    public static final V5_ControllerIndex AnaSpare2 = new V5_ControllerIndex((byte) 5);
    public static final V5_ControllerIndex Button5U = new V5_ControllerIndex((byte) 6);
    public static final V5_ControllerIndex Button5D = new V5_ControllerIndex((byte) 7);
    public static final V5_ControllerIndex Button6U = new V5_ControllerIndex((byte) 8);
    public static final V5_ControllerIndex Button6D = new V5_ControllerIndex((byte) 9);
    public static final V5_ControllerIndex Button7U = new V5_ControllerIndex((byte) 10);
    public static final V5_ControllerIndex Button7D = new V5_ControllerIndex((byte) 11);
    public static final V5_ControllerIndex Button7L = new V5_ControllerIndex((byte) 12);
    public static final V5_ControllerIndex Button7R = new V5_ControllerIndex((byte) 13);
    public static final V5_ControllerIndex Button8U = new V5_ControllerIndex((byte) 14);
    public static final V5_ControllerIndex Button8D = new V5_ControllerIndex((byte) 15);
    public static final V5_ControllerIndex Button8L = new V5_ControllerIndex((byte) 16);
    public static final V5_ControllerIndex Button8R = new V5_ControllerIndex((byte) 17);
    public static final V5_ControllerIndex ButtonSEL = new V5_ControllerIndex((byte) 18);
    public static final V5_ControllerIndex BatteryLevel = new V5_ControllerIndex((byte) 19);
    public static final V5_ControllerIndex ButtonAll = new V5_ControllerIndex((byte) 20);
    public static final V5_ControllerIndex Flags = new V5_ControllerIndex((byte) 21);
    public static final V5_ControllerIndex BatteryCapacity = new V5_ControllerIndex((byte) 22);

    public static final V5_ControllerIndex Axis1 = AnaRightX;
    public static final V5_ControllerIndex Axis2 = AnaRightY;
    public static final V5_ControllerIndex Axis3 = AnaLeftY;
    public static final V5_ControllerIndex Axis4 = AnaLeftX;
    public static final V5_ControllerIndex ButtonL1 = Button5U;
    public static final V5_ControllerIndex ButtonL2 = Button5D;
    public static final V5_ControllerIndex ButtonR1 = Button6U;
    public static final V5_ControllerIndex ButtonR2 = Button6D;
    public static final V5_ControllerIndex ButtonUp = Button7U;
    public static final V5_ControllerIndex ButtonDown = Button7D;
    public static final V5_ControllerIndex ButtonLeft = Button7L;
    public static final V5_ControllerIndex ButtonRight = Button7R;
    public static final V5_ControllerIndex ButtonX = Button8U;
    public static final V5_ControllerIndex ButtonB = Button8D;
    public static final V5_ControllerIndex ButtonY = Button8L;
    public static final V5_ControllerIndex ButtonA = Button8R;
}
