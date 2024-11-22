package dev.vexide.hydrozoa.sdk;

@SuppressWarnings("HungarianNotationConstants")
public record V5MotorControlMode(byte value) {
    public static final byte kMotorControlModeOFF = 0;
    public static final V5MotorControlMode OFF = new V5MotorControlMode(kMotorControlModeOFF);
    public static final byte kMotorControlModeBRAKE = 1;
    public static final V5MotorControlMode BRAKE = new V5MotorControlMode(kMotorControlModeBRAKE);
    public static final byte kMotorControlModeHOLD = 2;
    public static final V5MotorControlMode HOLD = new V5MotorControlMode(kMotorControlModeHOLD);
    public static final byte kMotorControlModeSERVO = 3;
    public static final V5MotorControlMode SERVO = new V5MotorControlMode(kMotorControlModeSERVO);
    public static final byte kMotorControlModePROFILE = 4;
    public static final V5MotorControlMode PROFILE = new V5MotorControlMode(kMotorControlModePROFILE);
    public static final byte kMotorControlModeVELOCITY = 5;
    public static final V5MotorControlMode VELOCITY = new V5MotorControlMode(kMotorControlModeVELOCITY);
    public static final byte kMotorControlModeUNDEFINED = 6;
    public static final V5MotorControlMode UNDEFINED = new V5MotorControlMode(kMotorControlModeUNDEFINED);
}
