// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.UpperLowerIntake;

public class ToggleIntake extends SequentialCommandGroup {
  /** Creates a new ToggleIntake. */
  public ToggleIntake(IntakeMotor m_IntakeMotor, UpperLowerIntake m_UpperLowerIntake) {
    if(m_IntakeMotor.isDeployed()){
      addCommands(   
        new IntakeStowStop(m_UpperLowerIntake, m_IntakeMotor, Constants.DEPLOY_SPEED)
      );
    }
    else {
      addCommands(
        new IntakeDeploySpin(m_UpperLowerIntake, m_IntakeMotor, Constants.INTAKE_DEPLOY_SPEED, Constants.INTAKE_LOWER_SPEED, Constants.INTAKE_UPPER_SPEED)
      );
    }

  }
}
  

