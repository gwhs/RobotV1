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
    }

    @Override
    public void initialize(){
        motors.setSpeed();
        start = System.currentTimeMillis();
    }


    @Override
    public void execute(){
        current = System.currentTimeMillis();
        elapsedTime = current - start;
        if (elapsedTime >= 2000){
            motors.stop();
        }
    }

    @Override
    public void end(boolean interupted){
        System.out.println("ENDING INTAKE");
    }

    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        return true;
        
        //return false;
    }
}