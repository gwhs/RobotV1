// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ClimberContainer;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberLeftSubsystem;

public class LeftClimbCommand extends CommandBase {
  /** Creates a new ClimvberCommand. */
  private ClimberLeftSubsystem climberLeftSubsystem;
  private double targetPositionTicks;
  private double positionRight = 0;
  private boolean goingUp;


  public LeftClimbCommand(ClimberLeftSubsystem climberLeftSubsystem, double inches) {  //Multiply 568.8 by 
    this.climberLeftSubsystem = climberLeftSubsystem;
    this.targetPositionTicks = climberLeftSubsystem.inchesToTicks(inches);
    addRequirements(climberLeftSubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (climberLeftSubsystem.getLeftArmPosition() > targetPositionTicks){
      goingUp = false;
      climberLeftSubsystem.setSpeedLeft(-.2);
    } else {
      goingUp = true;
      climberLeftSubsystem.setSpeedLeft(.2);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("RightPosition", climberLeftSubsystem.getLeftArmPosition());
    System.out.println(climberLeftSubsystem.ticksToInches(climberLeftSubsystem.getLeftArmPosition()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climberLeftSubsystem.setSpeedLeft(0);
    System.out.println("done");
    System.out.println("Right position " + climberLeftSubsystem.getLeftArmPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (goingUp){ //if position is greater than where we want to go it stop climbing, and sets to brake mode.
      return climberLeftSubsystem.getLeftArmPosition() > targetPositionTicks || climberLeftSubsystem.getLeftArmPosition() > climberLeftSubsystem.inchesToTicks(29);
    } else {
      return climberLeftSubsystem.getLeftArmPosition() < targetPositionTicks || climberLeftSubsystem.getLeftArmPosition() < 5000;
    }
  }
}
