

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.ClimberCommands.AutoClimb;
import frc.robot.commands.ClimberCommands.ClimberCommand;
import frc.robot.commands.ClimberCommands.ExtendArm;
import frc.robot.commands.ClimberCommands.RetractArm;
import frc.robot.utils.TriggerSensing;
import frc.robot.utils.Utilities;

public class FinalContainer {
  private final ClimberSubsystem m_ClimberSubsytem = new ClimberSubsystem(43, 45); //FIX INPUTS
  private final XboxController m_controller = new XboxController(0);
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();

  

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
    JoystickButton buttonLTrigger = new JoystickButton(m_controller, XboxController.Button.kLeftBumper.value);
    JoystickButton buttonRTrigger = new JoystickButton(m_controller, XboxController.Button.kRightBumper.value);
    // JoystickButton button = new JoystickButton(m_controller, XboxController.k.value);

    //buttonX.whenPressed(command)
  }
  
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }
}
