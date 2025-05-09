package dev.vexide.hydrozoa.display;

import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A piece of text that can be drawn on the display.
 *
 * @param text   the desired text to be displayed
 * @param font   the font to use for rendering the text
 * @param x      the x-coordinate of the text
 * @param y      the y-coordinate of the text
 * @param hAlign the horizontal alignment of the text
 * @param vAlign the vertical alignment of the text
 */
public record Text(@NotNull String text, int x, int y, @NotNull Font font, HAlign hAlign, VAlign vAlign) {
    /**
     * Creates a new text widget with the given text, font, and position using the default alignment of left and top.
     *
     * @param text the desired text to be displayed
     * @param font the font to use for rendering the text
     * @param x    the x-coordinate of the text
     * @param y    the y-coordinate of the text
     */
    public Text(@NotNull String text, int x, int y, @NotNull Font font) {
        this(text, x, y, font, HAlign.LEFT, VAlign.Top);
    }

    /**
     * Creates a new text widget with the given text and position using the default medium-sized monospace font and an
     * alignment of left and top.
     *
     * @param text the desired text to be displayed
     * @param x    the x-coordinate of the text
     * @param y    the y-coordinate of the text
     */
    public Text(@NotNull String text, int x, int y) {
        this(text, x, y, Font.DEFAULT, HAlign.LEFT, VAlign.Top);
    }

    /**
     * Creates a copy of this text widget that uses the given font.
     *
     * @param font the new font to use
     * @return a new text widget using the new font but with everything else the same
     */
    @Contract(value = "_ -> new", pure = true)
    public @NotNull Text withFont(@NotNull Font font) {
        return new Text(text, x, y, font, hAlign, vAlign);
    }


    /**
     * Creates a copy of this text widget that uses the given alignment settings.
     *
     * @param hAlign the new horizontal alignment
     * @param vAlign the new vertical alignment
     * @return a new text widget using the new alignment settings but with everything else the same
     */
    @Contract(value = "_, _ -> new", pure = true)
    public @NotNull Text withAlignment(@NotNull HAlign hAlign, @NotNull VAlign vAlign) {
        return new Text(text, x, y, font, hAlign, vAlign);
    }

    /**
     * Gets the height of this text if it were to be drawn on the display.
     * @return the height of the text, in pixels
     */
    @Contract(pure = true)
    public int height() {
        font.apply();
        return VexSdk.Display.getStringHeight(text);
    }

    /**
     * Gets the width of this text if it were to be drawn on the display.
     * @return the width of the text, in pixels
     */
    @Contract(pure = true)
    public int width() {
        font.apply();
        return VexSdk.Display.getStringWidth(text);
    }

    /**
     * Draws this text on the display using the given colors.
     *
     * @param display the display to draw the text on
     * @param fgColor the color to draw the text in
     * @param bgColor the color to draw the text's background in, or {@code null} for transparency
     */
    public void draw(@NotNull Display display, @NotNull Color fgColor, @Nullable Color bgColor) {
        VexSdk.Display.foregroundColor(fgColor.raw());
        if (bgColor != null) {
            VexSdk.Display.backgroundColor(bgColor.raw());
        }

        var x = switch (hAlign) {
            case LEFT -> this.x;
            case CENTER -> this.x - width() / 2;
            case RIGHT -> this.x - width();
        };

        var y = switch (vAlign) {
            case Top -> this.y;
            case Center -> this.y - height() / 2;
            case Bottom -> this.y - height();
        } + Display.HEADER_HEIGHT;

        font.apply();
        VexSdk.Display.printf(x, y, bgColor != null, text);
    }

    /**
     * The horizontal alignment of the text, which determines how the text is positioned relative to the x-coordinate.
     */
    public enum HAlign {
        /**
         * The x-coordinate represents the position of the text's left edge.
         */
        LEFT,
        /**
         * The x-coordinate represents the position of the text's center.
         */
        CENTER,
        /**
         * The x-coordinate represents the position of the text's right edge.
         */
        RIGHT
    }

    /**
     * The vertical alignment of the text, which determines how the text is positioned relative to the y-coordinate.
     */
    public enum VAlign {
        /**
         * The y-coordinate represents the position of the text's top edge.
         */
        Top,
        /**
         * The y-coordinate represents the position of the text's center.
         */
        Center,
        /**
         * The y-coordinate represents the position of the text's bottom edge.
         */
        Bottom
    }
}
