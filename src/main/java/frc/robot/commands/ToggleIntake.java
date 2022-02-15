package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;

public class ToggleIntake extends CommandBase{
    private IntakeMotors motors;
    private static boolean deployed = false;
    private long elapsedTime;
    private long start;
    private long current;
    


    public ToggleIntake(IntakeMotors motors){
        this.motors = motors;

        addRequirements(motors);
    }

    @Override
    public void initialize(){
        if (!deployed)
        motors.deploy();
        else if (deployed)
        motors.undeploy();

        start = System.currentTimeMillis();
    }


    @Override
    public void execute(){
        
        current = System.currentTimeMillis();
        System.out.println("Alpha Motor Position: " + motors.getAlphaPosition());
        System.out.println("Beta Motor Position: " + motors.getBetaPosition());
        elapsedTime = current - start;
        
    }

    @Override
    public void end(boolean interupted){
        System.out.println("ENDING INTAKE - DEPLOY");
    }

    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        if (!deployed && motors.getAlphaPosition() >= 11600){
            motors.chill();
            deployed = !deployed;
            return true;
        }
        else if (deployed && motors.getAlphaPosition() <= 0){
            motors.chill();
            deployed = !deployed;
            return true;
        }

        return false;
    }
}