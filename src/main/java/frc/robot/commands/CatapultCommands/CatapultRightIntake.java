package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeCommands.IntakeStowStop;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.UpperLowerIntake;



public class CatapultRightIntake extends ParallelCommandGroup {
    public CatapultRightIntake(IntakeMotor m_intakeMotor, UpperLowerIntake m_upperLowerIntake, double deploySpeed, CatapultSubsystem m_CatapultSubsystem1, double rightSpeed) {
        addCommands(new IntakeStowStop(m_upperLowerIntake, m_intakeMotor, deploySpeed).withTimeout(1), 
                    new CatapultCommand(m_CatapultSubsystem1, rightSpeed).withTimeout(1));
    } 
}
