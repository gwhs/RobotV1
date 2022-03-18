// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoAlignCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;

import frc.robot.commands.CatapultCommands.CatapultIntake;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.LimelightPortal;
import frc.robot.subsystems.TimeOfFlightRange;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AlignToFender extends SequentialCommandGroup {
  /** Creates a new AlignToFender. 
   * Aligns w/ limelight then drives forward with limelight till upper hub 
   * is out of view, finishing it up with time of flight sensor
  */

  // TrajectoryMaker move = TrajectoryHelper.createMoveToFender();

  public AlignToFender(DrivetrainSubsystem drivetrain, LimelightPortal ll, TimeOfFlightRange sensor, CatapultSubsystem m_CatapultSubsystemRight, CatapultSubsystem m_CatapultSubsystemLeft, IntakeMotor m_intakeMotor) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

    super(/*new AlignAndMoveToLimelight(44, swerveDrive, ll), 
      new AlignAndMoveToLimelight(44, swerveDrive, ll), 
      new TurnToZeroLimelight(0, swerveDrive, ll).withTimeout(0.25),
      new GoToDistanceTimeOfFlight(3, swerveDrive, sensor)*/
      new TurnToZeroLimelight(0, drivetrain, ll).withTimeout(0.75), 
      new GoToDistanceTimeOfFlight(6.5, drivetrain, sensor).withTimeout(3.5),
      new CatapultIntake(m_intakeMotor, m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.CATAPULT_SPEED, Constants.CATAPULT_SPEED, Constants.INTAKE_DEPLOY_SPEED, Constants.CATAPULT_DELAY)); 
  }
}
