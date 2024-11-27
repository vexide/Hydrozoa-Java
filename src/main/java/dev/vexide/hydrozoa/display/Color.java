package dev.vexide.hydrozoa.display;

import org.jetbrains.annotations.Contract;

/**
 * Represents an RGB color that can be used to specify the color of shapes and widgets drawn on the display.
 *
 * @param raw the raw RGB value of the color, for example {@code 0xFF0000} for red
 */
public record Color(int raw) {
    /**
     * The black color specified in HTML 4.01.
     */
    public static final Color BLACK = new Color(0x000000);
    /**
     * The silver color specified in HTML 4.01.
     */
    public static final Color SILVER = new Color(0xC0C0C0);
    /**
     * The gray color specified in HTML 4.01.
     */
    public static final Color GRAY = new Color(0x808080);
    /**
     * The white color specified in HTML 4.01.
     */
    public static final Color WHITE = new Color(0xFFFFFF);
    /**
     * The maroon color specified in HTML 4.01.
     */
    public static final Color MAROON = new Color(0x800000);
    /**
     * The red color specified in HTML 4.01.
     */
    public static final Color RED = new Color(0xFF0000);
    /**
     * The purple color specified in HTML 4.01.
     */
    public static final Color PURPLE = new Color(0x800080);
    /**
     * The fuchsia color specified in HTML 4.01.
     */
    public static final Color FUCHSIA = new Color(0xFF00FF);
    /**
     * The green color specified in HTML 4.01.
     */
    public static final Color GREEN = new Color(0x008000);
    /**
     * The lime color specified in HTML 4.01.
     */
    public static final Color LIME = new Color(0x00FF00);
    /**
     * The olive color specified in HTML 4.01.
     */
    public static final Color OLIVE = new Color(0x808000);
    /**
     * The yellow color specified in HTML 4.01.
     */
    public static final Color YELLOW = new Color(0xFFFF00);
    /**
     * The navy color specified in HTML 4.01.
     */
    public static final Color NAVY = new Color(0x000080);
    /**
     * The blue color specified in HTML 4.01.
     */
    public static final Color BLUE = new Color(0x0000FF);
    /**
     * The teal color specified in HTML 4.01.
     */
    public static final Color TEAL = new Color(0x008080);
    /**
     * The aqua color specified in HTML 4.01.
     */
    public static final Color AQUA = new Color(0x00FFFF);

    /**
     * Creates a new color from its red, green, and blue components.
     *
     * @param red   the red component of the color, from 0 to 255
     * @param green the green component of the color, from 0 to 255
     * @param blue  the blue component of the color, from 0 to 255
     */
    public Color(int red, int green, int blue) {
        this((red << 16) | (green << 8) | blue);
    }

    /**
     * Gets the red component of this color.
     *
     * @return the red component of this color, from 0 to 255
     */
    @Contract(pure = true)
    public int red() {
        return (raw >> 16) & 0xFF;
    }

    /**
     * Gets the green component of this color.
     * @return the green component of this color, from 0 to 255
     */
    @Contract(pure = true)
    public int green() {
        return (raw >> 8) & 0xFF;
    }

    /**
     * Gets the blue component of this color.
     * @return the blue component of this color, from 0 to 255
     */
    @Contract(pure = true)
    public int blue() {
        return raw & 0xFF;
    }
}
