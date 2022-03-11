package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.CatapultCommands.CatapultCommand;
import frc.robot.commands.CatapultCommands.CatapultDouble;
import frc.robot.commands.CatapultCommands.CatapultRight;
import frc.robot.commands.IntakeCommands.IntakeDeploy;
import frc.robot.commands.IntakeCommands.IntakeStow;
import frc.robot.commands.IntakeCommands.SpinIntake;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeMotors;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

public class AutoCommand extends SequentialCommandGroup {
    //private DrivetrainSubsystem m_drivetrainSubsystem;


    public AutoCommand(DrivetrainSubsystem m_drivetrainSubsystem, CatapultSubsystem m_catapultSubsystemLeft, CatapultSubsystem m_catapultSubsystemRight, IntakeMotors m_intakeMotors) {
        //this.m_drivetrainSubsystem = m_drivetrainSubsystem;
        PathPlannerTrajectory threeCargoR= PathPlanner.loadPath("2 Cargo - Right", 1, 1);
        
        PathPlannerTrajectory path = threeCargoR;
        addCommands(
            new CatapultRight(m_catapultSubsystemRight, 0.45),
                       new IntakeDeploy(m_intakeMotors, Constants.INTAKE_DEPLOY_SPEED),
                       new InstantCommand(() -> m_drivetrainSubsystem.resetOdometry(new Pose2d(7.95, 2.73, new Rotation2d(Math.toRadians(-111.80))))),
                       new ParallelCommandGroup(
                         new SpinIntake(m_intakeMotors, Constants.UPPERSPEED, Constants.LOWERSPEED).withTimeout(path.getTotalTimeSeconds()),
                        new PPSwerveControllerCommand(
                            path,
                            m_drivetrainSubsystem::getPose,
                            m_drivetrainSubsystem.getKinematics(),
                            new PIDController(1, 0, 0),
                            new PIDController(1, 0, 0),
                            m_drivetrainSubsystem.getThetaController(),
                            m_drivetrainSubsystem::setStates,
                            m_drivetrainSubsystem)), 
                     new IntakeStow(m_intakeMotors, Constants.INTAKE_DEPLOY_SPEED),
                     new IntakeDeploy(m_intakeMotors, Constants.INTAKE_DEPLOY_SPEED),
                    new CatapultDouble(m_catapultSubsystemLeft, m_catapultSubsystemRight, 0.45, 0.45, 0)

        );
    }

}
