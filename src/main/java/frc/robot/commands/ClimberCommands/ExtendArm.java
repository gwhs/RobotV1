// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ExtendArm extends CommandBase {
  /** Creates a new ClimvberCommand. */
  

  private ClimberSubsystem climberSubsystem;
  public ExtendArm(ClimberSubsystem climberSubsystem, double target) {
    //this.speed = speed;
    this.climberSubsystem = climberSubsystem;
    

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climberSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climberSubsystem.setSpeed(.2);
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
    if (climberSubsystem.getRightArmPosition() > 200000){
      return true;
    }
    return false;
  }
}
