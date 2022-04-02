// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Add your docs here. */
public class ShuffleDisplay extends SubsystemBase {
    private IntakeMotor m_IntakeMotor;
    private  ShuffleboardTab tab = Shuffleboard.getTab("Intake");
    private NetworkTableEntry ticks = tab.add("ticks", m_IntakeMotor.isOtherData()).getEntry();

    // public void ShuffleDisplay(){
    // NetworkTableEntry ticks = DeployTab.add("Tick movement", m_IntakeMotor.isOtherData()).getEntry();
    // }
}
