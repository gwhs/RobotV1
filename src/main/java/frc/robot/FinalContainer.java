// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


// package frc.robot;


// import edu.wpi.first.wpilibj.GenericHID;
// import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
// import frc.robot.subsystems.ClimberSubsystem;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import frc.robot.commands.ClimberCommands.AutoClimb;
// import frc.robot.commands.ClimberCommands.ClimberCommand;
// import frc.robot.commands.ClimberCommands.ExtendArm;
// import frc.robot.commands.ClimberCommands.RetractArm;


// public class ClimberContainer {
//   private final ClimberSubsystem m_ClimberSubsytem = new ClimberSubsystem(43, 45); //FIX INPUTS
//   private final XboxController m_controller = new XboxController(0);

//   public ClimberContainer() {



//     m_drivetrainSubsystem.zeroGyroscope();
//     // Set up the default command for the drivetrain.
//     // The controls are for field-oriented driving:
//     // Left stick Y axis -> forward and backwards movement
//     // Left stick X axis -> left and right movement
//     // Right stick X axis -> rotation
//     m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
//             m_drivetrainSubsystem,
//             () -> -modifyAxis(m_controller.getLeftY()) * 1, //DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
//             () -> -modifyAxis(m_controller.getLeftX()) * 1, //DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
//             //() -> -modifyAxis(m_controller.getRightX()) * 2//DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
//             () -> modifyAxis(m_controller.getLeftTriggerAxis() - m_controller.getRightTriggerAxis()) * 2 //DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
//     ));

// //     // Configure the button bindings
// //     configureButtonBindings();
// //   }

//   /**
//    * Use this method to define your button->command mappings. Buttons can be created by
//    * instantiating a {@link GenericHID} or one of its subclasses ({@link
//    * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
//    * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
//    */
//   private void configureButtonBindings() {
//     JoystickButton buttonX = new JoystickButton(m_controller, XboxController.Button.kX.value);
//     JoystickButton buttonB = new JoystickButton(m_controller, XboxController.Button.kB.value);
//     JoystickButton buttonA = new JoystickButton(m_controller, XboxController.Button.kA.value);
//     JoystickButton buttonY = new JoystickButton(m_controller, XboxController.Button.kY.value);
//     JoystickButton buttonLTrigger = new JoystickButton(m_controller, XboxController.Button.kLeftBumper.value);
//     JoystickButton buttonRTrigger = new JoystickButton(m_controller, XboxController.Button.kRightBumper.value);
//     JoystickButton button = new JoystickButton(m_controller, XboxController.g.value);
//     JoystickButton button = new JoystickButton(m_controller, XboxController.Button.k.value);
//     JoystickButton button = new JoystickButton(m_controller, XboxController.Button.k.value);
//     JoystickButton button = new JoystickButton(m_controller, XboxController.Button.k.value);
//     JoystickButton button = new JoystickButton(m_controller, XboxController.Button.k.value);
//     JoystickButton button = new JoystickButton(m_controller, XboxController.Button.k.value);
//     JoystickButton button = new JoystickButton(m_controller, XboxController.Button.k.value);
//     // Back button zeros the gyroscope
//     // new Button(m_controller::getBackButton)
//     //         // No requirements because we don't need to interrupt anything
//     //         .whenPressed(m_drivetrainSubsystem::zeroGyroscope);
    
// //     buttonX.whenPressed(new AutoClimb(m_ClimberSubsytem)); //Climb
    
// //     // buttonX.whenPressed(new ClimberCommand(m_ClimberSubsytem, ));
// //     // // buttonb.whileHeld(new ExtendArm(m_ClimberSubsytem));
// //     // buttonA.whenPressed(new RetractArm(m_ClimberSubsytem));
    
    
    

    
  
    
  
// //   }

// //   /**
// //    * Use this to pass the autonomous command to the main {@link Robot} class.
// //    *
// //    * @return the command to run in autonomous
// //    */
// //   public Command getAutonomousCommand() {
// //     // An ExampleCommand will run in autonomous
// //     return new InstantCommand();
// //   }


//   private static double deadband(double value, double deadband) {
//     if (Math.abs(value) > deadband) {
//       if (value > 0.0) {
//         return (value - deadband) / (1.0 - deadband);
//       } else {
//         return (value + deadband) / (1.0 - deadband);
//       }
//     } else {
//       return 0.0;
//     }
//   }

//   private static double modifyAxis(double value) {
//     // Deadband
//     value = deadband(value, 0.2); //0.05, 0.1 seems to work


//     return value;
//   }
// }
// // }
