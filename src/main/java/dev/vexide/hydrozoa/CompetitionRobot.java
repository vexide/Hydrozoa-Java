package dev.vexide.hydrozoa;

public abstract class CompetitionRobot {
    public void connected() {}
    public void disconnected() {}

    public void disabledInit() {}
    public void disabledPeriodic() {}
    public void disabledExit() {}

    public void autonomousInit() {}
    public void autonomousPeriodic() {}
    public void autonomousExit() {}

    public void driverInit() {}
    public void driverPeriodic() {}
    public void driverExit() {}
}
