package dev.vexide.hydrozoa.display;

import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Rect(int x1, int y1, int x2, int y2) implements Shape {
    @Contract(value = "_, _, _, _ -> new", pure = true)
    public static @NotNull Rect fromDimensions(int x, int y, int width, int height) {
        return new Rect(x, y, x + width, y + height);
    }

    @Contract(value = "_, _, _, _ -> new", pure = true)
    public static @NotNull Rect centeredAround(int x, int y, int width, int height) {
        return Rect.fromDimensions(x + width / 2, y + height / 2, width, height);
    }

    @Override
    public void draw(@NotNull Display screen, @NotNull Rgb color, boolean fill) {
        VexSdk.Display.vexDisplayForegroundColor(color.toInteger());
        if (fill) {
            VexSdk.Display.vexDisplayRectFill(x1, y1 + Display.HEADER_HEIGHT, x2, y2 + Display.HEADER_HEIGHT);
        } else {
            VexSdk.Display.vexDisplayRectDraw(x1, y1 + Display.HEADER_HEIGHT, x2, y2 + Display.HEADER_HEIGHT);
        }
    }
}
