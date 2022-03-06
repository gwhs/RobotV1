// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class RetractArm extends CommandBase {
  /** Creates a new ClimvberCommand. */
  private double beginningPosition;
  private ClimberSubsystem climberSubsystem;
  public RetractArm(ClimberSubsystem climberSubsystem) {
    //this.speed = speed;
    this.climberSubsystem = climberSubsystem;


    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    beginningPosition = climberSubsystem.getRightArmPosition();
    climberSubsystem.setSpeed(-0.2);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // climberSubsystem.setSpeed(-0.2);
    // position = climberSubsystem.getRightArmPosition();
    
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
    if(climberSubsystem.getRightArmPosition() < beginningPosition - 108000){
      return true;
    }
    return false;
  }
}
