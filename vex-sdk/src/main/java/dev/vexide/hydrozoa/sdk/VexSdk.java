package dev.vexide.hydrozoa.sdk;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.teavm.interop.Import;
import org.teavm.interop.StaticInit;

@StaticInit
public final class VexSdk {
    @Import(module = "vex", name = "vexTasksRun")
    public static native void vexTasksRun();

    @Import(module = "vex", name = "vexCompetitionStatus")
    public static native int vexCompetitionStatus();

    @StaticInit
    public static final class Display {
        @Import(module = "vex", name = "vexDisplayForegroundColor")
        public static native void vexDisplayForegroundColor(int color);

        @Import(module = "vex", name = "vexDisplayRectFill")
        public static native void vexDisplayRectFill(int x1, int y1, int x2, int y2);
    }

    @StaticInit
    public static final class Controller {
        /**
         * Get the value of a controller's data channel.
         */
        public static int vexControllerGet(@NotNull V5_ControllerId id, @NotNull V5_ControllerIndex index) {
            return vexControllerGetRaw(id.value(), index.value());
        }

        @Import(module = "vex", name = "vexControllerGet")
        private static native int vexControllerGetRaw(byte id, byte index);

        /**
         * Returns `1` if the controller on the given ID is connected, or `0` if not.
         */
        public static @NotNull V5_ControllerStatus vexControllerConnectionStatusGet(@NotNull V5_ControllerId id) {
            return new V5_ControllerStatus(vexControllerConnectionStatusGetRaw(id.value()));
        }

        @Import(module = "vex", name = "vexControllerConnectionStatusGet")
        private static native byte vexControllerConnectionStatusGetRaw(byte id);
    }


    @StaticInit
    public static final class Device {
        public static final int V5_MAX_DEVICE_PORTS = 32;

        @Import(module = "vex", name = "vexControllerConnectionStatusGet")
        private static native int vexDevicesGetNumber();

        public static int vexDevicesGetNumberByType(@NotNull V5_DeviceType deviceType) {
            return vexDevicesGetNumberByTypeRaw(deviceType.value());
        }

        @Import(module = "vex", name = "vexDevicesGetNumberByType")
        private static native int vexDevicesGetNumberByTypeRaw(int deviceType);

        @Import(module = "vex", name = "vexDeviceGetStatus")
        public static native int vexDeviceGetStatus(byte[] devices);

        @Contract("_ -> new")
        public static @NotNull V5_Device vexDeviceGetByIndex(int index) {
            return new V5_Device(vexDeviceGetByIndexRaw(index));
        }

        @Import(module = "vex", name = "vexDeviceGetByIndex")
        private static native int vexDeviceGetByIndexRaw(int index);
    }

    @StaticInit
    public static final class Motor {
        public static void vexDeviceMotorBrakeModeSet(@NotNull V5_Device device, @NotNull V5MotorBrakeMode mode) {
            vexDeviceMotorBrakeModeSetRaw(device.raw(), mode.value());
        }

        @Import(module = "vex", name = "vexDeviceMotorBrakeModeSet")
        private static native void vexDeviceMotorBrakeModeSetRaw(int device, byte mode);

        public static void vexDeviceMotorVoltageSet(@NotNull V5_Device device, int voltage) {
            vexDeviceMotorVoltageSetRaw(device.raw(), voltage);
        }

        @Import(module = "vex", name = "vexDeviceMotorVoltageSet")
        private static native void vexDeviceMotorVoltageSetRaw(int device, int voltage);

        public static void vexDeviceMotorVelocitySet(@NotNull V5_Device device, int velocity) {
            vexDeviceMotorVelocitySetRaw(device.raw(), velocity);
        }

        @Import(module = "vex", name = "vexDeviceMotorVelocitySet")
        private static native void vexDeviceMotorVelocitySetRaw(int device, int velocity);

        public static void vexDeviceMotorAbsoluteTargetSet(@NotNull V5_Device device, double position, int velocity) {
            vexDeviceMotorAbsoluteTargetSetRaw(device.raw(), position, velocity);
        }

        @Import(module = "vex", name = "vexDeviceMotorAbsoluteTargetSet")
        private static native void vexDeviceMotorAbsoluteTargetSetRaw(int device, double position, int velocity);

        public static void vexDeviceMotorEncoderUnitsSet(@NotNull V5_Device device, V5MotorEncoderUnits units) {
            vexDeviceMotorEncoderUnitsSetRaw(device.raw(), units.value());
        }

        @Import(module = "vex", name = "vexDeviceMotorEncoderUnitsSet")
        private static native void vexDeviceMotorEncoderUnitsSetRaw(int device, byte units);

        public static void vexDeviceMotorReverseFlagSet(@NotNull V5_Device device, boolean flag) {
            vexDeviceMotorReverseFlagSetRaw(device.raw(), flag);
        }

        @Import(module = "vex", name = "vexDeviceMotorReverseFlagSet")
        private static native void vexDeviceMotorReverseFlagSetRaw(int device, boolean flag);

        public static void vexDeviceMotorGearingSet(@NotNull V5_Device device, V5MotorGearset gearset) {
            vexDeviceMotorGearingSetRaw(device.raw(), gearset.value());
        }

        @Import(module = "vex", name = "vexDeviceMotorGearingSet")
        private static native void vexDeviceMotorGearingSetRaw(int device, byte gearset);
    }

}