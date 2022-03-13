

// package frc.robot;


// import edu.wpi.first.wpilibj.GenericHID;
// import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
// import frc.robot.subsystems.CatapultSubsystem;
// import frc.robot.subsystems.ClimberSubsystem;
// import frc.robot.subsystems.DrivetrainSubsystem;
// import frc.robot.subsystems.IntakeMotor;
// import frc.robot.subsystems.LimelightPortal;
// import frc.robot.subsystems.TimeOfFlightRange;
// import frc.robot.subsystems.UpperLowerIntake;
// import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import frc.robot.commands.DefaultDriveCommand;
// import frc.robot.commands.AutoAlignCommands.AlignToFender;
// import frc.robot.commands.AutoAlignCommands.PrintLLandTOFDistance;
// import frc.robot.commands.AutoAlignCommands.TurnToZeroLimelight;
// import frc.robot.utils.Utilities;
// import frc.robot.commands.AutoMeter;

// public class FinalContainer implements BaseContainer{
//   private final XboxController m_controller1 = new XboxController(0);
//   private final XboxController m_controller2 = new XboxController(1);

//   //drivetrain
//   private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();

//   //climber
//   private final ClimberSubsystem m_climberRightSubsystem = new ClimberSubsystem(43, true); //FIX INPUTS
//   private final ClimberSubsystem m_climberLeftSubsystem = new ClimberSubsystem(45, false);

//   //catapult
//   private final CatapultSubsystem m_catapultSubsystemLeft = new CatapultSubsystem(Constants.CATAPULT_LEFT_ID, false);
//   private final CatapultSubsystem m_catapultSubsystemRight = new CatapultSubsystem(Constants.CATAPULT_RIGHT_ID, true);
  
//   //intake
//   private final IntakeMotor m_intakeMotor = new IntakeMotor(Constants.INTAKE_DEPLOY_ID);
//   private final UpperLowerIntake m_upperLowerIntake = new UpperLowerIntake(Constants.INTAKE_UPPERTALON_ID, Constants.INTAKE_LOWERTALON_ID);
//   private final IntakeMotor m_IntakeMotor = new IntakeMotor(Constants.INTAKE_DEPLOY_ID);

//   private final LimelightPortal ll = new LimelightPortal();
//   private final TimeOfFlightRange tof = new TimeOfFlightRange();
//   public FinalContainer() {



//     m_drivetrainSubsystem.zeroGyroscope();
//     // Set up the default command for the drivetrain.
//     // The controls are for field-oriented driving:
//     // Left stick Y axis -> forward and backwards movement
//     // Left stick X axis -> left and right movement
//     // Right stick X axis -> rotation
//     m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
//             m_drivetrainSubsystem,
//             () -> -Utilities.modifyAxis(m_controller1.getLeftY()) * 1, //DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
//             () -> -Utilities.modifyAxis(m_controller1.getLeftX()) * 1, //DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
//             //() -> -modifyAxis(m_controller1.getRightX()) * 2//DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
//             () -> Utilities.modifyAxis(m_controller1.getLeftTriggerAxis() - m_controller1.getRightTriggerAxis()) * 2 //DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
//     ));

//     configureButtonBindings();
//   };


//   /**
//    * Use this method to define your button->command mappings. Buttons can be created by
//    * instantiating a {@link GenericHID} or one of its subclasses ({@link
//    * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
//    * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
//    */
//   private void configureButtonBindings() {
//     JoystickButton buttonX = new JoystickButton(m_controller1, XboxController.Button.kX.value);
//     JoystickButton buttonB = new JoystickButton(m_controller1, XboxController.Button.kB.value);
//     JoystickButton buttonA = new JoystickButton(m_controller1, XboxController.Button.kA.value);
//     JoystickButton buttonY = new JoystickButton(m_controller1, XboxController.Button.kY.value);
//     JoystickButton buttonLBumper = new JoystickButton(m_controller1, XboxController.Button.kLeftBumper.value);
//     JoystickButton buttonRBumper = new JoystickButton(m_controller1, XboxController.Button.kRightBumper.value);
//     JoystickButton buttonBack = new JoystickButton(m_controller1, XboxController.Button.kBack.value);
//     JoystickButton buttonLeftJoystickButton = new JoystickButton(m_controller1, XboxController.Button.kLeftStick.value);
//     JoystickButton buttonRightJoystickButton = new JoystickButton(m_controller1, XboxController.Button.kRightStick.value);

