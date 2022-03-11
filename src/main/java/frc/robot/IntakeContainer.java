
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.commands.*;
import frc.robot.commands.IntakeCommands.IntakeDeploy;
import frc.robot.commands.IntakeCommands.IntakeGoToPos;
import frc.robot.commands.IntakeCommands.IntakeStow;
import frc.robot.commands.IntakeCommands.SpinIntake;
import frc.robot.commands.IntakeCommands.ToggleIntake;
import frc.robot.commands.IntakeCommands.ToggleIntakeCatapult;

public class IntakeContainer implements BaseContainer{
  private final IntakeMotors m_IntakeMotors = new IntakeMotors(Constants.INTAKE_DEPLOY_ID,Constants.INTAKE_UPPERTALON_ID, Constants.INTAKE_LOWERTALON_ID); //FIX INPUTS
  private final XboxController m_controller = new XboxController(0);
  

  public IntakeContainer() {




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
    // Back button zeros the gyroscope
    // new Button(m_controller::getBackButton)
    //         // No requirements because we don't need to interrupt anything
    //         .whenPressed(m_drivetrainSubsystem::zeroGyroscope);
    
    JoystickButton RB = new JoystickButton(m_controller, XboxController.Button.kRightBumper.value);
    JoystickButton X = new JoystickButton(m_controller,XboxController.Button.kX.value);
    JoystickButton Y = new JoystickButton(m_controller, XboxController.Button.kY.value);
    JoystickButton B = new JoystickButton(m_controller, XboxController.Button.kB.value);

    RB.whenPressed(new ToggleIntake(m_IntakeMotors, 0.3, 0, 0));
    Y.whenPressed(new IntakeStow(m_IntakeMotors, 0.3));
    X.whenPressed(new SpinIntake(m_IntakeMotors, -1, 1));
    B.whenPressed(new IntakeDeploy(m_IntakeMotors, 0.3));


    
    
  
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
