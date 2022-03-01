package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;

public class ToggleIntake extends CommandBase{
    private IntakeMotors motor;
    private boolean deploying = true;
    private IntakeMotors deployMotor;
    private double speed;


    public ToggleIntake(IntakeMotors motor){
        this.motor = motor;
        this.deploying = deploying;
        motor.setCoastMode();
        addRequirements(motor);
    }

    @Override
    public void initialize(){
        double currentPosition = deployMotor.getPosition();
        if (currentPosition <= 10){
            deploying = true;
            motor.undeploy(-speed);
            motor.choke();
        } else if(currentPosition >= 11000){
            deploying = false;
            motor.deploy(speed);
        } else {
            deploying = false;
            motor.undeploy(-speed);
            motor.choke();
        }


    }


    @Override
    public void execute(){
        System.out.println("Deploy motor Position: " + motor.getPosition());
        System.out.println(".");


    }

    @Override
    public void end(boolean interrupted){
        System.out.println("ENDING INTAKE - DEPLOY");
        motor.stopDeploy();
        if (motor.getPosition() > 1000 && motor.getPosition() < 10000){
            deploying = !deploying;
        }
    }

    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        double currentPosition = motor.getPosition();
        if (currentPosition >= 11000 && deploying == true){
            motor.suck();
            return true;
        } else if (currentPosition <= 10 && deploying == false){
            return true;
        }

        return false;
    }
}