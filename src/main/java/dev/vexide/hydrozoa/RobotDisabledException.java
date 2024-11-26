package dev.vexide.hydrozoa;

public class RobotDisabledException extends DeviceException {
    public RobotDisabledException() {
        super("Cannot perform this operation when the robot is disabled");
    }
}
