// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.IntakeMotors;

public class ToggleIntake extends CommandBase {
  private IntakeMotors m_IntakeMotors;
  private double deploySpeed;
  public boolean deployed = false;
  /** Creates a new ToggleIntake. */
  public ToggleIntake(IntakeMotors m_IntakeMotors, double deploySpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_IntakeMotors = m_IntakeMotors;
    this.deploySpeed = deploySpeed;
    addRequirements(m_IntakeMotors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_IntakeMotors.isFWDLIMIT() == 1){
      deployed = false;
      System.out.println("ISFWDLIMIT IS RUNNING");
      m_IntakeMotors.setDeployMotorSpeed(-deploySpeed);
    }
    else if (m_IntakeMotors.isREVLIMIT() == 1){
      deployed = true;
      System.out.println("ISREVLIMIT IS RUNNING");
      m_IntakeMotors.setDeployMotorSpeed(deploySpeed);
    }
    else{
      deployed = false;
      m_IntakeMotors.setDeployMotorSpeed(-deploySpeed);
      System.out.println("INTAKE WAS IN MIDDLE POS");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_IntakeMotors.setDeployMotorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("" + m_IntakeMotors.isFWDLIMIT() + " " + m_IntakeMotors.isREVLIMIT() + " "+ deployed);
    if(deployed = false && m_IntakeMotors.isFWDLIMIT() == 1 && m_IntakeMotors.isREVLIMIT() == 0){
      return true;
    }
    else if(deployed = true && m_IntakeMotors.isFWDLIMIT() == 0 && m_IntakeMotors.isREVLIMIT() == 1){
      return true;
    }
    return false;
  }
}

