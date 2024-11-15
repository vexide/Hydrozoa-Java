package dev.vexide.sdk.screen;

import dev.vexide.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Rect(int x1, int y1, int x2, int y2) implements Fillable {
    @Contract("_, _, _, _ -> new")
    public static @NotNull Rect fromDimensions(int x, int y, int width, int height) {
        return new Rect(x, y, x + width, y + height);
    }

    @Contract("_, _, _, _ -> new")
    public static @NotNull Rect centeredAround(int x, int y, int width, int height) {
        return Rect.fromDimensions(x + width / 2, y + height / 2, width, height);
    }

    @Override
    public void fill(@NotNull Screen screen, @NotNull Rgb color) {
        VexSdk.vexDisplayRectFill(x1, y1, x2, y2);
    }
}
