package frc.robot.subsystems;

import java.util.ResourceBundle.Control;

import javax.swing.RowFilter.ComparisonType;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ExternalFollower;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeMotors extends SubsystemBase{
    private TalonFX upperMotor;
    private TalonFX lowerMotor;
    private TalonFX deployMotr;
    private double upperSpeed;
    private double lowerSpeed;
    private double deploySpeed;

    private TalonFX deployMotor;

    
    public IntakeMotors(int deployMotorID, int upperMotorID, int lowerMotorID, double deploySpeed, double upperSpeed, double lowerSpeed){
        this.upperMotor = new TalonFX(upperMotorID);
        this.lowerMotor = new TalonFX(lowerMotorID);
        this.deployMotor = new TalonFX(deployMotorID);
        this.upperSpeed = upperSpeed;
        this.lowerSpeed = lowerSpeed;
        this.deploySpeed = deploySpeed;
        this.setSoftLimits();
        this.setZero();
    }

    public void setZero(){
        deployMotor.setSelectedSensorPosition(0);
    }

    public double getPosition(){
        return deployMotor.getSelectedSensorPosition();
    }

    public void suckBalls(){
        upperMotor.set(ControlMode.PercentOutput, upperSpeed);
        lowerMotor.set(ControlMode.PercentOutput, lowerSpeed);
    }

    public void spit(){
        upperMotor.set(ControlMode.PercentOutput, -upperSpeed);
        lowerMotor.set(ControlMode.PercentOutput, lowerSpeed);
    }

    public void choke(){
        upperMotor.set(ControlMode.PercentOutput, 0);
        lowerMotor.set(ControlMode.PercentOutput, 0);
    }

    //setAlphaPosition() sets perceived position to specified value "pos"
    //returns previous position as a double

    public void setCoastMode()
    {
        deployMotor.setNeutralMode(NeutralMode.Coast);
    }

    public void setBrakeMode()
    {
        deployMotor.setNeutralMode(NeutralMode.Brake);
    }



    //deploy intake
    public void deploy(double speed){
        deployMotor.setSelectedSensorPosition(0);
        //alphaMotor.setSoftLimit(SoftLimitDirection.kForward, 4000);
        deployMotor.set(ControlMode.PercentOutput, speed);
    }

    public void stopDeploy(){
        deployMotor.set(ControlMode.PercentOutput, 0);
    }

    //undeploy intake
    public void undeploy(double speed){
        //alphaMotor.setSoftLimit(SoftLimitDirection.kReverse, 4000);
        deployMotor.set(ControlMode.PercentOutput, -speed);
    }

    public void setSoftLimits(){
        
    }
}
