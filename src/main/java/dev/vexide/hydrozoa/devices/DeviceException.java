package dev.vexide.hydrozoa.devices;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class DeviceException extends Exception {
    public DeviceException(String message) {
        super(message);
    }

    @Contract(" -> new")
    public static @NotNull DeviceException robotDisabled() {
        return new DeviceException("Cannot perform this operation when the robot is disabled");
    }

    @Contract(" -> new")
    public static @NotNull DeviceException deviceDisconnected() {
        return new DeviceException("Cannot perform this operation when the robot is disabled");
    }
}
