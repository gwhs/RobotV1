package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Motor;


public class SpinMotor extends CommandBase {
    private Motor motor;

    public SpinMotor(Motor moto) {
        this.motor = moto;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        motor.setSelectedSensorPosition();
        //changes speed -1 to 1;
        motor.setMotorPercent(1);
        System.out.println("position is: " + motor.getPosition());
    }

    @Override
    public void end(boolean interrupted) {
        motor.setMotorPercent(0);
    System.out.println("Goodbye World");
    }


    @Override
    public boolean isFinished() {
        return false;
    }
}
