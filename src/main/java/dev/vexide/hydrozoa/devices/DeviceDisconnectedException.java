package dev.vexide.hydrozoa.devices;

import dev.vexide.hydrozoa.devices.smart.SmartDevice;

public class DeviceDisconnectedException extends DeviceException {
    public DeviceDisconnectedException(int port, SmartDevice.Type type) {
        super(String.format("Expected a %s to be connected to port %d, but none was found", type, port));
    }
}
