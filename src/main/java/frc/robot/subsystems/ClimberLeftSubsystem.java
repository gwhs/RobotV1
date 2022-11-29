
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// Justin was here! 

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberLeftSubsystem extends SubsystemBase {
  /** Creates a new ClimberSubsystem. */
  private TalonFX leftArm;
  public ClimberLeftSubsystem(int leftMotorId){//, int leftMotorId) {
    this.leftArm = new TalonFX(leftMotorId);
    leftArm.setInverted(false);
    leftArm.setNeutralMode(NeutralMode.Brake);
    this.setZero();
  }

  public double inchesToTicks(double inches){
    return inches * Constants.CLIMBER_RATIO * Constants.TICKS_PER_REVOLUTION / (Constants.PITCH_DIAMETER_30 * Math.PI);
  }

  public double ticksToInches(double d){
    return d / Constants.CLIMBER_RATIO / Constants.TICKS_PER_REVOLUTION * (Constants.PITCH_DIAMETER_30* Math.PI); 
  }

  public void setSpeedLeft(double speed){
    leftArm.set(ControlMode.PercentOutput, speed);
  }

  public void setBrake(){
    leftArm.setNeutralMode(NeutralMode.Brake);
  }

  public void setZero(){
    leftArm.setSelectedSensorPosition(0);
  }

  public double getLeftArmPosition(){
    return leftArm.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}