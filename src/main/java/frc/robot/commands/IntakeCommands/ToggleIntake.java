// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.IntakeMotor;

public class ToggleIntake extends CommandBase {
  private IntakeMotor m_IntakeMotor;
  private double speed;
  public boolean deployed = false;
  /** Creates a new ToggleIntake. */
  public ToggleIntake(IntakeMotor m_IntakeMotor, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_IntakeMotor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_IntakeMotor.isFWDLIMIT() == 1){
      deployed = false;
      m_IntakeMotor.setDeployMotorSpeed(-speed);
    }
    else if (m_IntakeMotor.isREVLIMIT() == 1){
      deployed = true;
      m_IntakeMotor.setDeployMotorSpeed(speed);
    }
    else{
      deployed = false;
      m_IntakeMotor.setDeployMotorSpeed(-speed);
      System.out.println("INTAKE WAS IN MIDDLE POS");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_IntakeMotor.setDeployMotorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("" + m_IntakeMotor.isFWDLIMIT() + " " + m_IntakeMotor.isREVLIMIT() + " "+ deployed);
    if(deployed = false && m_IntakeMotor.isFWDLIMIT() == 1 && m_IntakeMotor.isREVLIMIT() == 0){
      return true;
    }
    else if(deployed = true && m_IntakeMotor.isFWDLIMIT() == 0 && m_IntakeMotor.isREVLIMIT() == 1){
      return true;
    }
    return false;
  }
}

