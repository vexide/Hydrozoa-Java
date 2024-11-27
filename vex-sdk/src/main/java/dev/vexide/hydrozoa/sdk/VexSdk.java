package dev.vexide.hydrozoa.sdk;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.teavm.interop.Import;
import org.teavm.interop.StaticInit;

@SuppressWarnings("MissingJavadoc")
@StaticInit
public final class VexSdk {
    @Import(module = "vex", name = "vexTasksRun")
    public static native void vexTasksRun();

    @Import(module = "vex", name = "vexCompetitionStatus")
    public static native int vexCompetitionStatus();

    @StaticInit
    public static final class Display {
        @Import(module = "vex", name = "vexDisplayRender")
        public static native void vexDisplayRender(boolean bVsyncWait, boolean bRunScheduler);

        @Import(module = "vex", name = "vexDisplayDoubleBufferDisable")
        public static native void vexDisplayDoubleBufferDisable();

        @Import(module = "vex", name = "vexDisplayForegroundColor")
        public static native void vexDisplayForegroundColor(int color);

        @Import(module = "vex", name = "vexDisplayBackgroundColor")
        public static native void vexDisplayBackgroundColor(int color);

        @Import(module = "vex", name = "vexDisplayRectFill")
        public static native void vexDisplayRectFill(int x1, int y1, int x2, int y2);

        @Import(module = "vex", name = "vexDisplayRectDraw")
        public static native void vexDisplayRectDraw(int x1, int y1, int x2, int y2);

        @Import(module = "vex", name = "vexDisplayCircleFill")
        public static native void vexDisplayCircleFill(int xc, int yc, int radius);

        @Import(module = "vex", name = "vexDisplayCircleDraw")
        public static native void vexDisplayCircleDraw(int xc, int yc, int radius);

        // Text functions

        @Import(module = "vex", name = "vexDisplayTextSize")
        public static native void vexDisplayTextSize(int n, int d);

        @Import(module = "vex", name = "vexDisplayFontNamedSet")
        public static native void vexDisplayFontNamedSet(@NotNull String pFontName);

        @Import(module = "vex", name = "vexDisplayStringWidthGet")
        public static native int vexDisplayStringWidthGet(@NotNull String text);

        @Import(module = "vex", name = "vexDisplayStringHeightGet")
        public static native int vexDisplayStringHeightGet(@NotNull String text);

        @Import(module = "vex", name = "vexDisplayPrintf")
        public static native void vexDisplayPrintf(int xpos, int ypos, boolean bOpaque, @NotNull String text);

        @Import(module = "vex", name = "vexDisplayString")
        public static native void vexDisplayString(int nLineNumber, @NotNull String text);

        @Import(module = "vex", name = "vexDisplayStringAt")
        public static native void vexDisplayStringAt(int xpos, int ypos, @NotNull String text);

        @Import(module = "vex", name = "vexDisplayBigString")
        public static native void vexDisplayBigString(int nLineNumber, @NotNull String text);

        @Import(module = "vex", name = "vexDisplayBigStringAt")
        public static native void vexDisplayBigStringAt(int xpos, int ypos, @NotNull String text);

        @Import(module = "vex", name = "vexDisplaySmallStringAt")
        public static native void vexDisplaySmallStringAt(int xpos, int ypos, @NotNull String text);

        @Import(module = "vex", name = "vexDisplayCenteredString")
        public static native void vexDisplayCenteredString(int nLineNumber, @NotNull String text);

