package dev.vexide.hydrozoa.display;

import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Text(@NotNull String text, @NotNull Size size, int x, int y, HAlign hAlign, VAlign vAlign) {
    public Text(@NotNull String text, @NotNull Size size, int x, int y) {
        this(text, size, x, y, HAlign.Left, VAlign.Top);
    }

    public Text(@NotNull String text, int x, int y) {
        this(text, Size.Medium, x, y, HAlign.Left, VAlign.Top);
    }

    public @NotNull Text withSize(@NotNull Size size) {
        return new Text(text, size, x, y, hAlign, vAlign);
    }

    public @NotNull Text withAlignment(@NotNull HAlign hAlign, @NotNull VAlign vAlign) {
        return new Text(text, size, x, y, hAlign, vAlign);
    }

    private void configureTextMeasuring() {
        switch (size) {
            case Small -> VexSdk.Display.vexDisplaySmallStringAt(0, 0, "");
            case Medium -> VexSdk.Display.vexDisplayStringAt(0, 0, "");
            case Large -> VexSdk.Display.vexDisplayBigStringAt(0, 0, "");
        }
    }

    /**
     * Get the height of the rendered text
     * @return the height of the text, in pixels
     */
    @Contract(pure = true)
    public int height() {
        configureTextMeasuring();
        return VexSdk.Display.vexDisplayStringHeightGet(text);
    }

    /**
     * Get the width of the rendered text
     * @return the width of the text, in pixels
     */
    @Contract(pure = true)
    public int width() {
        configureTextMeasuring();
        return VexSdk.Display.vexDisplayStringWidthGet(text);
    }

    public void draw(@NotNull Display display, @NotNull Color fgColor, @NotNull Color bgColor) {
        VexSdk.Display.vexDisplayForegroundColor(fgColor.raw());
        VexSdk.Display.vexDisplayBackgroundColor(bgColor.raw());

        var x = switch (hAlign) {
            case Left -> this.x;
            case Center -> this.x - width() / 2;
            case Right -> this.x - width();
        };

        var y = switch (vAlign) {
            case Top -> this.y;
            case Center -> this.y - height() / 2;
            case Bottom -> this.y - height();
        } + Display.HEADER_HEIGHT;

        switch (size) {
            case Small -> VexSdk.Display.vexDisplaySmallStringAt(x, y, text);
            case Medium -> VexSdk.Display.vexDisplayStringAt(x, y, text);
            case Large -> VexSdk.Display.vexDisplayBigStringAt(x, y, text);
        }
    }

    public enum Size {
        Small,
        Medium,
        Large
    }

    public enum HAlign {
        Left,
        Center,
        Right
    }

    public enum VAlign {
        Top,
        Center,
        Bottom
    }
}
