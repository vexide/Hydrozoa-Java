package dev.vexide.hydrozoa.display;

import org.jetbrains.annotations.NotNull;

public interface Shape {
    void draw(@NotNull Display screen, @NotNull Color color, boolean fill);
}
