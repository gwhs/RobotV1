package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.IntakeCommands.IntakeStowStop;
import frc.robot.commands.IntakeCommands.SpinIntake;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.IntakeMotor;
import frc.robot.subsystems.UpperLowerIntake;



public class OneCatapultIntake extends ParallelCommandGroup {
    public OneCatapultIntake(IntakeMotor m_intakeMotor, UpperLowerIntake m_upperLowerIntake, double deploySpeed, CatapultSubsystem m_CatapultSubsystem, double catapultSpeed) {
        addCommands(new SpinIntake(m_upperLowerIntake, 0, 0).withTimeout(1), 
                    new CatapultCommand(m_CatapultSubsystem, catapultSpeed).withTimeout(1));
    } 
}
