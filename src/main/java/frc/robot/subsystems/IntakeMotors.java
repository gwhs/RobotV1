package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;


public class IntakeMotors {
    private TalonFX motor1;
    private TalonFX motor2;
    private double speed1;
    private double speed2;

    
    public IntakeMotors(int talon1, int talon2, double speed1, double speed2){
        this.motor1 = new TalonFX(talon1);
        this.motor2 = new TalonFX(talon2);
        this.speed1 = speed1;
        this.speed2 = speed2;
    }

    public void getPosition(){
        System.out.println("" + motor2.getSelectedSensorPosition() + motor1.getSelectedSensorPosition());
    }

    public void setSpeed(){
        motor1.set(ControlMode.PercentOutput, speed1);
        motor2.set(ControlMode.PercentOutput, speed2);
    }
}
