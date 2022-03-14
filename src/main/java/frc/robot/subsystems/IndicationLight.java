// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AddressableLED;
public class IndicationLight extends SubsystemBase {
 private AddressableLED m_led;
  /** Creates a new IndicationLight. */
  public IndicationLight() {
    m_led = new AddressableLED(9);
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
