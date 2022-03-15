// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CatapultSubsystem;

public class ChangeCatapultPower extends CommandBase {
  private double power = 0;
  private CatapultSubsystem m_CatapultSubsystemRight;
  private CatapultSubsystem m_CatapultSubsystemLeft;
  /** Creates a new IncreaseCatapultPower. */
  public ChangeCatapultPower(CatapultSubsystem m_CatapultSubsystemRight, CatapultSubsystem m_CatapultSubsystemLeft, double power) {
    this.m_CatapultSubsystemRight = m_CatapultSubsystemRight;
    this.m_CatapultSubsystemLeft = m_CatapultSubsystemLeft;
    this.power = power;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_CatapultSubsystemRight, m_CatapultSubsystemLeft);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_CatapultSubsystemRight.changePower(power);
    m_CatapultSubsystemLeft.changePower(power);
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
