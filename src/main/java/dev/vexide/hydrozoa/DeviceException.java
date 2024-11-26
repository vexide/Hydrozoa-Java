package dev.vexide.hydrozoa;

/**
 * An exception that is thrown when a device is in an invalid state.
 */
public abstract class DeviceException extends Exception {
    /**
     * Create a new {@code DeviceException}.
     *
     * @param message the exception message
     */
    public DeviceException(String message) {
        super(message);
    }
}
