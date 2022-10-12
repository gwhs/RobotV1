package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

import edu.wpi.first.math.filter.SlewRateLimiter;

import java.util.function.DoubleSupplier;

public class DefaultDriveCommandSlew extends CommandBase {
    private final DrivetrainSubsystem m_drivetrainSubsystem;

    private final DoubleSupplier m_translationXSupplier;
    private final DoubleSupplier m_translationYSupplier;
    private final DoubleSupplier m_rotationSupplier;

    private SlewRateLimiter x_sLimiter = new SlewRateLimiter(2.5); //higher the number, the faster the wheels slow down to a stop
    private SlewRateLimiter y_sLimiter = new SlewRateLimiter(2.5);
    private SlewRateLimiter rot_sLimiter = new SlewRateLimiter(5);

    public DefaultDriveCommandSlew(DrivetrainSubsystem drivetrainSubsystem,
                               DoubleSupplier translationXSupplier,
                               DoubleSupplier translationYSupplier,
                               DoubleSupplier rotationSupplier) {
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.m_translationXSupplier = translationXSupplier;
        this.m_translationYSupplier = translationYSupplier;
        this.m_rotationSupplier = rotationSupplier;

        addRequirements(drivetrainSubsystem);
    }

    

    @Override
    public void execute() {
        // You can use `new ChassisSpeeds(...)` for robot-oriented movement instead of field-oriented movement
        m_drivetrainSubsystem.drive(
                ChassisSpeeds.fromFieldRelativeSpeeds(
            x_sLimiter.calculate(m_translationXSupplier.getAsDouble()),
            y_sLimiter.calculate(m_translationYSupplier.getAsDouble()),
            rot_sLimiter.calculate(m_rotationSupplier.getAsDouble()),
            m_drivetrainSubsystem.getGyroscopeRotation()
)
        );
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrainSubsystem.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }
}
