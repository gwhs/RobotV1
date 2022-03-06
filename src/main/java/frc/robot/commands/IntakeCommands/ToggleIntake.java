// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeMotors;

public class ToggleIntake extends CommandBase {
  private IntakeMotors motors;
  private double deploySpeed;
  private double upperSpeed;
  private double lowerSpeed;
  private boolean deployed;
  private int offset;
  private double currentPos;

  /** Creates a new DeployCommand. */
  public ToggleIntake(IntakeMotors motors, double deploySpeed, double upperSpeed, double lowerSpeed) {
    this.motors = motors;

    this.deploySpeed = deploySpeed;
    this.upperSpeed = upperSpeed;
    this.lowerSpeed = lowerSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(motors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    double offset = motors.getDeployPosition();
    motors.setZero();
    System.out.print(currentPos);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentPos = motors.getDeployPosition();
    if (currentPos <= 18399 - offset){ // About 32 degrees
      deployed = true;
      motors.setDeployMotorSpeed(deploySpeed);
    }
    else {
      deployed = false;
      motors.setDeployMotorSpeed(-deploySpeed);
    }
      // if(deployed) {
      //   motors.setDeployMotorSpeed(deploySpeed); //undeploys
      // }
      // else {
      //   motors.setDeployMotorSpeed(-deploySpeed); //deploys
      // }
          
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(!deployed) {
      motors.setDeployMotorSpeed(0);
      motors.setIntakeMotorSpeeds(0, 0);
    } 
    else {
      motors.setDeployMotorSpeed(0);
      motors.setIntakeMotorSpeeds(-upperSpeed, lowerSpeed);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      if(deployed && motors.getDeployPosition() >= 36399) { //64 degrees
        deployed = false;
        return true;
      }
      if(!deployed && motors.getDeployPosition() >= 18399) {
        deployed = true;
        return true;
      }
    return false;
  }
}
