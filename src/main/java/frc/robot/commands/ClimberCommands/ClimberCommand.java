// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ClimberContainer;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberCommand extends CommandBase {
  /** Creates a new ClimvberCommand. */
  private ClimberSubsystem ClimberSubsystem;
  private double targetPositionTicks;
  private boolean goingUp;


  public ClimberCommand(ClimberSubsystem ClimberSubsystem, double inches) {  
    this.ClimberSubsystem = ClimberSubsystem;
    this.targetPositionTicks = ClimberSubsystem.inchesToTicks(inches);
    addRequirements(ClimberSubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (ClimberSubsystem.getPosition() > targetPositionTicks){
      goingUp = false;
      ClimberSubsystem.setSpeed(-1);
    } else {
      goingUp = true;
      ClimberSubsystem.setSpeed(1);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("RightPosition", ClimberSubsystem.getPosition());
    System.out.println(ClimberSubsystem.ticksToInches(ClimberSubsystem.getPosition()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    ClimberSubsystem.setSpeed(0);
    System.out.println("done");
    System.out.println("Right position " + ClimberSubsystem.getPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (goingUp){ //if position is greater than where we want to go it stop climbing, and sets to brake mode.
      return ClimberSubsystem.getPosition() > targetPositionTicks || ClimberSubsystem.getPosition() > ClimberSubsystem.inchesToTicks(29);
    } else {
      return ClimberSubsystem.getPosition() < targetPositionTicks || ClimberSubsystem.getPosition() < -20000;
    }
  }
}
