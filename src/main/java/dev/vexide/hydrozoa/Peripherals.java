package dev.vexide.hydrozoa;

import dev.vexide.hydrozoa.devices.smart.SmartPort;
import dev.vexide.hydrozoa.display.Display;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.Optional;

public class Peripherals {
    private static final Key key = new Key();
    private static Peripherals instance = new Peripherals();

    private final @Nullable SmartPort[] ports = new SmartPort[21];
    private @Nullable Controller primaryController = new Controller(Controller.Id.Primary);
    private @Nullable Controller partnerController = new Controller(Controller.Id.Partner);
    private @Nullable Display display = new Display(key);

    private Peripherals() {
        for (int i = 0; i < ports.length; i++) {
            ports[i] = new SmartPort(key, i);
        }
    }

    public static Optional<Peripherals> take() {
        if (instance == null) {
            return Optional.empty();
        }

        var peripherals = instance;
        instance = null;
        return Optional.of(peripherals);
    }

    public static @NotNull Peripherals unsafelyCreate() {
        return new Peripherals();
    }

    @Contract("_ -> new")
    public @NotNull SmartPort takePort(int portNumber) throws IllegalArgumentException, NoSuchElementException {
        if (portNumber < 0 || portNumber >= ports.length) {
            throw new IllegalArgumentException(String.format("Port %d is out of range [0, 21]", portNumber));
        }

        var port = ports[portNumber];
        if (port == null) {
            throw new NoSuchElementException(String.format("Port %d has already been used", portNumber));
        }

        ports[portNumber] = null;
        return port;
    }

    @Contract("_ -> new")
    public @NotNull Controller takeController(@NotNull Controller.Id id) throws NoSuchElementException {
        Controller controller;
        if (id == Controller.Id.Primary) {
            controller = primaryController;
            primaryController = null;
        } else {
            controller = partnerController;
            partnerController = null;
        }

        if (controller == null) {
            throw new NoSuchElementException(String.format("%s controller has already been taken", id));
        }

        return controller;
    }

    @Contract("-> new")
    public @NotNull Display takeDisplay() throws NoSuchElementException {
        var display = this.display;
        this.display = null;

        if (display == null) {
            throw new NoSuchElementException("The display has already been taken");
        }

        return display;
    }

    public static class Key {
        Key() {
        }
    }
}
