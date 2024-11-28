package dev.vexide.hydrozoa;

import java.util.function.Function;

/**
 * A robot that can be used in a competition. Classes implementing this interface can be used with the competition
 * runtime to control what the robot does in different states.
 *
 * @see CompetitionRuntime#start(Function)
 */
public interface CompetitionRobot {
    /**
     * Called when the robot is connected to the field.
     */
    default void connected() {
    }

    /**
     * Called when the robot is disconnected from the field.
     */
    default void disconnected() {
    }

    /**
     * Called when the robot switches to the disabled mode.
     */
    default void disabledInit() {
    }

    /**
     * Called periodically while the robot is disabled.
     */
    default void disabledPeriodic() {
    }

    /**
     * Called when the robot exits the disabled mode.
     */
    default void disabledExit() {
    }

    /**
     * Called when the robot switches to the autonomous mode.
     */
    default void autonomousInit() {
    }

    /**
     * Called periodically while the robot is in the autonomous mode.
     */
    default void autonomousPeriodic() {
    }

    /**
     * Called when the robot exits the autonomous mode.
     */
    default void autonomousExit() {
    }

    /**
     * Called when the robot switches to the driver mode.
     */
    default void driverInit() {
    }

    /**
     * Called periodically while the robot is in the driver mode.
     */
    default void driverPeriodic() {
    }

    /**
     * Called when the robot exits the driver mode.
     */
    default void driverExit() {
    }
}