//     JoystickButton buttonX2 = new JoystickButton(m_controller2, XboxController.Button.kX.value);
//     JoystickButton buttonB2 = new JoystickButton(m_controller2, XboxController.Button.kB.value);
//     JoystickButton buttonA2 = new JoystickButton(m_controller2, XboxController.Button.kA.value);
//     JoystickButton buttonY2 = new JoystickButton(m_controller2, XboxController.Button.kY.value);
//     JoystickButton buttonLBumper2 = new JoystickButton(m_controller2, XboxController.Button.kLeftBumper.value);
//     JoystickButton buttonRBumper2 = new JoystickButton(m_controller2, XboxController.Button.kRightBumper.value);
//     JoystickButton buttonBack2 = new JoystickButton(m_controller2, XboxController.Button.kBack.value);
//     JoystickButton buttonLeftJoystickButton2 = new JoystickButton(m_controller2, XboxController.Button.kLeftStick.value);
//     JoystickButton buttonRightJoystickButton2 = new JoystickButton(m_controller2, XboxController.Button.kRightStick.value);

//     // JoystickButton button = new JoystickButton(m_controller1, XboxController.k.value);
//     buttonBack.whenPressed(m_drivetrainSubsystem::zeroGyroscope);
//     // buttonX.whenPressed(new CatapultDouble(m_CatapultLeftSubsystem, m_CatapultRightSubsystem, 2));
//     // buttonB.whenPressed();
//     // buttonA.whenPressed(new CatapultDouble(m_CatapultLeftSubsystem, m_CatapultRightSubsystem,1 ));
//     // buttonY.whenPressed(new CatapultDouble(m_CatapultLeftSubsystem, m_CatapultRightSubsystem, 2));
//     // buttonRBumper.whenPressed(new SpinIntake(m_IntakeMotor, 0.1, 0.1));
//     // buttonLBumper.whenPressed(new ToggleIntake(m_IntakeMotor));
//     // buttonRBumper.whenPressed(new Spit(m_IntakeMotor));
//     buttonLeftJoystickButton.whenPressed(new AutoMeter(m_drivetrainSubsystem, m_CatapultLeftSubsystem, m_CatapultLeftSubsystem, m_IntakeMotor));
//    // buttonRightJoystickButton.whenPressed(new AutoCommand(m_drivetrainSubsystem));


//     // limelight and tof testing
//     // TODO: use final button configuration
//     buttonB.whenPressed(new TurnToZeroLimelight(0, m_drivetrainSubsystem, ll));
//     // buttonB.whenPressed(new GoToDistanceTimeOfFlight(6, m_drivetrainSubsystem, tof));
//     buttonA.whenPressed(new PrintLLandTOFDistance());
//     buttonX.whenPressed(new TurnToZeroLimelight(0, m_drivetrainSubsystem, ll).withTimeout(0.75));
//     buttonY.whenPressed(new AlignToFender(m_drivetrainSubsystem, ll, tof, m_CatapultRightSubsystem));


//     buttonA2.whenPressed();
//     buttonB2.whenPressed();
//     buttonX2.whenPressed();
//     buttonY2.whenPressed();
//     buttonLBumper2.whenPressed();
//     buttonRBumber2.whenPressed();


//   }
// /*
//     * Use this to pass the autonomous command to the main {@link Robot} class.
//     *
//     * @return the command to run in autonomous
// */

//   public Command getAutonomousCommand() {
//     // An ExampleCommand will run in autonomous
//     return new InstantCommand();
//   }
// }
