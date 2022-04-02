// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    // System.out.println("Stow running");
    m_IntakeMotor.setDeployMotorSpeed(speed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("Stow state: " + m_IntakeMotor.isFWDLIMIT());
    SmartDashboard.putNumber("Ticks", m_IntakeMotor.isOtherData());
    SmartDashboard.putNumber("Deploy Motor State FWD", m_IntakeMotor.isFWDLIMIT());
    SmartDashboard.putNumber("Deploy Motor State REV ", m_IntakeMotor.isREVLIMIT());
    SmartDashboard.putNumber("Deploy Motor Stator Current", m_IntakeMotor.getStatorCurrent());
    SmartDashboard.putNumber("Deploy Motor Supply", m_IntakeMotor.getSupplyCurrent());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_IntakeMotor.stop();
    // System.out.println("end intake stow");
    // System.out.println("End Stow State: " + m_IntakeMotor.isFWDLIMIT());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // System.out.println("isFinishe Stow State: " + m_IntakeMotor.isFWDLIMIT());
    return m_IntakeMotor.isFWDLIMIT() == 1;
}
}
