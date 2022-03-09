// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeMotors;

public class ToggleIntakeCatapult extends CommandBase {
  private IntakeMotors motors;
  private double speed;
  private double currentPos;
  private double offset;
  private boolean deployed;
  /** Creates a new ToggleIntakeCatapult. */
  public ToggleIntakeCatapult(IntakeMotors motors, double speed) {
    this.motors = motors;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(motors);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    offset = motors.getDeployPosition();
    motors.setZero();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentPos = motors.getDeployPosition();
    if (currentPos <= 14200 - offset){ // intake 29 degrees out
      deployed = true;
      motors.setDeployMotorSpeed(0.1);
    }
    else {
      deployed = false;
      motors.setDeployMotorSpeed(-0.1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    motors.setDeployMotorSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(deployed && motors.getDeployPosition() <= 14200) { // intake 29 degrees out
      deployed = false;
      return true;
    }
    if(!deployed && motors.getDeployPosition() >= 14200) {
      deployed = true;
      return true;
    }
  return false;
}
  }
