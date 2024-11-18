package dev.vexide.hydrozoa;

import dev.vexide.hydrozoa.sdk.V5_ControllerId;
import dev.vexide.hydrozoa.sdk.V5_ControllerIndex;
import dev.vexide.hydrozoa.sdk.V5_ControllerStatus;
import dev.vexide.hydrozoa.sdk.VexSdk;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class Controller {
    private final @NotNull Id id;
    private @NotNull State previousState = State.empty();

    Controller(@NotNull Id type) {
        this.id = type;
    }

    public @NotNull Controller.Id getId() {
        return id;
    }

    public boolean connected() {
        var status = VexSdk.Controller.vexControllerConnectionStatusGet(id.raw());
        return !status.equals(V5_ControllerStatus.kV5ControllerOffline);
    }

    public @NotNull Optional<State> getState() {
        if (!CompetitionRuntime.mode().equals(CompetitionRuntime.Mode.Driver) || !connected()) {
            return Optional.empty();
        }
        var state = new State(
                new JoystickState(
                        (byte) VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.AnaLeftX),
                        (byte) VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.AnaLeftY)
                ),
                new JoystickState(
                        (byte) VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.AnaRightX),
                        (byte) VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.AnaRightY)
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonA) == 1,
                        previousState.a().pressed()
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonB) == 1,
                        previousState.b().pressed()
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonX) == 1,
                        previousState.x().pressed()
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonY) == 1,
                        previousState.y().pressed()
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonUp) == 1,
                        previousState.up().pressed()
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonDown) == 1,
                        previousState.down().pressed()
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonLeft) == 1,
                        previousState.left().pressed()
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonRight) == 1,
                        previousState.right().pressed()
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonL1) == 1,
                        previousState.l1().pressed()
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonL2) == 1,
                        previousState.l2().pressed()
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonR1) == 1,
                        previousState.r1().pressed()
                ),
                new ButtonState(
                        VexSdk.Controller.vexControllerGet(id.raw(), V5_ControllerIndex.ButtonR2) == 1,
                        previousState.r2().pressed()
                )
        );
        previousState = state;
        return Optional.of(state);
    }

    public enum Id {
        Primary,
        Partner;

        @NotNull
        V5_ControllerId raw() {
            return switch (this) {
                case Primary -> V5_ControllerId.kControllerMaster;
                case Partner -> V5_ControllerId.kControllerPartner;
            };
        }
    }

    public record State(
            @NotNull JoystickState leftStick,
            @NotNull JoystickState rightStick,
            @NotNull ButtonState a,
            @NotNull ButtonState b,
            @NotNull ButtonState x,
            @NotNull ButtonState y,
            @NotNull ButtonState up,
            @NotNull ButtonState down,
            @NotNull ButtonState left,
            @NotNull ButtonState right,
            @NotNull ButtonState l1,
            @NotNull ButtonState l2,
            @NotNull ButtonState r1,
            @NotNull ButtonState r2
    ) {
        @Contract(value = "-> new", pure = true)
        public static @NotNull State empty() {
            return new State(
                    JoystickState.empty(),
                    JoystickState.empty(),
                    ButtonState.empty(),
                    ButtonState.empty(),
                    ButtonState.empty(),
                    ButtonState.empty(),
                    ButtonState.empty(),
                    ButtonState.empty(),
                    ButtonState.empty(),
                    ButtonState.empty(),
                    ButtonState.empty(),
                    ButtonState.empty(),
                    ButtonState.empty(),
                    ButtonState.empty()
            );
        }
    }

    public record JoystickState(
            byte rawX,
            byte rawY
    ) {
        @Contract(value = "-> new", pure = true)
        public static @NotNull JoystickState empty() {
            return new JoystickState((byte) 0, (byte) 0);
        }

        public double getX() {
            return rawX / 127.0;
        }

        public double getY() {
            return rawY / 127.0;
        }
    }

    public record ButtonState(
            boolean pressed,
            boolean previousPressed
    ) {
        @Contract(value = "-> new", pure = true)
        public static @NotNull ButtonState empty() {
            return new ButtonState(false, false);
        }

        public boolean released() {
            return !pressed;
        }

        public boolean isNowPressed() {
            return pressed && !previousPressed;
        }

        public boolean isNowReleased() {
            return !pressed && previousPressed;
        }
    }
}
