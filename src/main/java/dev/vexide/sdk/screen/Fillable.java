package dev.vexide.sdk.screen;

import org.jetbrains.annotations.NotNull;

public interface Fillable {
    void fill(@NotNull Screen screen, @NotNull Rgb color);
}
