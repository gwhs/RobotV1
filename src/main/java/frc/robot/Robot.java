// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.print.attribute.standard.Finishings;

import com.ctre.phoenix.CTREJNIWrapper;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ShuffleboardUpdater;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.ShuffleboardTest;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private BaseContainer m_BaseContainer;
  ShuffleboardUpdater m_ShuffleboardUpdater = new ShuffleboardUpdater();
  ShuffleboardTest tab = new ShuffleboardTest();
  
  

  public static final String CATAPULT = "Catapult"; 
  public static final String SWERVE = "Swerve";
  public static final String INTAKE = "Intake";
  public static final String CLIMBER = "Climber";
  public static final String INDICATORLIGHT = "Indicator Light";
  public static final String FINAL = "Final";

  //first is default
  public static final String[] ALL_CONTAINER = {
    CATAPULT, SWERVE, INTAKE, CLIMBER, FINAL
  }; 

  public static final String container = SWERVE;

  /*To set the robot container, use the dropdown menu in shuffleboard, under the smartdashboard tab*/

  private AddressableLEDBuffer m_ledBuffer;
  private AddressableLED m_led;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    // m_led = new AddressableLED(9);

    // // Reuse buffer
    // // Default to a length of 60, start empty output
    // // Length is expensive to set, so only set it once, then just update data
    // m_ledBuffer = new AddressableLEDBuffer(5);
    // m_led.setLength(m_ledBuffer.getLength());

    // // Set the data
    // m_led.setData(m_ledBuffer);
    // m_led.start();
    // for (var i = 0; i < m_ledBuffer.getLength(); i++) {
    //   // Sets the specified LED to the RGB values for red
    //   m_ledBuffer.setHSV(i, 0, 0, 100);
    // }
    // m_led.setData(m_ledBuffer);

    switch (container){
      case SWERVE:
        m_BaseContainer = new RobotContainer();
        break;
      case CATAPULT:
        m_BaseContainer = new CatapultContainer();
        break;
      case INTAKE:
        m_BaseContainer = new IntakeContainer();
        break;
      case CLIMBER:
        m_BaseContainer = new ClimberContainer();
        break;
      case INDICATORLIGHT:
        m_BaseContainer = new IndicaterLightContainer();
        break;
      case FINAL:
        //m_BaseContainer = new FinalContainer();
        break;
    } 
    m_autonomousCommand = m_BaseContainer.getAutonomousCommand();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    m_ShuffleboardUpdater = new ShuffleboardUpdater();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
  }}

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {

    
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
