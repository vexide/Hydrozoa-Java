package dev.vexide.hydrozoa.devices.smart;

import dev.vexide.hydrozoa.DeviceException;
import dev.vexide.hydrozoa.Peripherals;
import dev.vexide.hydrozoa.devices.DeviceDisconnectedException;
import dev.vexide.hydrozoa.devices.IncorrectDeviceException;
import dev.vexide.hydrozoa.sdk.Hydrozoa;
import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

/**
 * A Smart Port on the VEX V5 Robot Brain.
 */
public final class SmartPort {
    private final int number;
    private @Nullable Peripherals.Key key;

    /**
     * Creates a new Smart Port.
     *
     * @param key the peripherals key, which may only be accessed from within the {@link Peripherals} class
     * @param number     the port number of the smart port
     * @see Peripherals#takePort(int)
     */
    @ApiStatus.Internal
    public SmartPort(@NotNull Peripherals.Key key, int number) {
        this.key = key;
        this.number = number;
    }

    /**
     * Create a duplicate of this smart port while consuming this instance. This method is called during the
     * instantiation of {@link SmartDevice} objects to ensure that each smart device has its own unique smart port.
     *
     * @return a new smart port with the same port number
     */
    @Contract("-> new")
    public @NotNull SmartPort take() {
        var key = validate();
        this.key = null;
        return new SmartPort(key, number);
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

    @Contract(value = "-> _")
    private @NotNull Peripherals.Key validate() {
        if (key == null) {
            throw new IllegalStateException(String.format("This smart port (%d) is already being used by another smart device", number));
        }
        return this.key;
    }

    /**
     * Gets the underlying device handle for this smart port so that it can be passed to the {@link VexSdk}.
     * @return the device's SDK handle
     * @throws IllegalStateException if the smart port is already being used by another smart device
     */
    @Contract(" -> new")
    @NotNull
    VexSdk.V5Device deviceHandle() {
        validate();
        return VexSdk.Device.getByIndex(getIndex());
    }

    /**
     * Gets the type of device currently physically connected to this smart port.
     * @return the device's type, if a device is connected
     */
    public Optional<SmartDevice.Type> deviceType() {
        var deviceTypes = new byte[32];
        VexSdk.Device.getStatus(Hydrozoa.getByteArrayPointer(deviceTypes));

        return SmartDevice.Type.fromRaw(new VexSdk.Device.Type(deviceTypes[getIndex()]));
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
