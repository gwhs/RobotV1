// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.IntakeMotors;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeGoToPos extends PIDCommand {
  private IntakeMotors m_IntakeMotors;
  private double ticks;

  /** Creates a new IntakeGoToPos. */
  public IntakeGoToPos(IntakeMotors m_IntakeMotors, double ticks) {
    super(
        // The controller that the command will use
        new PIDController(0.8, 0, 0),
        // This should return the measurement
        m_IntakeMotors::getDeployPosition,
        // This should return the setpoint (can also be a constant)
        ticks,
        // This uses the output
        (output) -> m_IntakeMotors.goToPosition((int) output),
        m_IntakeMotors);
          // Use the output here);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    this.ticks = ticks;
    this.m_IntakeMotors = m_IntakeMotors;
    System.out.println("CONS Sensor ticks: " + m_IntakeMotors.getDeployPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("Sensor ticks: " + m_IntakeMotors.getDeployPosition());
    return Math.abs(m_IntakeMotors.getDeployPosition() - ticks) <= 2000;
  }
}
