// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;



import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotor;

public class IntakeDeploy extends CommandBase {
  /** Creates a new IntakeDeploy. */
  private IntakeMotor m_IntakeMotor;
  private double deploySpeed;
  public IntakeDeploy(IntakeMotor m_IntakeMotor, double deploySpeed) {
    this.m_IntakeMotor = m_IntakeMotor;
    this.deploySpeed = deploySpeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_IntakeMotor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //System.out.println("Deploying");
   m_IntakeMotor.setDeployMotorSpeed(-deploySpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //System.out.println("Deploy state: " + m_IntakeMotor.isREVLIMITbool());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_IntakeMotor.stop();
    //System.out.println("end deploy");
    //System.out.println("End State: " + m_IntakeMotor.isREVLIMITbool());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
  return m_IntakeMotor.isREVLIMIT() == 1;
  }
}
