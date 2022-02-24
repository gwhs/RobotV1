//this file is where the catapult is ran
//find the SpinMotor's to change id and speed

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.CatapultCommands.CatapultCommand;
import frc.robot.commands.CatapultCommands.CatapultDouble;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.IndicationLight;


public class IndicaterLightContainer{
<<<<<<< HEAD
    private final IndicationLight light = new IndicationLight();
    private GenericHID m_controller;
=======
    private final CatapultSubsystem motor = new CatapultSubsystem(21);
    private final XboxController m_controller = new XboxController(0);
    private final CatapultSubsystem m_CatapultSubsystemLeft = new CatapultSubsystem(14);
    private final CatapultSubsystem m_CatapultSubsystemRight = new CatapultSubsystem(21);
>>>>>>> fb5992bc874e6941ab5e40bf6038b855834c9a3b

    public IndicaterLightContainer(){

        configureButtonBindings();
    }

    public void configureButtonBindings(){
        JoystickButton buttonB = new JoystickButton(m_controller, XboxController.Button.kB.value);
        JoystickButton buttonX = new JoystickButton(m_controller, XboxController.Button.kX.value);
        JoystickButton buttonA = new JoystickButton(m_controller, XboxController.Button.kA.value);

    }

    public void printSomething(){
        System.out.println("Hellow there");
    }

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new InstantCommand(this::printSomething);
      }
}