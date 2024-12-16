package dev.vexide.hydrozoa;

import io.nacular.measured.units.Angle;

public final class Units {
    private Units() {}

    public static final Angle Radians = Angle.Companion.getRadians();
    public static final Angle Degrees = Angle.Companion.getDegrees();
}
