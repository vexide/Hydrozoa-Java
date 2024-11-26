package dev.vexide.hydrozoa.devices;

import dev.vexide.hydrozoa.DeviceException;
import dev.vexide.hydrozoa.devices.smart.SmartDevice;

public class IncorrectDeviceException extends DeviceException {
    public IncorrectDeviceException(int port, SmartDevice.Type expected, SmartDevice.Type actual) {
        super(String.format("Expected a %s to be connected to port %d, but a %s was found", expected, port, actual));
    }
}
