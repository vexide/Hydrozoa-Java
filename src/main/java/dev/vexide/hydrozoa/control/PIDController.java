package dev.vexide.hydrozoa.control;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public class PIDController {
    @NotNull
    protected Clock clock;
    @NotNull
    protected Instant prevTime;
    private @NotNull PIDGains gains;
    private @Nullable Double integrationRange;

    private double integral;
    private double prevError;

    public PIDController(@NotNull PIDGains gains, @Nullable Double integrationRange, @NotNull Clock clock) {
        this.clock = clock;
        this.gains = gains;
        this.integrationRange = integrationRange;
        this.prevTime = clock.instant();
    }

    public PIDController(@NotNull PIDGains gains) {
        this(gains, null, Clock.systemUTC());
    }

    public PIDController(double kP, double kI, double kD, @Nullable Double integrationRange, @NotNull Clock clock) {
        this(new PIDGains(kP, kI, kD), integrationRange, clock);
    }

    public PIDController(double kP, double kI, double kD) {
        this(new PIDGains(kP, kI, kD));
    }

    protected double update(double error, double deltaTime) {
        var inIntegrationRange = integrationRange == null || Math.abs(error) < integrationRange;
        var hasCrossedSetpoint = Math.signum(error) != Math.signum(prevError);
        if (inIntegrationRange && !hasCrossedSetpoint) {
            integral += error * deltaTime;
        } else {
            integral = 0;
        }

        var derivative = (error - prevError) / deltaTime;
        prevError = error;

        return (error * gains.kP()) + (integral * gains.kI()) + (derivative * gains.kD());
    }

    public double calculate(double measurement, double setpoint, double deltaTime) {
         return update(setpoint - measurement, deltaTime);
    }

    public double calculate(double measurement, double setpoint) {
        var now = clock.instant();
        prevTime = now;
        return calculate(measurement, setpoint, Duration.between(prevTime, now));
    }

    public double calculate(double measurement, double setpoint, @NotNull Duration deltaTime) {
        var dtSecs = ControlUtils.durationAsSeconds(deltaTime);
        return calculate(measurement, setpoint, dtSecs);
    }

    public void reset() {
        integral = 0;
        prevError = 0;
        prevTime = clock.instant();
    }

    public @NotNull PIDGains getGains() {
        return gains;
    }

    public void setGains(@NotNull PIDGains gains) {
        this.gains = gains;
    }

    public @Nullable Double getIntegrationRange() {
        return integrationRange;
    }

    public void setIntegrationRange(@Nullable Double integrationRange) {
        this.integrationRange = integrationRange;
    }

    public @NotNull Instant getPrevTime() {
        return prevTime;
    }
}
