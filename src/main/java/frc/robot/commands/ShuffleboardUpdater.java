// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.CatapultCommands.CatapultCommand;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.subsystems.CatapultSubsystem;


public class ShuffleboardUpdater extends CommandBase {
   ShuffleboardTab ShTab;
   NetworkTableEntry output;
   CatapultSubsystem m_CatapultSubsystem;
   CatapultCommand m_CatapultCommand;
   double speed;
  /** Creates a new ShuffleboardUpdater. */
  public ShuffleboardUpdater() {
    ShTab = Shuffleboard.getTab("Percent Output ");
  
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    output = ShTab.add("Percent Output ", 1).getEntry();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_CatapultCommand = new CatapultCommand(m_CatapultSubsystem,output.getDouble(speed));
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
