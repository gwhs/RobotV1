// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CatapultSubsystem extends SubsystemBase {
  private TalonFX motor;
  private double initialOffset;
  private static final int SHOOT_LIMIT = 5100;
  private double power = 0.0;//will be changed
  /** Creates a new motor. */
  public CatapultSubsystem(int id, boolean inverted) {
    motor = new TalonFX(id);
    //consistant amt of power every time
    motor.configVoltageCompSaturation(11);
    motor.enableVoltageCompensation(true);
    initialOffset = motor.getSelectedSensorPosition();
    

    //one will go forward, the other will go inverted
    if(inverted){
      motor.setInverted(InvertType.InvertMotorOutput);
    }
    else{
      motor.setInverted(InvertType.None);
    }
    motor.setNeutralMode(NeutralMode.Coast);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler 

    }

  public void setPercent(double speed){
    motor.set(ControlMode.PercentOutput, speed);
  }

  //power is changed using ChangePower command
  public void setChangedSpeed(double speed){
    motor.set(ControlMode.PercentOutput, speed + power);
  }
  
  public boolean isFinishedShooting(){
    double shotposition = motor.getSelectedSensorPosition();
    if(Math.abs(shotposition) >= SHOOT_LIMIT - initialOffset){
      return true;
    }
    return false;
  }

  public boolean isFinishedReturning(){
    if (motor.getSelectedSensorPosition() < 0 - initialOffset){
      return true;
    }
    return false;
  }

  public void setSelectedSensorPosition() {
    motor.setSelectedSensorPosition(0.0);
  }

  public double getPosition(){
    return motor.getSelectedSensorPosition();
  }

  public void setCoast(){
    motor.setNeutralMode(NeutralMode.Coast);
  }

  public TalonFX getMotor(){
    return motor;
  }

  public double getStatorCurrent(){
    return motor.getStatorCurrent();
  }

  public double getSupplyCurrent(){
    return motor.getSupplyCurrent();
  }

  public void setCurrentLimit(){
    motor.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, 0, 45, 1));
    // motor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, 0, 100, 1));
  }

  public void changePower(double change){
    power += change;
    power = Math.min(0, power);
    power = Math.max(-.99, power);
    // if (power < .2 && power > -.2){
    //   power += change;
    // }

    round(power, 2);
  }

  public double getPower(){
    return power;
  }

  public void round(double num, int decimals){
    num = num * Math.pow(10, decimals);
    num = Math.floor(num);
    num = num / Math.pow(10,decimals);
    power = num;
  }
}

