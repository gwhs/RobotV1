// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberCommand extends CommandBase {
  /** Creates a new ClimvberCommand. */
  private double targetPosition;
  private double position = 0;
  private ClimberSubsystem climberSubsystem;
  public ClimberCommand(ClimberSubsystem climberSubsystem, double target) {
    //this.speed = speed;
    this.climberSubsystem = climberSubsystem;
    this.targetPosition = target;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climberSubsystem.setZero();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("RightPosition", climberSubsystem.getRightArmPosition());
    SmartDashboard.putNumber("LeftPosition", climberSubsystem.getLeftArmPosition());
    climberSubsystem.setSpeed(.2);
    position = climberSubsystem.getRightArmPosition();
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climberSubsystem.setSpeed(0);
    System.out.println("done");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (position > targetPosition){
      climberSubsystem.setSpeed(0);
      climberSubsystem.setBrake();
      return true;
    }
    return false;
  }
}
