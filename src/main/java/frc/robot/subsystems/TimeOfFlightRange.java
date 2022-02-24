// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.playingwithfusion.TimeOfFlight;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import com.playingwithfusion.TimeOfFlight;

public class TimeOfFlightRange extends SubsystemBase {
  /** Creates a new TimeOfFlightRange. */
  private TimeOfFlight sensor;

  public TimeOfFlightRange() {
    sensor = new TimeOfFlight(2); //get the id of the timeOfFlight on the v1, get ToF added if it has yet to be then scan
    sensor.setRangingMode(TimeOfFlight.RangingMode.Long, 24); //remember to review and see if improvements can be made
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //ask hajel for usage of this
  }

  public double getDistanceSensor() {
    double distance = sensor.getRange(); //gets distance from target from timeOfFlight sensor in mm
    return distance / 25.4;
  }
}
