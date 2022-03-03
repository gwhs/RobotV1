package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;

public class ToggleIntake extends CommandBase{
    private IntakeMotors motor;
    private boolean deploying;
    private double speed = .3;


    public ToggleIntake(IntakeMotors motor){
        this.motor = motor;
<<<<<<< HEAD
        this.deploying = deploying;
        motor.setBrakeMode();
=======
        motor.setZero();
        motor.setCoastMode();
>>>>>>> be47373921ca1c1cd26d48466a030aef5fc507e1
        addRequirements(motor);
    }

    @Override
    public void initialize(){
<<<<<<< HEAD
        double currentPosition = deployMotor.getPosition();
        System.out.println(deployMotor.getPosition());
=======
        double currentPosition = motor.getPosition();
>>>>>>> be47373921ca1c1cd26d48466a030aef5fc507e1
        if (currentPosition <= 10){
            deploying = true;
            motor.undeploy(speed);
            motor.choke();
        } else if(currentPosition >= 1700){
            deploying = false;
            motor.deploy(speed);
        } else {
            deploying = false;
            motor.undeploy(speed);
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
    }

    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        double currentPosition = motor.getPosition();
<<<<<<< HEAD
        if (currentPosition >= 1700 && deploying == true){
            motor.setIntakeMotorSpeeds(0, 0);
=======
        if (currentPosition >= 11000 && deploying == true){
            motor.suckBalls();
>>>>>>> be47373921ca1c1cd26d48466a030aef5fc507e1
            return true;
        } else if (currentPosition <= 10 && deploying == false){
            motor.choke();
            return true;
        }

        return false;
    }
}