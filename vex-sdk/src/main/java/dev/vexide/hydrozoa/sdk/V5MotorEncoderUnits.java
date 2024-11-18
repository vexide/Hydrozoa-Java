package dev.vexide.hydrozoa.sdk;

@SuppressWarnings("HungarianNotationConstants")
public record V5MotorEncoderUnits(byte value) {
    public static final byte kMotorEncoderDegrees = 0;
    public static final V5MotorEncoderUnits Degrees = new V5MotorEncoderUnits(kMotorEncoderDegrees);

    public static final byte kMotorEncoderRotations = 1;
    public static final V5MotorEncoderUnits Rotations = new V5MotorEncoderUnits(kMotorEncoderRotations);

    public static final byte kMotorEncoderCounts = 2;
    public static final V5MotorEncoderUnits Counts = new V5MotorEncoderUnits(kMotorEncoderCounts);
}
