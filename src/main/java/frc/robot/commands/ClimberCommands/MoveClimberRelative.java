package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimberRightSubsystem;

public class MoveClimberRelative extends SequentialCommandGroup{
    
    public MoveClimberRelative(ClimberRightSubsystem climberRightSubsystem, double distance){
        addCommands(new RightClimbCommand(climberRightSubsystem, climberRightSubsystem.ticksToInches(climberRightSubsystem.getRightArmPosition()) + distance));
    }
}
