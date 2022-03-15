// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoAlignCommands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightPortal;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SwitchLimelightStream extends InstantCommand {
  //used only to test and attempt to fix the limelight stream modes
  
  LimelightPortal ll;
  public SwitchLimelightStream() {
    ll = new LimelightPortal();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    // if(current == 0)
    // {
      ll.setCamera(2);
      double current = ll.getCamera();
      System.out.println(current);
    // }
  }
}
