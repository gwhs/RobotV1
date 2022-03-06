// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IndicationLight;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class IndicationLightCommand extends CommandBase {
  private IndicationLight m_IndicationLight;
  private AddressableLED m_led;
  private AddressableLEDBuffer m_ledBuffer;

  public IndicationLightCommand() {

    m_led = new AddressableLED(5); // # on the PMW on RoboRio
    m_ledBuffer = new AddressableLEDBuffer(5);// # of LED Lights
    m_led.setLength(m_ledBuffer.getLength());

    // Set the data
    m_led.setData(m_ledBuffer);
    m_led.start();

    for (var i = 0; i < m_ledBuffer.getLength(); i++) {
      // Sets the specified LED to the RGB values for red
      m_ledBuffer.setRGB(i, 255, 0, 0);
    }
    addRequirements(m_IndicationLight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
