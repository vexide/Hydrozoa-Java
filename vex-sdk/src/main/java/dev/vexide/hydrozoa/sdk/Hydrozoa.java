package dev.vexide.hydrozoa.sdk;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.teavm.interop.Import;

/**
 * A utility class for operations specific to the Hydrozoa runtime.
 */
public final class Hydrozoa {
    private Hydrozoa() {
    }

    /**
     * Causes the program to abnormally terminate with the given message.
     *
     * @param message a message describing the cause of the panic
     */
    @Import(module = "hydrozoa", name = "panic")
    @Contract("_ -> fail")
    public static native void panic(@NotNull String message);
}
