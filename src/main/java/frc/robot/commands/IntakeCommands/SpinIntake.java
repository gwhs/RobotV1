package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.commands.IntakeCommands.SpinIntake;

public class SpinIntake extends CommandBase{
    private IntakeMotors motors;
    private double lowerSpeed;
    private double upperSpeed;

    public SpinIntake(IntakeMotors motors, double upperSpeed, double lowerSpeed){
        this.motors = motors;
        this.upperSpeed = upperSpeed;
        this.lowerSpeed = lowerSpeed;

        addRequirements(motors);
    }

    @Override
    public void initialize(){
    
    }


    @Override
    public void execute(){
        
        motors.setIntakeMotorSpeeds(upperSpeed, lowerSpeed);
    }

    @Override
    public void end(boolean interrupted){
<<<<<<< HEAD:src/main/java/frc/robot/commands/IntakeCommands/SpinIntake.java
        motors.choke();
=======
        motors.suckBalls();
>>>>>>> be47373921ca1c1cd26d48466a030aef5fc507e1:src/main/java/frc/robot/commands/IntakeCommands/Spit.java
    }

    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        
        return false;
    }
}
