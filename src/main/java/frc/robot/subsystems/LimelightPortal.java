// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class LimelightPortal extends SubsystemBase {
  /** Creates a new LimelightPortal. */
  private NetworkTable shooterLL;
  /**
   * Creates a new LimelightPortal.
   */
  public LimelightPortal() {
    shooterLL = NetworkTableInstance.getDefault().getTable("limelight-shooter"); 

    // shooterLL.getEntry("ledMode").setNumber(1);
    shooterLL.getEntry("stream").setNumber(2); 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run


   // 10.55.7.11
    NetworkTableEntry shX = shooterLL.getEntry("tx");
    NetworkTableEntry shY = shooterLL.getEntry("ty");
    NetworkTableEntry shA = shooterLL.getEntry("ta");
  }

  public void printLoc()
  {
    NetworkTable shooterLL = NetworkTableInstance.getDefault().getTable("limelight-shooter"); // detects 
    /*gets the y, x, a values from the networktable, what limelight uses to hold their data
    * (they're in the limelight site menu, right below the camera */
    NetworkTableEntry shX = shooterLL.getEntry("tx"); 
    NetworkTableEntry shY = shooterLL.getEntry("ty");
    NetworkTableEntry shA = shooterLL.getEntry("ta");

    /* changes data obtained from network table into doubles*/
    double x = shX.getDouble(0.0);
    double y = shY.getDouble(0.0);
    double a = shA.getDouble(0.0);


    System.out.println("x="+x+",y="+y+",a="+a+" ,distance="+getDistance());
  }

  public double getX() //get x value, used for turning
  {
    NetworkTable shooterLL = NetworkTableInstance.getDefault().getTable("limelight-shooter"); // 10.55.7.11
    NetworkTableEntry shX = shooterLL.getEntry("tx");
    NetworkTableEntry shY = shooterLL.getEntry("ty");
    NetworkTableEntry shA = shooterLL.getEntry("ta");

    double x = shX.getDouble(0.0);
    //double y = shY.getDouble(0.0);
    //double a = shA.getDouble(0.0);

    return x;
  }

  public boolean foundTarget()
  {
    NetworkTable shooterLL = NetworkTableInstance.getDefault().getTable("limelight-shooter");
    NetworkTableEntry shV = shooterLL.getEntry("tv");

    int v = (int)shV.getDouble(0.0);

    return v > 0;
  }

  public double getDistance() //gets y and does trig to get distance to move forward
  {
    NetworkTable shooterLL = NetworkTableInstance.getDefault().getTable("limelight-shooter"); // 10.55.7.11
    NetworkTableEntry shX = shooterLL.getEntry("tx");
    NetworkTableEntry shY = shooterLL.getEntry("ty");
    NetworkTableEntry shA = shooterLL.getEntry("ta");

    //double x = shX.getDouble(0.0);
    double y = shY.getDouble(0.0);
    //double a = shA.getDouble(0.0);

    double distance = (Constants.VISION_TARGET_HEIGHT - Constants.LL_HEIGHT) /
                Math.tan(Math.toRadians(Constants.LL_MOUNT_ANGLE)+Math.toRadians(y));

    return distance;
  }

  public void setCamera(double mode)
  {
    shooterLL.getEntry("stream").setNumber(mode);
  }

  public double getCamera()
  {
    NetworkTableEntry streamMode = shooterLL.getEntry("stream");
    return streamMode.getDouble(3);
  }
}
