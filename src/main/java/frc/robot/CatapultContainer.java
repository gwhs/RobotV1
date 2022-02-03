//this file is where the catapult is ran
//find the SpinMotor's to change id and speed

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.SpinMotor;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Motor;


public class CatapultContainer{
    private final Motor motor = new Motor(1);
    private final Motor motor1 = new Motor(2);
    private final XboxController m_controller = new XboxController(0);

    public CatapultContainer(){

        configureButtonBindings();
    }

    public void configureButtonBindings(){
        JoystickButton buttonB = new JoystickButton(m_controller, XboxController.Button.kB.value);
        JoystickButton buttonX = new JoystickButton(m_controller, XboxController.Button.kX.value);

        // SpinMotor s
        // change motor and speed here. SpinMotor(motor id, percent output[-1 to 1 as double])
        buttonB.whenPressed(() -> new SpinMotor(motor));
        buttonX.whenPressed(() -> new SpinMotor(motor1));
    }

    public void printSomething(){
        System.out.println("Hellow there");
    }

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new InstantCommand(this::printSomething);
      }
}