// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CatapultCommands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.CatapultSubsystem;

public class CatapultLeft extends CommandBase {
  private CatapultSubsystem motor;
  private double speed;
  private double returnSpeed;
  private double offset;
  private boolean ran;

  /** Creates a new CatapultLeft. */
  public CatapultLeft(CatapultSubsystem motor, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.motor = motor;
    this.speed = speed;

    this.returnSpeed = -0.06;
    
    addRequirements(motor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ran = false;
    System.out.println("Round 1 pos:"+motor.getPosition());
    //sets speed\
    offset = motor.getPosition();
    motor.setSelectedSensorPosition();
    motor.setPercent(speed + motor.power);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double position = motor.getPosition();
    //position is 77.3k for 360 degrees of rotation
    if (Math.abs(position) >= 5100 - offset){
        motor.setBrake();
        motor.setPercent(returnSpeed);
        //put motor in reverse to reset
        ran = true;
    }
    System.out.println(motor.getPosition());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    motor.setPercent(0);
    System.out.println(motor.getPosition());
    System.out.println("Goodbye World");

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (motor.getPosition() < 0 - offset&& ran){
      return true;
  }
  return false;
  }
}
