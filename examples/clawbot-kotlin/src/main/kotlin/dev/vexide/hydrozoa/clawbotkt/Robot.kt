package dev.vexide.hydrozoa.clawbotkt

import dev.vexide.hydrozoa.CompetitionRobot
import dev.vexide.hydrozoa.Controller
import dev.vexide.hydrozoa.Peripherals
import dev.vexide.hydrozoa.devices.DeviceException
import dev.vexide.hydrozoa.devices.smart.Motor

class Robot(peripherals: Peripherals) : CompetitionRobot {
    private val leftMotor = Motor(peripherals.takePort(2), Motor.Gearset.Green, Motor.Direction.Forward)
    private val rightMotor = Motor(peripherals.takePort(8), Motor.Gearset.Green, Motor.Direction.Reverse)

    private val controller = peripherals.takeController(Controller.Id.Primary)

    override fun driverPeriodic() {
        val input = controller.state.orElseGet(Controller.State::empty)

        try {
            val forward = input.leftStick.y
            val turn = input.rightStick.x

            val leftVoltage = (forward + turn) * Motor.V5_MAX_VOLTAGE
            val rightVoltage = (forward - turn) * Motor.V5_MAX_VOLTAGE

            leftMotor.setVoltage(leftVoltage)
            rightMotor.setVoltage(rightVoltage)
        } catch (err: DeviceException) {
            throw RuntimeException(err)
        }
    }
}
