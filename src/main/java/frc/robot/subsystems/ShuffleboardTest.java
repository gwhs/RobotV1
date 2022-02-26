// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.CatapultCommands.CatapultDouble;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShuffleboardTest extends SubsystemBase {
  private ShuffleboardTab shTab;
  private NetworkTableEntry output;
  // private NetworkTableEntry containerSwap;
  private NetworkTableEntry delayInput;
  String containerMode;
  double delayCount;

  private SendableChooser<String> m_containerChooser = new SendableChooser<>();
  private SendableChooser<CatapultDouble> m_chooser = new SendableChooser<>();
  private final CatapultSubsystem m_CatapultSubsystemLeft = new CatapultSubsystem(14);
  private final CatapultSubsystem m_CatapultSubsystemRight = new CatapultSubsystem(21);


  public ShuffleboardTest() {
    shTab = Shuffleboard.getTab("Controller");
    output = shTab.add("Percent Output", 1).getEntry();
    // containerSwap = shTab.add("Container: ", Robot.CATAPULT).getEntry();
    delayInput = shTab.add("Delay set:", 3.0).getEntry();
    SmartDashboard.putData(m_chooser);
    SmartDashboard.putData(m_containerChooser);
    
    m_chooser.setDefaultOption("Double Shoot", new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_DOUBLE));
    m_chooser.addOption("Delay", new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_DELAY));
    m_chooser.addOption("Low + High", new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_LOW_HIGH));
    m_chooser.addOption("Dump", new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_DUMP));
    m_chooser.addOption("Left Only", new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_LEFT));
    m_chooser.addOption("Right Only", new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_RIGHT));
    m_chooser.addOption("Left Low Only", new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_LEFT_LOW));
    m_chooser.addOption("Right Low Only", new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_RIGHT_LOW));
    m_chooser.addOption("Left Dump Only", new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_LEFT_DUMP));
    m_chooser.addOption("Right Dump Only", new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_RIGHT_DUMP)); 
    
    m_containerChooser.setDefaultOption(Robot.CATAPULT , Robot.CATAPULT);
    m_containerChooser.addOption(Robot.SWERVE, Robot.SWERVE);
    m_containerChooser.addOption(Robot.INTAKE, Robot.INTAKE);
    m_containerChooser.addOption(Robot.CLIMBER, Robot.CLIMBER);
  }

  public String getContainerMode()
  {
    return m_containerChooser.getSelected();
    // return containerSwap.getString(Robot.CATAPULT);
  }


  /** Creates a new Shuffleboard. */
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}