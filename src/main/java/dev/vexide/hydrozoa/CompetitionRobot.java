package dev.vexide.hydrozoa;

public interface CompetitionRobot {
    default void connected() {
    }

    default void disconnected() {
    }

    default void disabledInit() {
    }

    default void disabledPeriodic() {
    }

    default void disabledExit() {
    }

    default void autonomousInit() {
    }

    default void autonomousPeriodic() {
    }

    default void autonomousExit() {
    }

    default void driverInit() {
    }

    default void driverPeriodic() {
    }

    default void driverExit() {
    }
}
