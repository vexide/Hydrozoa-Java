package dev.vexide.hydrozoa.display;

import dev.vexide.hydrozoa.Peripherals;
import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.NotNull;

public class Display {
    public static final int HEADER_HEIGHT = 32;

    public Display(@NotNull Peripherals.Key ignoredKey) {
    }

    private @NotNull RenderMode renderMode = RenderMode.Immediate;

    public void setRenderMode(@NotNull RenderMode renderMode) {
        switch (renderMode) {
            case Immediate -> VexSdk.Display.vexDisplayDoubleBufferDisable();
            case DoubleBuffered -> VexSdk.Display.vexDisplayRender(false, false);
        }
        this.renderMode = renderMode;
    }

    public @NotNull RenderMode getRenderMode() {
        return renderMode;
    }

    public void render() {
        if (renderMode == RenderMode.DoubleBuffered) {
            VexSdk.Display.vexDisplayRender(false, false);
        }
    }

    public void draw(@NotNull Shape shape, @NotNull Rgb color, boolean fill) {
        shape.draw(this, color, fill);
    }

    public void fill(@NotNull Shape shape, @NotNull Rgb color) {
        shape.draw(this, color, true);
    }

    public void stroke(@NotNull Shape shape, @NotNull Rgb color) {
        shape.draw(this, color, false);
    }

    public void write(@NotNull Text text, @NotNull Rgb fgColor, @NotNull Rgb bgColor) {
        text.draw(this, fgColor, bgColor);
    }

    public enum RenderMode {
        Immediate,
        DoubleBuffered
    }
}
