package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ClimberLeftSubsystem;
import frc.robot.subsystems.ClimberRightSubsystem;

public class ParallelbackCommand extends ParallelCommandGroup{
    public ParallelbackCommand(ClimberLeftSubsystem climberLeftSubsystem, ClimberRightSubsystem climberRightSubsystem){
        super(new LeftClimbCommand(climberLeftSubsystem, 18), new RightClimbCommand(climberRightSubsystem, 18));
    }
}
