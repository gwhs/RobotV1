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
    leftArm.setInverted(InvertType.OpposeMaster);
    leftArm.set(ControlMode.Follower, rightMotorId);
  }

  public void setSpeed(double speed){
    rightArm.set(ControlMode.PercentOutput, speed);
  }

  public void setZero(){
    rightArm.setSelectedSensorPosition(0);
  }

  public double getRightArmPosition(){
    return rightArm.getSelectedSensorPosition();
  }

  public double getLeftArmPosition(){
    return leftArm.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}