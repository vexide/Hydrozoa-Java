package dev.vexide.hydrozoa.devices.smart;

import dev.vexide.hydrozoa.sdk.V5MotorBrakeMode;
import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.NotNull;

public abstract class MotorControl {
    abstract void apply(@NotNull Motor motor);

    public static class Voltage extends MotorControl {
        private final double voltage;

        public Voltage(double voltage) {
            this.voltage = voltage;
        }

        public double getVoltage() {
            return voltage;
        }

        @Override
        void apply(@NotNull Motor motor) {
            // TODO: check motor type (V5 or EXP) before making this check
//            if (voltage < -Motor.V5_MAX_VOLTAGE || voltage > Motor.V5_MAX_VOLTAGE) {
//                throw new IllegalArgumentException("Voltage must be in the range [-12, 12]");
//            }

            System.out.println("Setting voltage to " + voltage);

            VexSdk.Motor.vexDeviceMotorBrakeModeSet(motor.handle, V5MotorBrakeMode.Coast);
            VexSdk.Motor.vexDeviceMotorVoltageSet(motor.handle, (int) (voltage * 1000));
        }
    }

    public static class Velocity extends MotorControl {
        private final int rpm;

        public Velocity(int rpm) {
            this.rpm = rpm;
        }

        public int getRpm() {
            return rpm;
        }

        @Override
        void apply(@NotNull Motor motor) {
            VexSdk.Motor.vexDeviceMotorBrakeModeSet(motor.handle, V5MotorBrakeMode.Coast);
            VexSdk.Motor.vexDeviceMotorVelocitySet(motor.handle, rpm);
        }
    }

    public static class Position extends MotorControl {
        private final @NotNull EncoderPosition position;
        private final int velocity;

        public Position(@NotNull EncoderPosition position, int velocity) {
            this.position = position;
            this.velocity = velocity;
        }

        public @NotNull EncoderPosition getPosition() {
            return position;
        }

        public int getVelocity() {
            return velocity;
        }

        @Override
        void apply(@NotNull Motor motor) {
            VexSdk.Motor.vexDeviceMotorBrakeModeSet(motor.handle, V5MotorBrakeMode.Coast);
            VexSdk.Motor.vexDeviceMotorAbsoluteTargetSet(
                    motor.handle,
                    position.ticks(motor.getGearset().getTicksPerRevolution()),
                    velocity
            );
        }
    }

    public static class Brake extends MotorControl {
        private final @NotNull Motor.BrakeMode mode;

        public Brake(@NotNull Motor.BrakeMode mode) {
            this.mode = mode;
        }

        public @NotNull Motor.BrakeMode getMode() {
            return mode;
        }

        @Override
        void apply(@NotNull Motor motor) {
            VexSdk.Motor.vexDeviceMotorBrakeModeSet(motor.handle, mode.getRaw());
            VexSdk.Motor.vexDeviceMotorVelocitySet(motor.handle, 0);
        }
    }
}
