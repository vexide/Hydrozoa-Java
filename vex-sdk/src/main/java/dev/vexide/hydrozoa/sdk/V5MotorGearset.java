package dev.vexide.hydrozoa.sdk;

@SuppressWarnings("HungarianNotationConstants")
public record V5MotorGearset(byte value) {
    public static final byte kMotorGearSet_36 = 0;
    public static final byte kMotorGearSet_18 = 1;
    public static final byte kMotorGearSet_06 = 2;
    public static final V5MotorGearset GearSet_36 = new V5MotorGearset(kMotorGearSet_36);
    public static final V5MotorGearset GearSet_18 = new V5MotorGearset(kMotorGearSet_18);
    public static final V5MotorGearset GearSet_06 = new V5MotorGearset(kMotorGearSet_06);
}
