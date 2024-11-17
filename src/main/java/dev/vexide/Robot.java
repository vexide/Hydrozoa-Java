package dev.vexide;

import dev.vexide.sdk.CompetitionRobot;
import dev.vexide.sdk.screen.Display;
import dev.vexide.sdk.screen.Rect;
import dev.vexide.sdk.screen.Rgb;

public class Robot extends CompetitionRobot {
    private final Display display = new Display();

    public Robot() {
        System.out.println("Starting!");
    }

    @Override
    public void driverInit() {
        System.out.println("Driver mode initialized!");
        display.fill(new Rect(0, 0 , 100, 100), Rgb.fromInteger(0xFFFFFF));
    }
}
