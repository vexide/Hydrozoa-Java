package dev.vexide.hydrozoa.devices.smart;

import dev.vexide.hydrozoa.DeviceException;
import dev.vexide.hydrozoa.sdk.V5_Device;
import dev.vexide.hydrozoa.sdk.V5_DeviceType;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Optional;

/**
 * An abstraction over device access through VEX V5 Smart Ports. This includes motors, many common sensors, VEXlink, and
 * raw serial access.
 */
public abstract class SmartDevice {
    /**
     * The interval at which the device's state may be updated.
     */
    public static final Duration UPDATE_INTERVAL = Duration.ofMillis(10);

    final @NotNull SmartPort port;
    final @NotNull V5_Device handle;

    /**
     * Create a new generic smart device.
     *
     * @param port the smart port which this device is connected to
     */
    public SmartDevice(@NotNull SmartPort port) {
        this.port = port;
        handle = port.deviceHandle();
    }

    /**
     * Gets the port number of this smart device.
     * @return the port number, as labeled on the robot brain
     */
    public int getPortNumber() {
        return port.getNumber();
    }

    /**
     * Gets the port index of this smart device.
     *
     * @return the zero-indexed port number
     */
    public int getPortIndex() {
        return port.getIndex();
    }

    /**
     * Gets the category of smart device this object represents.
     * @return the device type
     */
    public abstract @NotNull Type getDeviceType();

    /**
     * Checks if this device is connected to its smart port.
     * <p>
     * A device is considered connected if the {@link #getDeviceType expected} device type matches the actual device
     * type.
     * @return <code>true</code> if the device is connected, <code>false</code> otherwise
     */
    public boolean isConnected() {
        return port.deviceType().equals(Optional.of(getDeviceType()));
    }

    /**
     * Validates that the correct device is physically connected to this device's port.
     * @throws DeviceException if the device is not connected or the wrong device is connected
     */
    public void validateConnection() throws DeviceException {
        port.validateType(getDeviceType());
    }

    /**
     * A category of smart device that can be connected to the V5 Robot Brain.
     */
    public enum Type {
        /**
         * A VEX V5 Smart Motor.
         */
        Motor(V5_DeviceType.MotorSensor),
        /**
         * A VEX Rotation Sensor.
         */
        Rotation(V5_DeviceType.AbsEncSensor),
        /**
         * A VEX Inertial Sensor.
         */
        Imu(V5_DeviceType.ImuSensor),
        /**
         * A VEX Distance Sensor.
         */
        Distance(V5_DeviceType.DistanceSensor),
        /**
         * A VEX Vision Sensor.
         */
        Vision(V5_DeviceType.VisionSensor),
        /**
         * A VEX AI Vision Sensor.
         */
        AiVision(V5_DeviceType.AiVisionSensor),
        /**
         * A VEX Electromagnet.
         */
        Electromagnet(V5_DeviceType.MagnetSensor),
        /**
         * A VEX CTE Workcell Signal Tower.
         */
        LightTower(V5_DeviceType.LightTowerSensor),
        /**
         * A VEX CTE Workcell 6-Axis Arm.
         */
        Arm(V5_DeviceType.ArmDevice),
        /**
         * A VEX Optical Sensor.
         */
        Optical(V5_DeviceType.OpticalSensor),
        /**
         * A VEX V5 Game Positioning System Sensor.
         */
        Gps(V5_DeviceType.GpsSensor),
        /**
         * A VEX V5 Robot Radio.
         */
        Radio(V5_DeviceType.RadioSensor),
        /**
         * A VEX Analog/Digital Input Sensor.
         */
        Adi(V5_DeviceType.AdiSensor),
        /**
         * A generic, unbranded serial device.
         */
        GenericSerial(V5_DeviceType.GenericSerial),
        /**
         * An unknown device type.
         */
        Unknown(V5_DeviceType.UndefinedSensor);

        private final V5_DeviceType raw;

        Type(V5_DeviceType raw) {
            this.raw = raw;
        }

        /**
         * Converts a numeric raw V5 device type to a high-level device type.
         * @param raw the raw device type
         * @return the high-level device type, or an empty optional if the raw type is not recognized
         */
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

        /**
         * Gets the internal representation of the smart device type.
         * @return the raw device type
         */
        public V5_DeviceType getRaw() {
            return raw;
        }
    }
}
