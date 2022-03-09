// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberLeftSubsystem;

public class LeftClimbCommand extends CommandBase {
  /** Creates a new ClimvberCommand. */
  private ClimberLeftSubsystem climberLeftSubsystem;
  private double targetPositionTicks;
  private double positionLeft = 0;


  public LeftClimbCommand(ClimberLeftSubsystem climberLeftSubsystem) {  //Multiply 568.8 by 
    this.climberLeftSubsystem = climberLeftSubsystem;
    addRequirements(climberLeftSubsystem);
    climberLeftSubsystem.setZero();
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    climberLeftSubsystem.setZero();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("RightPosition", climberLeftSubsystem.getLeftArmPosition());
    climberLeftSubsystem.setSpeedLeft(.5);
    positionLeft = climberLeftSubsystem.getLeftArmPosition();
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("done");
    System.out.println("Right position " + climberLeftSubsystem.getLeftArmPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (positionLeft > targetPositionTicks){ //if position is greater than where we want to go it stop climbing, and sets to brake mode.
      climberLeftSubsystem.setSpeedLeft(0);
      climberLeftSubsystem.setBrake();
      return true;
    }
    return false;
  }
}
