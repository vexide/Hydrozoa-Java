package dev.vexide.hydrozoa;

import dev.vexide.hydrozoa.devices.DeviceException;

public class RobotDisabledException extends DeviceException {
    public RobotDisabledException() {
        super("Cannot perform this operation when the robot is disabled");
    }
}
