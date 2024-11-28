package dev.vexide.hydrozoa;

import dev.vexide.hydrozoa.sdk.VexSdk;

/**
 * A utility class for platform-specific operations.
 */
public final class Platform {
    private Platform() {
    }

    /**
     * Yields the current task so that background processing may run, sensors may be updated, and the serial port may be
     * flushed.
     */
    public static void yield() {
        VexSdk.vexTasksRun();
    }
}
