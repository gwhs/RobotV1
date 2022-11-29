// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.TestContainers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.BaseContainer;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.TurnDriveCommand;
import frc.robot.commands.ClimberCommands.OneInchClimber;
import frc.robot.commands.ClimberCommands.ParallelClimber;





public class TurnContainer implements BaseContainer{
  private final DrivetrainSubsystem m_DrivetrainSubsystem = new DrivetrainSubsystem(); //FIX INPUTS
  private final XboxController m_controller = new XboxController(0);

  public TurnContainer() {




    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton buttonX = new JoystickButton(m_controller, XboxController.Button.kX.value);

    buttonX.whenPressed(new TurnDriveCommand(m_DrivetrainSubsystem, 0));
    // buttonA.whenPressed(new RetractArm(m_ClimberSubsytem));
    
    
    

    
  
    
  
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }

}
