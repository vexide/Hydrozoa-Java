package dev.vexide.hydrozoa.sdk;

@SuppressWarnings("HungarianNotationConstants")
public record V5_DeviceType(byte value) {
    public static final byte kDeviceTypeNoSensor = 0;
    public static final V5_DeviceType NoSensor = new V5_DeviceType(kDeviceTypeNoSensor);

    public static final byte kDeviceTypeMotorSensor = 2;
    public static final V5_DeviceType MotorSensor = new V5_DeviceType(kDeviceTypeMotorSensor);

    public static final byte kDeviceTypeLedSensor = 3;
    public static final V5_DeviceType LedSensor = new V5_DeviceType(kDeviceTypeLedSensor);

    public static final byte kDeviceTypeAbsEncSensor = 4;
    public static final V5_DeviceType AbsEncSensor = new V5_DeviceType(kDeviceTypeAbsEncSensor);

    public static final byte kDeviceTypeCrMotorSensor = 5;
    public static final V5_DeviceType CrMotorSensor = new V5_DeviceType(kDeviceTypeCrMotorSensor);

    public static final byte kDeviceTypeImuSensor = 6;
    public static final V5_DeviceType ImuSensor = new V5_DeviceType(kDeviceTypeImuSensor);

    public static final byte kDeviceTypeDistanceSensor = 7;
    public static final V5_DeviceType DistanceSensor = new V5_DeviceType(kDeviceTypeDistanceSensor);

    public static final byte kDeviceTypeRadioSensor = 8;
    public static final V5_DeviceType RadioSensor = new V5_DeviceType(kDeviceTypeRadioSensor);

    public static final byte kDeviceTypeTetherSensor = 9;
    public static final V5_DeviceType TetherSensor = new V5_DeviceType(kDeviceTypeTetherSensor);

    public static final byte kDeviceTypeBrainSensor = 10;
    public static final V5_DeviceType BrainSensor = new V5_DeviceType(kDeviceTypeBrainSensor);

    public static final byte kDeviceTypeVisionSensor = 11;
    public static final V5_DeviceType VisionSensor = new V5_DeviceType(kDeviceTypeVisionSensor);

    public static final byte kDeviceTypeAdiSensor = 12;
    public static final V5_DeviceType AdiSensor = new V5_DeviceType(kDeviceTypeAdiSensor);

    public static final byte kDeviceTypeRes1Sensor = 13;
    public static final V5_DeviceType Res1Sensor = new V5_DeviceType(kDeviceTypeRes1Sensor);

    public static final byte kDeviceTypeRes2Sensor = 14;
    public static final V5_DeviceType Res2Sensor = new V5_DeviceType(kDeviceTypeRes2Sensor);

    public static final byte kDeviceTypeRes3Sensor = 15;
    public static final V5_DeviceType Res3Sensor = new V5_DeviceType(kDeviceTypeRes3Sensor);

    public static final byte kDeviceTypeOpticalSensor = 16;
    public static final V5_DeviceType OpticalSensor = new V5_DeviceType(kDeviceTypeOpticalSensor);

    public static final byte kDeviceTypeMagnetSensor = 17;
    public static final V5_DeviceType MagnetSensor = new V5_DeviceType(kDeviceTypeMagnetSensor);

    public static final byte kDeviceTypeGpsSensor = 20;
    public static final V5_DeviceType GpsSensor = new V5_DeviceType(kDeviceTypeGpsSensor);

    public static final byte kDeviceTypeAicameraSensor = 26;
    public static final V5_DeviceType AicameraSensor = new V5_DeviceType(kDeviceTypeAicameraSensor);

    public static final byte kDeviceTypeLightTowerSensor = 27;
    public static final V5_DeviceType LightTowerSensor = new V5_DeviceType(kDeviceTypeLightTowerSensor);

    public static final byte kDeviceTypeArmDevice = 28;
    public static final V5_DeviceType ArmDevice = new V5_DeviceType(kDeviceTypeArmDevice);

    public static final byte kDeviceTypeAiVisionSensor = 29;
    public static final V5_DeviceType AiVisionSensor = new V5_DeviceType(kDeviceTypeAiVisionSensor);

    public static final byte kDeviceTypePneumaticSensor = 30;
    public static final V5_DeviceType PneumaticSensor = new V5_DeviceType(kDeviceTypePneumaticSensor);

    public static final byte kDeviceTypeBumperSensor = 0x40;
    public static final V5_DeviceType BumperSensor = new V5_DeviceType(kDeviceTypeBumperSensor);

    public static final byte kDeviceTypeGyroSensor = 0x46;
    public static final V5_DeviceType GyroSensor = new V5_DeviceType(kDeviceTypeGyroSensor);

    public static final byte kDeviceTypeSonarSensor = 0x47;
    public static final V5_DeviceType SonarSensor = new V5_DeviceType(kDeviceTypeSonarSensor);

    public static final byte kDeviceTypeGenericSensor = (byte) 128;
    public static final V5_DeviceType GenericSensor = new V5_DeviceType(kDeviceTypeGenericSensor);

    public static final byte kDeviceTypeGenericSerial = (byte) 129;
    public static final V5_DeviceType GenericSerial = new V5_DeviceType(kDeviceTypeGenericSerial);

    public static final byte kDeviceTypeUndefinedSensor = (byte) 255;
    public static final V5_DeviceType UndefinedSensor = new V5_DeviceType(kDeviceTypeUndefinedSensor);

}
