// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class UpperLowerIntake extends SubsystemBase {
  private TalonFX upperMotor;
  private TalonFX lowerMotor;

  /** Creates a new UpperLowerIntake. */
    
  public UpperLowerIntake(int upperMotorID, int lowerMotorID){
    this.upperMotor = new TalonFX(upperMotorID);
    this.lowerMotor = new TalonFX(lowerMotorID);
  }


  public void setIntakeMotorSpeeds(double upperSpeed, double lowerSpeed){
    upperMotor.set(ControlMode.PercentOutput, upperSpeed);
    lowerMotor.set(ControlMode.PercentOutput, lowerSpeed);
}

public void choke(){
  upperMotor.set(ControlMode.PercentOutput, 0);
  lowerMotor.set(ControlMode.PercentOutput, 0);
}

  }
