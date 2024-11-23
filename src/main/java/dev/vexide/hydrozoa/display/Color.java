package dev.vexide.hydrozoa.display;

import org.jetbrains.annotations.Contract;

public record Color(int raw) {
    public static final Color TRANSPARENT = new Color(0, true);
    public static final Color BLACK = new Color(0x000000);
    public static final Color SILVER = new Color(0xC0C0C0);
    public static final Color GRAY = new Color(0x808080);
    public static final Color WHITE = new Color(0xFFFFFF);
    public static final Color MAROON = new Color(0x800000);
    public static final Color RED = new Color(0xFF0000);
    public static final Color PURPLE = new Color(0x800080);
    public static final Color FUCHSIA = new Color(0xFF00FF);
    public static final Color GREEN = new Color(0x008000);
    public static final Color LIME = new Color(0x00FF00);
    public static final Color OLIVE = new Color(0x808000);
    public static final Color YELLOW = new Color(0xFFFF00);
    public static final Color NAVY = new Color(0x000080);
    public static final Color BLUE = new Color(0x0000FF);
    public static final Color TEAL = new Color(0x008080);
    public static final Color AQUA = new Color(0x00FFFF);
    public Color(int red, int green, int blue) {
        this(red, green, blue, false);
    }
    public Color(int red, int green, int blue, boolean transparent) {
        this((red << 16) | (green << 8) | blue, transparent);
    }
    public Color(int raw, boolean transparent) {
        this(raw | (transparent ? 0x1000000 : 0));
    }

    @Contract(pure = true)
    public boolean transparent() {
        return (raw & 0x1000000) != 0;
    }

    @Contract(pure = true)
    public int red() {
        return (raw >> 16) & 0xFF;
    }

    @Contract(pure = true)
    public int green() {
        return (raw >> 8) & 0xFF;
    }

    @Contract(pure = true)
    public int blue() {
        return raw & 0xFF;
    }
}
