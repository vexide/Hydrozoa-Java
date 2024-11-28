package dev.vexide.hydrozoa;

import dev.vexide.hydrozoa.devices.Controller;
import dev.vexide.hydrozoa.devices.smart.SmartPort;
import dev.vexide.hydrozoa.display.Display;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * A collection of all the peripherals that are available to the robot.
 * <p>
 * Peripherals instances can be used to initialize the robot's hardware by using the various {@code take*} methods.
 * It is important to understand that peripherals may only be removed from the Peripherals instance once. This helps
 * to prevent the accidental duplication of hardware resources.
 * <p>
 * For example, the following examples will throw exceptions because the port number 1 has already been used:
 * <pre>{@code
 * var peripherals = Peripherals.take().orElseThrow();
 *
 * Motor leftMotor = new Motor(peripherals.takePort(1), Motor.Gearset.GREEN, Motor.Direction.FORWARD);
 * Motor rightMotor = new Motor(peripherals.takePort(1), Motor.Gearset.GREEN, Motor.Direction.FORWARD);
 * }</pre>
 * <pre>{@code
 * var peripherals = Peripherals.take().orElseThrow();
 *
 * var port = peripherals.takePort(1);
 * Motor leftMotor = new Motor(port, Motor.Gearset.GREEN, Motor.Direction.FORWARD);
 * Motor rightMotor = new Motor(port, Motor.Gearset.GREEN, Motor.Direction.FORWARD);
 * }</pre>
 */
public class Peripherals {
    private static final Key key = new Key();
    private static Peripherals instance = new Peripherals();

    private final @Nullable SmartPort[] ports = new SmartPort[21];
    private @Nullable Controller primaryController = new Controller(key, Controller.Id.Primary);
    private @Nullable Controller partnerController = new Controller(key, Controller.Id.Partner);
    private @Nullable Display display = new Display(key);

    private Peripherals() {
        for (int i = 0; i < ports.length; i++) {
            ports[i] = new SmartPort(key, i + 1);
        }
    }

    /**
     * Creates a new instance of the Peripherals class. This method may only be called once.
     *
     * @return a new instance of the Peripherals class, or an empty optional if this method has already been called
     */
    public static Optional<Peripherals> take() {
        if (instance == null) {
            return Optional.empty();
        }

        var peripherals = instance;
        instance = null;
        return Optional.of(peripherals);
    }

    /**
     * Infallibly creates a new instance of the Peripherals class. Using this method can make your program
     * susceptible to mistakes such as accidentally creating two motors that use the same smart port.
     * <h4>Conditions</h4>
     * <p>
     * This method may not be used to allow multiple instances of smart devices that use the same port to exist
     * simultaneously.
     * @return a new instance of the Peripherals class
     */
    public static @NotNull Peripherals unsafelyCreate() {
        return new Peripherals();
    }

    /**
     * Takes a smart port from this collection.
     * @param portNumber the port number to take, as labeled on the robot brain
     * @return the smart port
     * @throws IllegalArgumentException if the port number is not in the range [1, 21]
     * @throws NoSuchElementException if the port has already been removed
     */
    @Contract("_ -> new")
    public @NotNull SmartPort takePort(int portNumber) throws IllegalArgumentException, NoSuchElementException {
        if (portNumber <= 0 || portNumber > ports.length) {
            throw new IllegalArgumentException(String.format("Port %d is out of range [1, 21]", portNumber));
        }

        var port = ports[portNumber];
        if (port == null) {
            throw new NoSuchElementException(String.format("Port %d has already been used", portNumber));
        }

        ports[portNumber - 1] = null;
        return port;
    }

    /**
     * Takes a controller from this collection.
     * @param id the ID of the controller to take
     * @return the controller
     * @throws NoSuchElementException if the controller with this ID has already been removed
     */
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
            throw new NoSuchElementException(String.format("%s controller has already been removed", id));
        }

        return controller;
    }

    /**
     * Takes the display from this collection.
     * @return the display
     * @throws NoSuchElementException if the display has already been removed
     */
    @Contract("-> new")
    public @NotNull Display takeDisplay() throws NoSuchElementException {
        var display = this.display;
        this.display = null;

        if (display == null) {
            throw new NoSuchElementException("The display has already been removed");
        }

        return display;
    }

    /**
     * A unique key that is used to prevent the creation of peripheral devices outside the Peripherals class.
     */
    public static class Key {
        private Key() {}
    }
}
