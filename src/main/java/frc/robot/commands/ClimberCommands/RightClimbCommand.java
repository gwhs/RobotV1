// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberRightSubsystem;

public class RightClimbCommand extends CommandBase {
  /** Creates a new ClimvberCommand. */
  private ClimberRightSubsystem climberRightSubsystem;
  private double targetPositionTicks;
  private double positionRight = 0;
  private ClimberRightSubsystem climberSubsystem;


  public RightClimbCommand(ClimberRightSubsystem climberRightSubsystem, double inches) {  //Multiply 568.8 by 
    this.climberRightSubsystem = climberRightSubsystem;
    addRequirements(climberSubsystem);
    climberRightSubsystem.setZero();
    
  }

  public double calculateTicks(double Inches){
    return Inches * Constants.CLIMBER_RATIO * Constants.TICKS_PER_REVOLUTION / (Constants.INCHES_PER_REVOLUTION * Math.PI);
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
    climberSubsystem.setSpeedRight(.5);
    positionRight = climberRightSubsystem.getRightArmPosition();
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("done");
    System.out.println("Right position " + climberSubsystem.getRightArmPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (positionRight > targetPositionTicks){ //if position is greater than where we want to go it stop climbing, and sets to brake mode.
      climberSubsystem.setSpeedRight(0);
      climberSubsystem.setBrake();
      return true;
    }
    return false;
  }
}
