package dev.vexide.hydrozoa.devices.smart;

import dev.vexide.hydrozoa.DeviceException;
import dev.vexide.hydrozoa.sdk.VexDevice;
import dev.vexide.hydrozoa.sdk.VexDeviceType;
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

    /**
     * Creates a new generic smart device.
     *
     * @param port The smart port which this device is connected to. The port will be consumed by this device and may
     *             not be reused.
     */
    public SmartDevice(@NotNull SmartPort port) {
        this.port = port.take();
    }

    /**
     * Remove the smart port handle from this smart device, invalidating this device.
     *
     * @return a new smart port with the same port number as this device
     */
    public @NotNull SmartPort takePort() {
        return port.take();
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
        MOTOR(VexDeviceType.MOTOR_SENSOR),
        /**
         * A VEX Rotation Sensor.
         */
        ROTATION(VexDeviceType.ABS_ENC_SENSOR),
        /**
         * A VEX Inertial Sensor.
         */
        IMU(VexDeviceType.IMU_SENSOR),
        /**
         * A VEX Distance Sensor.
         */
        DISTANCE(VexDeviceType.DISTANCE_SENSOR),
        /**
         * A VEX Vision Sensor.
         */
        VISION(VexDeviceType.VISION_SENSOR),
        /**
         * A VEX AI Vision Sensor.
         */
        AI_VISION(VexDeviceType.AI_VISION_SENSOR),
        /**
         * A VEX Electromagnet.
         */
        ELECTROMAGNET(VexDeviceType.MAGNET_SENSOR),
        /**
         * A VEX CTE Workcell Signal Tower.
         */
        LIGHT_TOWER(VexDeviceType.LIGHT_TOWER_SENSOR),
        /**
         * A VEX CTE Workcell 6-Axis Arm.
         */
        ARM(VexDeviceType.ARM_DEVICE),
        /**
         * A VEX Optical Sensor.
         */
        OPTICAL(VexDeviceType.OPTICAL_SENSOR),
        /**
         * A VEX V5 Game Positioning System Sensor.
         */
        GPS(VexDeviceType.GPS_SENSOR),
        /**
         * A VEX V5 Robot Radio.
         */
        RADIO(VexDeviceType.RADIO_SENSOR),
        /**
         * A VEX Analog/Digital Input Sensor.
         */
        ADI(VexDeviceType.ADI_SENSOR),
        /**
         * A generic, unbranded serial device.
         */
        GENERIC_SERIAL(VexDeviceType.GENERIC_SERIAL),
        /**
         * An unknown device type.
         */
        UNKNOWN(VexDeviceType.UNDEFINED_SENSOR);

        private final VexDeviceType raw;

        Type(VexDeviceType raw) {
            this.raw = raw;
        }

        /**
         * Converts a numeric raw V5 device type to a high-level device type.
         *
         * @param raw the raw device type
         * @return the high-level device type, or an empty optional if the raw type is not recognized
         */
        public static Optional<Type> fromRaw(@NotNull VexDeviceType raw) {
            return switch (raw.getRawValue()) {
                case VexDeviceType.kDeviceTypeNoSensor -> Optional.empty();
                case VexDeviceType.kDeviceTypeMotorSensor -> Optional.of(MOTOR);
                case VexDeviceType.kDeviceTypeAbsEncSensor -> Optional.of(ROTATION);
                case VexDeviceType.kDeviceTypeImuSensor -> Optional.of(IMU);
                case VexDeviceType.kDeviceTypeDistanceSensor -> Optional.of(DISTANCE);
                case VexDeviceType.kDeviceTypeVisionSensor -> Optional.of(VISION);
                case VexDeviceType.kDeviceTypeAiVisionSensor -> Optional.of(AI_VISION);
                case VexDeviceType.kDeviceTypeMagnetSensor -> Optional.of(ELECTROMAGNET);
                case VexDeviceType.kDeviceTypeLightTowerSensor -> Optional.of(LIGHT_TOWER);
                case VexDeviceType.kDeviceTypeArmDevice -> Optional.of(ARM);
                case VexDeviceType.kDeviceTypeOpticalSensor -> Optional.of(OPTICAL);
                case VexDeviceType.kDeviceTypeGpsSensor -> Optional.of(GPS);
                case VexDeviceType.kDeviceTypeRadioSensor -> Optional.of(RADIO);
                case VexDeviceType.kDeviceTypeAdiSensor -> Optional.of(ADI);
                case VexDeviceType.kDeviceTypeGenericSerial -> Optional.of(GENERIC_SERIAL);
                default -> Optional.of(UNKNOWN);
            };
        }

        /**
         * Gets the internal representation of the smart device type.
         *
         * @return the raw device type
         */
        public VexDeviceType getRaw() {
            return raw;
        }
    }
}
