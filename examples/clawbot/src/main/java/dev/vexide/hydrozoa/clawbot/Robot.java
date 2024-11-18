package dev.vexide.hydrozoa.clawbot;

import dev.vexide.hydrozoa.CompetitionRobot;
import dev.vexide.hydrozoa.Controller;
import dev.vexide.hydrozoa.Peripherals;
import dev.vexide.hydrozoa.devices.DeviceException;
import dev.vexide.hydrozoa.devices.smart.Motor;

public class Robot extends CompetitionRobot {
    private final Motor leftMotor;
    private final Motor rightMotor;

    private final Controller controller;

    public Robot(Peripherals peripherals) {
        leftMotor = new Motor(peripherals.takePort(2), Motor.Gearset.Green, Motor.Direction.Forward);
        rightMotor = new Motor(peripherals.takePort(8), Motor.Gearset.Green, Motor.Direction.Reverse);
        controller = peripherals.takeController(Controller.Id.Primary);
    }

    @Override
    public void driverPeriodic() {
        final var input = controller.getState().orElseGet(Controller.State::empty);

        try {

            final var forward = input.leftStick().getY();
            final var turn = input.rightStick().getX();

            final var leftVoltage = (forward + turn) * Motor.V5_MAX_VOLTAGE;
            final var rightVoltage = (forward - turn) * Motor.V5_MAX_VOLTAGE;

            leftMotor.setVoltage(leftVoltage);
            rightMotor.setVoltage(rightVoltage);

        } catch (DeviceException err) {
            throw new RuntimeException(err);
        }
    }
}
