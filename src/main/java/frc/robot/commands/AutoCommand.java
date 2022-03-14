package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.CatapultCommands.CatapultDouble;
import frc.robot.commands.CatapultCommands.CatapultRight;
import frc.robot.commands.IntakeCommands.IntakeDeploy;
import frc.robot.commands.IntakeCommands.IntakeStow;
import frc.robot.commands.IntakeCommands.SpinIntake;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.UpperLowerIntake;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

public class AutoCommand extends SequentialCommandGroup {
    //private DrivetrainSubsystem m_drivetrainSubsystem;


    public AutoCommand(DrivetrainSubsystem m_drivetrainSubsystem, CatapultSubsystem m_catapultSubsystemLeft, CatapultSubsystem m_catapultSubsystemRight, IntakeMotor m_intakeMotor, UpperLowerIntake m_UpperLower) {
        //this.m_drivetrainSubsystem = m_drivetrainSubsystem;
        PathPlannerTrajectory threeCargoR = PathPlanner.loadPath("2 Cargo - Left", 1, 1);
        
        PathPlannerTrajectory path = threeCargoR;
        addCommands(
                    new IntakeDeploy(m_intakeMotor, Constants.INTAKE_DEPLOY_SPEED),
                    new CatapultRight(m_catapultSubsystemRight, Constants.CATAPULT_SPEED_LOW),
                    new InstantCommand(() -> m_drivetrainSubsystem.resetOdometry(new Pose2d(6.89, 4.44, new Rotation2d(Math.toRadians(159.0))))), //left
                    //new InstantCommand(() -> m_drivetrainSubsystem.resetOdometry(new Pose2d(7.95, 2.73, new Rotation2d(Math.toRadians(-111.00))))), //right
                    //new InstantCommand(() -> System.out.println(m_drivetrainSubsystem.getPose())),
                    new ParallelCommandGroup(
                         new SpinIntake(m_UpperLower, Constants.INTAKE_UPPER_SPEED, Constants.INTAKE_LOWER_SPEED).withTimeout(path.getTotalTimeSeconds()),
                         new PPSwerveControllerCommand(
                            path,
                            m_drivetrainSubsystem::getPose,
                            m_drivetrainSubsystem.getKinematics(),
                            new PIDController(1, 0, 0),
                            new PIDController(1, 0, 0),
                            m_drivetrainSubsystem.getThetaController(),
                            m_drivetrainSubsystem::setStates,
                            m_drivetrainSubsystem)), 
                    new InstantCommand(() -> m_drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0))),
                    //new CatapultRight(m_catapultSubsystemRight, 0.250),
                    //new SpinIntake(m_intakeMotor, 0, 0),
                    new IntakeStow(m_intakeMotor, Constants.INTAKE_DEPLOY_SPEED),
                    new IntakeDeploy(m_intakeMotor, Constants.INTAKE_DEPLOY_SPEED),
                    new WaitCommand(1),
                    new CatapultDouble(m_catapultSubsystemLeft, m_catapultSubsystemRight, Constants.CATAPULT_SPEED_LOW, Constants.CATAPULT_SPEED_LOW, 0)

        );
    }

}
