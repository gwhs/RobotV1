// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ClimberContainer;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;

public class OneInchClimber extends CommandBase {
  /** Creates a new ClimvberCommand. */
  private ClimberSubsystem climberSubsystem;
  private double targetPositionTicks;
  private boolean goingUp;
  private double inchesInTicks;


  public OneInchClimber(ClimberSubsystem climberSubsystem, double inches) {  
    this.climberSubsystem = climberSubsystem;
    this.inchesInTicks = climberSubsystem.inchesToTicks(inches);
    
    addRequirements(climberSubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    targetPositionTicks = climberSubsystem.getPosition() + inchesInTicks;
    if (climberSubsystem.getPosition() > targetPositionTicks){
      goingUp = false;
      climberSubsystem.setSpeed(-.5);
    } else {
      goingUp = true;
      climberSubsystem.setSpeed(.5);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climberSubsystem.setSpeed(0);
    System.out.println("Right position " + climberSubsystem.getPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (goingUp){ //if position is greater than where we want to go it stop climbing, and sets to brake mode.
      return climberSubsystem.getPosition() > targetPositionTicks;
    } else {
      return climberSubsystem.getPosition() < targetPositionTicks;
    }
  }
}
