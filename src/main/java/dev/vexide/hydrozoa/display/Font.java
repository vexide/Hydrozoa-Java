package dev.vexide.hydrozoa.display;

import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Options for how a text widget should be formatted.
 *
 * @param size   the size of the font
 * @param family the font family of the font
 * @see Text#Text(String, int, int, Font)
 */
public record Font(@NotNull Size size, @NotNull Family family) {
    /**
     * The default font for text widgets.
     */
    public static final Font DEFAULT = new Font(Size.MEDIUM, Family.MONOSPACE);

    void apply() {
        VexSdk.Display.vexDisplayFontNamedSet(family.fontName());
        VexSdk.Display.vexDisplayTextSize(size.numerator(), size.denominator());
    }

    /**
     * The font family of a text widget.
     */
    public enum Family {
        /**
         * A monospaced font which has a fixed width for each character.
         * <p>
         * This font at {@link Size#FULL full} size is 49pt Noto Mono.
         */
        MONOSPACE,
        /**
         * A proportional font which has a varying width for each character.
         * <p>
         * This font at {@link Size#FULL full} size is 49pt Noto Sans.
         */
        PROPORTIONAL;

        /**
         * Gets the name of the font family for use with the VEX SDK.
         *
         * @return the name of the font family
         */
        @Contract(pure = true)
        public @NotNull String fontName() {
            return switch (this) {
                case MONOSPACE -> "monospace";
                case PROPORTIONAL -> "proportional";
            };
        }
    }

    /**
     * A fractional font scaling factor.
     *
     * @param numerator   the numerator of the fractional font scale
     * @param denominator the denominator of the fractional font scale
     */
    public record Size(int numerator, int denominator) {
        /**
         * An extra-small font size with a value of one-fifth.
         */
        public static final Size EXTRA_SMALL = new Size(1, 5);
        /**
         * A small font size with a value of one-fourth.
         */
        public static final Size SMALL = new Size(1, 4);
        /**
         * A medium font size with a value of one-third.
         */
        public static final Size MEDIUM = new Size(1, 3);
        /**
         * A large font size with a value of one-half.
         */
        public static final Size LARGE = new Size(1, 2);
        /**
         * An extra-large font size with a value of two-thirds.
         */
        public static final Size EXTRA_LARGE = new Size(2, 3);
        /**
         * The full size of the font.
         */
        public static final Size FULL = new Size(1, 1);
        private static final int FRAC_PRECISION = 10_000;

        /**
         * Create a custom fractional font size. If you want to create a font size from a floating-point size, use
         * {@link #Size(int)} instead.
         *
         * @param numerator   the numerator of the fraction
         * @param denominator the denominator of the fraction
         */
        @Contract(pure = true)
        public Size {
        }

        /**
         * Create a font size from an integer multiplier. This is equivalent to creating a font size with a denominator
         * of 1.
         *
         * @param size the size multiplier
         */
        @Contract(pure = true)
        public Size(int size) {
            this(size, 1);
        }

        /**
         * Create a font size from a floating-point size. The size must be a positive number.
         *
         * @param size the size
         * @return the font size
         * @throws IllegalArgumentException if the size is not a positive number
         */
        @Contract(value = "_ -> new", pure = true)
        public static @NotNull Size fromDouble(double size) {
            if (!Double.isFinite(size) || size < 0) {
                throw new IllegalArgumentException(String.format("Font size must be a positive number, not `%f`", size));
            }
            int integer_part = (int) Math.floor(size);
            double fractional_part = size - (int) size;


            if (fractional_part == 0) {
                return new Size(integer_part, 1);
            } else {
                int top = (int) Math.round(fractional_part * FRAC_PRECISION);
                int gcd = gcd(top);
                int denominator = FRAC_PRECISION / gcd;
                int numerator = top / gcd;
                return new Size(numerator + integer_part * denominator, denominator);
            }
        }

        @Contract(pure = true)
        private static int gcd(int a) {
            int b = FRAC_PRECISION;
            while (a != b) {
                if (a > b) {
                    a -= b;
                } else {
                    b -= a;
                }
            }
            return a;
        }
    }
}
