//
///
// REVIEW: RUns the motor, uses methods from motor.java





package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.sensors.CANCoder;
import frc.robot.Motor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpinMotor extends CommandBase {
    private Motor motor;
    private double speed; // percent output -1 -> 1 double
    private double offset; // motor keeps moving after end, so we get an offset to make sure the motor returns to the same position each time
    private boolean ran; // ensures the motor shoots, otherwise, it will not run after one shot, needed for isFinished

    private long start;
    private long end;
    //used for creating the motor and setting the speed
    public SpinMotor(Motor moto, double speed) {
        this.motor = moto;
        this.speed = speed;
        
    }

    @Override
    public void initialize() {
        //sets the offset, not done at end because motor can stil be moving
        offset = motor.getPosition();
        //resets position to 0
        motor.setSelectedSensorPosition();
        start = System.currentTimeMillis();
        ran = false;
        System.out.println("Round 1 pos:"+motor.getPosition());
        System.out.println("Round 2 pos: "+motor.getPosition());
        //sets speed
        motor.setMotorPercent(speed);
    }


    @Override
    // keeps going until isFinished returns true, pretty much a while loop
    public void execute() {
        long elapsedTime = System.currentTimeMillis() - start;
        double position = motor.getPosition();
        //position is 77.3k for 360 degrees of rotation
        if (elapsedTime >100){
            motor.brakeMode();
            motor.setMotorPercent(0);
            
            //put motor in reverse to reset
            ran = true;
        }
        System.out.println("Elapsed time: " + elapsedTime);
        System.out.println("check position " + motor.getPosition());
      //  SmartDashboard.putNumber("Spinner Pos", motor.getPosition());

    }

    @Override
    // executed when isfinished returns true (below)
    public void end(boolean interrupted) {
        //stops 
        end = System.currentTimeMillis();
        System.out.println("End time: " + (end - start));
        motor.setMotorPercent(0);
        System.out.println("Goodbye World");
    }


    @Override
    public boolean isFinished() {
        // makes sure arm is at bottom and has shot before ending.
        if (motor.getPosition() < 0 - offset&& ran){
            return true;
        }
        return false;
    }
}
