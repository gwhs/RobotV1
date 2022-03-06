package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ShuffleboardUpdater;

public class UpdateContainer implements BaseContainer{
    ShuffleboardUpdater updater;
    XboxController m_controller;

    public UpdateContainer(){
        updater = new ShuffleboardUpdater();

        
    }
}