package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.CatapultCommands.CatapultDouble;
import frc.robot.commands.IntakeCommands.Spit;
import frc.robot.commands.IntakeCommands.ToggleIntake;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeMotors;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

import java.util.function.DoubleSupplier;

import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

public class AutoMeter extends SequentialCommandGroup {
    private DrivetrainSubsystem m_drivetrainSubsystem;
    private CatapultSubsystem m_catapultSubsystemLeft;
    private CatapultSubsystem m_catapultSubsystemRight;
    private IntakeMotors m_intakeMotors;

    //HARD CODE IN INITIAL POSE
    public AutoMeter(DrivetrainSubsystem m_drivetrainSubsystem, CatapultSubsystem m_catapultSubsystemLeft, CatapultSubsystem m_catapultSubsystemRight, IntakeMotors m_intakeMotors) {
        this.m_drivetrainSubsystem = m_drivetrainSubsystem;
        this.m_catapultSubsystemLeft = m_catapultSubsystemLeft;
        this.m_catapultSubsystemRight = m_catapultSubsystemRight;
        this.m_intakeMotors = m_intakeMotors;
        PathPlannerTrajectory oneMeter = PathPlanner.loadPath("oneMeter", 1, 1);
        PathPlannerTrajectory simple = PathPlanner.loadPath("simple", 1, 1);
        PathPlannerTrajectory simpleReversed = PathPlanner.loadPath("simpleReversed", 1, 1);
        PathPlannerTrajectory diagonal = PathPlanner.loadPath("diagonal", 1, 1);
        PathPlannerTrajectory path = diagonal;

        System.out.println(simple.getInitialPose());
        //addCommands(new InstantCommand(() -> System.out.println(simple.getInitialPose())));
        addCommands(new InstantCommand(() -> m_drivetrainSubsystem.resetOdometry(new Pose2d(new Translation2d(1.0, 3.0), new Rotation2d(Math.toRadians(225))))),
                    new ParallelCommandGroup(new Spit(m_intakeMotors).withTimeout(8), new PPSwerveControllerCommand(
                        path,
                        m_drivetrainSubsystem::getPose,
                        m_drivetrainSubsystem.getKinematics(),
                        new PIDController(1, 0, 0),
                        new PIDController(1, 0, 0),
                        m_drivetrainSubsystem.getThetaController(),
                        m_drivetrainSubsystem::setStates,
                        m_drivetrainSubsystem)),
                        new CatapultDouble(m_catapultSubsystemLeft, m_catapultSubsystemRight, Constants.SHOOTER_MODE_RIGHT)
                    );
    }

}

    

   