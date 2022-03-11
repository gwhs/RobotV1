// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;

public class ToggleIntake extends CommandBase {
  private IntakeMotors motors;
  private double deploySpeed;
  private double upperSpeed;
  private double lowerSpeed;
  private boolean deploying;

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
    boolean isDeployed = motors.isDeployed();
    boolean isStowed = motors.isStowed();
    boolean isMiddle = motors.isMiddle();
    if(isStowed){
      deploying = true;
      System.out.println("Up ticks: " + motors.getDeployPosition());
    }
    else if(isDeployed){
      deploying = false;
      System.out.println("Down ticks: " + motors.getDeployPosition());
    }
    else if(isMiddle){
      deploying = false;
      System.out.println("In middle ticks: " + motors.getDeployPosition());
     }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(deploying){
      motors.setDeployMotorSpeed(deploySpeed);
    }
    else{
      motors.setDeployMotorSpeed(-deploySpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    motors.setDeployMotorSpeed(0);
    System.out.println("End Pos: " + motors.getDeployPosition());
    if(deploying) {
      motors.setIntakeMotorSpeeds(-upperSpeed, lowerSpeed);
    } 
    else {
      motors.setIntakeMotorSpeeds(0, 0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
      if(deploying) { //64 degrees
        return motors.isDeployed();
      }
      if(deploying == false) {
        return motors.isStowed();
      }
    return false;
  }
}
