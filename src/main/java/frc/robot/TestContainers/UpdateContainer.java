package frc.robot.TestContainers;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.BaseContainer;
import frc.robot.commands.ShuffleboardUpdater;

public class UpdateContainer implements BaseContainer{
    ShuffleboardUpdater updater;
    XboxController m_controller;

    public UpdateContainer(){
        updater = new ShuffleboardUpdater();

        
    }
}