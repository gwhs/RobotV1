// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeCommands.IntakeDeploy;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.IntakeMotor;

public class CatapultIntake extends SequentialCommandGroup {
  /** Creates a new CatapultIntake. */
  double deployDelay = 0.3;
  public CatapultIntake(IntakeMotor m_IntakeMotor, CatapultSubsystem m_CatapultSubsystemLeft, CatapultSubsystem m_CatapultSubsystemRight, double leftSpeed, double rightSpeed, double deploySpeed, double delay) {
    addCommands(new IntakeDeploy(m_IntakeMotor, deploySpeed),
                  new SequentialCommandGroup(new WaitCommand(deployDelay)),
                    new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, leftSpeed, rightSpeed, deploySpeed, delay));
  }

}
