package dev.vexide.sdk;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

public final class CompetitionRuntime {
    private CompetitionRuntime() {}

    private static @Nullable Boolean wasConnected = null;
    private static @Nullable Mode previousMode = null;

    public static void start(@NotNull CompetitionRobot robot) {
        while (true){
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

            VexSdk.vexTasksRun();
        }
    }

    @Contract(value = "-> new", pure = true)
    public static @NotNull Status status() {
        return Status.fromBitflags(VexSdk.vexCompetitionStatus());
    }

    @Contract(pure = true)
    public static boolean connected() {
        return status().connected();
    }

    @Contract(pure = true)
    public static @NotNull Optional<System> system() {
        return status().competitionSystem();
    }

    public static @NotNull Mode mode() {
        return status().mode();
    }

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

        @Contract(value = "_ -> new", pure = true)
        public static @NotNull Status fromBitflags(int flags) {
            return new Status((flags & DISABLED) != 0, (flags & AUTONOMOUS) != 0, (flags & CONNECTED) != 0, (flags & SYSTEM) != 0);
        }

        @Contract(pure = true)
        public static int toBitflags(@NotNull Status status) {
            return (status.disabled() ? DISABLED : 0) | (status.autonomous() ? AUTONOMOUS : 0) | (status.connected() ? CONNECTED : 0) | (status.system() ? SYSTEM : 0);
        }

        @Contract(pure = true)
        public @NotNull Optional<System> competitionSystem() {
            if (connected()) {
                if (system()) {
                    return Optional.of(System.FieldControl);
                } else {
                    return Optional.of(System.CompetitionSwitch);
                }
            } else {
                return Optional.empty();
            }
        }

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

    public enum System {
        FieldControl,
        CompetitionSwitch,
    }

    public enum Mode {
        Disabled,
        Autonomous,
        Driver,
    }
}
