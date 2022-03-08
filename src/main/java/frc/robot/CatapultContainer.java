//this file is where the catapult is ran
//find the SpinMotor's to change id and speed

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.CatapultCommands.CatapultCommand;
import frc.robot.commands.CatapultCommands.CatapultDouble;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.CatapultSubsystem;


public class CatapultContainer implements BaseContainer{
    private final CatapultSubsystem motor = new CatapultSubsystem(21);
    private final XboxController m_controller = new XboxController(0);
    private final CatapultSubsystem m_CatapultSubsystemLeft = new CatapultSubsystem(Constants.CATAPULT_LEFT_ID);
    private final CatapultSubsystem m_CatapultSubsystemRight = new CatapultSubsystem(Constants.CATAPULT_RIGHT_ID);

    public CatapultContainer(){

        configureButtonBindings();
    }

    public void configureButtonBindings(){
        JoystickButton buttonB = new JoystickButton(m_controller, XboxController.Button.kB.value);
        JoystickButton buttonX = new JoystickButton(m_controller, XboxController.Button.kX.value);
        JoystickButton buttonA = new JoystickButton(m_controller, XboxController.Button.kA.value);
        // SpinMotor s
        // change motor and speed here. SpinMotor(motor id, percent output[-1 to 1 as double])
        buttonB.whenPressed( new CatapultDouble(m_CatapultSubsystemLeft,m_CatapultSubsystemRight, Constants.SHOOTER_MODE_LEFT));
        buttonX.whenPressed( new CatapultDouble(m_CatapultSubsystemLeft,m_CatapultSubsystemRight, Constants.SHOOTER_MODE_RIGHT));
        buttonA.whenPressed( new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_DELAY));


    }

    public void printSomething(){
        System.out.println("Hellow there");
    }

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new InstantCommand(this::printSomething);
      }
}