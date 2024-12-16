package dev.vexide.hydrozoa.control;

import dev.vexide.hydrozoa.Units;
import io.nacular.measured.units.Angle;
import io.nacular.measured.units.Measure;
import io.nacular.measured.units.UnitsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public class AngularPIDController extends PIDController {
    public AngularPIDController(@NotNull PIDGains gains, @Nullable Double integrationRange, @NotNull Clock clock) {
        super(gains, integrationRange, clock);
    }

    public AngularPIDController(@NotNull PIDGains gains, @Nullable Measure<Angle> integrationRange, @NotNull Clock clock) {
        super(gains, integrationRange == null ? null : integrationRange.in(Units.Radians), clock);
    }

    public AngularPIDController(@NotNull PIDGains gains) {
        super(gains);
    }

    public AngularPIDController(double kP, double kI, double kD, @Nullable Double integrationRange, @NotNull Clock clock) {
        super(new PIDGains(kP, kI, kD), integrationRange, clock);
    }

    public AngularPIDController(double kP, double kI, double kD) {
        super(new PIDGains(kP, kI, kD));
    }

    public AngularPIDController(double kP, double kI, double kD, @Nullable Measure<Angle> integrationRange, @NotNull Clock clock) {
        this(new PIDGains(kP, kI, kD), integrationRange, clock);
    }

    /**
     * Normalizes an angle in radians to a value between -PI and PI.
     * @param radians the angle in radians
     * @return the wrapped angle
     */
    private static double normalizeAngle(double radians) {
        double result = -radians + Math.PI;
        result %= Math.TAU;
        if (result < 0) {
            result += Math.TAU;
        }
        return result - Math.PI;
    }

    public double calculate(@NotNull Measure<Angle> measurement, @NotNull Measure<Angle> setpoint) {
        return calculate(measurement.in(Units.Radians), setpoint.in(Units.Radians));
    }

    public double calculate(@NotNull Measure<Angle> measurement, @NotNull Measure<Angle> setpoint, double deltaTime) {
        return calculate(measurement.in(Units.Radians), setpoint.in(Units.Radians), deltaTime);
    }

    public double calculate(@NotNull Measure<Angle> measurement, @NotNull Measure<Angle> setpoint, @NotNull Duration deltaTime) {
        return calculate(measurement.in(Units.Radians), setpoint.in(Units.Radians), deltaTime);
    }

    @Override
    public double calculate(double measurement, double setpoint, double deltaTime) {
        var error = normalizeAngle(setpoint - measurement);

        var inIntegrationRange = integrationRange == null
                || Math.abs(error) < integrationRange;
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
