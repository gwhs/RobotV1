// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

//
//
// REVIEW: idk, kinda useless to me
//
//
package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
/**
 * This is a demo program showing the use of the RobotDrive class, specifically it contains the code
 * necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private XboxController xbox;
  private CaitpultContainer m_Container;


  @Override
  public void robotInit() {
    xbox = new XboxController(0);

    m_Container = new CaitpultContainer();
  //  m_myRobot = new DifferentialDrive(m_blue, m_white);

  SmartDashboard.putData(CommandScheduler.getInstance());
    
  }

  @Override
  public void robotPeriodic(){
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {

  }
  @Override
  public void disabledPeriodic(){

  }
@Override
public void autonomousInit(){
}
  @Override
  public void autonomousPeriodic() {
  
  }
@Override
public void teleopInit(){
  }

  @Override
  public void teleopPeriodic() {
  
  }
}
