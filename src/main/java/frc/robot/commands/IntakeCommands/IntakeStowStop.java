// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.UpperLowerIntake;

public class IntakeStowStop extends ParallelCommandGroup {
  public IntakeStowStop(UpperLowerIntake upperLowerIntake, IntakeMotor m_IntakeMotor, double deploySpeed) {
      addCommands(new IntakeStow(m_IntakeMotor, deploySpeed),
                  new SpinIntake(upperLowerIntake, 0, 0));
    }
  }