package dev.vexide.hydrozoa.display;

import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A rectangle widget that may be drawn on the display either as an outline or filled.
 *
 * @param x1 the x-coordinate of the top-left corner of the rectangle
 * @param y1 the y-coordinate of the top-left corner of the rectangle
 * @param x2 the x-coordinate of the bottom-right corner of the rectangle
 * @param y2 the y-coordinate of the bottom-right corner of the rectangle
 */
public record Rect(int x1, int y1, int x2, int y2) implements Shape {
    /**
     * Creates a new rectangle widget at the given coordinates with a specified width and height.
     *
     * @param x      the x-coordinate of the top-left corner of the rectangle
     * @param y      the y-coordinate of the top-left corner of the rectangle
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     * @return a new rectangle widget
     */
    @Contract(value = "_, _, _, _ -> new", pure = true)
    public static @NotNull Rect fromDimensions(int x, int y, int width, int height) {
        return new Rect(x, y, x + width, y + height);
    }

    /**
     * Creates a new rectangle widget that is centered around the given coordinates with a specified width and height.
     *
     * @param x      the x-coordinate of the center of the rectangle
     * @param y      the y-coordinate of the center of the rectangle
     * @param width  the width of the rectangle
     * @param height the height of the rectangle
     * @return a new rectangle widget
     */
    @Contract(value = "_, _, _, _ -> new", pure = true)
    public static @NotNull Rect centeredAround(int x, int y, int width, int height) {
        return Rect.fromDimensions(x + width / 2, y + height / 2, width, height);
    }

    @Override
    public void draw(@NotNull Display display, @NotNull Color color, boolean fill) {
        VexSdk.Display.vexDisplayForegroundColor(color.raw());
        if (fill) {
            VexSdk.Display.vexDisplayRectFill(x1, y1 + Display.HEADER_HEIGHT, x2, y2 + Display.HEADER_HEIGHT);
        } else {
            VexSdk.Display.vexDisplayRectDraw(x1, y1 + Display.HEADER_HEIGHT, x2, y2 + Display.HEADER_HEIGHT);
        }
    }
}
