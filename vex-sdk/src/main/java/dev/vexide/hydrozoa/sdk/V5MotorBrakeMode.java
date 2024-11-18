package dev.vexide.hydrozoa.sdk;

@SuppressWarnings("HungarianNotationConstants")
public record V5MotorBrakeMode(byte value) {
    public static final byte kV5MotorBrakeModeCoast = 0;
    public static final V5MotorBrakeMode Coast = new V5MotorBrakeMode(kV5MotorBrakeModeCoast);

    public static final byte kV5MotorBrakeModeBrake = 1;
    public static final V5MotorBrakeMode Brake = new V5MotorBrakeMode(kV5MotorBrakeModeBrake);

    public static final byte kV5MotorBrakeModeHold = 2;
    public static final V5MotorBrakeMode Hold = new V5MotorBrakeMode(kV5MotorBrakeModeHold);
}
