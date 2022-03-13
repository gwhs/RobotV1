// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.IntakeMotor;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeGoToPos extends PIDCommand {
  private IntakeMotor m_IntakeMotor;
  private double ticks;

  /** Creates a new IntakeGoToPos. */
  public IntakeGoToPos(IntakeMotor m_IntakeMotor, double ticks) {
    super(
        // The controller that the command will use
        new PIDController(5, 0, 0),
        // This should return the measurement
        m_IntakeMotor::getDeployPosition,
        // This should return the setpoint (can also be a constant)
        ticks,
        // This uses the output
        (output) -> m_IntakeMotor.goToPosition((int) output),
        m_IntakeMotor);
          // Use the output here);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    this.ticks = ticks;
    this.m_IntakeMotor = m_IntakeMotor;
    System.out.println("CONS Sensor ticks: " + m_IntakeMotor.getDeployPosition());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("Sensor ticks: " + m_IntakeMotor.getDeployPosition());
    return Math.abs(m_IntakeMotor.getDeployPosition() - ticks) <= 2000;
  }
}
