package dev.vexide.hydrozoa.devices.smart;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record EncoderPosition(long raw) {
    public static final int INTERNAL_TPR = 4_608_000;

    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull EncoderPosition ofTicks(long ticks, int tpr) {
        return new EncoderPosition(ticks * ((long) INTERNAL_TPR / (long) tpr));
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull EncoderPosition ofDegrees(double degrees) {
        return new EncoderPosition((long) (degrees / 360.0 * INTERNAL_TPR));
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull EncoderPosition ofRadians(double radians) {
        return new EncoderPosition((long) (radians / Math.TAU * INTERNAL_TPR));
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull EncoderPosition ofRotations(double rotations) {
        return new EncoderPosition((long) (rotations * INTERNAL_TPR));
    }

    @Contract(pure = true)
    public long ticks(int tpr) {
        return raw * tpr / INTERNAL_TPR;
    }

    @Contract(pure = true)
    public double degrees() {
        return (double) raw / INTERNAL_TPR * 360.0;
    }

    @Contract(pure = true)
    public double radians() {
        return (double) raw / INTERNAL_TPR * Math.TAU;
    }

    @Contract(pure = true)
    public double rotations() {
        return (double) raw / INTERNAL_TPR;
    }

    @Contract(pure = true)
    public @NotNull EncoderPosition plus(@NotNull EncoderPosition other) {
        return new EncoderPosition(raw + other.raw);
    }

    @Contract(pure = true)
    public @NotNull EncoderPosition minus(@NotNull EncoderPosition other) {
        return new EncoderPosition(raw - other.raw);
    }

    @Contract(pure = true)
    public @NotNull EncoderPosition times(int scalar) {
        return new EncoderPosition(raw * scalar);
    }

    @Contract(pure = true)
    public @NotNull EncoderPosition div(int scalar) {
        return new EncoderPosition(raw / scalar);
    }

    @Contract(pure = true)
    public @NotNull EncoderPosition mod(int scalar) {
        return new EncoderPosition(raw % scalar);
    }

    @Contract(pure = true)
    public @NotNull EncoderPosition abs() {
        return new EncoderPosition(Math.abs(raw));
    }

    @Contract(pure = true)
    public @NotNull EncoderPosition unaryMinus() {
        return new EncoderPosition(-raw);
    }

    @Contract(pure = true)
    public int compareTo(@NotNull EncoderPosition other) {
        return Long.compare(raw, other.raw);
    }
}
