package dev.vexide.hydrozoa;

import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;

public class Platform {
    public static void yield() {
        VexSdk.vexTasksRun();
    }
}
