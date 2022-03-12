package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.subsystems.UpperLowerIntake;
import frc.robot.commands.IntakeCommands.SpinIntake;

public class SpinIntake extends CommandBase{
    private UpperLowerIntake upperLowerIntake;
    private double lowerSpeed;
    private double upperSpeed;

    public SpinIntake(UpperLowerIntake m_UpperLowerIntake, double upperSpeed, double lowerSpeed){
        this.upperLowerIntake = upperLowerIntake;
        this.upperSpeed = upperSpeed;
        this.lowerSpeed = lowerSpeed;

        addRequirements(m_UpperLowerIntake);
    }

    @Override
    public void initialize(){
    
    }


    @Override
    public void execute(){
        
        upperLowerIntake.setIntakeMotorSpeeds(upperSpeed, lowerSpeed);
    }

    @Override
    public void end(boolean interrupted){
        upperLowerIntake.choke();
    }

    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        
        return false;
    }
}
