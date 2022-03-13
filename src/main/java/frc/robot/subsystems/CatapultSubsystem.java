// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CatapultSubsystem extends SubsystemBase {
  private TalonFX motor;
  private double initialOffset;
  private static final int SHOOT_LIMIT = 5100;
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
    motor.setNeutralMode(NeutralMode.Brake);
  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler 

    }

  public void setPercent(double speed){
    motor.set(ControlMode.PercentOutput, speed);
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

  public void increaseCatapultByOne(double speed){
    motor.set(ControlMode.PercentOutput, speed);
  }

  public void setSelectedSensorPosition() {
    motor.setSelectedSensorPosition(0.0);
  }

  public double getPosition(){
    return motor.getSelectedSensorPosition();
  }

  public void setBrake(){
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
  }
