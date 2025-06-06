package dev.vexide.hydrozoa.examples.clawbot;

import dev.vexide.hydrozoa.CompetitionRobot;
import dev.vexide.hydrozoa.DeviceException;
import dev.vexide.hydrozoa.Peripherals;
import dev.vexide.hydrozoa.devices.Controller;
import dev.vexide.hydrozoa.devices.smart.Motor;

public class Robot implements CompetitionRobot {
    private final Motor leftMotor;
    private final Motor rightMotor;
    private final Motor claw;
    private final Motor arm;

    private final Controller controller;

    public Robot(Peripherals peripherals) {
        leftMotor = new Motor(peripherals.takePort(2), Motor.Gearset.Green, Motor.Direction.FORWARD);
        rightMotor = new Motor(peripherals.takePort(8), Motor.Gearset.Green, Motor.Direction.FORWARD);
        claw = new Motor(peripherals.takePort(9), Motor.Gearset.Green, Motor.Direction.REVERSE);
        arm = new Motor(peripherals.takePort(7), Motor.Gearset.Green, Motor.Direction.REVERSE);
        controller = peripherals.takeController(Controller.Id.Primary);
    }

    @Override
    public void driverPeriodic() {
        final var input = controller.getState().orElseGet(Controller.State::empty);


        final var forward = -input.leftStick().getY();
        final var turn = input.rightStick().getX();

        final var leftVoltage = (turn + forward) * Motor.V5_MAX_VOLTAGE;
        final var rightVoltage = (turn - forward) * Motor.V5_MAX_VOLTAGE;

        try {
            leftMotor.setVoltage(leftVoltage);
            rightMotor.setVoltage(rightVoltage);

            if (input.l1().pressed()) {
                arm.setVoltage(6.0);
            } else if (input.r1().pressed()) {
                arm.setVoltage(-6.0);
            } else {
                arm.brake(Motor.BrakeMode.HOLD);
            }

            if (input.l2().pressed()) {
                claw.setVoltage(6.0);
            } else if (input.r2().pressed()) {
                claw.setVoltage(-6.0);
            } else {
                claw.brake(Motor.BrakeMode.HOLD);
            }
        } catch (DeviceException err) {
            throw new RuntimeException(err);
        }
    }
}
