// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ClimberContainer;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;

public class RightClimbCommand extends CommandBase {
  /** Creates a new ClimvberCommand. */
  private ClimberSubsystem leftClimber;
  private double targetPositionTicks;
  private double positionRight = 0;
  private boolean goingUp;


  public RightClimbCommand(ClimberSubsystem leftClimber, double inches) {  //Multiply 568.8 by 
    this.leftClimber = leftClimber;
    this.targetPositionTicks = leftClimber.inchesToTicks(inches);
    addRequirements(leftClimber);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (leftClimber.getPosition() > targetPositionTicks){
      goingUp = false;
      leftClimber.setSpeed(-.5);
    } else {
      goingUp = true;
      leftClimber.setSpeed(.5);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("RightPosition", leftClimber.getPosition());
    System.out.println(leftClimber.ticksToInches(leftClimber.getPosition()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    leftClimber.setSpeed(0);
    System.out.println("done");
    System.out.println("Right position " + leftClimber.getPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (goingUp){ //if position is greater than where we want to go it stop climbing, and sets to brake mode.
      return leftClimber.getPosition() > targetPositionTicks || leftClimber.getPosition() > leftClimber.inchesToTicks(29);
    } else {
      return leftClimber.getPosition() < targetPositionTicks || leftClimber.getPosition() < 5000;
    }
  }
}
