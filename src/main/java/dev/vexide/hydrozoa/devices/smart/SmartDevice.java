package dev.vexide.hydrozoa.devices.smart;

import dev.vexide.hydrozoa.DeviceException;
import dev.vexide.hydrozoa.sdk.VexSdk;
import dev.vexide.hydrozoa.sdk.VexSdk.Device;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.Arrays;
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
        MOTOR(Device.Type.MOTOR_SENSOR),
        /**
         * A VEX Rotation Sensor.
         */
        ROTATION(Device.Type.ABS_ENC_SENSOR),
        /**
         * A VEX Inertial Sensor.
         */
        IMU(Device.Type.IMU_SENSOR),
        /**
         * A VEX Distance Sensor.
         */
        DISTANCE(Device.Type.DISTANCE_SENSOR),
        /**
         * A VEX Vision Sensor.
         */
        VISION(Device.Type.VISION_SENSOR),
        /**
         * A VEX AI Vision Sensor.
         */
        AI_VISION(Device.Type.AI_VISION_SENSOR),
        /**
         * A VEX Electromagnet.
         */
        ELECTROMAGNET(Device.Type.MAGNET_SENSOR),
        /**
         * A VEX CTE Workcell Signal Tower.
         */
        LIGHT_TOWER(Device.Type.LIGHT_TOWER_SENSOR),
        /**
         * A VEX CTE Workcell 6-Axis Arm.
         */
        ARM(Device.Type.ARM_DEVICE),
        /**
         * A VEX Optical Sensor.
         */
        OPTICAL(Device.Type.OPTICAL_SENSOR),
        /**
         * A VEX V5 Game Positioning System Sensor.
         */
        GPS(Device.Type.GPS_SENSOR),
        /**
         * A VEX V5 Robot Radio.
         */
        RADIO(Device.Type.RADIO_SENSOR),
        /**
         * A VEX Analog/Digital Input Sensor.
         */
        ADI(Device.Type.ADI_SENSOR),
        /**
         * A generic, unbranded serial device.
         */
        GENERIC_SERIAL(Device.Type.GENERIC_SERIAL),
        /**
         * An unknown device type.
         */
        UNKNOWN(Device.Type.UNDEFINED_SENSOR);

        private final Device.Type raw;

        Type(Device.Type raw) {
            this.raw = raw;
        }

        private static final Type[] supportedTypes = values();
        /**
         * Converts a numeric raw V5 device type to a high-level device type.
         *
         * @param raw the raw device type
         * @return the high-level device type, or an empty optional if the raw type is not recognized
         */
        public static Optional<Type> fromRaw(@NotNull Device.Type raw) {
            if (raw.value() == Device.Type.NO_SENSOR.value()) return Optional.empty();
            var search = Arrays.stream(supportedTypes).filter(i -> i.raw.value() == raw.value()).findFirst();
            return Optional.of(search.orElse(UNKNOWN));
        }

        /**
         * Gets the internal representation of the smart device type.
         *
         * @return the raw device type
         */
        public Device.Type getRaw() {
            return raw;
        }
    }
}
