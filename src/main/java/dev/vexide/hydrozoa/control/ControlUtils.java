package dev.vexide.hydrozoa.control;

import org.jetbrains.annotations.NotNull;

import java.time.Duration;

final class ControlUtils {
    private ControlUtils() {}

    static double durationAsSeconds(@NotNull Duration duration) {
        return duration.getSeconds() + duration.getNano() / 1_000_000_000.0;
    }
}
