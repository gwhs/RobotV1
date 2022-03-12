

// import java.util.function.Consumer;
// import java.util.function.Supplier;

// import com.pathplanner.lib.PathPlannerTrajectory;
// import com.pathplanner.lib.PathPlannerTrajectory.PathPlannerState;
// import com.pathplanner.lib.commands.PPSwerveControllerCommand;

// import edu.wpi.first.math.controller.HolonomicDriveController;
// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.controller.ProfiledPIDController;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
// import edu.wpi.first.math.kinematics.SwerveModuleState;
// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj2.command.Subsystem;

// /**
//  * A command that uses two PID controllers ({@link PIDController}) and a
//  * ProfiledPIDController
//  * ({@link ProfiledPIDController}) to follow a trajectory {@link PathPlannerTrajectory}
//  * with a swerve drive.
//  *
//  * <p>
//  * This command outputs the raw desired Swerve Module States
//  * ({@link SwerveModuleState}) in an
//  * array. The desired wheel and module rotation velocities should be taken from
//  * those and used in
//  * velocity PIDs.
//  *
//  * <p>
//  * The robot angle controller does not follow the angle given by the trajectory
//  * but rather goes
//  * to the angle given in the final state of the trajectory.
//  *
//  * <p>
//  * This class is provided by the NewCommands VendorDep
//  */
// @SuppressWarnings("MemberName")
// public class SwerveControllerCommandOverride extends PPSwerveControllerCommand {
//     private final Timer m_timer = new Timer();
//     private final static PathPlannerTrajectory m_trajectory;
//     private final static Supplier<Pose2d> m_pose;
//     private final static SwerveDriveKinematics m_kinematics;
//     private final HolonomicDriveController m_controller;
//     private final static Consumer<SwerveModuleState[]> m_outputModuleStates;

//     public SwerveControllerCommandOverride() {
//         super(m_trajectory, m_pose, m_kinematics, null, null, null, m_outputModuleStates, null);
//     }

//     @Override
//     public void initialize() {
//         m_timer.reset();
//         m_timer.start();
//     }

//     @Override
//     @SuppressWarnings("LocalVariableName")
//     public void execute() {
//         double curTime = m_timer.get();
//         var desiredState = (PathPlannerState) m_trajectory.sample(curTime);

//         var targetChassisSpeeds = m_controller.calculate(m_pose.get(), desiredState, desiredState.holonomicRotation);
//         var targetModuleStates = m_kinematics.toSwerveModuleStates(targetChassisSpeeds);

//         m_outputModuleStates.accept(targetModuleStates);
//     }

//     @Override
//     public void end(boolean interrupted) {
//         m_timer.stop();
//     }

//     @Override
//     public boolean isFinished() {
//         return m_timer.hasElapsed(m_trajectory.getTotalTimeSeconds());
//     }
// }