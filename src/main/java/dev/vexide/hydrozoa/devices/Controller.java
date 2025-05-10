package dev.vexide.hydrozoa.devices;

import dev.vexide.hydrozoa.CompetitionRuntime;
import dev.vexide.hydrozoa.Peripherals;
import dev.vexide.hydrozoa.sdk.VexSdk.Controller.Index;
import dev.vexide.hydrozoa.sdk.VexSdk;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

/**
 * A video-game-style VEX V5 controller, with two joysticks and a variety of buttons.
 * <p>
 * This class allows you to read from the buttons and joysticks on the controller and write to the controller’s display.
 * Controller instances are accessible through the {@link Peripherals} class.
 */
public class Controller {
    private final @NotNull Id id;
    private @NotNull State previousState = State.empty();

    /**
     * Creates a new controller instance.
     *
     * @param ignoredKey the peripherals key, which may only be accessed from within the {@link Peripherals} class
     * @param type       the type of controller to create
     * @see Peripherals#takeController(Controller.Id)
     */
    @ApiStatus.Internal
    public Controller(@NotNull Peripherals.Key ignoredKey, @NotNull Id type) {
        this.id = type;
    }

    /**
     * Gets the ID of this controller - whether it is the primary or partner controller.
     *
     * @return the controller ID
     */
    public @NotNull Controller.Id getId() {
        return id;
    }

    /**
     * Checks if this controller is connected to the robot brain.
     *
     * @return {@code true} if the controller is connected, {@code false} otherwise
     */
    public boolean connected() {
        var status = VexSdk.Controller.getConnectionStatus(id.raw());
        return status != VexSdk.Controller.Status.OFFLINE;
    }

