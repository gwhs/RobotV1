package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.IntakeMotors;

public class CatapultDouble extends ParallelCommandGroup {
    // private CatapultSubsystem m_CatapultSubsystemLeft;
    // private CatapultSubsystem m_CatapultSubsystemRight;

    public CatapultDouble(CatapultSubsystem m_CatapultSubsystemLeft, CatapultSubsystem m_CatapultSubsystemRight, double leftSpeed, double rightSpeed, double delay) {
        // this.m_CatapultSubsystemLeft = m_CatapultSubsystemLeft;
        // this.m_CatapultSubsystemRight = m_CatapultSubsystemRight;
        addCommands(new CatapultLeft(m_CatapultSubsystemLeft, leftSpeed),
                        new SequentialCommandGroup(new WaitCommand(delay),
                            new CatapultRight(m_CatapultSubsystemRight, rightSpeed)));
    } 
}