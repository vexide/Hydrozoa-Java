package dev.vexide.sdk.screen;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Rgb(int red, int green, int blue) {
    @Contract(pure = true)
    public int toInteger() {
        return (red << 16) | (green << 8) | blue;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull Rgb fromInteger(int color) {
        int red = (color >> 16) & 0xFF;
        int green = (color >> 8) & 0xFF;
        int blue = color & 0xFF;
        return new Rgb(red, green, blue);
    }
}
