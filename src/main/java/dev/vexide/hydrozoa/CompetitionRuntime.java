package dev.vexide.hydrozoa;

import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * A runtime environment which manages the competition state and robot lifecycle.
 */
public final class CompetitionRuntime {
    private CompetitionRuntime() {
    }

    /**
     * The default interval at which the robot will run its periodic methods.
     */
    public static final Duration DEFAULT_PERIOD = Duration.ofMillis(2);

    private static @Nullable Boolean wasConnected = null;
    private static @Nullable Mode previousMode = null;

    /**
     * The rate at which this robot will run its periodic methods.
     */
    public static @NotNull Duration period = DEFAULT_PERIOD;

    /**
     * Asserts that this robot is enabled.
     *
     * @throws DeviceException if this robot is disabled
     */
    public static void assertRobotEnabled() throws DeviceException {
        if (mode().equals(Mode.Disabled)) {
            throw new RobotDisabledException();
        }
    }

    /**
     * Starts the competition runtime, using the given function to create the robot instance.
     * @param factory a function which creates a robot instance from the peripherals
     * @throws IllegalStateException if the peripherals have already been taken or this method has already been called
     */
    public static void start(@NotNull Function<@NotNull Peripherals, @NotNull CompetitionRobot> factory) {
        try {
            var robot = factory.apply(Peripherals.take().orElseThrow(() -> new IllegalStateException("Peripherals already taken")));

            //noinspection InfiniteLoopStatement
            while (true) {
                var begin = Instant.now();

                VexSdk.Tasks.run();

                var status = status();

                if (!Objects.equals(status.connected(), wasConnected)) {
                    if (status.connected()) {
                        robot.connected();
                    } else {
                        robot.disconnected();
                    }
                    wasConnected = status.connected();
                }

                if (!Objects.equals(status.mode(), previousMode)) {
                    if (previousMode != null) {
                        switch (previousMode) {
                            case Disabled -> robot.disabledExit();
                            case Autonomous -> robot.autonomousExit();
                            case Driver -> robot.driverExit();
                        }
                    }

                    switch (status.mode()) {
                        case Disabled -> robot.disabledInit();
                        case Autonomous -> robot.autonomousInit();
                        case Driver -> robot.driverInit();
                    }
                    previousMode = status.mode();
                }

                switch (status.mode()) {
                    case Disabled -> robot.disabledPeriodic();
                    case Autonomous -> robot.autonomousPeriodic();
                    case Driver -> robot.driverPeriodic();
                }

                while (Duration.between(begin, Instant.now()).compareTo(period) < 0) {
                    Platform.yield();
                }
            }
        } catch (Throwable e) {
            Platform.handleException(e);
        }
    }

    /**
     * Gets the current competition status.
     * @return the competition status
     */
    @Contract(value = "-> new", pure = true)
    public static @NotNull Status status() {
        return Status.fromBitflags(VexSdk.Competition.status());
    }

    /**
     * Determines whether this robot is connected to a competition control system (either a competition switch or
     * field control).
     * @return {@code true} if the robot is connected, {@code false} otherwise
     */
    @Contract(pure = true)
    public static boolean connected() {
        return status().connected();
    }

    /**
     * Determines which competition system this robot is connected to.
     * @return the competition system, or an empty optional if the robot is not connected to one
     */
    @Contract(pure = true)
    public static @NotNull Optional<CompetitionSystem> system() {
        return status().competitionSystem();
    }

    /**
     * Determines the current mode of the robot's competition - disabled, autonomous, or driver.
     * @return the current mode
     */
    public static @NotNull Mode mode() {
        return status().mode();
    }

    /**
     * A competition system which the robot can be connected to.
     */
    public enum CompetitionSystem {
        /**
         * The robot is connected to a Smart Field Control system.
         */
        FieldControl,
        /**
         * The robot is connected to a competition switch.
         */
        CompetitionSwitch,
    }

    /**
     * An enumeration of the possible competition modes.
     */
    public enum Mode {
        /**
         * The robot is disabled by field control.
         */
        Disabled,
        /**
         * The robot is in autonomous mode.
         */
        Autonomous,
        /**
         * The robot is in driver control mode.
         */
        Driver,
    }

    /**
     * The status of the competition.
     * @param disabled whether the robot is disabled by field control
     * @param autonomous whether the robot is in autonomous mode
     * @param connected whether the robot is connected to competition control
     * @param system whether the robot is connected to field control
     */
    public record Status(boolean disabled, boolean autonomous, boolean connected, boolean system) {
        /**
         * Robot is disabled by field control.
         */
        static final int DISABLED = 1;
        /**
         * Robot is in autonomous mode.
         */
        static final int AUTONOMOUS = 1 << 1;
        /**
         * Robot is connected to competition control (either competition switch or field control).
         */
        static final int CONNECTED = 1 << 2;
        /**
         * Robot is connected to field control (NOT competition switch)
         */
        static final int SYSTEM = 1 << 3;

        /**
         * Creates a new competition status from bitflags.
         * @param flags the bitflags representing the status
         * @return the new competition status
         */
        @Contract(value = "_ -> new", pure = true)
        public static @NotNull Status fromBitflags(int flags) {
            return new Status((flags & DISABLED) != 0, (flags & AUTONOMOUS) != 0, (flags & CONNECTED) != 0, (flags & SYSTEM) != 0);
        }

        /**
         * Converts this competition status to bitflags.
         * @return the bitflags representing the status
         */
        @Contract(pure = true)
        public int toBitflags() {
            return (disabled() ? DISABLED : 0) | (autonomous() ? AUTONOMOUS : 0) | (connected() ? CONNECTED : 0) | (system() ? SYSTEM : 0);
        }

        /**
         * Determines the competition system that the robot is connected to.
         * @return the competition system, or an empty optional if the robot is not connected
         */
        @Contract(pure = true)
        public @NotNull Optional<CompetitionSystem> competitionSystem() {
            if (connected()) {
                if (system()) {
                    return Optional.of(CompetitionSystem.FieldControl);
                } else {
                    return Optional.of(CompetitionSystem.CompetitionSwitch);
                }
            } else {
                return Optional.empty();
            }
        }

        /**
         * Determines the current mode of the robot's competition.
         * @return the current mode
         */
        @Contract(pure = true)
        public @NotNull Mode mode() {
            if (disabled()) {
                return Mode.Disabled;
            } else if (autonomous()) {
                return Mode.Autonomous;
            } else {
                return Mode.Driver;
            }
        }
    }
}
