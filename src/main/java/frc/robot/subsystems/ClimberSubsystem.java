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
  private double speed;
  public ClimberSubsystem(int rightMotor, int leftMotor) {
    this.rightArm = new TalonFX(rightMotor);
    this.leftArm = new TalonFX(leftMotor);
  }

  public void setSpeed() {
    rightArm.set(ControlMode.PercentOutput, speed);
    leftArm.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}