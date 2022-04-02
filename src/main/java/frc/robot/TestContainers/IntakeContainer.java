
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.TestContainers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.UpperLowerIntake;
import frc.robot.BaseContainer;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.IntakeCommands.IntakeDeploy;
import frc.robot.commands.IntakeCommands.IntakeDeploySpin;
import frc.robot.commands.IntakeCommands.IntakeStow;
import frc.robot.commands.IntakeCommands.IntakeStowStop;
import frc.robot.commands.IntakeCommands.SpinIntake;
import frc.robot.commands.IntakeCommands.ToggleIntake;

public class IntakeContainer implements BaseContainer{
  private final IntakeMotor m_IntakeMotor = new IntakeMotor(Constants.INTAKE_DEPLOY_ID); //FIX INPUTS
  private final UpperLowerIntake m_UpperLowerIntake = new UpperLowerIntake(Constants.INTAKE_UPPERTALON_ID, Constants.INTAKE_LOWERTALON_ID);
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
    JoystickButton A = new JoystickButton(m_controller, XboxController.Button.kA.value);

    // RB.whenPressed(new IntakeDeploySpin(m_UpperLowerIntake, m_IntakeMotor, 0.5, 0.85, -1));
    // Y.whenPressed(new IntakeStowStop(m_UpperLowerIntake, m_IntakeMotor, 0.5));
    X.whenPressed(new IntakeStow(m_IntakeMotor, 1));
    B.whenPressed(new IntakeDeploy(m_IntakeMotor, 1));
    Y.whenPressed(() -> System.out.println("Foward Limit: " + m_IntakeMotor.isFWDLIMIT())); 
    A.whenPressed(() -> System.out.println("Reverse Limit: " +  m_IntakeMotor.isREVLIMIT()));
    RB.whenPressed(() -> System.out.println("Position: " + m_IntakeMotor.isOtherData()));


    
    
  
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
