package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.commands.IntakeCommands.Spit;

public class Spit extends CommandBase{
    private IntakeMotors motors;
    private boolean spittingMode;

    public Spit(IntakeMotors motors){
        this.motors = motors;

        addRequirements(motors);
    }

    @Override
    public void initialize(){

    }


    @Override
    public void execute(){
        
        motors.spit();
    }

    @Override
    public void end(boolean interrupted){
        motors.suck();
    }

    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        
        return false;
    }
}
