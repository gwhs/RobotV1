

package frc.robot;



import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeMotors;
import frc.robot.subsystems.LimelightPortal;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.AutoAlignCommands.AlignAndMoveToLimelight;
import frc.robot.commands.AutoAlignCommands.GoToDistanceLimelight;
import frc.robot.commands.AutoAlignCommands.PrintLimelightDistance;
import frc.robot.commands.AutoAlignCommands.TurnToZeroLimelight;
import frc.robot.commands.CatapultCommands.CatapultDouble;
import frc.robot.commands.IntakeCommands.SpinIntake;
import frc.robot.utils.Utilities;
import frc.robot.commands.AutoMeter;
import frc.robot.commands.AutoCommand;

public class FinalContainer implements BaseContainer{
  private final XboxController m_controller = new XboxController(0);
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();

  private final ClimberSubsystem m_ClimberSubsytem = new ClimberSubsystem(43, 45); //FIX INPUTS
  private final CatapultSubsystem m_CatapultLeftSubsystem = new CatapultSubsystem(1);
  private final CatapultSubsystem m_CatapultRightSubsystem = new CatapultSubsystem(21);
  private final IntakeMotors m_IntakeMotors = new IntakeMotors(Constants.INTAKE_DEPLOY_ID,Constants.INTAKE_UPPERTALON_ID, Constants.INTAKE_LOWERTALON_ID);

  private final LimelightPortal ll = new LimelightPortal();
  public FinalContainer() {



    m_drivetrainSubsystem.zeroGyroscope();
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
            m_drivetrainSubsystem,
            () -> -Utilities.modifyAxis(m_controller.getLeftY()) * 1, //DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
            () -> -Utilities.modifyAxis(m_controller.getLeftX()) * 1, //DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND, //1
            //() -> -modifyAxis(m_controller.getRightX()) * 2//DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
            () -> Utilities.modifyAxis(m_controller.getLeftTriggerAxis() - m_controller.getRightTriggerAxis()) * 2 //DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));

    configureButtonBindings();
  };


  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton buttonX = new JoystickButton(m_controller, XboxController.Button.kX.value);
    JoystickButton buttonB = new JoystickButton(m_controller, XboxController.Button.kB.value);
    JoystickButton buttonA = new JoystickButton(m_controller, XboxController.Button.kA.value);
    JoystickButton buttonY = new JoystickButton(m_controller, XboxController.Button.kY.value);
    JoystickButton buttonLBumper = new JoystickButton(m_controller, XboxController.Button.kLeftBumper.value);
    JoystickButton buttonRBumper = new JoystickButton(m_controller, XboxController.Button.kRightBumper.value);
    JoystickButton buttonBack = new JoystickButton(m_controller, XboxController.Button.kBack.value);
    JoystickButton buttonLeftJoystickButton = new JoystickButton(m_controller, XboxController.Button.kLeftStick.value);
    JoystickButton buttonRightJoystickButton = new JoystickButton(m_controller, XboxController.Button.kRightStick.value);

    // JoystickButton button = new JoystickButton(m_controller, XboxController.k.value);
    buttonBack.whenPressed(m_drivetrainSubsystem::zeroGyroscope);
    // buttonX.whenPressed(new CatapultDouble(m_CatapultLeftSubsystem, m_CatapultRightSubsystem, 2));
    //buttonB.whenPressed();
    buttonA.whenPressed(new CatapultDouble(m_CatapultLeftSubsystem, m_CatapultRightSubsystem,1 ));
    buttonY.whenPressed(new CatapultDouble(m_CatapultLeftSubsystem, m_CatapultRightSubsystem, 2));
    buttonRBumper.whenPressed(new SpinIntake(m_IntakeMotors, 0.1, 0.1));
    // buttonLBumper.whenPressed(new ToggleIntake(m_IntakeMotors));
    // buttonRBumper.whenPressed(new Spit(m_IntakeMotors));
    buttonLeftJoystickButton.whenPressed(new AutoMeter(m_drivetrainSubsystem, m_CatapultLeftSubsystem, m_CatapultLeftSubsystem, m_IntakeMotors));
    buttonRightJoystickButton.whenPressed(new AutoCommand(m_drivetrainSubsystem));


    // limelight commands
    // buttonB.whenPressed(new GoToDistanceLimelight(70, m_drivetrainSubsystem, ll));
    // buttonA.whenPressed(new SwitchLimelightStream());
    // buttonX.whenPressed(new TurnToZeroLimelight(0, m_drivetrainSubsystem, ll));
    // buttonY.whenPressed(new AlignAndMoveToLimelight(60, m_drivetrainSubsystem, ll));

    
  }
/*
    * Use this to pass the autonomous command to the main {@link Robot} class.
    *
    * @return the command to run in autonomous
*/
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }
}
