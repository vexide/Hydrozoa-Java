package dev.vexide.hydrozoa;

/**
 * An exception that is thrown when an operation that requires the robot to be enabled is attempted while the robot is
 * disabled.
 */
public class RobotDisabledException extends DeviceException {
    /**
     * Create a new {@code RobotDisabledException}.
     */
    public RobotDisabledException() {
        super("Cannot perform this operation when the robot is disabled");
    }
}