        @Import(module = "vex", name = "vexDisplayBigCenteredString")
        public static native void vexDisplayBigCenteredString(int nLineNumber, @NotNull String text);
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
        public static native int vexDevicesGetNumber();

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
        //    TODO:
        //    vexDeviceMotorZeroPositionFlagGet(device: V5_DeviceT) -> bool,
        //    vexDeviceMotorReverseFlagGet(device: V5_DeviceT) -> bool,
        //    vexDeviceMotorEncoderUnitsGet(device: V5_DeviceT) -> V5MotorEncoderUnits,
        //    vexDeviceMotorBrakeModeGet(device: V5_DeviceT) -> V5MotorBrakeMode,
        //    vexDeviceMotorPositionSet(device: V5_DeviceT, position: c_double),
        //    vexDeviceMotorPositionGet(device: V5_DeviceT) -> c_double,
        //    vexDeviceMotorPositionRawGet(
        //        device: V5_DeviceT,
        //        timestamp: *mut u32,
        //    ) -> i32,
        //    vexDeviceMotorPositionReset(device: V5_DeviceT),
        //    vexDeviceMotorTargetGet(device: V5_DeviceT) -> c_double,
        //    vexDeviceMotorServoTargetSet(device: V5_DeviceT, position: c_double),
        //    vexDeviceMotorAbsoluteTargetSet(
        //        device: V5_DeviceT,
        //        position: c_double,
        //        veloctiy: i32,
        //    ),
        //    vexDeviceMotorRelativeTargetSet(
        //        device: V5_DeviceT,
        //        position: c_double,
        //        velocity: i32,
        //    ),
        //    vexDeviceMotorFaultsGet(device: V5_DeviceT) -> u32,
        //    vexDeviceMotorFlagsGet(device: V5_DeviceT) -> u32,
        //    vexDeviceMotorVoltageSet(device: V5_DeviceT, voltage: i32),
        //    vexDeviceMotorVoltageGet(device: V5_DeviceT) -> i32,
        //    vexDeviceMotorGearingSet(device: V5_DeviceT, gearset: V5MotorGearset),
        //    vexDeviceMotorGearingGet(device: V5_DeviceT) -> V5MotorGearset,
        //    vexDeviceMotorVoltageLimitSet(device: V5_DeviceT, limit: i32),
        //    vexDeviceMotorVoltageLimitGet(device: V5_DeviceT) -> i32,
        //    vexDeviceMotorVelocityUpdate(device: V5_DeviceT, velocity: i32),
        //    vexDeviceMotorPositionPidSet(device: V5_DeviceT, pid: *mut V5_DeviceMotorPid),
        //    vexDeviceMotorVelocityPidSet(device: V5_DeviceT, pid: *mut V5_DeviceMotorPid),
        //    vexDeviceMotorExternalProfileSet(
        //        device: V5_DeviceT,
        //        position: c_double,
        //        velocity: i32,
        //    ),
        public static void vexDeviceMotorVelocitySet(@NotNull V5_Device device, int velocity) {
            vexDeviceMotorVelocitySetRaw(device.raw(), velocity);
        }

        @Import(module = "vex", name = "vexDeviceMotorVelocitySet")
        private static native void vexDeviceMotorVelocitySetRaw(int device, int velocity);

