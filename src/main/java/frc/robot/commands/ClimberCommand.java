// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.CANifier.LEDChannel;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.ClimberContainer;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberCommand extends CommandBase {
  /** Creates a new ClimvberCommand. */
  private double distance;
  private double speed;
  private TalonFX leaderArm;
  private TalonFX followerArm;
  private double initPos;
  private double targetPos;

  private ClimberSubsystem climberArms;
  private int climberID;
  private double ticks;
  public ClimberCommand(ClimberSubsystem climberArms, double ticks) {
    //this.speed = speed;
    this.climberArms = climberArms;
    this.ticks = ticks;


    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(climberArms);
    leaderArm = climberArms.rightArm();
    followerArm = climberArms.leftArm();
    distance = ticks;


  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    followerArm.setInverted(InvertType.OpposeMaster);
    followerArm.set(ControlMode.Follower, Constants.leaderArm);
    targetPos = initPos + distance;
    leaderArm.set(TalonFXControlMode.Position, targetPos);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climberArms.moveBothArms(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climberArms.moveBothArms(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(targetPos - leaderArm.getSelectedSensorPosition()) < 600;
  }
}
