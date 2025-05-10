package dev.vexide.hydrozoa.sdk;

import org.jetbrains.annotations.NotNull;
import org.teavm.interop.Import;
import org.teavm.interop.StaticInit;

import java.util.Arrays;

import dev.vexide.hydrozoa.sdk.Hydrozoa.Util;

@SuppressWarnings("MissingJavadoc")
@StaticInit
public final class VexSdk {
    private VexSdk() {
    }

    public record RawPointer(int raw) {
    }

    public record V5Device(int raw) {
    }

    public record FIL(int raw) {
    }

    @Import(module = "vex", name = "vexTasksRun")
    public static native void vexTasksRun();

    @Import(module = "vex", name = "vexCompetitionStatus")
    public static native int vexCompetitionStatus();

    @StaticInit
    public static final class Display {
        private Display() {
        }

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
    public static final class AbsEnc {
        private AbsEnc() {
        }

        @Import(module = "vex", name = "vexDeviceAbsEncReset")
        private static native void _vexDeviceAbsEncReset(int device);

        public static void reset(V5Device device) {
            _vexDeviceAbsEncReset(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceAbsEncPositionSet")
        private static native void _vexDeviceAbsEncPositionSet(int device, int position);

        public static void setPosition(V5Device device, int position) {
            _vexDeviceAbsEncPositionSet(device.raw, (int) position);
        }

        @Import(module = "vex", name = "vexDeviceAbsEncPositionGet")
        private static native int _vexDeviceAbsEncPositionGet(int device);

        public static int getPosition(V5Device device) {
            return _vexDeviceAbsEncPositionGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceAbsEncVelocityGet")
        private static native int _vexDeviceAbsEncVelocityGet(int device);

        public static int getVelocity(V5Device device) {
            return _vexDeviceAbsEncVelocityGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceAbsEncAngleGet")
        private static native int _vexDeviceAbsEncAngleGet(int device);

        public static int getAngle(V5Device device) {
            return _vexDeviceAbsEncAngleGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceAbsEncReverseFlagSet")
        private static native void _vexDeviceAbsEncReverseFlagSet(int device, boolean value);

        public static void setReverseFlag(V5Device device, boolean value) {
            _vexDeviceAbsEncReverseFlagSet(device.raw, value);
        }

        @Import(module = "vex", name = "vexDeviceAbsEncReverseFlagGet")
        private static native boolean _vexDeviceAbsEncReverseFlagGet(int device);

        public static boolean getReverseFlag(V5Device device) {
            return _vexDeviceAbsEncReverseFlagGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceAbsEncStatusGet")
        private static native int _vexDeviceAbsEncStatusGet(int device);

        public static int getStatus(V5Device device) {
            return _vexDeviceAbsEncStatusGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceAbsEncDataRateSet")
        private static native void _vexDeviceAbsEncDataRateSet(int device, int rate);

        public static void setDataRate(V5Device device, long rate) {
            _vexDeviceAbsEncDataRateSet(device.raw, (int) rate);
        }
    }

    @StaticInit
    public static final class Adi {
        private Adi() {
        }

        public record PortConfiguration(byte value) {
            public static final PortConfiguration TYPE_ANALOG_IN = new PortConfiguration((byte) 0);
            public static final PortConfiguration TYPE_ANALOG_OUT = new PortConfiguration((byte) 1);
            public static final PortConfiguration TYPE_DIGITAL_IN = new PortConfiguration((byte) 2);
            public static final PortConfiguration TYPE_DIGITAL_OUT = new PortConfiguration((byte) 3);
            public static final PortConfiguration TYPE_SMART_BUTTON = new PortConfiguration((byte) 4);
            public static final PortConfiguration TYPE_SMART_POT = new PortConfiguration((byte) 5);
            public static final PortConfiguration TYPE_LEGACY_BUTTON = new PortConfiguration((byte) 6);
            public static final PortConfiguration TYPE_LEGACY_POTENTIOMETER = new PortConfiguration((byte) 7);
            public static final PortConfiguration TYPE_LEGACY_LINE_SENSOR = new PortConfiguration((byte) 8);
            public static final PortConfiguration TYPE_LEGACY_LIGHT_SENSOR = new PortConfiguration((byte) 9);
            public static final PortConfiguration TYPE_LEGACY_GYRO = new PortConfiguration((byte) 10);
            public static final PortConfiguration TYPE_LEGACY_ACCELEROMETER = new PortConfiguration((byte) 11);
            public static final PortConfiguration TYPE_LEGACY_SERVO = new PortConfiguration((byte) 12);
            public static final PortConfiguration TYPE_LEGACY_PWM = new PortConfiguration((byte) 13);
            public static final PortConfiguration TYPE_QUAD_ENCODER = new PortConfiguration((byte) 14);
            public static final PortConfiguration TYPE_SONAR = new PortConfiguration((byte) 15);
            public static final PortConfiguration TYPE_LEGACY_PWM_SLEW = new PortConfiguration((byte) 16);
            public static final PortConfiguration TYPE_UNDEFINED = new PortConfiguration((byte) 255);
        }

        public record BumperState(byte value) {
            public static final BumperState RELEASED = new BumperState((byte) 0);
            public static final BumperState PRESSED = new BumperState((byte) 1);
        }

        @Import(module = "vex", name = "vexDeviceAdiPortConfigSet")
        private static native void _vexDeviceAdiPortConfigSet(int device, int port, int config);

        public static void setPortConfig(V5Device device, long port, PortConfiguration config) {
            _vexDeviceAdiPortConfigSet(device.raw, (int) port, config.value);
        }

        @Import(module = "vex", name = "vexDeviceAdiPortConfigGet")
        private static native int _vexDeviceAdiPortConfigGet(int device, int port);

        public static PortConfiguration getPortConfig(V5Device device, long port) {
            var result = _vexDeviceAdiPortConfigGet(device.raw, (int) port);
            return new PortConfiguration((byte) result);
        }

        @Import(module = "vex", name = "vexDeviceAdiValueSet")
        private static native void _vexDeviceAdiValueSet(int device, int port, int value);

        public static void setValue(V5Device device, long port, int value) {
            _vexDeviceAdiValueSet(device.raw, (int) port, (int) value);
        }

        @Import(module = "vex", name = "vexDeviceAdiValueGet")
        private static native int _vexDeviceAdiValueGet(int device, int port);

        public static int getValue(V5Device device, long port) {
            return _vexDeviceAdiValueGet(device.raw, (int) port);
        }

        @Import(module = "vex", name = "vexDeviceAdiAddrLedSet")
        private static native void _vexDeviceAdiAddrLedSet(int device, int port, int pData, int nOffset, int nLength, int options);

        /**
         * Derived from <a href="https://github.com/purduesigbots/pros/blob/89a7417352fbbc86420325afe410861e2210743c/src/devices/vdml_ext_adi.c#L40">this PROS api</a>
         */
        public static void setAddrLed(V5Device device, long port, RawPointer pData, long nOffset, long nLength, long options) {
            _vexDeviceAdiAddrLedSet(device.raw, (int) port, pData.raw, (int) nOffset, (int) nLength, (int) options);
        }

        @Import(module = "vex", name = "vexDeviceBumperGet")
        private static native int _vexDeviceBumperGet(int device);

        public static BumperState getBumper(V5Device device) {
            var result = _vexDeviceBumperGet(device.raw);
            return new BumperState((byte) result);
        }

        @Import(module = "vex", name = "vexDeviceGyroReset")
        private static native void _vexDeviceGyroReset(int device);

        public static void gyroReset(V5Device device) {
            _vexDeviceGyroReset(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGyroHeadingGet")
        private static native double _vexDeviceGyroHeadingGet(int device);

        public static double getGyroHeading(V5Device device) {
            return _vexDeviceGyroHeadingGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGyroDegreesGet")
        private static native double _vexDeviceGyroDegreesGet(int device);

        public static double getGyroDegrees(V5Device device) {
            return _vexDeviceGyroDegreesGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceSonarValueGet")
        private static native int _vexDeviceSonarValueGet(int device);

        public static int getSonarValue(V5Device device) {
            return _vexDeviceSonarValueGet(device.raw);
        }
    }

    @StaticInit
    public static final class AiVision {
        private AiVision() {
        }

        public record Code(
                short id,
                short len,
                short c1,
                short c2,
                short c3,
                short c4,
                short c5,
                short c6,
                short c7
        ) {
            public static final int SIZE = 16;

            public static Code fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i16
                });
                return new Code((short) values[0], (short) values[1], (short) values[2], (short) values[3], (short) values[4], (short) values[5], (short) values[6], (short) values[7], (short) values[8]);
            }
        }

        public record Color(
                short id,
                short red,
                short grn,
                short blu,
                float hangle,
                float hdsat,
                long reserved
        ) {
            public static final int SIZE = 16;

            public static Color fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.FLOAT,
                        Util.Primitive.FLOAT,
                        Util.Primitive.u32
                });
                return new Color((short) values[0], (short) values[1], (short) values[2], (short) values[3], (float) values[4], (float) values[5], (long) values[6]);
            }
        }

        public record ColorData(
                int xoffset,
                int yoffset,
                int width,
                int height,
                int angle
        ) {
            public static final int SIZE = 10;

            public static ColorData fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.u16,
                        Util.Primitive.u16,
                        Util.Primitive.u16,
                        Util.Primitive.u16,
                        Util.Primitive.u16
                });
                return new ColorData((int) values[0], (int) values[1], (int) values[2], (int) values[3], (int) values[4]);
            }
        }

        public record TagData(
                short x0,
                short y0,
                short x1,
                short y1,
                short x2,
                short y2,
                short x3,
                short y3
        ) {
            public static final int SIZE = 16;

            public static TagData fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i16
                });
                return new TagData((short) values[0], (short) values[1], (short) values[2], (short) values[3], (short) values[4], (short) values[5], (short) values[6], (short) values[7]);
            }
        }

