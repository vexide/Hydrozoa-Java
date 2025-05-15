package dev.vexide.hydrozoa.devices.smart;

import dev.vexide.hydrozoa.CompetitionRuntime;
import dev.vexide.hydrozoa.DeviceException;
import dev.vexide.hydrozoa.sdk.VexMotorBrakeMode;
import dev.vexide.hydrozoa.sdk.VexMotorEncoderUnits;
import dev.vexide.hydrozoa.sdk.VexMotorGearset;
import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A V5 Smart Motor connected to the robot brain.
 */
public class Motor extends SmartDevice {
    /**
     * The maximum voltage that can be applied to the 11 watt variant of the V5 Smart Motor.
     */
    public static final double V5_MAX_VOLTAGE = 12.0;

    private @NotNull Gearset gearset;
    private @NotNull Direction direction;
    private @NotNull MotorControl target = new MotorControl.Voltage(0.0);

    /**
     * Creates a new 11 watt V5 Smart Motor.
     *
     * @param port      the smart port the motor is connected to
     * @param gearset   the motor's built-in gearset color
     * @param direction the direction to which a positive voltage should cause the motor to spin
     */
    public Motor(@NotNull SmartPort port, @NotNull Gearset gearset, @NotNull Direction direction) {
        super(port);
        this.gearset = gearset;
        this.direction = direction;

        var handle = this.port.deviceHandle();
        VexSdk.Device.setMotorEncoderUnits(handle, VexMotorEncoderUnits.COUNTS);
        VexSdk.Device.setMotorReverseFlag(handle, direction.equals(Direction.REVERSE));
        VexSdk.Device.setMotorGearing(handle, gearset.getRaw());
    }

    @Override
    public @NotNull Type getDeviceType() {
        return Type.MOTOR;
    }

    /**
     * Gets the gearset of this motor.
     *
     * @return the gearset
     */
    public @NotNull Gearset getGearset() {
        return gearset;
    }

    /**
     * Sets the gearset of this motor.
     *
     * @param gearset the new gearset
     */
    public void setGearset(@NotNull Gearset gearset) {
        this.gearset = gearset;
        VexSdk.Device.setMotorGearing(port.deviceHandle(), gearset.getRaw());
    }

    /**
     * Gets the direction of this motor.
     *
     * @return the direction
     */
    public @NotNull Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction of this motor.
     *
     * @param direction the new direction
     */
    public void setDirection(@NotNull Direction direction) {
        this.direction = direction;
        VexSdk.Device.setMotorReverseFlag(port.deviceHandle(), direction.equals(Direction.REVERSE));
    }

    /**
     * Applies a voltage control request to the motor.
     *
     * @param voltage the desired voltage to apply, in volts
     * @throws DeviceException if the motor is not connected or the robot is disabled
     * @see MotorControl.Voltage
     */
    public void setVoltage(double voltage) throws DeviceException {
        setTarget(new MotorControl.Voltage(voltage));
    }

    /**
     * Applies a velocity control request to the motor.
     *
     * @param rpm the desired speed of the motor during the movement operation, in rotations per minute
     * @throws DeviceException if the motor is not connected or the robot is disabled
     * @see MotorControl.Velocity
     */
    public void spinToVelocity(int rpm) throws DeviceException {
        setTarget(new MotorControl.Velocity(rpm));
    }

    /**
     * Applies a position control request to the motor.
     *
     * @param position the desired position to move the motor to
     * @param velocity the maximum desired speed of the motor during the movement operation, in rotations per minute
     * @throws DeviceException if the motor is not connected or the robot is disabled
     * @see MotorControl.Position
     */
    public void moveToPosition(@NotNull EncoderPosition position, int velocity) throws DeviceException {
        setTarget(new MotorControl.Position(position, velocity));
    }

    /**
     * Removes power from the motor and applies a specified brake mode to stop it.
     *
     * @param mode the brake mode to use when stopping the motor
     * @throws DeviceException if the motor is not connected or the robot is disabled
     * @see MotorControl.Brake
     */
    public void brake(@NotNull BrakeMode mode) throws DeviceException {
        setTarget(new MotorControl.Brake(mode));
    }

