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
import frc.robot.subsystems.LimelightPortal;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class GoToDistanceLimelight extends ProfiledPIDCommand {
  /** Creates a new GoToDistanceLimelight. 
   * Goes to set distance target using limelight and trig
  */
  private DrivetrainSubsystem drivetrainSubsystem;
  private LimelightPortal limeL;
  TrapezoidProfile.Constraints rampUpDown = new TrapezoidProfile.Constraints(10, 5);

  /**
   * Turns to robot to the specified angle.
   *
   * @param targetAngleDegrees The angle to turn to
   * @param drive              The drive subsystem to use
   * 
   * default distanceInches should be how big the bumper is
   */
  public GoToDistanceLimelight(double distanceInches, DrivetrainSubsystem drivetrain, LimelightPortal ll) {
  super(
      new ProfiledPIDController(Constants.DISTANCE_PID_P,Constants.DISTANCE_PID_I, Constants.DISTANCE_PID_D, //need to tune this better
          new TrapezoidProfile.Constraints(Constants.MAX_DISTANCE_VELOCITY,Constants.MAX_DISTANCE_ACCELERATION)),
      
      // Close loop on heading
      ll::getDistance,
      // Set reference to target
      distanceInches,  
      // Pipe output to turn branchrobot
      (output,setpoint) -> drivetrain.drive(new ChassisSpeeds(output,0,0)),
      // Require the drive
      drivetrain);

    // Set the controller to be continuous (because it is an angle controller)
    //getController().enableContinuousInput(-180, 180);
    drivetrainSubsystem = drivetrain;
    limeL = ll;
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController().setTolerance(Constants.DISTANCE_TOLERANCE, 10);
    System.out.println("***********" + distanceInches + "**********");
  }


  @Override
  public void execute() {
  
    super.execute();
    System.out.println("robot distance: " 
        + limeL.getDistance());
  }
  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    return getController().atGoal();
  }
}
