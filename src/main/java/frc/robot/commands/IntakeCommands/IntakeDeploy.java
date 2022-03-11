// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;

public class IntakeDeploy extends CommandBase {
  /** Creates a new IntakeDeploy. */
  private IntakeMotors m_IntakeMotors;
  private double deploySpeed;
  public IntakeDeploy(IntakeMotors m_IntakeMotors, double deploySpeed) {
    this.m_IntakeMotors = m_IntakeMotors;
    this.deploySpeed = deploySpeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_IntakeMotors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   m_IntakeMotors.setDeployMotorSpeed(-deploySpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Forward: " + m_IntakeMotors.isFWDLIMIT());
    System.out.println("Reverse: " + m_IntakeMotors.isREVLIMIT());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
  return m_IntakeMotors.isREVLIMIT() == 1;
  }
}
