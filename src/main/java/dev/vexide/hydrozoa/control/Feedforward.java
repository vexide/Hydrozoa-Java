package dev.vexide.hydrozoa.control;

import org.jetbrains.annotations.NotNull;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

public class Feedforward {
    @NotNull
    protected Clock clock;
    @NotNull
    protected Instant prevTime;
    private double kS, kV, kA;

    public Feedforward(double kS, double kV, double kA, @NotNull Clock clock) {
        if (kV < 0 || kA < 0) {
            throw new IllegalArgumentException(String.format("kV and kA must be non-negative, got kV=%f, kA=%f", kV, kA));
        }

        this.clock = clock;
        this.prevTime = clock.instant();
        this.kS = kS;
        this.kV = kV;
        this.kA = kA;
    }

    public Feedforward(double kS, double kV, double kA) {
        this(kS, kV, kA, Clock.systemUTC());
    }

    public double getkS() {
        return kS;
    }

    public void setkS(double kS) {
        this.kS = kS;
    }

    public double getkV() {
        return kV;
    }

    public void setkV(double kV) {
        this.kV = kV;
    }

    public double getkA() {
        return kA;
    }

    public void setkA(double kA) {
        this.kA = kA;
    }

    public double calculate(double velocity) {
        return kS * Math.signum(velocity) + kV * velocity;
    }

    /**
     * Calculates the feedforward from the gains and setpoints assuming discrete control.
     *
     * <p>Note this method is inaccurate when the velocity crosses 0.
     *
     * @param currentVelocity The current velocity setpoint.
     * @param nextVelocity The next velocity setpoint.
     * @return The computed feedforward.
     */
    public double calculateWithVelocities(double currentVelocity, double nextVelocity) {
        var now = clock.instant();
        var deltaTime = ControlUtils.durationAsSeconds(Duration.between(prevTime, now));
        prevTime = now;

        // See wpimath/algorithms.md#Simple_motor_feedforward for derivation
        if (kA == 0.0) {
            return kS * Math.signum(nextVelocity) + kV * nextVelocity;
        } else {
            double A = -kV / kA;
            double B = 1.0 / kA;
            double A_d = Math.exp(A * deltaTime);
            double B_d = 1.0 / A * (A_d - 1.0) * B;
            return kS * Math.signum(currentVelocity) + 1.0 / B_d * (nextVelocity - A_d * currentVelocity);
        }
    }

    /**
     * Calculates the maximum achievable velocity given a maximum voltage supply and an acceleration.
     * Useful for ensuring that velocity and acceleration constraints for a trapezoidal profile are
     * simultaneously achievable - enter the acceleration constraint, and this will give you a
     * simultaneously-achievable velocity constraint.
     *
     * <p>The units should be radians for angular systems and meters for linear systems.
     *
     * @param maxVoltage The maximum voltage that can be supplied to the motor, in volts.
     * @param acceleration The acceleration of the motor, in (units/s²).
     * @return The maximum possible velocity in (units/s) at the given acceleration.
     */
    public double maxAchievableVelocity(double maxVoltage, double acceleration) {
        // Assume max velocity is positive
        return (maxVoltage - kS - acceleration * kA) / kV;
    }

    /**
     * Calculates the minimum achievable velocity given a maximum voltage supply and an acceleration.
     * Useful for ensuring that velocity and acceleration constraints for a trapezoidal profile are
     * simultaneously achievable - enter the acceleration constraint, and this will give you a
     * simultaneously-achievable velocity constraint.
     *
     * <p>The units should be radians for angular systems and meters for linear systems.
     *
     * @param maxVoltage The maximum voltage that can be supplied to the motor, in volts.
     * @param acceleration The acceleration of the motor, in (units/s²).
     * @return The maximum possible velocity in (units/s) at the given acceleration.
     */
    public double minAchievableVelocity(double maxVoltage, double acceleration) {
        // Assume min velocity is negative, ks flips sign
        return (-maxVoltage + kS - acceleration * kA) / kV;
    }

    /**
     * Calculates the maximum achievable acceleration given a maximum voltage supply and a velocity.
     * Useful for ensuring that velocity and acceleration constraints for a trapezoidal profile are
     * simultaneously achievable - enter the velocity constraint, and this will give you a
     * simultaneously-achievable acceleration constraint.
     *
     * <p>The units should be radians for angular systems and meters for linear systems.
     *
     * @param maxVoltage The maximum voltage that can be supplied to the motor, in volts.
     * @param velocity The velocity of the motor, in (units/s).
     * @return The maximum possible acceleration in (units/s²) at the given velocity.
     */
    public double maxAchievableAcceleration(double maxVoltage, double velocity) {
        return (maxVoltage - kS * Math.signum(velocity) - velocity * kV) / kA;
    }

    /**
     * Calculates the minimum achievable acceleration given a maximum voltage supply and a velocity.
     * Useful for ensuring that velocity and acceleration constraints for a trapezoidal profile are
     * simultaneously achievable - enter the velocity constraint, and this will give you a
     * simultaneously-achievable acceleration constraint.
     *
     * <p>The units should be radians for angular systems and meters for linear systems.
     *
     * @param maxVoltage The maximum voltage that can be supplied to the motor, in volts.
     * @param velocity The velocity of the motor, in (units/s).
     * @return The maximum possible acceleration in (units/s²) at the given velocity.
     */
    public double minAchievableAcceleration(double maxVoltage, double velocity) {
        return maxAchievableAcceleration(-maxVoltage, velocity);
    }
}
