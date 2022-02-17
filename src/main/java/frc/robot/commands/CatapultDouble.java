package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.CatapultSubsystem;

public class CatapultDouble extends SequentialCommandGroup {
    private CatapultSubsystem m_CatapultSubsystemLeft;
    private CatapultSubsystem m_CatapultSubsystemRight;


    public CatapultDouble(CatapultSubsystem m_CatapultSubsystemLeft, CatapultSubsystem m_CatapultSubsystemRight) {
        this.m_CatapultSubsystemLeft = m_CatapultSubsystemLeft;
        this.m_CatapultSubsystemRight = m_CatapultSubsystemRight;
        addCommands(
            new CatapultCommand(m_CatapultSubsystemLeft, 1).withTimeout(1),
            new CatapultCommand(m_CatapultSubsystemRight, 1).withTimeout(1)
        );
    }

}