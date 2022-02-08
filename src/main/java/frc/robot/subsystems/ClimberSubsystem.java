// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {
  /** Creates a new ClimberSubsystem. */
  private TalonFX rightArm;
  private TalonFX leftArm;
  public ClimberSubsystem(int rightMotorId, int leftMotorId) {
    this.rightArm = new TalonFX(rightMotorId);
    this.leftArm = new TalonFX(leftMotorId);
  }

  public void moveRightArm(double speed) {
    rightArm.set(ControlMode.PercentOutput, speed);
  }

  public void moveLeftArm(double speed) {
    leftArm.set(ControlMode.PercentOutput, speed);
  }

  public void moveBothArms(double speed){
    moveRightArm(speed);
    moveLeftArm(speed);
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

  // public double getghtArm(){
  //   rightArm.getSelectedSensorPosition();
  //   return rightArm.getSelectedSensorPosition();
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}