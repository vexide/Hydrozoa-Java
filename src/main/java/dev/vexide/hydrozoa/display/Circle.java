package dev.vexide.hydrozoa.display;

import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.NotNull;

public record Circle(int x, int y, int radius) implements Shape {
    public Circle {
        if (radius < 0) {
            throw new IllegalArgumentException(String.format("Radius must be positive or zero, not `%d`", radius));
        }
    }

    @Override
    public void draw(@NotNull Display screen, @NotNull Color color, boolean fill) {
        VexSdk.Display.vexDisplayForegroundColor(color.raw());
        if (fill) {
            VexSdk.Display.vexDisplayCircleFill(x, y + Display.HEADER_HEIGHT, radius);
        } else {
            VexSdk.Display.vexDisplayCircleDraw(x, y + Display.HEADER_HEIGHT, radius);
        }
    }
}
