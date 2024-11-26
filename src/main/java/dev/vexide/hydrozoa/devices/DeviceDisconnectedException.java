package dev.vexide.hydrozoa.devices;

import dev.vexide.hydrozoa.DeviceException;
import dev.vexide.hydrozoa.devices.smart.SmartDevice;

/**
 * An exception thrown when a device is expected to be connected to a port, but none is found.
 */
public class DeviceDisconnectedException extends DeviceException {
    /**
     * Create a new {@code DeviceDisconnectedException}.
     *
     * @param port the port number
     * @param type the expected device type
     */
    public DeviceDisconnectedException(int port, SmartDevice.Type type) {
        super(String.format("Expected a %s to be connected to port %d, but none was found", type, port));
    }
}
