// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoAlignCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
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
  private LimelightPortal limelight;
  private DrivetrainSubsystem swervedrive; 
  private TimeOfFlightRange sensor;
  // TrajectoryMaker move = TrajectoryHelper.createMoveToFender();

  public AlignToFender(LimelightPortal ll, DrivetrainSubsystem swerveDrive, TimeOfFlightRange sensor) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());

    super(new AlignAndMoveToLimelight(70, swerveDrive, ll), 
      new AlignAndMoveToLimelight(70, swerveDrive, ll), 
      new TurnToZeroLimelight(0, swerveDrive, ll).withTimeout(0.25),
      new GoToDistanceTimeOfFlight(3, swerveDrive, sensor));
  }
}
