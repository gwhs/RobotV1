package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;

public class ToggleIntake extends CommandBase{
    private IntakeMotors motors;
    private boolean deploying = true;


    public ToggleIntake(IntakeMotors motors, boolean deploying){
        this.motors = motors;
        this.deploying = deploying;
        motors.setCoastMode();
        addRequirements(motors);
    }

    @Override
    public void initialize(){
        double currentPosition = motors.getAlphaPosition();
        if (currentPosition <= 10){
            deploying = true;
            motors.undeploy();
            motors.choke();
        } else if(currentPosition >= 11000){
            deploying = false;
            motors.deploy();
        } else {
            deploying = false;
            motors.undeploy();
            motors.choke();
        }


    }


    @Override
    public void execute(){
        System.out.println("Alpha Motor Position: " + motors.getAlphaPosition());
        System.out.println(".");


    }

    @Override
    public void end(boolean interrupted){
        System.out.println("ENDING INTAKE - DEPLOY");
        motors.stopDeploy();
        if (motors.getAlphaPosition() > 1000 && motors.getAlphaPosition() < 10000){
            deploying = !deploying;
        }
    }

    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        double currentPosition = motors.getAlphaPosition();
        if (currentPosition >= 11000 && deploying == true){
            motors.suck();
            return true;
        } else if (currentPosition <= 10 && deploying == false){
            return true;
        }

        return false;
    }
}