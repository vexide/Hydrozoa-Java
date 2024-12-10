package dev.vexide.hydrozoa.control;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.Optional;
import java.util.OptionalDouble;

public class PIDController {
    private @NotNull PIDGains gains;

    private double integral;
    private @Nullable Double integrationRange;
    private double prevError;

    public PIDController(@NotNull PIDGains gains, @Nullable Double integrationRange) {
        this.gains = gains;
        this.integrationRange = integrationRange;
    }

    public PIDController(@NotNull PIDGains gains) {
        this.gains = gains;
    }

    public PIDController(double kP, double kI, double kD, @Nullable Double integrationRange) {
        this(new PIDGains(kP, kI, kD), integrationRange);
    }

    public PIDController(double kP, double kI, double kD) {
        this(new PIDGains(kP, kI, kD));
    }

    public double update(double measurement, double setpoint, @NotNull Duration deltaTime) {
        // TODO
        return 0.0;
    }
}
