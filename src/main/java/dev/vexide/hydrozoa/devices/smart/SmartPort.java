package dev.vexide.hydrozoa.devices.smart;

import dev.vexide.hydrozoa.DeviceException;
import dev.vexide.hydrozoa.Peripherals;
import dev.vexide.hydrozoa.devices.DeviceDisconnectedException;
import dev.vexide.hydrozoa.devices.IncorrectDeviceException;
import dev.vexide.hydrozoa.sdk.V5_Device;
import dev.vexide.hydrozoa.sdk.V5_DeviceType;
import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

/**
 * A Smart Port on the VEX V5 Robot Brain.
 */
public final class SmartPort {
    private final int number;

    /**
     * Creates a new Smart Port.
     *
     * @param ignoredKey the peripherals key, which may only be accessed from within the {@link Peripherals} class
     * @param number     the port number of the smart port
     * @see Peripherals#takePort(int)
     */
    @ApiStatus.Internal
    public SmartPort(Peripherals.Key ignoredKey, int number) {
        this.number = number;
    }

    /**
     * Gets the port number of this smart port.
     * @return the port number, as labeled on the robot brain
     */
    @Contract(pure = true)
    public int getNumber() {
        return number;
    }

    /**
     * Gets the zero-indexed port number of this smart port.
     * @return the zero-indexed port number
     */
    @Contract(pure = true)
    public int getIndex() {
        return number - 1;
    }

    /**
     * Gets the underlying device handle for this smart port so that it can be passed to the {@link VexSdk}.
     * @return the device's SDK handle
     */
    @Contract(" -> new")
    @NotNull
    V5_Device deviceHandle() {
        return VexSdk.Device.vexDeviceGetByIndex(getIndex());
    }

    /**
     * Gets the type of device currently physically connected to this smart port.
     * @return the device's type, if a device is connected
     */
    public Optional<SmartDevice.Type> deviceType() {
        var deviceTypes = new byte[VexSdk.Device.V5_MAX_DEVICE_PORTS];
        VexSdk.Device.vexDeviceGetStatus(deviceTypes);

        return SmartDevice.Type.fromRaw(new V5_DeviceType(deviceTypes[getIndex()]));
    }

    /**
     * Validates that the device physically connected to this port is of the expected type.
     * @param expected the expected device type
     * @throws DeviceException if the device is not connected or the wrong device is connected
     */
    public void validateType(SmartDevice.Type expected) throws DeviceException {
        var type = deviceType().orElseThrow(() -> new DeviceDisconnectedException(number, expected));
        if (!type.equals(expected)) throw new IncorrectDeviceException(number, expected, type);
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SmartPort) obj;
        return this.number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "SmartPort[" +
                "number=" + number + ']';
    }

}
