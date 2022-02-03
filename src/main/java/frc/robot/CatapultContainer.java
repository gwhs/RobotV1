//this file is where the catapult is ran
//find the SpinMotor's to change id and speed

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.CatapultCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.CatapultMotor;


public class CatapultContainer{
    private final CatapultMotor motor = new CatapultMotor(1);
    private final CatapultMotor motor1 = new CatapultMotor(2);
    private final XboxController m_controller = new XboxController(0);

    public CatapultContainer(){

        configureButtonBindings();
    }

    public void configureButtonBindings(){
        JoystickButton buttonB = new JoystickButton(m_controller, XboxController.Button.kB.value);
        JoystickButton buttonX = new JoystickButton(m_controller, XboxController.Button.kX.value);

        // SpinMotor s
        // change motor and speed here. SpinMotor(motor id, percent output[-1 to 1 as double])
        buttonB.whenPressed(() -> new CatapultCommand(motor, .8));
        buttonX.whenPressed(() -> new CatapultCommand(motor1, .8));
    }

    public void printSomething(){
        System.out.println("Hellow there");
    }

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new InstantCommand(this::printSomething);
      }
}