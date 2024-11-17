package dev.vexide.sdk;

import org.teavm.interop.Import;
import org.teavm.interop.StaticInit;

@StaticInit
public final class VexSdk {
    @Import(module = "vex", name = "vexTasksRun")
    public static native void vexTasksRun();

    @Import(module = "vex", name = "vexCompetitionStatus")
    public static native int vexCompetitionStatus();

    @Import(module = "vex", name = "vexDisplayForegroundColor")
    public static native void vexDisplayForegroundColor(int color);
    @Import(module = "vex", name = "vexDisplayRectFill")
    public static native void vexDisplayRectFill(int x1, int y1, int x2, int y2);
}
