package dev.vexide.hydrozoa;

import dev.vexide.hydrozoa.sdk.Hydrozoa;
import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;

/**
 * A utility class for platform-specific operations.
 */
public final class Platform {
    private Platform() {
    }

    public static final int STDOUT_BUFFER_SIZE = 2048;
    public static final int STDIN_BUFFER_SIZE = 4096;
    private static final int STDIO_CHANNEL = 1;

    /**
     * Yields the current task so that background processing may run, sensors may be updated, and the serial port may be
     * flushed.
     */
    public static void yield() {
        VexSdk.Tasks.run();
    }
    private static final Duration FLUSH_TIMEOUT = Duration.ofMillis(15);

    // TODO: Does System.exit() work in Hydrozoa?
//    /**
//     * Exits the program, flushing the serial port before returning to the VEX V5's home screen.
//     * <p>
//     * This method does not return.
//     */
//    @SuppressWarnings("InfiniteLoopStatement")
//    @Contract("-> fail")
//    public static void exit() {
//        var exitTime = Instant.now().plus(FLUSH_TIMEOUT);
//
//        while (Instant.now().isBefore(exitTime)) {
//            VexSdk.vexTasksRun();
//
//            if (VexSdk.Serial.vexSerialWriteFree(STDIO_CHANNEL) == STDOUT_BUFFER_SIZE) {
//                break;
//            }
//        }
//
//        VexSdk.System.vexSystemExitRequest();
//
//        while (true) {
//            VexSdk.vexTasksRun();
//        }
//    }

    private static final int SKIPPED_FRAMES = 7;
    private static final String EXCEPTION_PREFIX = "Uncaught exception: ";

    @Contract("_ -> fail")
    public static void handleException(@NotNull Throwable error) {
        System.out.println(EXCEPTION_PREFIX);
        error.printStackTrace();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.print(EXCEPTION_PREFIX);
        pw.println(error);

        var stackTraceElements = error.getStackTrace();
        for (int i = SKIPPED_FRAMES; i < stackTraceElements.length; i++) {
            pw.println("\tat " + stackTraceElements[i]);
        }

        Hydrozoa.panic(sw.toString());
    }
}