    /**
     * Gets the last control request applied to the motor.
     *
     * @return the last control request
     */
    public @NotNull MotorControl getTarget() {
        return this.target;
    }

    /**
     * Applies a control request to the motor.
     *
     * @param target the control request to apply
     * @throws DeviceException if the motor is not connected or the robot is disabled
     */
    public void setTarget(@NotNull MotorControl target) throws DeviceException {
        validateConnection();
        CompetitionRuntime.assertRobotEnabled();
        target.apply(this);
        this.target = target;
    }

    /**
     * Gets the current position of the motor.
     * @return the current position of the motor as an {@link EncoderPosition}
     * @throws DeviceException if the motor is not connected
     */
    public @NotNull EncoderPosition getPosition() throws DeviceException {
        validateConnection();

        double pos = VexSdk.Device.getMotorPosition(port.deviceHandle());
        return EncoderPosition.ofTicks((int) pos, gearset.getTicksPerRevolution());
    }

    /**
     * Sets the current encoder position to the given position without moving the motor.
     * Analogous to taring or resetting the encoder so that the new position is equal to the given position.
     * @param position the new position of the motor
     * @throws DeviceException if the motor is not connected
     */
    public void setPosition(@NotNull EncoderPosition position) throws DeviceException {
        validateConnection();

        VexSdk.Device.setMotorPosition(
                port.deviceHandle(),
                position.ticks(gearset.getTicksPerRevolution())
        );
    }

    /**
     * The internal gearset of a V5 Smart Motor.
     */
    public enum Gearset {
        /**
         * The red (100 RPM) gearset.
         */
        Red(VexMotorGearset.GEAR_SET_06),
        /**
         * The green (200 RPM) gearset.
         */
        Green(VexMotorGearset.GEAR_SET_18),
        /**
         * The blue (600 RPM) gearset.
         */
        Blue(VexMotorGearset.GEAR_SET_36);

        private final VexMotorGearset raw;

        Gearset(VexMotorGearset raw) {
            this.raw = raw;
        }

        /**
         * Gets the internal representation of the gearset.
         *
         * @return the raw gearset value
         */
        @Contract(pure = true)
        public VexMotorGearset getRaw() {
            return raw;
        }

        /**
         * Gets the number of encoder ticks per revolution of a motor with this gearset.
         *
         * @return the number of ticks per revolution
         */
        @Contract(pure = true)
        public int getTicksPerRevolution() {
            return switch (this) {
                case Red -> 1800;
                case Green -> 900;
                case Blue -> 300;
            };
        }

        /**
         * Gets the maximum RPM of a motor with this gearset.
         *
         * @return the maximum RPM
         */
        @Contract(pure = true)
        public double getMaxRpm() {
            return switch (this) {
                case Red -> 100;
                case Green -> 200;
                case Blue -> 600;
            };
        }
    }

    /**
     * A possible direction that a motor may be configured as.
     */
    public enum Direction {
        /**
         * The forward direction; positive voltage causes the motor to spin clockwise.
         */
        FORWARD,
        /**
         * The reverse direction; positive voltage causes the motor to spin counterclockwise.
         */
        REVERSE;

        /**
         * Gets the inverse direction of this direction.
         *
         * @return the inverse direction
         */
        @Contract(pure = true)
        public @NotNull Direction not() {
            return switch (this) {
                case FORWARD -> REVERSE;
                case REVERSE -> FORWARD;
            };
        }
    }

    /**
     * Determines how a motor should act when braking.
     */
    public enum BrakeMode {
        /**
         * The motor does not actively brake, allowing it to coast to a stop.
         */
        Coast(VexMotorBrakeMode.COAST),
        /**
         * The motor uses regenerative braking to slow down faster.
         */
        Brake(VexMotorBrakeMode.BRAKE),
        /**
         * The motor uses its internal PID controller to hold its current position.
         */
        Hold(VexMotorBrakeMode.HOLD);

        private final VexMotorBrakeMode raw;

        BrakeMode(VexMotorBrakeMode raw) {
            this.raw = raw;
        }

        /**
         * Gets the internal representation of the brake mode.
         *
         * @return the raw brake mode value
         */
        @Contract(pure = true)
        public VexMotorBrakeMode getRaw() {
            return raw;
        }
    }
}
