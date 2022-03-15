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
public class TurnToZeroLimelight extends ProfiledPIDCommand {
  /** Creates a new TurnToZeroLimelight. */

     TrapezoidProfile.Constraints rampUpDown = new TrapezoidProfile.Constraints(10,5);

    /**
     * Turns to robot to the specified angle.
     *
     * @param targetAngleDegrees The angle to turn to
     * @param drive              The drive subsystem to use
     */
    private LimelightPortal limeL;
    public TurnToZeroLimelight(double targetAngleDegrees, DrivetrainSubsystem drivetrain, LimelightPortal ll) {
    super(
        new ProfiledPIDController(Constants.ANGLE_PID_P,Constants.ANGLE_PID_I, Constants.ANGLE_PID_D, //need to tune this better
            new TrapezoidProfile.Constraints(Constants.MAX_ANGLE_VELOCITY,Constants.MAX_ANGLE_ACCELERATION)),

        // Close loop on heading
        ll::getX,
        // Set reference to target
        targetAngleDegrees,
        // Pipe output to turn branchrobot
        (output,setpoint) -> drivetrain.drive(new ChassisSpeeds(0,0,output)),
        // Require the drive
        drivetrain);



      // Set the controller to be continuous (because it is an angle controller)
      //getController().enableContinuousInput(-180, 180);
    limeL = ll;
    // Set the controller tolerance - the delta tolerance ensures the robot is stationary at the
    // setpoint before it is considered as having reached the reference
    getController().setTolerance(Constants.TURN_TOLERANCE, 10);

    // ll.printLoc();
    // Shuffleboard.getTab("limelight-test").add("test");
    // System.out.println("***********" + ll.getDistance() + "**********");
  }


  @Override
  public void execute() {
    super.execute();
  }

  @Override
  public boolean isFinished() {
    // End when the controller is at the reference.
    System.out.println(limeL.getX() + ":x-value and is not finished");
    return getController().atGoal();
    // return Math.abs(limeL.getX()) <= 1.5;
  }
}
