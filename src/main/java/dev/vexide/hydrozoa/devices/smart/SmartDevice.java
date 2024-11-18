package dev.vexide.hydrozoa.devices.smart;

import dev.vexide.hydrozoa.devices.DeviceException;
import dev.vexide.hydrozoa.sdk.V5_Device;
import dev.vexide.hydrozoa.sdk.V5_DeviceType;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Optional;

public abstract class SmartDevice {
    public static final Duration UPDATE_INTERVAL = Duration.ofMillis(10);

    final @NotNull SmartPort port;
    final @NotNull V5_Device handle;

    public SmartDevice(@NotNull SmartPort port) {
        this.port = port;
        handle = port.deviceHandle();
    }

    public int getPortNumber() {
        return port.getNumber();
    }

    public int getPortIndex() {
        return port.getIndex();
    }

    public abstract @NotNull Type getDeviceType();

    public boolean isConnected() {
        return port.deviceType().equals(Optional.of(getDeviceType()));
    }

    public void validateConnection() throws DeviceException {
        port.validateType(getDeviceType());
    }

    public enum Type {
        Motor(V5_DeviceType.MotorSensor),
        Rotation(V5_DeviceType.AbsEncSensor),
        Imu(V5_DeviceType.ImuSensor),
        Distance(V5_DeviceType.DistanceSensor),
        Vision(V5_DeviceType.VisionSensor),
        AiVision(V5_DeviceType.AiVisionSensor),
        Electromagnet(V5_DeviceType.MagnetSensor),
        LightTower(V5_DeviceType.LightTowerSensor),
        Arm(V5_DeviceType.ArmDevice),
        Optical(V5_DeviceType.OpticalSensor),
        Gps(V5_DeviceType.GpsSensor),
        Radio(V5_DeviceType.RadioSensor),
        Adi(V5_DeviceType.AdiSensor),
        GenericSerial(V5_DeviceType.GenericSerial),
        Unknown(V5_DeviceType.UndefinedSensor);

        private final V5_DeviceType raw;

        Type(V5_DeviceType raw) {
            this.raw = raw;
        }

        public static Optional<Type> fromRaw(@NotNull V5_DeviceType raw) {
            return switch (raw.value()) {
                case V5_DeviceType.kDeviceTypeNoSensor -> Optional.empty();
                case V5_DeviceType.kDeviceTypeMotorSensor -> Optional.of(Motor);
                case V5_DeviceType.kDeviceTypeAbsEncSensor -> Optional.of(Rotation);
                case V5_DeviceType.kDeviceTypeImuSensor -> Optional.of(Imu);
                case V5_DeviceType.kDeviceTypeDistanceSensor -> Optional.of(Distance);
                case V5_DeviceType.kDeviceTypeVisionSensor -> Optional.of(Vision);
                case V5_DeviceType.kDeviceTypeAiVisionSensor -> Optional.of(AiVision);
                case V5_DeviceType.kDeviceTypeMagnetSensor -> Optional.of(Electromagnet);
                case V5_DeviceType.kDeviceTypeLightTowerSensor -> Optional.of(LightTower);
                case V5_DeviceType.kDeviceTypeArmDevice -> Optional.of(Arm);
                case V5_DeviceType.kDeviceTypeOpticalSensor -> Optional.of(Optical);
                case V5_DeviceType.kDeviceTypeGpsSensor -> Optional.of(Gps);
                case V5_DeviceType.kDeviceTypeRadioSensor -> Optional.of(Radio);
                case V5_DeviceType.kDeviceTypeAdiSensor -> Optional.of(Adi);
                case V5_DeviceType.kDeviceTypeGenericSerial -> Optional.of(GenericSerial);
                default -> Optional.of(Unknown);
            };
        }

        public V5_DeviceType getRaw() {
            return raw;
        }
    }
}
