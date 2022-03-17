package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeCommands.IntakeStowStop;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.UpperLowerIntake;



public class CatapultLeftIntake extends ParallelCommandGroup {
    public CatapultLeftIntake(IntakeMotor m_intakeMotor, UpperLowerIntake m_upperLowerIntake, double deploySpeed, CatapultSubsystem m_CatapultSubsystem1, double leftSpeed) {
        addCommands(new IntakeStowStop(m_upperLowerIntake, m_intakeMotor, deploySpeed), 
                    new CatapultCommand(m_CatapultSubsystem1, leftSpeed).withTimeout(1));
    } 
}
