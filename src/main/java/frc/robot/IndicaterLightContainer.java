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
    private final IndicationLight light = new IndicationLight();
    private GenericHID m_controller;

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