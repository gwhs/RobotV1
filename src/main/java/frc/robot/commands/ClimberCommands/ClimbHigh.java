// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.subsystems.ClimberSubsystem;

public class ClimbHigh extends SequentialCommandGroup {
  /** Creates a new ClimbHigh. */
  public ClimbHigh(ClimberSubsystem climberLeftSubsystem, ClimberSubsystem climberRightSubsystem, double upOne, double downOne) {
    addCommands(new ParallelClimber(climberLeftSubsystem, climberRightSubsystem, 23), new WaitCommand(3), new ParallelClimber(climberLeftSubsystem, climberRightSubsystem, 1.5));
  }}