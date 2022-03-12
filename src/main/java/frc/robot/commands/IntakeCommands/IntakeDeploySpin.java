// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.subsystems.UpperLowerIntake;

public class IntakeDeploySpin extends ParallelCommandGroup {
  public IntakeDeploySpin(UpperLowerIntake upperLowerIntake,IntakeMotors m_IntakeMotors, double deploySpeed, double lowerSpeed, double upperSpeed) {
    addCommands(new IntakeDeploy(m_IntakeMotors, deploySpeed),
                new SpinIntake(upperLowerIntake, upperSpeed, lowerSpeed));
                }
              }