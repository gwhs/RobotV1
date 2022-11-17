// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.lang.annotation.Target;
import java.util.Currency;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TurnDriveCommand extends CommandBase {
  /** Creates a new TurnDriveCommand. */
  DrivetrainSubsystem m_DrivetrainSubsystem;
  
  double targetTheta;
  public TurnDriveCommand(DrivetrainSubsystem m_DrivetrainSubsystem, double targetTheta) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_DrivetrainSubsystem = m_DrivetrainSubsystem;
    this.targetTheta = targetTheta;
    }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //Get Current Position
    Rotation2d CurrentPos = m_DrivetrainSubsystem.getGyroscopeRotation();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Rotate Robot left or right
    m_DrivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 5*Math.PI/180)); 
    //print angle

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //set motor speed 0
    m_DrivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0)); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    Rotation2d CurrentPos = m_DrivetrainSubsystem.getGyroscopeRotation();
    if(CurrentPos.getDegrees() - targetTheta >= -0.5 && CurrentPos.getDegrees() - targetTheta <= 0.5)
    {
      return true;
    }
    //If angle is close to targetTheta, is finished
    //If angle is not close to targetTheta, not finished

    return false;
  }
}
