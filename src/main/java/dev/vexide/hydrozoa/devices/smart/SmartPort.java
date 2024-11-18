package dev.vexide.hydrozoa.devices.smart;

import dev.vexide.hydrozoa.Peripherals;
import dev.vexide.hydrozoa.devices.DeviceDisconnectedException;
import dev.vexide.hydrozoa.devices.DeviceException;
import dev.vexide.hydrozoa.devices.IncorrectDeviceException;
import dev.vexide.hydrozoa.sdk.V5_Device;
import dev.vexide.hydrozoa.sdk.V5_DeviceType;
import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;

public final class SmartPort {
    private final int number;

    public SmartPort(Peripherals.Key ignoredKey, int number) {
        this.number = number;
    }

    @Contract(pure = true)
    public int getNumber() {
        return number;
    }

    @Contract(pure = true)
    public int getIndex() {
        return number - 1;
    }

    @Contract(" -> new")
    @NotNull
    V5_Device deviceHandle() {
        return VexSdk.Device.vexDeviceGetByIndex(getIndex());
    }

    public Optional<SmartDevice.Type> deviceType() {
        var deviceTypes = new byte[VexSdk.Device.V5_MAX_DEVICE_PORTS];
        VexSdk.Device.vexDeviceGetStatus(deviceTypes);

        return SmartDevice.Type.fromRaw(new V5_DeviceType(deviceTypes[getIndex()]));
    }

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
