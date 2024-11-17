package dev.vexide.sdk.screen;

import org.jetbrains.annotations.NotNull;

public interface Fillable {
    void fill(@NotNull Display screen, @NotNull Rgb color);
}
