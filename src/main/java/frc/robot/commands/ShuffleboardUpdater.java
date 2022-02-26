// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.CatapultCommands.CatapultCommand;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.CatapultSubsystem;


public class ShuffleboardUpdater extends CommandBase {
  private ShuffleboardTab ShTab;
  private NetworkTableEntry output;
  private NetworkTableEntry containerSwap;
  private NetworkTableEntry delayInput;
  public static String containerMode;
  public static double delayCount;
  SendableChooser<Integer> m_chooser = new SendableChooser<>();

  /** Creates a new ShuffleboardUpdater. */
  public ShuffleboardUpdater() {
    ShTab = Shuffleboard.getTab("Percent Output");
  
    // Use addRequirements() here to declare subsystem dependencies.
    
    //dropdown menu selections:
    m_chooser.setDefaultOption("Double Shoot", Constants.SHOOTER_MODE_DOUBLE);
    m_chooser.addOption("Delay", Constants.SHOOTER_MODE_DELAY);
    m_chooser.addOption("Low + High", Constants.SHOOTER_MODE_LOW_HIGH);
    m_chooser.addOption("Dump", Constants.SHOOTER_MODE_DUMP);
    m_chooser.addOption("Left Only", Constants.SHOOTER_MODE_LEFT);
    m_chooser.addOption("Right Only", Constants.SHOOTER_MODE_RIGHT);
    m_chooser.addOption("Left Low Only", Constants.SHOOTER_MODE_LEFT_LOW);
    m_chooser.addOption("Right Low Only", Constants.SHOOTER_MODE_RIGHT_LOW);
    m_chooser.addOption("Left Dump Only", Constants.SHOOTER_MODE_LEFT_DUMP);
    m_chooser.addOption("Right Dump Only", Constants.SHOOTER_MODE_RIGHT_DUMP);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    output = ShTab.add("Percent Output", 1).getEntry();
    containerSwap = ShTab.add("Container: ", "CATAPULT").getEntry();
    delayInput = ShTab.add("Delay set:", 3.0).getEntry();
    SmartDashboard.putData(m_chooser);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    containerMode = containerSwap.getString("CATAPULT");

    delayCount = delayInput.getDouble(3.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
