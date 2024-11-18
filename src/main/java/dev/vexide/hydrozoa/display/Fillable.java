package dev.vexide.hydrozoa.display;

import org.jetbrains.annotations.NotNull;

public interface Fillable {
    void fill(@NotNull Display screen, @NotNull Rgb color);
}
