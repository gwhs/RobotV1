package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;

public class IntakeCommand extends CommandBase{
    private IntakeMotors motors;
    private long elapsedTime;
    private long start;
    private long current;
    


    public IntakeCommand(IntakeMotors motors){
        this.motors = motors;

        addRequirements(motors);
    }

    @Override
    public void initialize(){
        motors.deploy();

        start = System.currentTimeMillis();
    }


    @Override
    public void execute(){
        
        motors.suck();
        
    }

    @Override
    public void end(boolean interupted){
    }

    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        
        return false;
    }
}
