package dev.vexide.sdk.screen;

import org.jetbrains.annotations.NotNull;

public class Screen {
    public void fill(@NotNull Fillable shape, @NotNull Rgb color) {
        shape.fill(this, color);
    }
}
