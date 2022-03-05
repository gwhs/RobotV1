package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.math.controller.PIDController;


import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.commands.PPSwerveControllerCommand;

public class AutoCommand extends SequentialCommandGroup {
    //private DrivetrainSubsystem m_drivetrainSubsystem;


    public AutoCommand(DrivetrainSubsystem m_drivetrainSubsystem) {
        //this.m_drivetrainSubsystem = m_drivetrainSubsystem;
        PathPlannerTrajectory test1 = PathPlanner.loadPath("test1", 1, 1);
        PathPlannerTrajectory test2 = PathPlanner.loadPath("test2", 1, 1);
        PathPlannerTrajectory oneMeter = PathPlanner.loadPath("oneMeter", 1, 1);
        PathPlannerTrajectory interesting = PathPlanner.loadPath("interesting", 1, 1);
        PathPlannerTrajectory threeBall = PathPlanner.loadPath("3 Cargo - Right", 0.5, 0.5);
        PathPlannerTrajectory simple = PathPlanner.loadPath("simple", 1, 1);
        PathPlannerTrajectory straightPath = PathPlanner.loadPath("Straight2", 0.5, 0.5);
        PathPlannerTrajectory weird = PathPlanner.loadPath("oneMeterWeird", 1, 1);

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

    

   