package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

public class AutoCommand extends SequentialCommandGroup {
    //private DrivetrainSubsystem m_drivetrainSubsystem;


    public AutoCommand(DrivetrainSubsystem m_drivetrainSubsystem) {
        //this.m_drivetrainSubsystem = m_drivetrainSubsystem;
        PathPlannerTrajectory threeCargoR= PathPlanner.loadPath("3 Cargo - Right", 1, 1);
        
        PathPlannerTrajectory path = threeCargoR;
        addCommands(new InstantCommand(() -> m_drivetrainSubsystem.resetOdometry(new Pose2d(8.17, 2.94, new Rotation2d(Math.toRadians(-113.20))))),
                    new PPSwerveControllerCommand(
                        path,
                        m_drivetrainSubsystem::getPose,
                        m_drivetrainSubsystem.getKinematics(),
                        new PIDController(1, 0, 0),
                        new PIDController(1, 0, 0),
                        m_drivetrainSubsystem.getThetaController(),
                        m_drivetrainSubsystem::setStates,
                        m_drivetrainSubsystem)

        );
    }

}
