// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ClimberContainer;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberRightSubsystem;

public class RightClimbCommand extends CommandBase {
  /** Creates a new ClimvberCommand. */
  private ClimberRightSubsystem climberRightSubsystem;
  private double targetPositionTicks;
  private double positionRight = 0;
  private boolean goingUp;


  public RightClimbCommand(ClimberRightSubsystem climberRightSubsystem, double inches) {  //Multiply 568.8 by 
    this.climberRightSubsystem = climberRightSubsystem;
    this.targetPositionTicks = climberRightSubsystem.inchesToTicks(inches);
    addRequirements(climberRightSubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (climberRightSubsystem.getRightArmPosition() > targetPositionTicks){
      goingUp = false;
      climberRightSubsystem.setSpeedRight(-.5);
    } else {
      goingUp = true;
      climberRightSubsystem.setSpeedRight(.5);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("RightPosition", climberRightSubsystem.getRightArmPosition());
    System.out.println(climberRightSubsystem.ticksToInches(climberRightSubsystem.getRightArmPosition()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climberRightSubsystem.setSpeedRight(0);
    System.out.println("done");
    System.out.println("Right position " + climberRightSubsystem.getRightArmPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (goingUp){ //if position is greater than where we want to go it stop climbing, and sets to brake mode.
      return climberRightSubsystem.getRightArmPosition() > targetPositionTicks || climberRightSubsystem.getRightArmPosition() > climberRightSubsystem.inchesToTicks(29);
    } else {
      return climberRightSubsystem.getRightArmPosition() < targetPositionTicks || climberRightSubsystem.getRightArmPosition() < 5000;
    }
  }
}
