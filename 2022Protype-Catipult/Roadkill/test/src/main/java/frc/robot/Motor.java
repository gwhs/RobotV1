// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//
//
//REVIEW: This creates motors, gives them speeds, and gets the position
// to create motors, pass in id to Motor, to give the speed, pass % speed as a decimal to  setMotorPercent, get position by calling getPosition
//
//
package frc.robot;
import java.sql.Time;
import frc.robot.SpinMotor;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
  private TalonFX motor;
  /** Creates a new motor. */
  public Motor(int id) {
    motor = new TalonFX(id);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler 

    }

  // set the speed, speed is from -1 to 1 as decimal
  public void setMotorPercent(double speed){
      motor.set(ControlMode.PercentOutput, speed);
  }
  
  // sets motor position to 0 wherever it is
  public void setSelectedSensorPosition() {
    motor.setSelectedSensorPosition(0.0);
  }

  // gets the current position of the motor
  public double getPosition(){
    return motor.getSelectedSensorPosition();
  }

  public void brakeMode(){
    motor.setNeutralMode(NeutralMode.Brake);
    System.out.println("Changed to break mode.");
  }

  public void coastMode(){
    motor.setNeutralMode(NeutralMode.Coast);
  }

  public TalonFX getMotor(){
    return motor;
  }


  }
