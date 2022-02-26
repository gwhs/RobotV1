// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.CatapultCommands.CatapultCommand;
import frc.robot.commands.CatapultCommands.CatapultDouble;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.ShuffleboardTest;


public class ShuffleboardUpdater extends CommandBase {
  private ShuffleboardTab ShTab;
  private NetworkTableEntry output;
  private NetworkTableEntry containerSwap;
  private NetworkTableEntry delayInput;
  public static String containerMode;
  public static double delayCount;

  /** Creates a new ShuffleboardUpdater. */
  public ShuffleboardUpdater() {
    ShTab = Shuffleboard.getTab("Percent Output");
  
    // Use addRequirements() here to declare subsystem dependencies.
    
    //dropdown menu selections:

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    output = ShTab.add("Percent Output", 1).getEntry();
    containerSwap = ShTab.add("Container: ", "CATAPULT").getEntry();
    delayInput = ShTab.add("Delay set:", 3.0).getEntry();
    SmartDashboard.putData(ShuffleboardTest.m_chooser);
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
