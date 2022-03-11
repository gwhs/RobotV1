package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.CatapultSubsystem;


public class CatapultDouble extends ParallelCommandGroup {
    public CatapultDouble(CatapultSubsystem m_CatapultSubsystemLeft, CatapultSubsystem m_CatapultSubsystemRight, double leftSpeed, double rightSpeed, double delay) {
        addCommands(new CatapultCommand(m_CatapultSubsystemLeft, leftSpeed),
                    new SequentialCommandGroup(new WaitCommand(delay),
                    new CatapultCommand(m_CatapultSubsystemRight, rightSpeed)));
    } 
}
