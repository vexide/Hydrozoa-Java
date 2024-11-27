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
     * Creates a new generic smart device.
     *
     * @param port the smart port which this device is connected to
     */
    public SmartDevice(@NotNull SmartPort port) {
        this.port = port;
        handle = port.deviceHandle();
    }

    /**
     * Gets the port number of this smart device.
     *
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
     *
     * @return the device type
     */
    public abstract @NotNull Type getDeviceType();

    /**
     * Checks if this device is connected to its smart port.
     * <p>
     * A device is considered connected if the {@link #getDeviceType expected} device type matches the actual device
     * type.
     *
     * @return {@code true} if the device is connected, {@code false} otherwise
     */
    public boolean isConnected() {
        return port.deviceType().equals(Optional.of(getDeviceType()));
    }

    /**
     * Validates that the correct device is physically connected to this device's port.
     *
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
        MOTOR(V5_DeviceType.MotorSensor),
        /**
         * A VEX Rotation Sensor.
         */
        ROTATION(V5_DeviceType.AbsEncSensor),
        /**
         * A VEX Inertial Sensor.
         */
        IMU(V5_DeviceType.ImuSensor),
        /**
         * A VEX Distance Sensor.
         */
        DISTANCE(V5_DeviceType.DistanceSensor),
        /**
         * A VEX Vision Sensor.
         */
        VISION(V5_DeviceType.VisionSensor),
        /**
         * A VEX AI Vision Sensor.
         */
        AI_VISION(V5_DeviceType.AiVisionSensor),
        /**
         * A VEX Electromagnet.
         */
        ELECTROMAGNET(V5_DeviceType.MagnetSensor),
        /**
         * A VEX CTE Workcell Signal Tower.
         */
        LIGHT_TOWER(V5_DeviceType.LightTowerSensor),
        /**
         * A VEX CTE Workcell 6-Axis Arm.
         */
        ARM(V5_DeviceType.ArmDevice),
        /**
         * A VEX Optical Sensor.
         */
        OPTICAL(V5_DeviceType.OpticalSensor),
        /**
         * A VEX V5 Game Positioning System Sensor.
         */
        GPS(V5_DeviceType.GpsSensor),
        /**
         * A VEX V5 Robot Radio.
         */
        RADIO(V5_DeviceType.RadioSensor),
        /**
         * A VEX Analog/Digital Input Sensor.
         */
        ADI(V5_DeviceType.AdiSensor),
        /**
         * A generic, unbranded serial device.
         */
        GENERIC_SERIAL(V5_DeviceType.GenericSerial),
        /**
         * An unknown device type.
         */
        UNKNOWN(V5_DeviceType.UndefinedSensor);

        private final V5_DeviceType raw;

        Type(V5_DeviceType raw) {
            this.raw = raw;
        }

        /**
         * Converts a numeric raw V5 device type to a high-level device type.
         *
         * @param raw the raw device type
         * @return the high-level device type, or an empty optional if the raw type is not recognized
         */
        public static Optional<Type> fromRaw(@NotNull V5_DeviceType raw) {
            return switch (raw.value()) {
                case V5_DeviceType.kDeviceTypeNoSensor -> Optional.empty();
                case V5_DeviceType.kDeviceTypeMotorSensor -> Optional.of(MOTOR);
                case V5_DeviceType.kDeviceTypeAbsEncSensor -> Optional.of(ROTATION);
                case V5_DeviceType.kDeviceTypeImuSensor -> Optional.of(IMU);
                case V5_DeviceType.kDeviceTypeDistanceSensor -> Optional.of(DISTANCE);
                case V5_DeviceType.kDeviceTypeVisionSensor -> Optional.of(VISION);
                case V5_DeviceType.kDeviceTypeAiVisionSensor -> Optional.of(AI_VISION);
                case V5_DeviceType.kDeviceTypeMagnetSensor -> Optional.of(ELECTROMAGNET);
                case V5_DeviceType.kDeviceTypeLightTowerSensor -> Optional.of(LIGHT_TOWER);
                case V5_DeviceType.kDeviceTypeArmDevice -> Optional.of(ARM);
                case V5_DeviceType.kDeviceTypeOpticalSensor -> Optional.of(OPTICAL);
                case V5_DeviceType.kDeviceTypeGpsSensor -> Optional.of(GPS);
                case V5_DeviceType.kDeviceTypeRadioSensor -> Optional.of(RADIO);
                case V5_DeviceType.kDeviceTypeAdiSensor -> Optional.of(ADI);
                case V5_DeviceType.kDeviceTypeGenericSerial -> Optional.of(GENERIC_SERIAL);
                default -> Optional.of(UNKNOWN);
            };
        }

        /**
         * Gets the internal representation of the smart device type.
         *
         * @return the raw device type
         */
        public V5_DeviceType getRaw() {
            return raw;
        }
    }
}
