// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
// import frc.robot.commands.AutoCommand;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.ClimberCommands.ParallelClimber;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.UpperLowerIntake;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer implements BaseContainer{
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private final XboxController m_controller = new XboxController(0);
  private final CatapultSubsystem m_catapultSubsystemLeft = new CatapultSubsystem(Constants.CATAPULT_LEFT_ID, false);
  private final CatapultSubsystem m_catapultSubsystemRight = new CatapultSubsystem(Constants.CATAPULT_RIGHT_ID, true);
  private final IntakeMotor m_intakeMotor = new IntakeMotor(Constants.INTAKE_DEPLOY_ID);
  private final UpperLowerIntake m_upperLowerIntake = new UpperLowerIntake(Constants.INTAKE_UPPERTALON_ID, Constants.INTAKE_LOWERTALON_ID);
  
  private final ClimberSubsystem m_climberRightSubsystem = new ClimberSubsystem(43, true); //FIX INPUTS
  private final ClimberSubsystem m_climberLeftSubsystem = new ClimberSubsystem(45, false);
  public RobotContainer() {
    m_drivetrainSubsystem.zeroGyroscope();
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
            m_drivetrainSubsystem,
            () -> -modifyAxis(m_controller.getLeftY()) * 1, // DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
            () -> -modifyAxis(m_controller.getLeftX()) * 1, //DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
            //() -> -modifyAxis(m_controller.getRightX()) * 2//DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
            () -> modifyAxis(m_controller.getLeftTriggerAxis() - m_controller.getRightTriggerAxis()) * 2 //DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));




    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} o
   * r {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Back button zeros the gyroscope
    // new Button(m_controller::getBackButton)
    //         // No requirements because we don't need to interrupt anything
    //         .whenPressed(m_drivetrainSubsystem::zeroGyroscope);
    

    JoystickButton buttonA = new JoystickButton(m_controller, XboxController.Button.kA.value);
    JoystickButton buttonY = new JoystickButton(m_controller, XboxController.Button.kY.value);
    JoystickButton back = new JoystickButton(m_controller, XboxController.Button.kBack.value);
    JoystickButton start = new JoystickButton(m_controller, XboxController.Button.kStart.value);
    JoystickButton buttonB = new JoystickButton(m_controller, XboxController.Button.kB.value);
    JoystickButton buttonX = new JoystickButton(m_controller, XboxController.Button.kX.value);
    

  //   start.whenPressed(m_drivetrainSubsystem::toggleDriveMode);
  //  buttonY.whenPressed(() -> m_drivetrainSubsystem.setWheelAngle(0));
  //   buttonA.whenPressed(() -> m_drivetrainSubsystem.changeWheelAngleBy45());
  //ROBOT DOWN
    buttonA.whenPressed(new ParallelClimber(m_climberLeftSubsystem, m_climberRightSubsystem, 23, 1));
  //ROBOT UP
    buttonB.whenPressed(new ParallelClimber(m_climberLeftSubsystem, m_climberRightSubsystem, -1.25, 1));
    buttonY.whenPressed(() -> m_drivetrainSubsystem.forcingZero());
    buttonX.whenPressed(() -> System.out.println(m_drivetrainSubsystem.getPose()));

    // SwervedDrive
    back.whenPressed(m_drivetrainSubsystem::zeroGyroscope);
    
    
  
    
  
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

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.2); //0.05, 0.1 seems to work


    return value;
  }
}
