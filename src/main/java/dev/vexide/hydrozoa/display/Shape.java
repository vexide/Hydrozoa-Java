package dev.vexide.hydrozoa.display;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a shape widget that can be drawn on the display either as an outline or filled. It is recommended that
 * the consumers of shape widgets draw them using the {@link Display#draw(Shape, Color, boolean)} method as it may be
 * more natural to read.
 * <p>
 * Notably, the {@link Text} widget does not implement this interface as it cannot be outlined.
 *
 * @see Display#draw(Shape, Color, boolean)
 */
public interface Shape {
    /**
     * Draws this shape on the given display using the given color and fill settings.
     *
     * @param display the display to draw the shape on
     * @param color   the color to draw the shape in
     * @param fill    whether to fill the shape or just draw its outline
     */
    void draw(@NotNull Display display, @NotNull Color color, boolean fill);
}
