// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoAlignCommands;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.TimeOfFlightRange;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class GoToDistanceTimeOfFlight extends ProfiledPIDCommand {
  /** Creates a new GoToDistanceTimeOfFlight. 
   * Uses time of flight to move forward up until a certain 
   * away from a wall
  */
  TimeOfFlightRange sensorCheck;
  double target;

  public GoToDistanceTimeOfFlight(double distanceInches, DrivetrainSubsystem drivetrain, TimeOfFlightRange sensor) {
    super(
        // The ProfiledPIDController used by the command
        new ProfiledPIDController(Constants.DISTANCE_PID_P,Constants.DISTANCE_PID_I, Constants.DISTANCE_PID_D, //need to tune this better
            new TrapezoidProfile.Constraints(Constants.MAX_DISTANCE_VELOCITY,Constants.MAX_DISTANCE_ACCELERATION)),
        // This should return the measurement
        sensor::getDistanceSensor,
        // This should return the goal (can also be a constant)
        distanceInches,
        // This uses the output
        (output, setpoint) -> drivetrain.drive(new ChassisSpeeds(output,0,0)),
        // Require the drive
        drivetrain);
  
      // Set the controller to be continuous (because it is an angle controller)
      //getController().enableContinuousInput(-180, 180);
      target = distanceInches;
      sensorCheck = sensor;
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double distance = sensorCheck.getDistanceSensor();
    return Math.abs(distance) < target; 
  }
}
