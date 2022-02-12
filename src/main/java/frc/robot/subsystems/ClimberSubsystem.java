// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {
  /** Creates a new ClimberSubsystem. */
  private TalonFX rightArm;
  private TalonFX leftArm;
  public ClimberSubsystem(int rightMotorId, int leftMotorId){//, int leftMotorId) {
    this.rightArm = new TalonFX(rightMotorId);
    this.leftArm = new TalonFX(leftMotorId);
    leftArm.setInverted(InvertType.InvertMotorOutput);
    leftArm.set(ControlMode.Follower, rightMotorId);

  }
  //need to find ticks
  public void climbLower(){
    double position = getRightArm();
    if (position < 15000){
      rightArm.set(ControlMode.PercentOutput, -.5);
    } else{
      this.pullUp();
    }
  }

  public void climbUpper(){
    double position = getRightArm();
    if (position < 15000){
      rightArm.set(ControlMode.PercentOutput, -.5);
    } else{
      this.pullUp();
    }
  }
  
  public void pullUp(){
    this.setSpeed(-.5);
    long start = System.currentTimeMillis();
    if (System.currentTimeMillis() - start > 3000){
      this.setSpeed(0);
    }
  }

  public void setSpeed(double speed){
    rightArm.set(ControlMode.PercentOutput, speed);
  }

  public TalonFX rightArm(){
    return rightArm;
  }

  public TalonFX leftArm(){
    return leftArm;
  }
  // public double getLeftArm(){
  //   leftArm.getSelectedSensorPosition();
  //   return leftArm.getSelectedSensorPosition();
  // }

  public void setPosition(){
    rightArm.setSelectedSensorPosition(0);
  }

  public double getRightArm(){
    rightArm.getSelectedSensorPosition();
    return rightArm.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}