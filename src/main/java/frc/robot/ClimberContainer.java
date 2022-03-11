// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ClimberLeftSubsystem;
import frc.robot.subsystems.ClimberRightSubsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ClimberCommands.LeftClimbCommand;
import frc.robot.commands.ClimberCommands.MoveClimberRelative;
import frc.robot.commands.ClimberCommands.ParallelClimber;
import frc.robot.commands.ClimberCommands.RightClimbCommand;




public class ClimberContainer implements BaseContainer{
  private final ClimberRightSubsystem m_ClimberRightSubsystem = new ClimberRightSubsystem(43); //FIX INPUTS
  private final ClimberLeftSubsystem m_ClimberLeftSubsystem = new ClimberLeftSubsystem(45);
  private final XboxController m_controller = new XboxController(0);

  public ClimberContainer() {




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
    JoystickButton buttonb = new JoystickButton(m_controller, XboxController.Button.kB.value);
    JoystickButton buttonA = new JoystickButton(m_controller, XboxController.Button.kA.value);
    JoystickButton buttonY = new JoystickButton(m_controller, XboxController.Button.kY.value);
    // Back button zeros the gyroscope
    // new Button(m_controller::getBackButton)
    //         // No requirements because we don't need to interrupt anything
    //         .whenPressed(m_drivetrainSubsystem::zeroGyroscope);
    
    // buttonX.whenPressed(new RightClimbCommand(m_ClimberRightSubsytem, 6)); //Climb
    // buttonb.whenPressed(new RightClimbCommand(m_ClimberRightSubsytem,12));
    // buttonY.whenPressed(new LeftClimbCommand(m_ClimberLeftSubsystem, 6));
    // buttonA.whenPressed(new LeftClimbCommand(m_ClimberLeftSubsystem, 12));

    buttonX.whenPressed(new ParallelClimber(m_ClimberLeftSubsystem, m_ClimberRightSubsystem, 23));
    buttonA.whenPressed(new ParallelClimber(m_ClimberLeftSubsystem, m_ClimberRightSubsystem, 18));

    buttonb.whenPressed(new MoveClimberRelative(m_ClimberRightSubsystem, 1));
    buttonY.whenPressed(new MoveClimberRelative(m_ClimberRightSubsystem, -1));
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
