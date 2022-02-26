package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;

import edu.wpi.first.math.geometry.Rotation2d;
import java.util.function.DoubleSupplier;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

public class AutoMeter extends SequentialCommandGroup {
    private DrivetrainSubsystem m_drivetrainSubsystem;


    public AutoMeter(DrivetrainSubsystem m_drivetrainSubsystem) {
        this.m_drivetrainSubsystem = m_drivetrainSubsystem;
        PathPlannerTrajectory oneMeter = PathPlanner.loadPath("oneMeter", 1, 1);
        PathPlannerTrajectory simple = PathPlanner.loadPath("simple", 1, 1);
        PathPlannerTrajectory path = simple;
        addCommands(new InstantCommand(() -> m_drivetrainSubsystem.resetOdometry(path.getInitialPose())),
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

    

   