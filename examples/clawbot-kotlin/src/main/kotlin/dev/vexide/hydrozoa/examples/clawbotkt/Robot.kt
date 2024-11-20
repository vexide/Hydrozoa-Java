package dev.vexide.hydrozoa.examples.clawbotkt

import dev.vexide.hydrozoa.CompetitionRobot
import dev.vexide.hydrozoa.Controller
import dev.vexide.hydrozoa.Peripherals
import dev.vexide.hydrozoa.devices.DeviceException
import dev.vexide.hydrozoa.devices.smart.Motor
import dev.vexide.hydrozoa.devices.smart.Motor.BrakeMode
import dev.vexide.hydrozoa.devices.smart.MotorControl.Brake
import dev.vexide.hydrozoa.devices.smart.MotorControl.Voltage

class Robot(peripherals: Peripherals) : CompetitionRobot {
    private val leftMotor = Motor(peripherals.takePort(2), Motor.Gearset.Green, Motor.Direction.Forward)
    private val rightMotor = Motor(peripherals.takePort(8), Motor.Gearset.Green, Motor.Direction.Forward)
    private val claw = Motor(peripherals.takePort(9), Motor.Gearset.Green, Motor.Direction.Reverse)
    private val arm = Motor(peripherals.takePort(7), Motor.Gearset.Green, Motor.Direction.Reverse)

    private val controller = peripherals.takeController(Controller.Id.Primary)

    override fun driverPeriodic() {
        val input = controller.state.orElseGet(Controller.State::empty)

        val forward = -input.leftStick.y
        val turn = input.rightStick.x

        val leftVoltage = (turn + forward) * Motor.V5_MAX_VOLTAGE
        val rightVoltage = (turn - forward) * Motor.V5_MAX_VOLTAGE

        try {
            leftMotor.target = Voltage(leftVoltage)
            rightMotor.target = Voltage(rightVoltage)

            if (input.l1.pressed) {
                arm.target = Voltage(6.0)
            } else if (input.r1.pressed) {
                arm.target = Voltage(-6.0)
            } else {
                arm.target = Brake(BrakeMode.Hold)
            }

            if (input.l2.pressed) {
                claw.target = Voltage(6.0)
            } else if (input.r2.pressed) {
                claw.target = Voltage(-6.0)
            } else {
                arm.target = Brake(BrakeMode.Hold)
            }
        } catch (err: DeviceException) {
            throw RuntimeException(err)
        }
    }
}
