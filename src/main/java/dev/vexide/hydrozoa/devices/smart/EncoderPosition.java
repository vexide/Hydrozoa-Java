package dev.vexide.hydrozoa.devices.smart;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/// The position of an encoder.
///
/// @param raw the number of ticks the encoder has counted
////
public record EncoderPosition(long raw) {
    /// The ticks per rotation ratio internally used by the `EncoderPosition` class.
    public static final int INTERNAL_TPR = 4_608_000;

    /// Creates a new encoder position using a number of ticks.
    ///
    /// @param ticks the number of ticks the encoder has counted
    /// @param tpr   the number of ticks which represent one full rotation of the encoder
    /// @return the new encoder position
    @Contract(value = "_, _ -> new", pure = true)
    public static @NotNull EncoderPosition ofTicks(long ticks, int tpr) {
        return new EncoderPosition(ticks * ((long) INTERNAL_TPR / (long) tpr));
    }

    /// Creates a new encoder position using a number of degrees.
    ///
    /// @param degrees the number of degrees the encoder has rotated
    /// @return the new encoder position
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull EncoderPosition ofDegrees(double degrees) {
        return new EncoderPosition((long) (degrees / 360.0 * INTERNAL_TPR));
    }

    /// Creates a new encoder position using a number of radians.
    ///
    /// @param radians the number of radians the encoder has rotated
    /// @return the new encoder position
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull EncoderPosition ofRadians(double radians) {
        return new EncoderPosition((long) (radians / Math.TAU * INTERNAL_TPR));
    }

    /// Creates a new encoder position using a number of rotations.
    ///
    /// @param rotations the number of full rotations the encoder has completed
    /// @return the new encoder position
    @Contract(value = "_ -> new", pure = true)
    public static @NotNull EncoderPosition ofRotations(double rotations) {
        return new EncoderPosition((long) (rotations * INTERNAL_TPR));
    }

     /// Converts the encoder position to a number of ticks at a given TPR ratio.
     ///
     /// @param tpr the number of ticks which represent one full rotation of the encoder
     /// @return the number of ticks the encoder has counted
    @Contract(pure = true)
    public long ticks(int tpr) {
        return raw * tpr / INTERNAL_TPR;
    }

    /// Converts the encoder position to a number of degrees.
    ///
    /// @return the number of degrees the encoder has rotated
    @Contract(pure = true)
    public double degrees() {
        return (double) raw / INTERNAL_TPR * 360.0;
    }

    /// Converts the encoder position to a number of radians.
    ///
    /// @return the number of radians the encoder has rotated
    @Contract(pure = true)
    public double radians() {
        return (double) raw / INTERNAL_TPR * Math.TAU;
    }

    /// Converts the encoder position to a number of full rotations.
    ///
    /// @return the number of full rotations the encoder has completed
    @Contract(pure = true)
    public double rotations() {
        return (double) raw / INTERNAL_TPR;
    }

    /// Adds two encoder positions together.
    ///
    /// @param other the other encoder position
    /// @return the sum of the two encoder positions
    @Contract(pure = true)
    public @NotNull EncoderPosition plus(@NotNull EncoderPosition other) {
        return new EncoderPosition(raw + other.raw);
    }

    /// Subtracts one encoder position from another.
    ///
    /// @param other the other encoder position
    /// @return the difference of the two encoder positions
    @Contract(pure = true)
    public @NotNull EncoderPosition minus(@NotNull EncoderPosition other) {
        return new EncoderPosition(raw - other.raw);
    }

    /// Multiplies an encoder position by a scalar.
    ///
    /// @param scalar the scalar to multiply by
    /// @return the product of the encoder position and the scalar
    @Contract(pure = true)
    public @NotNull EncoderPosition times(int scalar) {
        return new EncoderPosition(raw * scalar);
    }

    /// Divides an encoder position by a scalar.
    ///
    /// @param scalar the scalar to divide by
    /// @return the quotient of the encoder position and the scalar
    @Contract(pure = true)
    public @NotNull EncoderPosition div(int scalar) {
        return new EncoderPosition(raw / scalar);
    }

    /// Computes the remainder of dividing an encoder position by a scalar.
    ///
    /// @param scalar the scalar to divide by
    /// @return the remainder of the division
    @Contract(pure = true)
    public @NotNull EncoderPosition rem(int scalar) {
        return new EncoderPosition(raw % scalar);
    }

    /// Computes the floor modulus of dividing an encoder position by a scalar.
    ///
    /// @param scalar the scalar to divide by
    /// @return the floor modulus of the division
    /// @see Math#floorMod(int, int)
    @Contract(pure = true)
    public @NotNull EncoderPosition mod(int scalar) {
        return new EncoderPosition(Math.floorMod(raw, scalar));
    }

    /// Computes the absolute value of the encoder position.
    ///
    /// @return the absolute value of the encoder position
    @Contract(pure = true)
    public @NotNull EncoderPosition abs() {
        return new EncoderPosition(Math.abs(raw));
    }

    /// Computes the negation of the encoder position.
    ///
    /// @return the negation of the encoder position
    @Contract(pure = true)
    public @NotNull EncoderPosition unaryMinus() {
        return new EncoderPosition(-raw);
    }

    /// Compares two encoder positions numerically.
    ///
    /// @param other the other encoder position
    /// @return the value {@code 0} if {@code this = other}; a value less than {@code 0} if {@code this < other}; and a
    /// value greater than {@code 0} if {@code this > other}
    @Contract(pure = true)
    public int compareTo(@NotNull EncoderPosition other) {
        return Long.compare(raw, other.raw);
    }
}
