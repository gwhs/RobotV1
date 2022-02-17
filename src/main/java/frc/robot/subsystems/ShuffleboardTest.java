// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShuffleboardTest extends SubsystemBase {
  /** Creates a new Shuffleboard. */
  private ShuffleboardTab ShTab = Shuffleboard.getTab("Percent Output");
  NetworkTableEntry output = ShTab.add("Percent Output", 1).getEntry();
  public ShuffleboardTest() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double PercentOutput = output.getDouble(1);
    Constants.CATAPULT_SPEED = PercentOutput;
    
  }
}