    /**
     * Gets the latest data from the controller.
     *
     * @return the controller state, if the controller is connected and the robot is in driver control mode
     */
    public @NotNull Optional<State> getState() {
        if (!CompetitionRuntime.mode().equals(CompetitionRuntime.Mode.Driver) || !connected()) {
            return Optional.empty();
        }
        var state = new State(new JoystickState((byte) VexSdk.Controller.getController(id.raw(), VexSdk.Controller.Index.ANA_LEFT_X), (byte) VexSdk.Controller.getController(id.raw(), Index.ANA_LEFT_Y)), new JoystickState((byte) VexSdk.Controller.getController(id.raw(), Index.ANA_RIGHT_X), (byte) VexSdk.Controller.getController(id.raw(), Index.ANA_RIGHT_Y)), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_A) == 1, previousState.a().pressed()), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_B) == 1, previousState.b().pressed()), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_X) == 1, previousState.x().pressed()), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_Y) == 1, previousState.y().pressed()), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_UP) == 1, previousState.up().pressed()), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_DOWN) == 1, previousState.down().pressed()), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_LEFT) == 1, previousState.left().pressed()), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_RIGHT) == 1, previousState.right().pressed()), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_L1) == 1, previousState.l1().pressed()), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_L2) == 1, previousState.l2().pressed()), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_R1) == 1, previousState.r1().pressed()), new ButtonState(VexSdk.Controller.getController(id.raw(), Index.BUTTON_R2) == 1, previousState.r2().pressed()));
        previousState = state;
        return Optional.of(state);
    }

    /**
     * A unique identifier for one of the two V5 controllers that may be connected to the robot brain.
     */
    public enum Id {
        /**
         * The primary controller, also known as the "master" controller. This controller is directly connected to the
         * robot brain via a radio or wired connection.
         */
        Primary,
        /**
         * The partner controller. This controller is connected to the primary controller via a wired connection.
         */
        Partner;

        /**
         * Gets the internal representation of this controller ID.
         *
         * @return the raw controller ID value
         */
        @NotNull
        VexSdk.Controller.Id raw() {
            return switch (this) {
                case Primary -> VexSdk.Controller.Id.MASTER;
                case Partner -> VexSdk.Controller.Id.PARTNER;
            };
        }
    }

    /**
     * The current state of the controller, including the positions of the joysticks and the states of the buttons.
     *
     * @param leftStick  the state of the left joystick
     * @param rightStick the state of the right joystick
     * @param a          the state of the A button
     * @param b          the state of the B button
     * @param x          the state of the X button
     * @param y          the state of the Y button
     * @param up         the state of the up button
     * @param down       the state of the down button
     * @param left       the state of the left button
     * @param right      the state of the right button
     * @param l1         the state of the L1 button
     * @param l2         the state of the L2 button
     * @param r1         the state of the R1 button
     * @param r2         the state of the R2 button
     */
    public record State(@NotNull JoystickState leftStick, @NotNull JoystickState rightStick,
                        @NotNull ButtonState a, @NotNull ButtonState b, @NotNull ButtonState x,
                        @NotNull ButtonState y, @NotNull ButtonState up, @NotNull ButtonState down,
                        @NotNull ButtonState left, @NotNull ButtonState right,
                        @NotNull ButtonState l1, @NotNull ButtonState l2, @NotNull ButtonState r1,
                        @NotNull ButtonState r2) {
        /**
         * Gets an empty controller state, with all buttons and joysticks in their default positions.
         * <p>
         * This state is useful as a fallback when the controller is not connected or the robot is not in driver control
         * mode:
         * <pre>{@code
         * Controller controller = peripherals.takeController(Controller.Id.Primary);
         * var state = controller.getState().orElseGet(Controller.State::empty);
         * }</pre>
         *
         * @return an empty controller state
         */
        @Contract(value = "-> new", pure = true)
        public static @NotNull State empty() {
            return new State(JoystickState.empty(), JoystickState.empty(), ButtonState.empty(), ButtonState.empty(), ButtonState.empty(), ButtonState.empty(), ButtonState.empty(), ButtonState.empty(), ButtonState.empty(), ButtonState.empty(), ButtonState.empty(), ButtonState.empty(), ButtonState.empty(), ButtonState.empty());
        }
    }

    /**
     * The current state of a joystick, including the raw X and Y values and their normalized values.
     *
     * @param rawX the raw X value of the joystick, ranging from -127 to 127
     * @param rawY the raw Y value of the joystick, ranging from -127 to 127
     */
    public record JoystickState(byte rawX, byte rawY) {
        /**
         * Gets an empty joystick state, with both the X and Y values set to 0.
         *
         * @return an empty joystick state
         * @see State#empty()
         */
        @Contract(value = "-> new", pure = true)
        public static @NotNull JoystickState empty() {
            return new JoystickState((byte) 0, (byte) 0);
        }

        /**
         * Gets the normalized X value of the joystick, ranging from -1.0 to 1.0.
         *
         * @return the normalized X value
         */
        public double getX() {
            return rawX / 127.0;
        }

        /**
         * Gets the normalized Y value of the joystick, ranging from -1.0 to 1.0.
         *
         * @return the normalized Y value
         */
        public double getY() {
            return rawY / 127.0;
        }
    }

    /**
     * The current state of a button, including whether it is currently pressed and whether it was pressed when the
     * previous controller state was read.
     *
     * @param pressed           whether the button is currently pressed
     * @param previouslyPressed whether the button was pressed when the controller was last read from
     */
    public record ButtonState(boolean pressed, boolean previouslyPressed) {
        /**
         * Gets an empty button state, with the button both currently not pressed and previously not pressed.
         *
         * @return an empty button state
         * @see State#empty()
         */
        @Contract(value = "-> new", pure = true)
        public static @NotNull ButtonState empty() {
            return new ButtonState(false, false);
        }

        /**
         * Checks if the button is currently released (not pressed).
         *
         * @return {@code true} if the button is released, {@code false} if it is pressed
         */
        public boolean released() {
            return !pressed;
        }

        /**
         * Checks if the button used to be released, but is now pressed. This is useful for triggering actions when a
         * button is pressed, but not continuously running those actions while the button is held down.
         * <p>
         * For example, you might use this method to toggle a boolean value each time a button is pressed:
         * <pre>{@code
         * var state = controller.getState().orElseGet(Controller.State::empty);
         * boolean toggle = false;
         * if (state.a().isNowPressed()) {
         *     toggle = !toggle;
         *     System.out.println("Toggled: " + toggle);
         * }
         * }</pre>
         *
         * @return {@code true} if the button was released and is now pressed, {@code false} otherwise
         * @see #pressed()
         * @see #isNowReleased()
         */
        public boolean isNowPressed() {
            return pressed && !previouslyPressed;
        }

        /**
         * Checks if the button used to be pressed, but is now released. This is useful for triggering actions when a
         * button is released, but not continuously running those actions until the button is pressed again.
         *
         * @return {@code true} if the button was pressed and is now released, {@code false} otherwise
         * @see #released()
         * @see #isNowPressed()
         */
        public boolean isNowReleased() {
            return !pressed && previouslyPressed;
        }
    }
}
