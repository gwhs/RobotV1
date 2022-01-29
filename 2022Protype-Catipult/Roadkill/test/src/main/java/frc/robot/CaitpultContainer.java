// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



// REVIEW: this code controls the motors and lets the controller control the code. 
// in the constructor we take name the motor's id.

package frc.robot;
import frc.robot.SpinMotor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

/** Add your docs here. */
public class CaitpultContainer {
    XboxController mXbox;
    private Motor motor;

    public CaitpultContainer() {
        motor = new Motor(1);
        mXbox = new XboxController(0);
        configureButtonBindings();
        
    }
 
    private void configureButtonBindings() {
        JoystickButton buttonA = new JoystickButton(mXbox, XboxController.Button.kA.value);
        //shoots
        buttonA.whenPressed(new SpinMotor(motor, .86)); //.withTimeout(.35) after spinmotor

    }

    public Object getAutonomousCommand() {
        return null;
    }
}
