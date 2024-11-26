package dev.vexide.hydrozoa.devices;

import dev.vexide.hydrozoa.DeviceException;
import dev.vexide.hydrozoa.devices.smart.SmartDevice;

/**
 * An exception thrown when an unexpected type of device is connected to a port.
 * <p>
 * This is in contrast to {@link DeviceDisconnectedException}, which is thrown when no device at all is connected to a
 * port.
 */
public class IncorrectDeviceException extends DeviceException {
    /**
     * Creates a new {@code IncorrectDeviceException}.
     *
     * @param port     the port number of the device
     * @param expected the device type that was expected
     * @param actual   the device type that was found instead
     */
    public IncorrectDeviceException(int port, SmartDevice.Type expected, SmartDevice.Type actual) {
        super(String.format("Expected a %s to be connected to port %d, but a %s was found", expected, port, actual));
    }
}
