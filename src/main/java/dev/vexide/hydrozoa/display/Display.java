package dev.vexide.hydrozoa.display;

import dev.vexide.hydrozoa.Peripherals;
import org.jetbrains.annotations.NotNull;

public class Display {
    public Display(Peripherals.Key ignoredKey) {
    }

    public void fill(@NotNull Fillable shape, @NotNull Rgb color) {
        shape.fill(this, color);
    }
}
