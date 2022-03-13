// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotor;

public class IntakeStow extends CommandBase {
  private IntakeMotor m_IntakeMotor;
  private double speed;
  /** Creates a new IntakeStow. */
  public IntakeStow(IntakeMotor m_IntakeMotor, double speed) {
    this.m_IntakeMotor = m_IntakeMotor;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_IntakeMotor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_IntakeMotor.setDeployMotorSpeed(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println();
    System.out.println("stowed");
    System.out.println();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_IntakeMotor.isFWDLIMIT() == 1;
}
}
