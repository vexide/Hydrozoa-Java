package dev.vexide;

import dev.vexide.sdk.VexSdk;
import dev.vexide.sdk.screen.Rect;
import dev.vexide.sdk.screen.Rgb;
import dev.vexide.sdk.screen.Screen;

public class Main {

    public static Screen screen = new Screen();


    public static void main(String[] args) {
        System.out.println("Hello and welcome!");
        screen.fill(new Rect(0, 0 , 100, 100), new Rgb(255, 255, 255));
    }
}