        public static int vexDeviceMotorVelocityGet(@NotNull V5_Device device) {
            return vexDeviceMotorVelocityGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorVelocityGet")
        private static native int vexDeviceMotorVelocityGetRaw(int device);

        public static int vexDeviceMotorActualVelocityGet(@NotNull V5_Device device) {
            return vexDeviceMotorActualVelocityGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorActualVelocityGet")
        private static native int vexDeviceMotorActualVelocityGetRaw(int device);

        public static int vexDeviceMotorDirectionGet(@NotNull V5_Device device) {
            return vexDeviceMotorDirectionGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorDirectionGet")
        private static native int vexDeviceMotorDirectionGetRaw(int device);

        public static void vexDeviceMotorBrakeModeSet(@NotNull V5_Device device, @NotNull V5MotorBrakeMode mode) {
            vexDeviceMotorBrakeModeSetRaw(device.raw(), mode.value());
        }
        @Import(module = "vex", name = "vexDeviceMotorBrakeModeSet")
        private static native void vexDeviceMotorBrakeModeSetRaw(int device, byte mode);

        public static void vexDeviceMotorModeSet(@NotNull V5_Device device, @NotNull V5MotorControlMode mode) {
            vexDeviceMotorModeSetRaw(device.raw(), mode.value());
        }

        @Import(module = "vex", name = "vexDeviceMotorModeSet")
        private static native void vexDeviceMotorModeSetRaw(int device, byte mode);

        public static @NotNull V5MotorControlMode vexDeviceMotorModeGet(@NotNull V5_Device device) {
            return new V5MotorControlMode(vexDeviceMotorModeGetRaw(device.raw()));
        }

        @Import(module = "vex", name = "vexDeviceMotorModeGet")
        private static native byte vexDeviceMotorModeGetRaw(int device);

        public static void vexDeviceMotorPwmSet(@NotNull V5_Device device, int pwm) {
            vexDeviceMotorPwmSetRaw(device.raw(), pwm);
        }

        @Import(module = "vex", name = "vexDeviceMotorPwmSet")
        private static native void vexDeviceMotorPwmSetRaw(int device, int pwm);

        public static int vexDeviceMotorPwmGet(@NotNull V5_Device device) {
            return vexDeviceMotorPwmGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorPwmGet")
        private static native int vexDeviceMotorPwmGetRaw(int device);

        public static void vexDeviceMotorCurrentLimitSet(@NotNull V5_Device device, int limit) {
            vexDeviceMotorCurrentLimitSetRaw(device.raw(), limit);
        }

        @Import(module = "vex", name = "vexDeviceMotorCurrentLimitSet")
        private static native void vexDeviceMotorCurrentLimitSetRaw(int device, int limit);

        public static int vexDeviceMotorCurrentLimitGet(@NotNull V5_Device device) {
            return vexDeviceMotorCurrentLimitGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorCurrentLimitGet")
        private static native int vexDeviceMotorCurrentLimitGetRaw(int device);

        public static int vexDeviceMotorCurrentGet(@NotNull V5_Device device) {
            return vexDeviceMotorCurrentGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorCurrentGet")
        private static native int vexDeviceMotorCurrentGetRaw(int device);

        public static double vexDeviceMotorPowerGet(@NotNull V5_Device device) {
            return vexDeviceMotorPowerGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorPowerGet")
        private static native double vexDeviceMotorPowerGetRaw(int device);

        public static double vexDeviceMotorTorqueGet(@NotNull V5_Device device) {
            return vexDeviceMotorTorqueGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorTorqueGet")
        private static native double vexDeviceMotorTorqueGetRaw(int device);

        public static double vexDeviceMotorEfficiencyGet(@NotNull V5_Device device) {
            return vexDeviceMotorEfficiencyGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorEfficiencyGet")
        private static native double vexDeviceMotorEfficiencyGetRaw(int device);

        public static double vexDeviceMotorTemperatureGet(@NotNull V5_Device device) {
            return vexDeviceMotorTemperatureGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorTemperatureGet")
        private static native double vexDeviceMotorTemperatureGetRaw(int device);

        public static boolean vexDeviceMotorOverTempFlagGet(@NotNull V5_Device device) {
            return vexDeviceMotorOverTempFlagGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorOverTempFlagGet")
        private static native boolean vexDeviceMotorOverTempFlagGetRaw(int device);

        public static boolean vexDeviceMotorCurrentLimitFlagGet(@NotNull V5_Device device) {
            return vexDeviceMotorCurrentLimitFlagGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorCurrentLimitFlagGet")
        private static native boolean vexDeviceMotorCurrentLimitFlagGetRaw(int device);

        public static boolean vexDeviceMotorZeroVelocityFlagGet(@NotNull V5_Device device) {
            return vexDeviceMotorZeroVelocityFlagGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorZeroVelocityFlagGet")
        private static native boolean vexDeviceMotorZeroVelocityFlagGetRaw(int device);

        public static boolean vexDeviceMotorZeroPositionFlagGet(@NotNull V5_Device device) {
            return vexDeviceMotorZeroPositionFlagGetRaw(device.raw());
        }

        @Import(module = "vex", name = "vexDeviceMotorZeroPositionFlagGet")
        private static native boolean vexDeviceMotorZeroPositionFlagGetRaw(int device);

        public static void vexDeviceMotorGearingSet(@NotNull V5_Device device, V5MotorGearset gearset) {
            vexDeviceMotorGearingSetRaw(device.raw(), gearset.value());
        }

        @Import(module = "vex", name = "vexDeviceMotorGearingSet")
        private static native void vexDeviceMotorGearingSetRaw(int device, byte gearset);

        public static void vexDeviceMotorAbsoluteTargetSet(@NotNull V5_Device device, double position, int velocity) {
            vexDeviceMotorAbsoluteTargetSetRaw(device.raw(), position, velocity);
        }
        @Import(module = "vex", name = "vexDeviceMotorAbsoluteTargetSet")
        private static native void vexDeviceMotorAbsoluteTargetSetRaw(int device, double position, int velocity);

        public static void vexDeviceMotorReverseFlagSet(@NotNull V5_Device device, boolean flag) {
            vexDeviceMotorReverseFlagSetRaw(device.raw(), flag);
        }

        @Import(module = "vex", name = "vexDeviceMotorReverseFlagSet")
        private static native void vexDeviceMotorReverseFlagSetRaw(int device, boolean flag);

        public static void vexDeviceMotorEncoderUnitsSet(@NotNull V5_Device device, V5MotorEncoderUnits units) {
            vexDeviceMotorEncoderUnitsSetRaw(device.raw(), units.value());
        }
        @Import(module = "vex", name = "vexDeviceMotorEncoderUnitsSet")
        private static native void vexDeviceMotorEncoderUnitsSetRaw(int device, byte units);

        public static void vexDeviceMotorVoltageSet(@NotNull V5_Device device, int voltage) {
            vexDeviceMotorVoltageSetRaw(device.raw(), voltage);
        }

        @Import(module = "vex", name = "vexDeviceMotorVoltageSet")
        private static native void vexDeviceMotorVoltageSetRaw(int device, int voltage);

        public static void vexDeviceMotorVoltageGet(@NotNull V5_Device device, int voltage) {
            vexDeviceMotorVoltageGetRaw(device.raw(), voltage);
        }

        @Import(module = "vex", name = "vexDeviceMotorVoltageGet")
        private static native void vexDeviceMotorVoltageGetRaw(int device, int voltage);
    }

}
