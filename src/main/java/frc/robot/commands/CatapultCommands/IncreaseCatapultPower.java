// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CatapultSubsystem;

public class IncreaseCatapultPower extends CommandBase {
  private CatapultSubsystem m_CatapultSubsystem;
  private double power;
  /** Creates a new IncreaseCatapultPower. */
  public IncreaseCatapultPower(CatapultSubsystem m_CatapultSubsystem, double power) {
    this.m_CatapultSubsystem = m_CatapultSubsystem;
    this.power = power;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_CatapultSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_CatapultSubsystem.changePower(power);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
