package dev.vexide.hydrozoa.devices.smart;

import dev.vexide.hydrozoa.sdk.VexMotorBrakeMode;
import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.NotNull;

/**
 * A motor control request that can be applied to a motor to control its behavior.
 *
 * @see Motor#setTarget(MotorControl)
 */
public sealed abstract class MotorControl {
    private MotorControl() {
    }

    abstract void apply(@NotNull Motor motor);

    /**
     * A control request that sets the motor's voltage to a specific value.
     */
    public static final class Voltage extends MotorControl {
        private final double voltage;

        /**
         * Creates a new voltage control request.
         *
         * @param voltage the voltage to set the motor to
         */
        public Voltage(double voltage) {
            this.voltage = voltage;
        }

        /**
         * Gets the voltage that this control request will set the motor to.
         *
         * @return the voltage
         */
        public double getVoltage() {
            return voltage;
        }

        @Override
        void apply(@NotNull Motor motor) {
            // TODO: check motor type (V5 or EXP) before making this check
//            if (voltage < -Motor.V5_MAX_VOLTAGE || voltage > Motor.V5_MAX_VOLTAGE) {
//                throw new IllegalArgumentException("Voltage must be in the range [-12, 12]");
//            }

            var handle = motor.port.deviceHandle();
            VexSdk.Device.setMotorBrakeMode(handle, VexMotorBrakeMode.COAST);
            VexSdk.Device.setMotorVoltage(handle, (int) (getVoltage() * 1000));
        }
    }

    /**
     * A control request that uses the motor's built-in PID controller to maintain a specific velocity.
     */
    public static final class Velocity extends MotorControl {
        private final int rpm;

        /**
         * Creates a new velocity control request.
         *
         * @param rpm the desired speed of the motor during the movement operation, in rotations per minute
         */
        public Velocity(int rpm) {
            this.rpm = rpm;
        }

        /**
         * Gets the desired speed of the motor during the movement operation.
         *
         * @return the speed, in rotations per minute
         */
        public int getRpm() {
            return rpm;
        }

        @Override
        void apply(@NotNull Motor motor) {
            var handle = motor.port.deviceHandle();
            VexSdk.Device.setMotorBrakeMode(handle, VexMotorBrakeMode.COAST);
            VexSdk.Device.setMotorVelocity(handle, getRpm());
        }
    }

    /**
     * A control request that uses the motor's built-in PID controller to maintain a specific position.
     */
    public static final class Position extends MotorControl {
        private final @NotNull EncoderPosition position;
        private final int velocity;

        /**
         * Creates a new position control request.
         *
         * @param position the desired position of the motor after the movement operation
         * @param velocity the maximum desired speed of the motor during the movement operation, in rotations per minute
         */
        public Position(@NotNull EncoderPosition position, int velocity) {
            this.position = position;
            this.velocity = velocity;
        }

        /**
         * Gets the desired position of the motor after the movement operation.
         *
         * @return the position
         */
        public @NotNull EncoderPosition getPosition() {
            return position;
        }

        /**
         * Gets the maximum speed of the motor during the movement operation.
         *
         * @return the speed, in rotations per minute
         */
        public int getVelocity() {
            return velocity;
        }

        @Override
        void apply(@NotNull Motor motor) {
            var handle = motor.port.deviceHandle();
            VexSdk.Device.setMotorBrakeMode(handle, VexMotorBrakeMode.COAST);
            VexSdk.Device.setMotorAbsoluteTarget(
                    handle,
                    getPosition().ticks(motor.getGearset().getTicksPerRevolution()),
                    getVelocity()
            );
        }
    }

    /**
     * A control request that attempts to stop the motor using a specific brake mode.
     */
    public static final class Brake extends MotorControl {
        private final @NotNull Motor.BrakeMode mode;

        /**
         * Creates a new brake control request.
         *
         * @param mode the brake mode to use when stopping the motor
         */
        public Brake(@NotNull Motor.BrakeMode mode) {
            this.mode = mode;
        }

        /**
         * Gets the brake mode that this control request will use when stopping the motor.
         *
         * @return the brake mode
         */
        public @NotNull Motor.BrakeMode getMode() {
            return mode;
        }

        @Override
        void apply(@NotNull Motor motor) {
            var handle = motor.port.deviceHandle();
            VexSdk.Device.setMotorBrakeMode(handle, getMode().getRaw());
            VexSdk.Device.setMotorVelocity(handle, 0);
        }
    }
}
