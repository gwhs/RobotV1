package frc.robot.commands.CatapultCommands;
import frc.robot.subsystems.CatapultSubsystem;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CatapultCommand extends CommandBase {
    private CatapultSubsystem motor;
     // motor keeps moving after end, so we get an offset to make sure the motor returns to the same position each time
    private boolean ran; // ensures the motor shoots, otherwise, it will not run after one shot, needed for isFinished
    private double returnSpeed;// returns at slow pace
    private double speed;


    //private long start;
    //private long end;
    //used for creating the motor and setting the speed
    public CatapultCommand(CatapultSubsystem moto, double speed) {
        this.motor = moto;
        this.speed = speed;

        this.returnSpeed = 0;// -0.06;
        
        addRequirements(moto);

    }

    @Override
    public void initialize() {
        //start = System.currentTimeMillis();
        ran = false;
        System.out.println("Round 1 pos:"+motor.getPosition());
        //sets speed\
        motor.setSelectedSensorPosition();
        motor.setPercent(speed);
    }


    @Override
    // keeps going until isFinished returns true, pretty much a while loop
    public void execute() {
        //long elapsedTime = System.currentTimeMillis() - start;;
        //position is 77.3k for 360 degrees of rotation
        if (motor.isFinishedShooting()){
            motor.setBrake();
            motor.setPercent(returnSpeed);
            //put motor in reverse to reset
            ran = true;
        }
        System.out.println(motor.getPosition());
        //System.out.println("Elapsed time: " + elapsedTime);
        //System.out.println("check position " + motor.getPosition());
      //  SmartDashboard.putNumber("Spinner Pos", motor.getPosition());

    }

    @Override
    // executed when isfinished returns true (below)
    public void end(boolean interrupted) {
        //stops 
        //end = System.currentTimeMillis();
        //System.out.println("End time: " + (end - start));
        motor.setPercent(0);
        System.out.println(motor.getPosition());

    }


    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        if (motor.isFinishedReturning()&&ran){
            return true;
        }
        return false;
    }
}
