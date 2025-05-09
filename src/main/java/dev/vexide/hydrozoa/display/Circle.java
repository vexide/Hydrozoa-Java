package dev.vexide.hydrozoa.display;

import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.NotNull;

/**
 * A circle widget that may be drawn on the display either as an outline or filled.
 *
 * @param x      the x-coordinate of the center of the circle
 * @param y      the y-coordinate of the center of the circle
 * @param radius the radius of the circle
 */
public record Circle(int x, int y, int radius) implements Shape {
    /**
     * Creates a new circle widget at the given coordinates with the specified radius.
     *
     * @param x      the x-coordinate of the center of the circle
     * @param y      the y-coordinate of the center of the circle
     * @param radius the radius of the circle
     * @throws IllegalArgumentException if the radius is negative
     */
    public Circle {
        if (radius < 0) {
            throw new IllegalArgumentException(String.format("Radius must be positive or zero, not `%d`", radius));
        }
    }

    @Override
    public void draw(@NotNull Display display, @NotNull Color color, boolean fill) {
        VexSdk.Display.foregroundColor(color.raw());
        if (fill) {
            VexSdk.Display.fillCircle(x, y + Display.HEADER_HEIGHT, radius);
        } else {
            VexSdk.Display.drawCircle(x, y + Display.HEADER_HEIGHT, radius);
        }
    }
}
