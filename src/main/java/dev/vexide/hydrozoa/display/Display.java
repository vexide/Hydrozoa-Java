package dev.vexide.hydrozoa.display;

import dev.vexide.hydrozoa.Peripherals;
import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * Represents the display on the VEX V5 brain
 */
public class Display {
    /**
     * The vertical height taken by the user program header, which cannot be drawn on.
     */
    public static final int HEADER_HEIGHT = 32;
    /**
     * The horizontal resolution (width) of the display.
     */
    public static final int HORIZONTAL_RESOLUTION = 480;
    /**
     * The vertical resolution (height) of the display.
     */
    public static final int VERTICAL_RESOLUTION = 240;
    /**
     * The amount of time it takes for the brain's display to fully re-render. The display is refreshed at 60Hz.
     */
    public static final Duration REFRESH_INTERVAL = Duration.of(16667, ChronoUnit.MICROS);
    private @NotNull RenderMode renderMode = RenderMode.IMMEDIATE;

    /**
     * Creates a new display object.
     *
     * @param ignoredKey the peripherals key, which may only be accessed from within the {@link Peripherals} class
     * @see Peripherals#takeDisplay()
     */
    public Display(@NotNull Peripherals.Key ignoredKey) {
    }

    /**
     * Gets the current rendering mode of this display.
     *
     * @return the current rendering mode
     */
    public @NotNull RenderMode getRenderMode() {
        return renderMode;
    }

    /**
     * Sets the rendering mode of this display.
     *
     * @param renderMode the new rendering mode
     * @see RenderMode
     */
    public void setRenderMode(@NotNull RenderMode renderMode) {
        switch (renderMode) {
            case IMMEDIATE -> VexSdk.Display.vexDisplayDoubleBufferDisable();
            case DOUBLE_BUFFERED -> VexSdk.Display.vexDisplayRender(false, false);
        }
        this.renderMode = renderMode;
    }

    /**
     * Flushes this display's double buffer if it is enabled, so that every queued change to the display is applied.
     * This is a no-op when the {@link RenderMode#IMMEDIATE} rendering mode is enabled, but is necessary for anything to
     * be displayed when using the {@link RenderMode#DOUBLE_BUFFERED} mode.
     */
    public void render() {
        if (renderMode == RenderMode.DOUBLE_BUFFERED) {
            VexSdk.Display.vexDisplayRender(false, false);
        }
    }

    /**
     * Draws a shape on this display.
     * @param shape the shape to draw
     * @param color the color to draw the shape in
     * @param fill whether to fill the shape or just draw its outline
     */
    public void draw(@NotNull Shape shape, @NotNull Color color, boolean fill) {
        shape.draw(this, color, fill);
    }

    /**
     * Draws a text widget on this display with the given foreground and background colors.
     *
     * @param text    the text to draw
     * @param fgColor the color to draw the text in
     * @param bgColor the color to draw the text's background in, or {@code null} for transparency
     */
    public void draw(@NotNull Text text, @NotNull Color fgColor, @Nullable Color bgColor) {
        text.draw(this, fgColor, bgColor);
    }

    /**
     * Draws a text widget on this display with a transparent background.
     *
     * @param text    the text to draw
     * @param fgColor the color to draw the text in
     */
    public void draw(@NotNull Text text, @NotNull Color fgColor) {
        text.draw(this, fgColor, null);
    }

    /**
     * Fills a shape on this display with a color.
     * @param shape the shape to fill
     * @param color the color to fill the shape with
     */
    public void fill(@NotNull Shape shape, @NotNull Color color) {
        shape.draw(this, color, true);
    }

    /**
     * Outlines a shape on this display with a color.
     * @param shape the shape to outline
     * @param color the color to outline the shape with
     */
    public void stroke(@NotNull Shape shape, @NotNull Color color) {
        shape.draw(this, color, false);
    }

    /**
     * The rendering mode of the display.
     * <p>
     * When using the display in the {@link #IMMEDIATE} mode, all draw operations will immediately show up on the
     * display. The {@link #DOUBLE_BUFFERED} mode instead applies draw operations onto an intermediate buffer that can be
     * swapped onto the display by calling {@link Display#render}, thereby preventing screen tearing. By default, the
     * display uses the {@code Immediate} mode.
     * <p>
     * {@code Display.render} MUST be called for anything to appear on the display when using the {@code DoubleBuffered}
     * mode.
     */
    public enum RenderMode {
        /**
         * Draw operations are immediately applied to the display without the need to call {@link Display#render}.
         */
        IMMEDIATE,
        /**
         * Draw calls are affected on an intermediary display buffer, rather than directly drawn to the display. The
         * intermediate buffer can later be applied to the display using {@link Display#render}
         * <p>
         * This mode is necessary for preventing screen tearing when drawing at high speeds.
         */
        DOUBLE_BUFFERED
    }
}