        public record ModelData(
                int xoffset,
                int yoffset,
                int width,
                int height,
                int score
        ) {
            public static final int SIZE = 10;

            public static ModelData fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.u16,
                        Util.Primitive.u16,
                        Util.Primitive.u16,
                        Util.Primitive.u16,
                        Util.Primitive.u16
                });
                return new ModelData((int) values[0], (int) values[1], (int) values[2], (int) values[3], (int) values[4]);
            }
        }

        public record ObjectData(
                ColorData color,
                TagData tag,
                ModelData model
        ) {
            public static final int SIZE = 36;

            public static ObjectData fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{

                });
                return new ObjectData(ColorData.fromByteArray(Arrays.copyOfRange(array, 0, 10)), TagData.fromByteArray(Arrays.copyOfRange(array, 10, 26)), ModelData.fromByteArray(Arrays.copyOfRange(array, 26, 36)));
            }
        }

        public record Object(
                short id,
                short type,
                ObjectData object
        ) {
            public static final int SIZE = 14;

            public static Object fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.u8,
                        Util.Primitive.u8
                });
                return new Object((short) values[0], (short) values[1], ObjectData.fromByteArray(Arrays.copyOfRange(array, 2, 14)));
            }
        }

        @Import(module = "vex", name = "vexDeviceAiVisionClassNameGet")
        private static native int _vexDeviceAiVisionClassNameGet(int device, int id, int pName);

        public static int getClassName(V5Device device, int id, RawPointer pName) {
            return _vexDeviceAiVisionClassNameGet(device.raw, (int) id, pName.raw);
        }

        @Import(module = "vex", name = "vexDeviceAiVisionCodeGet")
        private static native boolean _vexDeviceAiVisionCodeGet(int device, int id, int pCode);

        public static boolean getCode(V5Device device, long id, RawPointer pCode) {
            return _vexDeviceAiVisionCodeGet(device.raw, (int) id, pCode.raw);
        }

        @Import(module = "vex", name = "vexDeviceAiVisionCodeSet")
        private static native void _vexDeviceAiVisionCodeSet(int device, int pCode);

        public static void setCode(V5Device device, RawPointer pCode) {
            _vexDeviceAiVisionCodeSet(device.raw, pCode.raw);
        }

        @Import(module = "vex", name = "vexDeviceAiVisionColorGet")
        private static native boolean _vexDeviceAiVisionColorGet(int device, int id, int pColor);

        public static boolean getColor(V5Device device, long id, RawPointer pColor) {
            return _vexDeviceAiVisionColorGet(device.raw, (int) id, pColor.raw);
        }

        @Import(module = "vex", name = "vexDeviceAiVisionColorSet")
        private static native void _vexDeviceAiVisionColorSet(int device, int pColor);

        public static void setColor(V5Device device, RawPointer pColor) {
            _vexDeviceAiVisionColorSet(device.raw, pColor.raw);
        }

        @Import(module = "vex", name = "vexDeviceAiVisionModeGet")
        private static native int _vexDeviceAiVisionModeGet(int device);

        public static int getMode(V5Device device) {
            return _vexDeviceAiVisionModeGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceAiVisionModeSet")
        private static native void _vexDeviceAiVisionModeSet(int device, int mode);

        public static void setMode(V5Device device, long mode) {
            _vexDeviceAiVisionModeSet(device.raw, (int) mode);
        }

        @Import(module = "vex", name = "vexDeviceAiVisionObjectCountGet")
        private static native int _vexDeviceAiVisionObjectCountGet(int device);

        public static int getObjectCount(V5Device device) {
            return _vexDeviceAiVisionObjectCountGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceAiVisionObjectGet")
        private static native int _vexDeviceAiVisionObjectGet(int device, int indexObj, int pObject);

        public static int getObject(V5Device device, long indexObj, RawPointer pObject) {
            return _vexDeviceAiVisionObjectGet(device.raw, (int) indexObj, pObject.raw);
        }

        @Import(module = "vex", name = "vexDeviceAiVisionSensorSet")
        private static native void _vexDeviceAiVisionSensorSet(int device, double brightness, double contrast);

        public static void setSensor(V5Device device, double brightness, double contrast) {
            _vexDeviceAiVisionSensorSet(device.raw, brightness, contrast);
        }

        @Import(module = "vex", name = "vexDeviceAiVisionStatusGet")
        private static native int _vexDeviceAiVisionStatusGet(int device);

        public static int getStatus(V5Device device) {
            return _vexDeviceAiVisionStatusGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceAiVisionTemperatureGet")
        private static native double _vexDeviceAiVisionTemperatureGet(int device);

        public static double getTemperature(V5Device device) {
            return _vexDeviceAiVisionTemperatureGet(device.raw);
        }
    }

    @StaticInit
    public static final class Arm {
        private Arm() {
        }

        public record TipPosition(
                int tip_x,
                int tip_y,
                int tip_z,
                int tip_roll,
                int tip_pitch,
                int tip_yaw,
                byte pose,
                short velocity
        ) {
            public static final int SIZE = 27;

            public static TipPosition fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.i32,
                        Util.Primitive.i32,
                        Util.Primitive.i32,
                        Util.Primitive.i32,
                        Util.Primitive.i32,
                        Util.Primitive.i32,
                        Util.Primitive.i8,
                        Util.Primitive.i16
                });
                return new TipPosition((int) values[0], (int) values[1], (int) values[2], (int) values[3], (int) values[4], (int) values[5], (byte) values[6], (short) values[7]);
            }
        }

        @Import(module = "vex", name = "vexDeviceArmMoveTipCommandLinearAdv")
        private static native void _vexDeviceArmMoveTipCommandLinearAdv(int device, int position, double j6_rotation, int j6_velocity, boolean relative);

        public static void moveTipCommandLinearAdv(V5Device device, RawPointer position, double j6_rotation, int j6_velocity, boolean relative) {
            _vexDeviceArmMoveTipCommandLinearAdv(device.raw, position.raw, j6_rotation, (int) j6_velocity, relative);
        }

        @Import(module = "vex", name = "vexDeviceArmMoveTipCommandJointAdv")
        private static native void _vexDeviceArmMoveTipCommandJointAdv(int device, int position, double j6_rotation, int j6_velocity, boolean relative);

        public static void moveTipCommandJointAdv(V5Device device, RawPointer position, double j6_rotation, int j6_velocity, boolean relative) {
            _vexDeviceArmMoveTipCommandJointAdv(device.raw, position.raw, j6_rotation, (int) j6_velocity, relative);
        }

        @Import(module = "vex", name = "vexDeviceArmTipPositionGetAdv")
        private static native void _vexDeviceArmTipPositionGetAdv(int device, int position);

        public static void tipPositionGetAdv(V5Device device, RawPointer position) {
            _vexDeviceArmTipPositionGetAdv(device.raw, position.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmPoseSet")
        private static native void _vexDeviceArmPoseSet(int device, int pose, int velocity);

        public static void setPose(V5Device device, short pose, int velocity) {
            _vexDeviceArmPoseSet(device.raw, (int) pose, (int) velocity);
        }

        @Import(module = "vex", name = "vexDeviceArmMoveTipCommandLinear")
        private static native void _vexDeviceArmMoveTipCommandLinear(int device, int x, int y, int z, int pose, int velocity, double rotation, int rot_velocity, boolean relative);

        public static void moveTipCommandLinear(V5Device device, int x, int y, int z, short pose, int velocity, double rotation, int rot_velocity, boolean relative) {
            _vexDeviceArmMoveTipCommandLinear(device.raw, (int) x, (int) y, (int) z, (int) pose, (int) velocity, rotation, (int) rot_velocity, relative);
        }

        @Import(module = "vex", name = "vexDeviceArmMoveTipCommandJoint")
        private static native void _vexDeviceArmMoveTipCommandJoint(int device, int x, int y, int z, int pose, int velocity, double rotation, int rot_velocity, boolean relative);

        public static void moveTipCommandJoint(V5Device device, int x, int y, int z, short pose, int velocity, double rotation, int rot_velocity, boolean relative) {
            _vexDeviceArmMoveTipCommandJoint(device.raw, (int) x, (int) y, (int) z, (int) pose, (int) velocity, rotation, (int) rot_velocity, relative);
        }

        @Import(module = "vex", name = "vexDeviceArmMoveJointsCommand")
        private static native void _vexDeviceArmMoveJointsCommand(int device, int positions, int velocities, double j6_rotation, int j6_velocity, double j7_volts, int j7_timeout, int j7_i_limit, boolean relative);

        public static void moveJointsCommand(V5Device device, RawPointer positions, RawPointer velocities, double j6_rotation, int j6_velocity, double j7_volts, int j7_timeout, int j7_i_limit, boolean relative) {
            _vexDeviceArmMoveJointsCommand(device.raw, positions.raw, velocities.raw, j6_rotation, (int) j6_velocity, j7_volts, (int) j7_timeout, (int) j7_i_limit, relative);
        }

        @Import(module = "vex", name = "vexDeviceArmSpinJoints")
        private static native void _vexDeviceArmSpinJoints(int device, int velocities);

        public static void spinJoints(V5Device device, RawPointer velocities) {
            _vexDeviceArmSpinJoints(device.raw, velocities.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmSetJointPositions")
        private static native void _vexDeviceArmSetJointPositions(int device, int new_positions);

        public static void setJointPositions(V5Device device, RawPointer new_positions) {
            _vexDeviceArmSetJointPositions(device.raw, new_positions.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmPickUpCommand")
        private static native void _vexDeviceArmPickUpCommand(int device);

        public static void pickUpCommand(V5Device device) {
            _vexDeviceArmPickUpCommand(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmDropCommand")
        private static native void _vexDeviceArmDropCommand(int device);

        public static void dropCommand(V5Device device) {
            _vexDeviceArmDropCommand(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmMoveVoltsCommand")
        private static native void _vexDeviceArmMoveVoltsCommand(int device, int voltages);

        public static void moveVoltsCommand(V5Device device, RawPointer voltages) {
            _vexDeviceArmMoveVoltsCommand(device.raw, voltages.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmFullStop")
        private static native void _vexDeviceArmFullStop(int device, int brakeMode);

        public static void fullStop(V5Device device, short brakeMode) {
            _vexDeviceArmFullStop(device.raw, (int) brakeMode);
        }

        @Import(module = "vex", name = "vexDeviceArmEnableProfiler")
        private static native void _vexDeviceArmEnableProfiler(int device, int enable);

        public static void enableProfiler(V5Device device, short enable) {
            _vexDeviceArmEnableProfiler(device.raw, (int) enable);
        }

        @Import(module = "vex", name = "vexDeviceArmProfilerVelocitySet")
        private static native void _vexDeviceArmProfilerVelocitySet(int device, int linear_velocity, int joint_velocity);

        public static void setProfilerVelocity(V5Device device, int linear_velocity, int joint_velocity) {
            _vexDeviceArmProfilerVelocitySet(device.raw, (int) linear_velocity, (int) joint_velocity);
        }

        @Import(module = "vex", name = "vexDeviceArmSaveZeroValues")
        private static native void _vexDeviceArmSaveZeroValues(int device);

        public static void saveZeroValues(V5Device device) {
            _vexDeviceArmSaveZeroValues(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmForceZeroCommand")
        private static native void _vexDeviceArmForceZeroCommand(int device);

        public static void forceZeroCommand(V5Device device) {
            _vexDeviceArmForceZeroCommand(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmClearZeroValues")
        private static native void _vexDeviceArmClearZeroValues(int device);

        public static void clearZeroValues(V5Device device) {
            _vexDeviceArmClearZeroValues(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmBootload")
        private static native void _vexDeviceArmBootload(int device);

        public static void bootload(V5Device device) {
            _vexDeviceArmBootload(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmTipPositionGet")
        private static native void _vexDeviceArmTipPositionGet(int device, int x, int y, int z);

        public static void getTipPosition(V5Device device, RawPointer x, RawPointer y, RawPointer z) {
            _vexDeviceArmTipPositionGet(device.raw, x.raw, y.raw, z.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmJointInfoGet")
        private static native void _vexDeviceArmJointInfoGet(int device, int positions, int velocities, int currents);

        public static void getJointInfo(V5Device device, RawPointer positions, RawPointer velocities, RawPointer currents) {
            _vexDeviceArmJointInfoGet(device.raw, positions.raw, velocities.raw, currents.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmJ6PositionGet")
        private static native double _vexDeviceArmJ6PositionGet(int device);

        public static double getJ6Position(V5Device device) {
            return _vexDeviceArmJ6PositionGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmBatteryGet")
        private static native int _vexDeviceArmBatteryGet(int device);

        public static int getBattery(V5Device device) {
            return _vexDeviceArmBatteryGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmServoFlagsGet")
        private static native int _vexDeviceArmServoFlagsGet(int device, int servoID);

        public static int getServoFlags(V5Device device, long servoID) {
            return _vexDeviceArmServoFlagsGet(device.raw, (int) servoID);
        }

        @Import(module = "vex", name = "vexDeviceArmStatusGet")
        private static native int _vexDeviceArmStatusGet(int device);

        public static int getStatus(V5Device device) {
            return _vexDeviceArmStatusGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmDebugGet")
        private static native int _vexDeviceArmDebugGet(int device, int id);

        public static int getDebug(V5Device device, int id) {
            return _vexDeviceArmDebugGet(device.raw, (int) id);
        }

        @Import(module = "vex", name = "vexDeviceArmJointErrorsGet")
        private static native void _vexDeviceArmJointErrorsGet(int device, int errors);

        public static void getJointErrors(V5Device device, RawPointer errors) {
            _vexDeviceArmJointErrorsGet(device.raw, errors.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmJ6PositionSet")
        private static native void _vexDeviceArmJ6PositionSet(int device, int position);

        public static void setJ6Position(V5Device device, short position) {
            _vexDeviceArmJ6PositionSet(device.raw, (int) position);
        }

        @Import(module = "vex", name = "vexDeviceArmStopJointsCommand")
        private static native void _vexDeviceArmStopJointsCommand(int device, int brakeModes);

        public static void stopJointsCommand(V5Device device, RawPointer brakeModes) {
            _vexDeviceArmStopJointsCommand(device.raw, brakeModes.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmReboot")
        private static native void _vexDeviceArmReboot(int device);

        public static void reboot(V5Device device) {
            _vexDeviceArmReboot(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceArmTipOffsetSet")
        private static native void _vexDeviceArmTipOffsetSet(int device, int x, int y, int z);

        public static void setTipOffset(V5Device device, int x, int y, int z) {
            _vexDeviceArmTipOffsetSet(device.raw, (int) x, (int) y, (int) z);
        }
    }

    @StaticInit
    public static final class Battery {
        private Battery() {
        }

        @Import(module = "vex", name = "vexBatteryVoltageGet")
        private static native int _vexBatteryVoltageGet();

        public static int getVoltage() {
            return _vexBatteryVoltageGet();
        }

        @Import(module = "vex", name = "vexBatteryCurrentGet")
        private static native int _vexBatteryCurrentGet();

        public static int getCurrent() {
            return _vexBatteryCurrentGet();
        }

        @Import(module = "vex", name = "vexBatteryTemperatureGet")
        private static native double _vexBatteryTemperatureGet();

        public static double getTemperature() {
            return _vexBatteryTemperatureGet();
        }

        @Import(module = "vex", name = "vexBatteryCapacityGet")
        private static native double _vexBatteryCapacityGet();

        public static double getCapacity() {
            return _vexBatteryCapacityGet();
        }
    }

    @StaticInit
    public static final class Competition {
        private Competition() {
        }

        @Import(module = "vex", name = "vexCompetitionStatus")
        private static native int _vexCompetitionStatus();

        public static int status() {
            return _vexCompetitionStatus();
        }

        @Import(module = "vex", name = "vexCompetitionControl")
        private static native void _vexCompetitionControl(int data);

        public static void control(long data) {
            _vexCompetitionControl((int) data);
        }
    }

    @StaticInit
    public static final class Controller {
        private Controller() {
        }

        public record Id(byte value) {
            public static final Id MASTER = new Id((byte) 0);
            public static final Id PARTNER = new Id((byte) 1);
        }

        public record Index(byte value) {
            public static final Index ANA_LEFT_X = new Index((byte) 0);
            public static final Index ANA_LEFT_Y = new Index((byte) 1);
            public static final Index ANA_RIGHT_X = new Index((byte) 2);
            public static final Index ANA_RIGHT_Y = new Index((byte) 3);
            public static final Index ANA_SPARE1 = new Index((byte) 4);
            public static final Index ANA_SPARE2 = new Index((byte) 5);
            public static final Index BUTTON5_U = new Index((byte) 6);
            public static final Index BUTTON5_D = new Index((byte) 7);
            public static final Index BUTTON6_U = new Index((byte) 8);
            public static final Index BUTTON6_D = new Index((byte) 9);
            public static final Index BUTTON7_U = new Index((byte) 10);
            public static final Index BUTTON7_D = new Index((byte) 11);
            public static final Index BUTTON7_L = new Index((byte) 12);
            public static final Index BUTTON7_R = new Index((byte) 13);
            public static final Index BUTTON8_U = new Index((byte) 14);
            public static final Index BUTTON8_D = new Index((byte) 15);
            public static final Index BUTTON8_L = new Index((byte) 16);
            public static final Index BUTTON8_R = new Index((byte) 17);
            public static final Index BUTTON_SEL = new Index((byte) 18);
            public static final Index BATTERY_LEVEL = new Index((byte) 19);
            public static final Index BUTTON_ALL = new Index((byte) 20);
            public static final Index FLAGS = new Index((byte) 21);
            public static final Index BATTERY_CAPACITY = new Index((byte) 22);
            public static final Index AXIS1 = new Index((byte) 2);
            public static final Index AXIS2 = new Index((byte) 3);
            public static final Index AXIS3 = new Index((byte) 1);
            public static final Index AXIS4 = new Index((byte) 0);
            public static final Index BUTTON_L1 = new Index((byte) 6);
            public static final Index BUTTON_L2 = new Index((byte) 7);
            public static final Index BUTTON_R1 = new Index((byte) 8);
            public static final Index BUTTON_R2 = new Index((byte) 9);
            public static final Index BUTTON_UP = new Index((byte) 10);
            public static final Index BUTTON_DOWN = new Index((byte) 11);
            public static final Index BUTTON_LEFT = new Index((byte) 12);
            public static final Index BUTTON_RIGHT = new Index((byte) 13);
            public static final Index BUTTON_X = new Index((byte) 14);
            public static final Index BUTTON_B = new Index((byte) 15);
            public static final Index BUTTON_Y = new Index((byte) 16);
            public static final Index BUTTON_A = new Index((byte) 17);
        }

        public record Status(byte value) {
            public static final Status OFFLINE = new Status((byte) 0);
            public static final Status TETHERED = new Status((byte) 1);
            public static final Status VEXNET = new Status((byte) 2);
        }

        @Import(module = "vex", name = "vexControllerGet")
        private static native int _vexControllerGet(int id, int index);

        /**
         * Get the value of a controller's data channel.
         */
        public static int getController(Id id, Index index) {
            return _vexControllerGet(id.value, index.value);
        }

        @Import(module = "vex", name = "vexControllerConnectionStatusGet")
        private static native int _vexControllerConnectionStatusGet(int id);

        /**
         * Returns `1` if the controller on the given ID is connected, or `0` if not.
         */
        public static Status getConnectionStatus(Id id) {
            var result = _vexControllerConnectionStatusGet(id.value);
            return new Status((byte) result);
        }

        @Import(module = "vex", name = "vexControllerTextSet")
        private static native int _vexControllerTextSet(int id, int line, int col, int buf);

        public static int setText(long id, long line, long col, RawPointer buf) {
            return _vexControllerTextSet((int) id, (int) line, (int) col, buf.raw);
        }
    }

    @StaticInit
    public static final class Device {
        private Device() {
        }

        public record Type(byte value) {
            /**
             * No device connected
             */
            public static final Type NO_SENSOR = new Type((byte) 0);
            /**
             * V5 Smart Motor
             */
            public static final Type MOTOR_SENSOR = new Type((byte) 2);
            /**
             * Unknown use (possibly unreleased hardware)
             */
            public static final Type LED_SENSOR = new Type((byte) 3);
            /**
             * Rotation Sensor
             */
            public static final Type ABS_ENC_SENSOR = new Type((byte) 4);
            /**
             * V5 Motor CR (unknown use)
             */
            public static final Type CR_MOTOR_SENSOR = new Type((byte) 5);
            /**
             * Inertial Sensor
             */
            public static final Type IMU_SENSOR = new Type((byte) 6);
            /**
             * Distance Sensor
             */
            public static final Type DISTANCE_SENSOR = new Type((byte) 7);
            /**
             * Radio
             */
            public static final Type RADIO_SENSOR = new Type((byte) 8);
            /**
             * Master Controller
             */
            public static final Type TETHER_SENSOR = new Type((byte) 9);
            /**
             * Brain
             */
            public static final Type BRAIN_SENSOR = new Type((byte) 10);
            /**
             * Vision Sensor
             */
            public static final Type VISION_SENSOR = new Type((byte) 11);
            /**
             * ADI
             */
            public static final Type ADI_SENSOR = new Type((byte) 12);
            /**
             * Partner Controller
             */
            public static final Type RES1_SENSOR = new Type((byte) 13);
            /**
             * Battery
             */
            public static final Type RES2_SENSOR = new Type((byte) 14);
            /**
             * Solenoid (unknown use)
             */
            public static final Type RES3_SENSOR = new Type((byte) 15);
            /**
             * Optical Sensor
             */
            public static final Type OPTICAL_SENSOR = new Type((byte) 16);
            /**
             * Electromagnet
             */
            public static final Type MAGNET_SENSOR = new Type((byte) 17);
            /**
             * GPS
             */
            public static final Type GPS_SENSOR = new Type((byte) 20);
            /**
             * AI Stereo Camera
             */
            public static final Type AICAMERA_SENSOR = new Type((byte) 26);
            /**
             * CTE Workcell Light Tower
             */
            public static final Type LIGHT_TOWER_SENSOR = new Type((byte) 27);
            /**
             * CTE Workcell Arm
             */
            public static final Type ARM_DEVICE = new Type((byte) 28);
            /**
             * AI Vision Sensor
             */
            public static final Type AI_VISION_SENSOR = new Type((byte) 29);
            /**
             * CTE Workcell Pneumatics
             */
            public static final Type PNEUMATIC_SENSOR = new Type((byte) 30);
            public static final Type BUMPER_SENSOR = new Type((byte) 0x40);
            public static final Type GYRO_SENSOR = new Type((byte) 0x46);
            public static final Type SONAR_SENSOR = new Type((byte) 0x47);
            public static final Type GENERIC_SENSOR = new Type((byte) 128);
            /**
             * Generic Serial
             */
            public static final Type GENERIC_SERIAL = new Type((byte) 129);
            /**
             * Unknown use
             */
            public static final Type UNDEFINED_SENSOR = new Type((byte) 255);
        }

        @Import(module = "vex", name = "vexDevicesGetNumber")
        private static native int _vexDevicesGetNumber();

        /**
         * Get the number of device ports currently present on this system.
         * <p>
         * As of VEXos 1.1.4, this number is the constant `23`.
         */
        public static int devicesGetNumber() {
            return _vexDevicesGetNumber();
        }

        @Import(module = "vex", name = "vexDevicesGetNumberByType")
        private static native int _vexDevicesGetNumberByType(int device_type);

        /**
         * Get the number of devices of a specific type plugged into the brain.
         */
        public static int devicesGetNumberByType(Type device_type) {
            return _vexDevicesGetNumberByType(device_type.value);
        }

        @Import(module = "vex", name = "vexDevicesGet")
        private static native int _vexDevicesGet();

        /**
         * Get a buffer of all devices on the brain.
         */
        public static V5Device getDevices() {
            var result = _vexDevicesGet();
            return new V5Device(result);
        }

        @Import(module = "vex", name = "vexDeviceGetByIndex")
        private static native int _vexDeviceGetByIndex(int index);

        /**
         * Get a handle to a device plugged into the specified port index.
         */
        public static V5Device getByIndex(long index) {
            var result = _vexDeviceGetByIndex((int) index);
            return new V5Device(result);
        }

        @Import(module = "vex", name = "vexDeviceGetStatus")
        private static native int _vexDeviceGetStatus(int devices);

        /**
         * Get a list of device types plugged into the brain.
         * <p>
         * Returns -1 if a null pointer is passed, otherwise the number of devices
         * that were written to the buffer.
         * <p>
         * The length of the buffer should be at most [`V5_MAX_DEVICE_PORTS`].
         */
        public static int getStatus(RawPointer devices) {
            return _vexDeviceGetStatus(devices.raw);
        }

        @Import(module = "vex", name = "vexDeviceGetTimestamp")
        private static native int _vexDeviceGetTimestamp(int device);

        /**
         * Get the timestamp recorded by a device's internal clock.
         */
        public static int getTimestamp(V5Device device) {
            return _vexDeviceGetTimestamp(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGenericValueGet")
        private static native double _vexDeviceGenericValueGet(int device);

        public static double getGenericValue(V5Device device) {
            return _vexDeviceGenericValueGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceButtonStateGet")
        private static native int _vexDeviceButtonStateGet();

        public static int getButtonState() {
            return _vexDeviceButtonStateGet();
        }
    }

    @StaticInit
    public static final class Distance {
        private Distance() {
        }

        @Import(module = "vex", name = "vexDeviceDistanceDistanceGet")
        private static native int _vexDeviceDistanceDistanceGet(int device);

        public static int getDistance(V5Device device) {
            return _vexDeviceDistanceDistanceGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceDistanceConfidenceGet")
        private static native int _vexDeviceDistanceConfidenceGet(int device);

        public static int getConfidence(V5Device device) {
            return _vexDeviceDistanceConfidenceGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceDistanceStatusGet")
        private static native int _vexDeviceDistanceStatusGet(int device);

        public static int getStatus(V5Device device) {
            return _vexDeviceDistanceStatusGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceDistanceObjectSizeGet")
        private static native int _vexDeviceDistanceObjectSizeGet(int device);

        public static int getObjectSize(V5Device device) {
            return _vexDeviceDistanceObjectSizeGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceDistanceObjectVelocityGet")
        private static native double _vexDeviceDistanceObjectVelocityGet(int device);

        public static double getObjectVelocity(V5Device device) {
            return _vexDeviceDistanceObjectVelocityGet(device.raw);
        }
    }

    @StaticInit
    public static final class File {
        private File() {
        }

        public record FRESULT(byte value) {
            public static final FRESULT FR__OK = new FRESULT((byte) 0);
            public static final FRESULT FR__DISK__ERR = new FRESULT((byte) 1);
            public static final FRESULT FR__INT__ERR = new FRESULT((byte) 2);
            public static final FRESULT FR__NOT__READY = new FRESULT((byte) 3);
            public static final FRESULT FR__NO__FILE = new FRESULT((byte) 4);
            public static final FRESULT FR__NO__PATH = new FRESULT((byte) 5);
            public static final FRESULT FR__INVALID__NAME = new FRESULT((byte) 6);
            public static final FRESULT FR__DENIED = new FRESULT((byte) 7);
            public static final FRESULT FR__EXIST = new FRESULT((byte) 8);
            public static final FRESULT FR__INVALID__OBJECT = new FRESULT((byte) 9);
            public static final FRESULT FR__WRITE__PROTECTED = new FRESULT((byte) 10);
            public static final FRESULT FR__INVALID__DRIVE = new FRESULT((byte) 11);
            public static final FRESULT FR__NOT__ENABLED = new FRESULT((byte) 12);
            public static final FRESULT FR__NO__FILESYSTEM = new FRESULT((byte) 13);
            public static final FRESULT FR__MKFS__ABORTED = new FRESULT((byte) 14);
            public static final FRESULT FR__TIMEOUT = new FRESULT((byte) 15);
            public static final FRESULT FR__LOCKED = new FRESULT((byte) 16);
            public static final FRESULT FR__NOT__ENOUGH__CORE = new FRESULT((byte) 17);
            public static final FRESULT FR__TOO__MANY__OPEN__FILES = new FRESULT((byte) 18);
            public static final FRESULT FR__INVALID__PARAMETER = new FRESULT((byte) 19);
        }

        @Import(module = "vex", name = "vexFileMountSD")
        private static native int _vexFileMountSD();

        public static FRESULT mountSD() {
            var result = _vexFileMountSD();
            return new FRESULT((byte) result);
        }

        @Import(module = "vex", name = "vexFileDirectoryGet")
        private static native int _vexFileDirectoryGet(int path, int buffer, int len);

        public static FRESULT getDirectory(RawPointer path, RawPointer buffer, long len) {
            var result = _vexFileDirectoryGet(path.raw, buffer.raw, (int) len);
            return new FRESULT((byte) result);
        }

        @Import(module = "vex", name = "vexFileOpen")
        private static native int _vexFileOpen(int filename, int mode);

        public static FIL open(RawPointer filename, RawPointer mode) {
            var result = _vexFileOpen(filename.raw, mode.raw);
            return new FIL(result);
        }

        @Import(module = "vex", name = "vexFileOpenWrite")
        private static native int _vexFileOpenWrite(int filename);

        public static FIL openWrite(RawPointer filename) {
            var result = _vexFileOpenWrite(filename.raw);
            return new FIL(result);
        }

        @Import(module = "vex", name = "vexFileOpenCreate")
        private static native int _vexFileOpenCreate(int filename);

        public static FIL openCreate(RawPointer filename) {
            var result = _vexFileOpenCreate(filename.raw);
            return new FIL(result);
        }

        @Import(module = "vex", name = "vexFileClose")
        private static native void _vexFileClose(int fdp);

        public static void close(FIL fdp) {
            _vexFileClose(fdp.raw);
        }

        @Import(module = "vex", name = "vexFileWrite")
        private static native int _vexFileWrite(int buf, int size, int nItems, int fdp);

        public static int write(RawPointer buf, long size, long nItems, FIL fdp) {
            return _vexFileWrite(buf.raw, (int) size, (int) nItems, fdp.raw);
        }

        @Import(module = "vex", name = "vexFileSize")
        private static native int _vexFileSize(int fdp);

        public static int size(FIL fdp) {
            return _vexFileSize(fdp.raw);
        }

        @Import(module = "vex", name = "vexFileSeek")
        private static native int _vexFileSeek(int fdp, int offset, int whence);

        public static FRESULT seek(FIL fdp, long offset, int whence) {
            var result = _vexFileSeek(fdp.raw, (int) offset, (int) whence);
            return new FRESULT((byte) result);
        }

        @Import(module = "vex", name = "vexFileRead")
        private static native int _vexFileRead(int buf, int size, int nItems, int fdp);

        public static int read(RawPointer buf, long size, long nItems, FIL fdp) {
            return _vexFileRead(buf.raw, (int) size, (int) nItems, fdp.raw);
        }

        @Import(module = "vex", name = "vexFileDriveStatus")
        private static native boolean _vexFileDriveStatus(int drive);

        public static boolean driveStatus(long drive) {
            return _vexFileDriveStatus((int) drive);
        }

        @Import(module = "vex", name = "vexFileTell")
        private static native int _vexFileTell(int fdp);

        public static int tell(FIL fdp) {
            return _vexFileTell(fdp.raw);
        }

        @Import(module = "vex", name = "vexFileSync")
        private static native void _vexFileSync(int fdp);

        public static void sync(FIL fdp) {
            _vexFileSync(fdp.raw);
        }

        @Import(module = "vex", name = "vexFileStatus")
        private static native int _vexFileStatus(int filename);

        public static int status(RawPointer filename) {
            return _vexFileStatus(filename.raw);
        }
    }

    @StaticInit
    public static final class GenericRadio {
        private GenericRadio() {
        }

        @Import(module = "vex", name = "vexDeviceGenericRadioWriteFree")
        private static native int _vexDeviceGenericRadioWriteFree(int device);

        public static int writeFree(V5Device device) {
            return _vexDeviceGenericRadioWriteFree(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGenericRadioTransmit")
        private static native int _vexDeviceGenericRadioTransmit(int device, int data, int size);

        public static int transmit(V5Device device, RawPointer data, int size) {
            return _vexDeviceGenericRadioTransmit(device.raw, data.raw, (int) size);
        }

        @Import(module = "vex", name = "vexDeviceGenericRadioReceiveAvail")
        private static native int _vexDeviceGenericRadioReceiveAvail(int device);

        public static int receiveAvail(V5Device device) {
            return _vexDeviceGenericRadioReceiveAvail(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGenericRadioReceive")
        private static native int _vexDeviceGenericRadioReceive(int device, int data, int size);

        public static int receive(V5Device device, RawPointer data, int size) {
            return _vexDeviceGenericRadioReceive(device.raw, data.raw, (int) size);
        }

        @Import(module = "vex", name = "vexDeviceGenericRadioLinkStatus")
        private static native boolean _vexDeviceGenericRadioLinkStatus(int device);

        public static boolean linkStatus(V5Device device) {
            return _vexDeviceGenericRadioLinkStatus(device.raw);
        }
    }

    @StaticInit
    public static final class GenericSerial {
        private GenericSerial() {
        }

        @Import(module = "vex", name = "vexDeviceGenericSerialEnable")
        private static native void _vexDeviceGenericSerialEnable(int device, int options);

        public static void enable(V5Device device, int options) {
            _vexDeviceGenericSerialEnable(device.raw, (int) options);
        }

        @Import(module = "vex", name = "vexDeviceGenericSerialBaudrate")
        private static native void _vexDeviceGenericSerialBaudrate(int device, int baudrate);

        public static void baudrate(V5Device device, int baudrate) {
            _vexDeviceGenericSerialBaudrate(device.raw, (int) baudrate);
        }

        @Import(module = "vex", name = "vexDeviceGenericSerialWriteChar")
        private static native int _vexDeviceGenericSerialWriteChar(int device, int c);

        public static int writeChar(V5Device device, short c) {
            return _vexDeviceGenericSerialWriteChar(device.raw, (int) c);
        }

        @Import(module = "vex", name = "vexDeviceGenericSerialWriteFree")
        private static native int _vexDeviceGenericSerialWriteFree(int device);

        public static int writeFree(V5Device device) {
            return _vexDeviceGenericSerialWriteFree(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGenericSerialTransmit")
        private static native int _vexDeviceGenericSerialTransmit(int device, int buffer, int length);

        public static int transmit(V5Device device, RawPointer buffer, int length) {
            return _vexDeviceGenericSerialTransmit(device.raw, buffer.raw, (int) length);
        }

        @Import(module = "vex", name = "vexDeviceGenericSerialReadChar")
        private static native int _vexDeviceGenericSerialReadChar(int device);

        public static int readChar(V5Device device) {
            return _vexDeviceGenericSerialReadChar(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGenericSerialPeekChar")
        private static native int _vexDeviceGenericSerialPeekChar(int device);

        public static int peekChar(V5Device device) {
            return _vexDeviceGenericSerialPeekChar(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGenericSerialReceiveAvail")
        private static native int _vexDeviceGenericSerialReceiveAvail(int device);

        public static int receiveAvail(V5Device device) {
            return _vexDeviceGenericSerialReceiveAvail(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGenericSerialReceive")
        private static native int _vexDeviceGenericSerialReceive(int device, int buffer, int length);

        public static int receive(V5Device device, RawPointer buffer, int length) {
            return _vexDeviceGenericSerialReceive(device.raw, buffer.raw, (int) length);
        }

        @Import(module = "vex", name = "vexDeviceGenericSerialFlush")
        private static native void _vexDeviceGenericSerialFlush(int device);

        public static void flush(V5Device device) {
            _vexDeviceGenericSerialFlush(device.raw);
        }
    }

    @StaticInit
    public static final class Gps {
        private Gps() {
        }

        public record Quaternion(
                double x,
                double y,
                double z,
                double w
        ) {
            public static final int SIZE = 32;

            public static Quaternion fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE
                });
                return new Quaternion((double) values[0], (double) values[1], (double) values[2], (double) values[3]);
            }
        }

        public record Attitude(
                double pitch,
                double roll,
                double yaw,
                double position_x,
                double position_y,
                double position_z,
                double az,
                double el,
                double rot
        ) {
            public static final int SIZE = 72;

            public static Attitude fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE
                });
                return new Attitude((double) values[0], (double) values[1], (double) values[2], (double) values[3], (double) values[4], (double) values[5], (double) values[6], (double) values[7], (double) values[8]);
            }
        }

        public record Raw(
                double x,
                double y,
                double z,
                double w
        ) {
            public static final int SIZE = 32;

            public static Raw fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE
                });
                return new Raw((double) values[0], (double) values[1], (double) values[2], (double) values[3]);
            }
        }

        @Import(module = "vex", name = "vexDeviceGpsReset")
        private static native void _vexDeviceGpsReset(int device);

        public static void reset(V5Device device) {
            _vexDeviceGpsReset(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGpsHeadingGet")
        private static native double _vexDeviceGpsHeadingGet(int device);

        public static double getHeading(V5Device device) {
            return _vexDeviceGpsHeadingGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGpsDegreesGet")
        private static native double _vexDeviceGpsDegreesGet(int device);

        public static double getDegrees(V5Device device) {
            return _vexDeviceGpsDegreesGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGpsQuaternionGet")
        private static native void _vexDeviceGpsQuaternionGet(int device, int data);

        public static void getQuaternion(V5Device device, RawPointer data) {
            _vexDeviceGpsQuaternionGet(device.raw, data.raw);
        }

        @Import(module = "vex", name = "vexDeviceGpsAttitudeGet")
        private static native void _vexDeviceGpsAttitudeGet(int device, int data, boolean bRaw);

        public static void getAttitude(V5Device device, RawPointer data, boolean bRaw) {
            _vexDeviceGpsAttitudeGet(device.raw, data.raw, bRaw);
        }

        @Import(module = "vex", name = "vexDeviceGpsRawGyroGet")
        private static native void _vexDeviceGpsRawGyroGet(int device, int data);

        public static void getRawGyro(V5Device device, RawPointer data) {
            _vexDeviceGpsRawGyroGet(device.raw, data.raw);
        }

        @Import(module = "vex", name = "vexDeviceGpsRawAccelGet")
        private static native void _vexDeviceGpsRawAccelGet(int device, int data);

        public static void getRawAccel(V5Device device, RawPointer data) {
            _vexDeviceGpsRawAccelGet(device.raw, data.raw);
        }

        @Import(module = "vex", name = "vexDeviceGpsStatusGet")
        private static native int _vexDeviceGpsStatusGet(int device);

        public static int getStatus(V5Device device) {
            return _vexDeviceGpsStatusGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGpsModeSet")
        private static native void _vexDeviceGpsModeSet(int device, int mode);

        public static void setMode(V5Device device, long mode) {
            _vexDeviceGpsModeSet(device.raw, (int) mode);
        }

        @Import(module = "vex", name = "vexDeviceGpsModeGet")
        private static native int _vexDeviceGpsModeGet(int device);

        public static int getMode(V5Device device) {
            return _vexDeviceGpsModeGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGpsDataRateSet")
        private static native void _vexDeviceGpsDataRateSet(int device, int rate);

        public static void setDataRate(V5Device device, long rate) {
            _vexDeviceGpsDataRateSet(device.raw, (int) rate);
        }

        @Import(module = "vex", name = "vexDeviceGpsOriginSet")
        private static native void _vexDeviceGpsOriginSet(int device, double ox, double oy);

        public static void setOrigin(V5Device device, double ox, double oy) {
            _vexDeviceGpsOriginSet(device.raw, ox, oy);
        }

        @Import(module = "vex", name = "vexDeviceGpsOriginGet")
        private static native void _vexDeviceGpsOriginGet(int device, int ox, int oy);

        public static void getOrigin(V5Device device, RawPointer ox, RawPointer oy) {
            _vexDeviceGpsOriginGet(device.raw, ox.raw, oy.raw);
        }

        @Import(module = "vex", name = "vexDeviceGpsRotationSet")
        private static native void _vexDeviceGpsRotationSet(int device, double value);

        public static void setRotation(V5Device device, double value) {
            _vexDeviceGpsRotationSet(device.raw, value);
        }

        @Import(module = "vex", name = "vexDeviceGpsRotationGet")
        private static native double _vexDeviceGpsRotationGet(int device);

        public static double getRotation(V5Device device) {
            return _vexDeviceGpsRotationGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceGpsInitialPositionSet")
        private static native void _vexDeviceGpsInitialPositionSet(int device, double initial_x, double initial_y, double initial_rotation);

        public static void setInitialPosition(V5Device device, double initial_x, double initial_y, double initial_rotation) {
            _vexDeviceGpsInitialPositionSet(device.raw, initial_x, initial_y, initial_rotation);
        }

        @Import(module = "vex", name = "vexDeviceGpsErrorGet")
        private static native double _vexDeviceGpsErrorGet(int device);

        public static double getError(V5Device device) {
            return _vexDeviceGpsErrorGet(device.raw);
        }
    }

    @StaticInit
    public static final class Imu {
        private Imu() {
        }

        public record Quaternion(
                double a,
                double b,
                double c,
                double d
        ) {
            public static final int SIZE = 32;

            public static Quaternion fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE
                });
                return new Quaternion((double) values[0], (double) values[1], (double) values[2], (double) values[3]);
            }
        }

        public record Attitude(
                double pitch,
                double roll,
                double yaw
        ) {
            public static final int SIZE = 24;

            public static Attitude fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE
                });
                return new Attitude((double) values[0], (double) values[1], (double) values[2]);
            }
        }

        public record Raw(
                double x,
                double y,
                double z,
                double w
        ) {
            public static final int SIZE = 32;

            public static Raw fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE
                });
                return new Raw((double) values[0], (double) values[1], (double) values[2], (double) values[3]);
            }
        }

        @Import(module = "vex", name = "vexDeviceImuReset")
        private static native void _vexDeviceImuReset(int device);

        /**
         * Calibrates the IMU. This function is non-blocking.
         */
        public static void reset(V5Device device) {
            _vexDeviceImuReset(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceImuHeadingGet")
        private static native double _vexDeviceImuHeadingGet(int device);

        /**
         * Returns the yaw-axis rotation of the IMU as an unbounded angle in degrees.
         */
        public static double getHeading(V5Device device) {
            return _vexDeviceImuHeadingGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceImuDegreesGet")
        private static native double _vexDeviceImuDegreesGet(int device);

        /**
         * Returns the yaw-axis rotation of the IMU as an angle in degrees bounded 0-360.
         */
        public static double getDegrees(V5Device device) {
            return _vexDeviceImuDegreesGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceImuQuaternionGet")
        private static native void _vexDeviceImuQuaternionGet(int device, int data);

        /**
         * Returns quaternion defined by the IMU's rotation.
         */
        public static void getQuaternion(V5Device device, RawPointer data) {
            _vexDeviceImuQuaternionGet(device.raw, data.raw);
        }

        @Import(module = "vex", name = "vexDeviceImuAttitudeGet")
        private static native void _vexDeviceImuAttitudeGet(int device, int data);

        /**
         * Returns the 3-axis euler angles of the IMU bounded from -180 to 180 degrees.
         */
        public static void getAttitude(V5Device device, RawPointer data) {
            _vexDeviceImuAttitudeGet(device.raw, data.raw);
        }

        @Import(module = "vex", name = "vexDeviceImuRawGyroGet")
        private static native void _vexDeviceImuRawGyroGet(int device, int data);

        /**
         * Returns the raw gyroscope readings of the IMU in degrees per second,
         */
        public static void getRawGyro(V5Device device, RawPointer data) {
            _vexDeviceImuRawGyroGet(device.raw, data.raw);
        }

        @Import(module = "vex", name = "vexDeviceImuRawAccelGet")
        private static native void _vexDeviceImuRawAccelGet(int device, int data);

        /**
         * Returns the raw accelerometer readings of the IMU in G.
         */
        public static void getRawAccel(V5Device device, RawPointer data) {
            _vexDeviceImuRawAccelGet(device.raw, data.raw);
        }

        @Import(module = "vex", name = "vexDeviceImuStatusGet")
        private static native int _vexDeviceImuStatusGet(int device);

        /**
         * Returns the IMU's status bits.
         */
        public static int getStatus(V5Device device) {
            return _vexDeviceImuStatusGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceImuModeSet")
        private static native void _vexDeviceImuModeSet(int device, int mode);

        public static void setMode(V5Device device, long mode) {
            _vexDeviceImuModeSet(device.raw, (int) mode);
        }

        @Import(module = "vex", name = "vexDeviceImuModeGet")
        private static native int _vexDeviceImuModeGet(int device);

        public static int getMode(V5Device device) {
            return _vexDeviceImuModeGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceImuDataRateSet")
        private static native void _vexDeviceImuDataRateSet(int device, int rate);

        public static void setDataRate(V5Device device, long rate) {
            _vexDeviceImuDataRateSet(device.raw, (int) rate);
        }
    }

    @StaticInit
    public static final class Led {
        private Led() {
        }

        public record Color(byte value) {
            public static final Color BLACK = new Color((byte) 0);
        }

        @Import(module = "vex", name = "vexDeviceLedSet")
        private static native void _vexDeviceLedSet(int device, int value);

        public static void setLed(V5Device device, Color value) {
            _vexDeviceLedSet(device.raw, value.value);
        }

        @Import(module = "vex", name = "vexDeviceLedRgbSet")
        private static native void _vexDeviceLedRgbSet(int device, int color);

        public static void setRgb(V5Device device, long color) {
            _vexDeviceLedRgbSet(device.raw, (int) color);
        }

        @Import(module = "vex", name = "vexDeviceLedGet")
        private static native int _vexDeviceLedGet(int device);

        public static Color getLed(V5Device device) {
            var result = _vexDeviceLedGet(device.raw);
            return new Color((byte) result);
        }

        @Import(module = "vex", name = "vexDeviceLedRgbGet")
        private static native int _vexDeviceLedRgbGet(int device);

        public static int getRgb(V5Device device) {
            return _vexDeviceLedRgbGet(device.raw);
        }
    }

    @StaticInit
    public static final class LightTower {
        private LightTower() {
        }

        @Import(module = "vex", name = "vexDeviceLightTowerBlinkSet")
        private static native void _vexDeviceLightTowerBlinkSet(int device, int select, int mask, int onTime, int offTime);

        public static void setBlink(V5Device device, short select, short mask, int onTime, int offTime) {
            _vexDeviceLightTowerBlinkSet(device.raw, (int) select, (int) mask, (int) onTime, (int) offTime);
        }

        @Import(module = "vex", name = "vexDeviceLightTowerColorSet")
        private static native void _vexDeviceLightTowerColorSet(int device, int color_id, int value);

        public static void setColor(V5Device device, long color_id, long value) {
            _vexDeviceLightTowerColorSet(device.raw, (int) color_id, (int) value);
        }

        @Import(module = "vex", name = "vexDeviceLightTowerRgbGet")
        private static native int _vexDeviceLightTowerRgbGet(int device);

        public static int getRgb(V5Device device) {
            return _vexDeviceLightTowerRgbGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceLightTowerRgbSet")
        private static native void _vexDeviceLightTowerRgbSet(int device, int rgb_value, int xyw_value);

        public static void setRgb(V5Device device, long rgb_value, long xyw_value) {
            _vexDeviceLightTowerRgbSet(device.raw, (int) rgb_value, (int) xyw_value);
        }

        @Import(module = "vex", name = "vexDeviceLightTowerStatusGet")
        private static native int _vexDeviceLightTowerStatusGet(int device);

        public static int getStatus(V5Device device) {
            return _vexDeviceLightTowerStatusGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceLightTowerDebugGet")
        private static native int _vexDeviceLightTowerDebugGet(int device, int id);

        public static int getDebug(V5Device device, int id) {
            return _vexDeviceLightTowerDebugGet(device.raw, (int) id);
        }

        @Import(module = "vex", name = "vexDeviceLightTowerXywGet")
        private static native int _vexDeviceLightTowerXywGet(int device);

        public static int getXyw(V5Device device) {
            return _vexDeviceLightTowerXywGet(device.raw);
        }
    }

    @StaticInit
    public static final class Magnet {
        private Magnet() {
        }

        public record Duration(byte value) {
            public static final Duration SHORT = new Duration((byte) 0);
            public static final Duration MEDIUM = new Duration((byte) 1);
            public static final Duration LONG = new Duration((byte) 2);
            public static final Duration EXTRA_LONG = new Duration((byte) 3);
        }

        @Import(module = "vex", name = "vexDeviceMagnetPowerSet")
        private static native void _vexDeviceMagnetPowerSet(int device, int value, int time);

        public static void setPower(V5Device device, int value, int time) {
            _vexDeviceMagnetPowerSet(device.raw, (int) value, (int) time);
        }

        @Import(module = "vex", name = "vexDeviceMagnetPowerGet")
        private static native int _vexDeviceMagnetPowerGet(int device);

        public static int getPower(V5Device device) {
            return _vexDeviceMagnetPowerGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMagnetPickup")
        private static native void _vexDeviceMagnetPickup(int device, int duration);

        public static void pickup(V5Device device, Duration duration) {
            _vexDeviceMagnetPickup(device.raw, duration.value);
        }

        @Import(module = "vex", name = "vexDeviceMagnetDrop")
        private static native void _vexDeviceMagnetDrop(int device, int duration);

        public static void drop(V5Device device, Duration duration) {
            _vexDeviceMagnetDrop(device.raw, duration.value);
        }

        @Import(module = "vex", name = "vexDeviceMagnetTemperatureGet")
        private static native double _vexDeviceMagnetTemperatureGet(int device);

        public static double getTemperature(V5Device device) {
            return _vexDeviceMagnetTemperatureGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMagnetCurrentGet")
        private static native double _vexDeviceMagnetCurrentGet(int device);

        public static double getCurrent(V5Device device) {
            return _vexDeviceMagnetCurrentGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMagnetStatusGet")
        private static native int _vexDeviceMagnetStatusGet(int device);

        public static int getStatus(V5Device device) {
            return _vexDeviceMagnetStatusGet(device.raw);
        }
    }

    @StaticInit
    public static final class Motor {
        private Motor() {
        }

        public record ControlMode(byte value) {
            public static final ControlMode OFF = new ControlMode((byte) 0);
            public static final ControlMode BRAKE = new ControlMode((byte) 1);
            public static final ControlMode HOLD = new ControlMode((byte) 2);
            public static final ControlMode SERVO = new ControlMode((byte) 3);
            public static final ControlMode PROFILE = new ControlMode((byte) 4);
            public static final ControlMode VELOCITY = new ControlMode((byte) 5);
            public static final ControlMode UNDEFINED = new ControlMode((byte) 6);
        }

        public record EncoderUnits(byte value) {
            public static final EncoderUnits DEGREES = new EncoderUnits((byte) 0);
            public static final EncoderUnits ROTATIONS = new EncoderUnits((byte) 1);
            public static final EncoderUnits COUNTS = new EncoderUnits((byte) 2);
        }

        public record BrakeMode(byte value) {
            public static final BrakeMode COAST = new BrakeMode((byte) 0);
            public static final BrakeMode BRAKE = new BrakeMode((byte) 1);
            public static final BrakeMode HOLD = new BrakeMode((byte) 2);
        }

        public record Gearset(byte value) {
            public static final Gearset _36 = new Gearset((byte) 0);
            public static final Gearset _18 = new Gearset((byte) 1);
            public static final Gearset _06 = new Gearset((byte) 2);
        }

        public record Pid(
                short kf,
                short kp,
                short ki,
                short kd,
                short filter,
                int limit,
                short threshold,
                short loopspeed
        ) {
            public static final int SIZE = 12;

            public static Pid fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u16,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.i16
                });
                return new Pid((short) values[0], (short) values[1], (short) values[2], (short) values[3], (short) values[4], (int) values[6], (short) values[7], (short) values[8]);
            }
        }

        @Import(module = "vex", name = "vexDeviceMotorVelocitySet")
        private static native void _vexDeviceMotorVelocitySet(int device, int velocity);

        public static void setVelocity(V5Device device, int velocity) {
            _vexDeviceMotorVelocitySet(device.raw, (int) velocity);
        }

        @Import(module = "vex", name = "vexDeviceMotorVelocityGet")
        private static native int _vexDeviceMotorVelocityGet(int device);

        public static int getVelocity(V5Device device) {
            return _vexDeviceMotorVelocityGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorActualVelocityGet")
        private static native double _vexDeviceMotorActualVelocityGet(int device);

        public static double getActualVelocity(V5Device device) {
            return _vexDeviceMotorActualVelocityGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorDirectionGet")
        private static native int _vexDeviceMotorDirectionGet(int device);

        public static int getDirection(V5Device device) {
            return _vexDeviceMotorDirectionGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorModeSet")
        private static native void _vexDeviceMotorModeSet(int device, int mode);

        public static void setMode(V5Device device, ControlMode mode) {
            _vexDeviceMotorModeSet(device.raw, mode.value);
        }

        @Import(module = "vex", name = "vexDeviceMotorModeGet")
        private static native int _vexDeviceMotorModeGet(int device);

        public static ControlMode getMode(V5Device device) {
            var result = _vexDeviceMotorModeGet(device.raw);
            return new ControlMode((byte) result);
        }

        @Import(module = "vex", name = "vexDeviceMotorPwmSet")
        private static native void _vexDeviceMotorPwmSet(int device, int pwm);

        public static void setPwm(V5Device device, int pwm) {
            _vexDeviceMotorPwmSet(device.raw, (int) pwm);
        }

        @Import(module = "vex", name = "vexDeviceMotorPwmGet")
        private static native int _vexDeviceMotorPwmGet(int device);

        public static int getPwm(V5Device device) {
            return _vexDeviceMotorPwmGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorCurrentLimitSet")
        private static native void _vexDeviceMotorCurrentLimitSet(int device, int limit);

        public static void setCurrentLimit(V5Device device, int limit) {
            _vexDeviceMotorCurrentLimitSet(device.raw, (int) limit);
        }

        @Import(module = "vex", name = "vexDeviceMotorCurrentLimitGet")
        private static native int _vexDeviceMotorCurrentLimitGet(int device);

        public static int getCurrentLimit(V5Device device) {
            return _vexDeviceMotorCurrentLimitGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorCurrentGet")
        private static native int _vexDeviceMotorCurrentGet(int device);

        public static int getCurrent(V5Device device) {
            return _vexDeviceMotorCurrentGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorPowerGet")
        private static native double _vexDeviceMotorPowerGet(int device);

        public static double getPower(V5Device device) {
            return _vexDeviceMotorPowerGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorTorqueGet")
        private static native double _vexDeviceMotorTorqueGet(int device);

        public static double getTorque(V5Device device) {
            return _vexDeviceMotorTorqueGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorEfficiencyGet")
        private static native double _vexDeviceMotorEfficiencyGet(int device);

        public static double getEfficiency(V5Device device) {
            return _vexDeviceMotorEfficiencyGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorTemperatureGet")
        private static native double _vexDeviceMotorTemperatureGet(int device);

        public static double getTemperature(V5Device device) {
            return _vexDeviceMotorTemperatureGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorOverTempFlagGet")
        private static native boolean _vexDeviceMotorOverTempFlagGet(int device);

        public static boolean getOverTempFlag(V5Device device) {
            return _vexDeviceMotorOverTempFlagGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorCurrentLimitFlagGet")
        private static native boolean _vexDeviceMotorCurrentLimitFlagGet(int device);

        public static boolean getCurrentLimitFlag(V5Device device) {
            return _vexDeviceMotorCurrentLimitFlagGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorZeroVelocityFlagGet")
        private static native boolean _vexDeviceMotorZeroVelocityFlagGet(int device);

        public static boolean getZeroVelocityFlag(V5Device device) {
            return _vexDeviceMotorZeroVelocityFlagGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorZeroPositionFlagGet")
        private static native boolean _vexDeviceMotorZeroPositionFlagGet(int device);

        public static boolean getZeroPositionFlag(V5Device device) {
            return _vexDeviceMotorZeroPositionFlagGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorReverseFlagSet")
        private static native void _vexDeviceMotorReverseFlagSet(int device, boolean reverse);

        public static void setReverseFlag(V5Device device, boolean reverse) {
            _vexDeviceMotorReverseFlagSet(device.raw, reverse);
        }

        @Import(module = "vex", name = "vexDeviceMotorReverseFlagGet")
        private static native boolean _vexDeviceMotorReverseFlagGet(int device);

        public static boolean getReverseFlag(V5Device device) {
            return _vexDeviceMotorReverseFlagGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorEncoderUnitsSet")
        private static native void _vexDeviceMotorEncoderUnitsSet(int device, int units);

        public static void setEncoderUnits(V5Device device, EncoderUnits units) {
            _vexDeviceMotorEncoderUnitsSet(device.raw, units.value);
        }

        @Import(module = "vex", name = "vexDeviceMotorEncoderUnitsGet")
        private static native int _vexDeviceMotorEncoderUnitsGet(int device);

        public static EncoderUnits getEncoderUnits(V5Device device) {
            var result = _vexDeviceMotorEncoderUnitsGet(device.raw);
            return new EncoderUnits((byte) result);
        }

        @Import(module = "vex", name = "vexDeviceMotorBrakeModeSet")
        private static native void _vexDeviceMotorBrakeModeSet(int device, int mode);

        public static void setBrakeMode(V5Device device, BrakeMode mode) {
            _vexDeviceMotorBrakeModeSet(device.raw, mode.value);
        }

        @Import(module = "vex", name = "vexDeviceMotorBrakeModeGet")
        private static native int _vexDeviceMotorBrakeModeGet(int device);

        public static BrakeMode getBrakeMode(V5Device device) {
            var result = _vexDeviceMotorBrakeModeGet(device.raw);
            return new BrakeMode((byte) result);
        }

        @Import(module = "vex", name = "vexDeviceMotorPositionSet")
        private static native void _vexDeviceMotorPositionSet(int device, double position);

        public static void setPosition(V5Device device, double position) {
            _vexDeviceMotorPositionSet(device.raw, position);
        }

        @Import(module = "vex", name = "vexDeviceMotorPositionGet")
        private static native double _vexDeviceMotorPositionGet(int device);

        public static double getPosition(V5Device device) {
            return _vexDeviceMotorPositionGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorPositionRawGet")
        private static native int _vexDeviceMotorPositionRawGet(int device, int timestamp);

        public static int getPositionRaw(V5Device device, RawPointer timestamp) {
            return _vexDeviceMotorPositionRawGet(device.raw, timestamp.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorPositionReset")
        private static native void _vexDeviceMotorPositionReset(int device);

        public static void positionReset(V5Device device) {
            _vexDeviceMotorPositionReset(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorTargetGet")
        private static native double _vexDeviceMotorTargetGet(int device);

        public static double getTarget(V5Device device) {
            return _vexDeviceMotorTargetGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorServoTargetSet")
        private static native void _vexDeviceMotorServoTargetSet(int device, double position);

        public static void setServoTarget(V5Device device, double position) {
            _vexDeviceMotorServoTargetSet(device.raw, position);
        }

        @Import(module = "vex", name = "vexDeviceMotorAbsoluteTargetSet")
        private static native void _vexDeviceMotorAbsoluteTargetSet(int device, double position, int veloctiy);

        public static void setAbsoluteTarget(V5Device device, double position, int veloctiy) {
            _vexDeviceMotorAbsoluteTargetSet(device.raw, position, (int) veloctiy);
        }

        @Import(module = "vex", name = "vexDeviceMotorRelativeTargetSet")
        private static native void _vexDeviceMotorRelativeTargetSet(int device, double position, int velocity);

        public static void setRelativeTarget(V5Device device, double position, int velocity) {
            _vexDeviceMotorRelativeTargetSet(device.raw, position, (int) velocity);
        }

        @Import(module = "vex", name = "vexDeviceMotorFaultsGet")
        private static native int _vexDeviceMotorFaultsGet(int device);

        public static int getFaults(V5Device device) {
            return _vexDeviceMotorFaultsGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorFlagsGet")
        private static native int _vexDeviceMotorFlagsGet(int device);

        public static int getFlags(V5Device device) {
            return _vexDeviceMotorFlagsGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorVoltageSet")
        private static native void _vexDeviceMotorVoltageSet(int device, int voltage);

        public static void setVoltage(V5Device device, int voltage) {
            _vexDeviceMotorVoltageSet(device.raw, (int) voltage);
        }

        @Import(module = "vex", name = "vexDeviceMotorVoltageGet")
        private static native int _vexDeviceMotorVoltageGet(int device);

        public static int getVoltage(V5Device device) {
            return _vexDeviceMotorVoltageGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorGearingSet")
        private static native void _vexDeviceMotorGearingSet(int device, int gearset);

        public static void setGearing(V5Device device, Gearset gearset) {
            _vexDeviceMotorGearingSet(device.raw, gearset.value);
        }

        @Import(module = "vex", name = "vexDeviceMotorGearingGet")
        private static native int _vexDeviceMotorGearingGet(int device);

        public static Gearset getGearing(V5Device device) {
            var result = _vexDeviceMotorGearingGet(device.raw);
            return new Gearset((byte) result);
        }

        @Import(module = "vex", name = "vexDeviceMotorVoltageLimitSet")
        private static native void _vexDeviceMotorVoltageLimitSet(int device, int limit);

        public static void setVoltageLimit(V5Device device, int limit) {
            _vexDeviceMotorVoltageLimitSet(device.raw, (int) limit);
        }

        @Import(module = "vex", name = "vexDeviceMotorVoltageLimitGet")
        private static native int _vexDeviceMotorVoltageLimitGet(int device);

        public static int getVoltageLimit(V5Device device) {
            return _vexDeviceMotorVoltageLimitGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorVelocityUpdate")
        private static native void _vexDeviceMotorVelocityUpdate(int device, int velocity);

        public static void velocityUpdate(V5Device device, int velocity) {
            _vexDeviceMotorVelocityUpdate(device.raw, (int) velocity);
        }

        @Import(module = "vex", name = "vexDeviceMotorPositionPidSet")
        private static native void _vexDeviceMotorPositionPidSet(int device, int pid);

        public static void setPositionPid(V5Device device, RawPointer pid) {
            _vexDeviceMotorPositionPidSet(device.raw, pid.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorVelocityPidSet")
        private static native void _vexDeviceMotorVelocityPidSet(int device, int pid);

        public static void setVelocityPid(V5Device device, RawPointer pid) {
            _vexDeviceMotorVelocityPidSet(device.raw, pid.raw);
        }

        @Import(module = "vex", name = "vexDeviceMotorExternalProfileSet")
        private static native void _vexDeviceMotorExternalProfileSet(int device, double position, int velocity);

        public static void setExternalProfile(V5Device device, double position, int velocity) {
            _vexDeviceMotorExternalProfileSet(device.raw, position, (int) velocity);
        }
    }

    @StaticInit
    public static final class Optical {
        private Optical() {
        }

        public record Rgb(
                double red,
                double green,
                double blue,
                double brightness
        ) {
            public static final int SIZE = 32;

            public static Rgb fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE,
                        Util.Primitive.DOUBLE
                });
                return new Rgb((double) values[0], (double) values[1], (double) values[2], (double) values[3]);
            }
        }

        public record Raw(
                int clear,
                int red,
                int green,
                int blue
        ) {
            public static final int SIZE = 8;

            public static Raw fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.u16,
                        Util.Primitive.u16,
                        Util.Primitive.u16,
                        Util.Primitive.u16
                });
                return new Raw((int) values[0], (int) values[1], (int) values[2], (int) values[3]);
            }
        }

        public record Gesture(
                short udata,
                short ddata,
                short ldata,
                short rdata,
                short gesture_type,
                int count,
                long time
        ) {
            public static final int SIZE = 12;

            public static Gesture fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u16,
                        Util.Primitive.u32
                });
                return new Gesture((short) values[0], (short) values[1], (short) values[2], (short) values[3], (short) values[4], (int) values[6], (long) values[7]);
            }
        }

        @Import(module = "vex", name = "vexDeviceOpticalHueGet")
        private static native double _vexDeviceOpticalHueGet(int device);

        public static double getHue(V5Device device) {
            return _vexDeviceOpticalHueGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalSatGet")
        private static native double _vexDeviceOpticalSatGet(int device);

        public static double getSat(V5Device device) {
            return _vexDeviceOpticalSatGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalBrightnessGet")
        private static native double _vexDeviceOpticalBrightnessGet(int device);

        public static double getBrightness(V5Device device) {
            return _vexDeviceOpticalBrightnessGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalProximityGet")
        private static native int _vexDeviceOpticalProximityGet(int device);

        public static int getProximity(V5Device device) {
            return _vexDeviceOpticalProximityGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalRgbGet")
        private static native void _vexDeviceOpticalRgbGet(int device, int data);

        public static void getRgb(V5Device device, RawPointer data) {
            _vexDeviceOpticalRgbGet(device.raw, data.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalLedPwmSet")
        private static native void _vexDeviceOpticalLedPwmSet(int device, int value);

        public static void setLedPwm(V5Device device, int value) {
            _vexDeviceOpticalLedPwmSet(device.raw, (int) value);
        }

        @Import(module = "vex", name = "vexDeviceOpticalLedPwmGet")
        private static native int _vexDeviceOpticalLedPwmGet(int device);

        public static int getLedPwm(V5Device device) {
            return _vexDeviceOpticalLedPwmGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalStatusGet")
        private static native int _vexDeviceOpticalStatusGet(int device);

        public static int getStatus(V5Device device) {
            return _vexDeviceOpticalStatusGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalRawGet")
        private static native void _vexDeviceOpticalRawGet(int device, int data);

        public static void getRaw(V5Device device, RawPointer data) {
            _vexDeviceOpticalRawGet(device.raw, data.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalModeSet")
        private static native void _vexDeviceOpticalModeSet(int device, int mode);

        public static void setMode(V5Device device, long mode) {
            _vexDeviceOpticalModeSet(device.raw, (int) mode);
        }

        @Import(module = "vex", name = "vexDeviceOpticalModeGet")
        private static native int _vexDeviceOpticalModeGet(int device);

        public static int getMode(V5Device device) {
            return _vexDeviceOpticalModeGet(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalGestureGet")
        private static native int _vexDeviceOpticalGestureGet(int device, int pData);

        public static int getGesture(V5Device device, RawPointer pData) {
            return _vexDeviceOpticalGestureGet(device.raw, pData.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalGestureEnable")
        private static native void _vexDeviceOpticalGestureEnable(int device);

        public static void gestureEnable(V5Device device) {
            _vexDeviceOpticalGestureEnable(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalGestureDisable")
        private static native void _vexDeviceOpticalGestureDisable(int device);

        public static void gestureDisable(V5Device device) {
            _vexDeviceOpticalGestureDisable(device.raw);
        }

        @Import(module = "vex", name = "vexDeviceOpticalProximityThreshold")
        private static native void _vexDeviceOpticalProximityThreshold(int device, int value);

        public static void proximityThreshold(V5Device device, int value) {
            _vexDeviceOpticalProximityThreshold(device.raw, (int) value);
        }

        @Import(module = "vex", name = "vexDeviceOpticalIntegrationTimeSet")
        private static native void _vexDeviceOpticalIntegrationTimeSet(int device, double timeMs);

        public static void setIntegrationTime(V5Device device, double timeMs) {
            _vexDeviceOpticalIntegrationTimeSet(device.raw, timeMs);
        }

        @Import(module = "vex", name = "vexDeviceOpticalIntegrationTimeGet")
        private static native double _vexDeviceOpticalIntegrationTimeGet(int device);

        public static double getIntegrationTime(V5Device device) {
            return _vexDeviceOpticalIntegrationTimeGet(device.raw);
        }
    }

    @StaticInit
    public static final class Pneumatic {
        private Pneumatic() {
        }

        public record Ctrl(
                int flags,
                short m1_pwm,
                short m2_pwm,
                short m3_pwm,
                short m4_pwm,
                short m1_time,
                short m2_time,
                short m3_time,
                short m4_time,
                short comp_pwm
        ) {
            public static final int SIZE = 11;

            public static Ctrl fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.u16,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8,
                        Util.Primitive.u8
                });
                return new Ctrl((int) values[0], (short) values[1], (short) values[2], (short) values[3], (short) values[4], (short) values[5], (short) values[6], (short) values[7], (short) values[8], (short) values[9]);
            }
        }

        @Import(module = "vex", name = "vexDevicePneumaticActuationStatusGet")
        private static native int _vexDevicePneumaticActuationStatusGet(int device, int ac1, int ac2, int ac3, int ac4);

        public static int getActuationStatus(V5Device device, RawPointer ac1, RawPointer ac2, RawPointer ac3, RawPointer ac4) {
            return _vexDevicePneumaticActuationStatusGet(device.raw, ac1.raw, ac2.raw, ac3.raw, ac4.raw);
        }

        @Import(module = "vex", name = "vexDevicePneumaticCompressorSet")
        private static native void _vexDevicePneumaticCompressorSet(int device, boolean bState);

        public static void setCompressor(V5Device device, boolean bState) {
            _vexDevicePneumaticCompressorSet(device.raw, bState);
        }

        @Import(module = "vex", name = "vexDevicePneumaticCtrlSet")
        private static native void _vexDevicePneumaticCtrlSet(int device, int pCtrl);

        public static void setCtrl(V5Device device, RawPointer pCtrl) {
            _vexDevicePneumaticCtrlSet(device.raw, pCtrl.raw);
        }

        @Import(module = "vex", name = "vexDevicePneumaticCylinderPwmSet")
        private static native void _vexDevicePneumaticCylinderPwmSet(int device, int id, boolean bState, int pwm);

        public static void setCylinderPwm(V5Device device, long id, boolean bState, short pwm) {
            _vexDevicePneumaticCylinderPwmSet(device.raw, (int) id, bState, (int) pwm);
        }

        @Import(module = "vex", name = "vexDevicePneumaticCylinderSet")
        private static native void _vexDevicePneumaticCylinderSet(int device, int id, boolean bState);

        public static void setCylinder(V5Device device, long id, boolean bState) {
            _vexDevicePneumaticCylinderSet(device.raw, (int) id, bState);
        }

        @Import(module = "vex", name = "vexDevicePneumaticPwmGet")
        private static native int _vexDevicePneumaticPwmGet(int device);

        public static int getPwm(V5Device device) {
            return _vexDevicePneumaticPwmGet(device.raw);
        }

        @Import(module = "vex", name = "vexDevicePneumaticPwmSet")
        private static native void _vexDevicePneumaticPwmSet(int device, int pwm);

        public static void setPwm(V5Device device, short pwm) {
            _vexDevicePneumaticPwmSet(device.raw, (int) pwm);
        }

        @Import(module = "vex", name = "vexDevicePneumaticStatusGet")
        private static native int _vexDevicePneumaticStatusGet(int device);

        public static int getStatus(V5Device device) {
            return _vexDevicePneumaticStatusGet(device.raw);
        }
    }

    @StaticInit
    public static final class Range {
        private Range() {
        }

        @Import(module = "vex", name = "vexDeviceRangeValueGet")
        private static native int _vexDeviceRangeValueGet(int device);

        /**
         * Rangefinder/Lidar - actual API to be determined
         */
        public static int getValue(V5Device device) {
            return _vexDeviceRangeValueGet(device.raw);
        }
    }

    @StaticInit
    public static final class Serial {
        private Serial() {
        }

        @Import(module = "vex", name = "vexSerialWriteChar")
        private static native int _vexSerialWriteChar(int channel, int c);

        /**
         * Writes a single byte to the serial FIFO output buffer.
         * <p>
         * # Arguments
         * <p>
         * - `channel`: The serial communications channel to write to. Use `1` for stdio.
         * - `c`: The byte to write.
         * <p>
         * # Return
         * <p>
         * The number of bytes written, or -1 if an internal error occurred.
         */
        public static int writeChar(long channel, short c) {
            return _vexSerialWriteChar((int) channel, (int) c);
        }

        @Import(module = "vex", name = "vexSerialWriteBuffer")
        private static native int _vexSerialWriteBuffer(int channel, int data, int data_len);

        /**
         * Writes an arbitrary buffer to the serial FIFO output buffer, returning how
         * many bytes were written. The output buffer has a maximum size of 2048 bytes,
         * meaning that a larger buffer may be truncated.
         * <p>
         * # Arguments
         * <p>
         * - `channel`: The serial communications channel to write to. Use `1` for stdio.
         * - `data`: A buffer of bytes to write.
         * - `data_len`: The length of the specified buffer.
         * <p>
         * # Return
         * <p>
         * The number of bytes written, or -1 if an internal error occurred.
         */
        public static int writeBuffer(long channel, RawPointer data, long data_len) {
            return _vexSerialWriteBuffer((int) channel, data.raw, (int) data_len);
        }

        @Import(module = "vex", name = "vexSerialReadChar")
        private static native int _vexSerialReadChar(int channel);

        /**
         * Reads a single byte from the input buffer.
         * <p>
         * # Arguments
         * <p>
         * - `channel`: The serial communications channel to read from. Use `1` for stdio.
         * <p>
         * # Return
         * <p>
         * The next byte in the input buffer, or -1 if no character is available to be read.
         */
        public static int readChar(long channel) {
            return _vexSerialReadChar((int) channel);
        }

        @Import(module = "vex", name = "vexSerialPeekChar")
        private static native int _vexSerialPeekChar(int channel);

        /**
         * Returns the next available byte to be read in the input buffer without removing
         * it from the buffer.
         * <p>
         * # Arguments
         * <p>
         * - `channel`: The serial communications channel to read from. Use `1` for stdio.
         * <p>
         * # Return
         * <p>
         * The next byte in the input buffer, or -1 if no character is available to be read.
         */
        public static int peekChar(long channel) {
            return _vexSerialPeekChar((int) channel);
        }

        @Import(module = "vex", name = "vexSerialWriteFree")
        private static native int _vexSerialWriteFree(int channel);

        /**
         * Returns the number of free bytes (out of `2048`) remaining in the serial output
         * buffer.
         * <p>
         * # Arguments
         * <p>
         * - `channel`: The serial communications channel to read from. Use `1` for stdio.
         * <p>
         * # Return
         * <p>
         * The number of remaining available bytes.
         */
        public static int writeFree(long channel) {
            return _vexSerialWriteFree((int) channel);
        }
    }

    @StaticInit
    public static final class Touch {
        private Touch() {
        }

        public record Event(byte value) {
            public static final Event RELEASE = new Event((byte) 0);
            public static final Event PRESS = new Event((byte) 1);
            public static final Event PRESS_AUTO = new Event((byte) 2);
        }

        public record Status(
                Event lastEvent,
                short lastXpos,
                short lastYpos,
                int pressCount,
                int releaseCount
        ) {
            public static final int SIZE = 13;

            public static Status fromByteArray(byte[] array) {
                var values = Util.parseStruct(array, new Util.Primitive[]{
                        Util.Primitive.i8,
                        Util.Primitive.i16,
                        Util.Primitive.i16,
                        Util.Primitive.i32,
                        Util.Primitive.i32
                });
                return new Status(new Event((byte) values[0]), (short) values[1], (short) values[2], (int) values[3], (int) values[4]);
            }
        }

        @Import(module = "vex", name = "vexTouchDataGet")
        private static native void _vexTouchDataGet(int status);

        public static void getData(RawPointer status) {
            _vexTouchDataGet(status.raw);
        }
    }
}