// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberRightSubsystem extends SubsystemBase {
  /** Creates a new ClimberSubsystem. */
  private TalonFX rightArm;
  public ClimberRightSubsystem(int rightMotorId){//, int leftMotorId) {
    this.rightArm = new TalonFX(rightMotorId);
    rightArm.setInverted(true);
    rightArm.setNeutralMode(NeutralMode.Brake);
    this.setZero();
  }

  public double inchesToTicks(double inches){
    return inches * Constants.CLIMBER_RATIO * Constants.TICKS_PER_REVOLUTION / (Constants.PITCH_DIAMETER_30 * Math.PI);
  }

  public double ticksToInches(double d){
    return d / Constants.CLIMBER_RATIO / Constants.TICKS_PER_REVOLUTION * (Constants.PITCH_DIAMETER_30* Math.PI); 
  }

  public void setSpeedRight(double speed){
    rightArm.set(ControlMode.PercentOutput, speed);
  }

  public void setBrake(){
    rightArm.setNeutralMode(NeutralMode.Brake);
  }

  public void setZero(){
    rightArm.setSelectedSensorPosition(0);

  }

  public double getRightArmPosition(){
    return rightArm.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}