package dev.vexide.hydrozoa.devices.smart;

import dev.vexide.hydrozoa.CompetitionRuntime;
import dev.vexide.hydrozoa.devices.DeviceException;
import dev.vexide.hydrozoa.sdk.V5MotorBrakeMode;
import dev.vexide.hydrozoa.sdk.V5MotorEncoderUnits;
import dev.vexide.hydrozoa.sdk.V5MotorGearset;
import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Motor extends SmartDevice {
    public static final double V5_MAX_VOLTAGE = 12.0;

    private @NotNull Gearset gearset;
    private @NotNull Direction direction;
    private @NotNull MotorControl target = new MotorControl.Voltage(0.0);

    public Motor(@NotNull SmartPort port, @NotNull Gearset gearset, @NotNull Direction direction) {
        super(port);
        this.gearset = gearset;
        this.direction = direction;

        VexSdk.Motor.vexDeviceMotorEncoderUnitsSet(handle, V5MotorEncoderUnits.Counts);
        VexSdk.Motor.vexDeviceMotorReverseFlagSet(handle, direction.equals(Direction.Reverse));
        VexSdk.Motor.vexDeviceMotorGearingSet(handle, gearset.getRaw());
    }

    @Override
    public @NotNull Type getDeviceType() {
        return Type.Motor;
    }

    public @NotNull Gearset getGearset() {
        return gearset;
    }

    public void setGearset(@NotNull Gearset gearset) {
        this.gearset = gearset;
        VexSdk.Motor.vexDeviceMotorGearingSet(handle, gearset.getRaw());
    }

    public @NotNull Direction getDirection() {
        return direction;
    }

    public void setDirection(@NotNull Direction direction) {
        this.direction = direction;
        VexSdk.Motor.vexDeviceMotorReverseFlagSet(handle, direction.equals(Direction.Reverse));
    }

    public void setVoltage(double voltage) throws DeviceException {
        setTarget(new MotorControl.Voltage(voltage));
    }

    public void setTarget(@NotNull MotorControl target) throws DeviceException {
        validateConnection();
        CompetitionRuntime.assertRobotEnabled();
        target.apply(this);
        this.target = target;
    }

    public enum Gearset {
        Red(V5MotorGearset.GearSet_06),
        Green(V5MotorGearset.GearSet_18),
        Blue(V5MotorGearset.GearSet_36);

        private final V5MotorGearset raw;

        Gearset(V5MotorGearset raw) {
            this.raw = raw;
        }

        @Contract(pure = true)
        public V5MotorGearset getRaw() {
            return raw;
        }

        @Contract(pure = true)
        public int getTicksPerRevolution() {
            return switch (this) {
                case Red -> 1800;
                case Green -> 900;
                case Blue -> 300;
            };
        }

        @Contract(pure = true)
        public double getMaxRpm() {
            return switch (this) {
                case Red -> 100;
                case Green -> 200;
                case Blue -> 600;
            };
        }
    }

    public enum Direction {
        Forward,
        Reverse;

        @Contract(pure = true)
        public @NotNull Direction not() {
            return switch (this) {
                case Forward -> Reverse;
                case Reverse -> Forward;
            };
        }
    }

    public enum BrakeMode {
        Coast(V5MotorBrakeMode.Coast),
        Brake(V5MotorBrakeMode.Brake),
        Hold(V5MotorBrakeMode.Hold);

        private final V5MotorBrakeMode raw;

        BrakeMode(V5MotorBrakeMode raw) {
            this.raw = raw;
        }

        @Contract(pure = true)
        public V5MotorBrakeMode getRaw() {
            return raw;
        }
    }
}
