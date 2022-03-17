package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.UpperLowerIntake;
import frc.robot.commands.IntakeCommands.SpinIntake;

public class SpinIntake extends CommandBase{

    private double lowerSpeed;
    private double upperSpeed;
    private UpperLowerIntake UpperLowerIntake;

    public SpinIntake(UpperLowerIntake UpperLowerIntake, double upperSpeed, double lowerSpeed){
        this.UpperLowerIntake = UpperLowerIntake;
        this.upperSpeed = upperSpeed;
        this.lowerSpeed = lowerSpeed;

        addRequirements(UpperLowerIntake);
    }

    @Override
    public void initialize(){
    
    }


    @Override
    public void execute(){
        
        UpperLowerIntake.setIntakeMotorSpeeds(upperSpeed, lowerSpeed);
    }

    @Override
    public void end(boolean interrupted){
        UpperLowerIntake.choke();
    }

    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        
        return false;
    }
}
