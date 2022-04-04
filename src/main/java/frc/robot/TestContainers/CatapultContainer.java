//this file is where the catapult is ran
//find the SpinMotor's to change id and speed

package frc.robot.TestContainers;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.BaseContainer;
import frc.robot.Constants;
import frc.robot.commands.CatapultCommands.CatapultDouble;
import frc.robot.commands.CatapultCommands.CatapultLeft;
import frc.robot.commands.CatapultCommands.CatapultRight;
import frc.robot.commands.CatapultCommands.ChangePower;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.CatapultSubsystem;



public class CatapultContainer implements BaseContainer{
    private final XboxController m_controller = new XboxController(0);
    private final CatapultSubsystem m_CatapultSubsystemLeft = new CatapultSubsystem(Constants.CATAPULT_LEFT_ID, false, Constants.CATAPULT_LEFT_SHOOT_LIMIT);
    private final CatapultSubsystem m_CatapultSubsystemRight = new CatapultSubsystem(Constants.CATAPULT_RIGHT_ID, true, Constants.CATAPULT_RIGHT_SHOOT_LIMIT);

    public CatapultContainer(){

        configureButtonBindings();
    }

    public void configureButtonBindings(){
        JoystickButton buttonB = new JoystickButton(m_controller, XboxController.Button.kB.value);
        JoystickButton buttonX = new JoystickButton(m_controller, XboxController.Button.kX.value);
        JoystickButton buttonA = new JoystickButton(m_controller, XboxController.Button.kA.value);
        JoystickButton buttonLBumper = new JoystickButton(m_controller, XboxController.Button.kLeftBumper.value);
        JoystickButton buttonRBumper = new JoystickButton(m_controller, XboxController.Button.kRightBumper.value);
        // SpinMotor s
        // change motor and speed here. SpinMotor(motor id, percent output[-1 to 1 as double])
        // buttonB.whenPressed( new CatapultDouble(m_CatapultSubsystemLeft,m_CatapultSubsystemRight, Constants.SHOOTER_MODE_LEFT));
        // buttonX.whenPressed( new CatapultDouble(m_CatapultSubsystemLeft,m_CatapultSubsystemRight, Constants.SHOOTER_MODE_RIGHT));
        // buttonA.whenPressed( new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.SHOOTER_MODE_DELAY));

        buttonB.whenPressed(new CatapultLeft(m_CatapultSubsystemLeft, Constants.CATAPULT_LEFT_SPEED));
        buttonX.whenPressed(new CatapultRight(m_CatapultSubsystemRight, 0.5));
        buttonA.whenPressed(new CatapultDouble(m_CatapultSubsystemLeft, m_CatapultSubsystemRight, Constants.CATAPULT_LEFT_SPEED, Constants.CATAPULT_RIGHT_SPEED, Constants.CATAPULT_DELAY));
        buttonRBumper.whenPressed(new ChangePower(.01, m_CatapultSubsystemLeft, m_CatapultSubsystemRight));
        buttonLBumper.whenPressed(new ChangePower(-.01, m_CatapultSubsystemLeft, m_CatapultSubsystemRight));

    }

    public void printSomething(){

    }

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new InstantCommand(this::printSomething);

      }
}