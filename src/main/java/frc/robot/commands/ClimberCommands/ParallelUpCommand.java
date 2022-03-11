package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.ClimberCommands.LeftClimbCommand;
import frc.robot.commands.ClimberCommands.RightClimbCommand;
import frc.robot.subsystems.ClimberLeftSubsystem;
import frc.robot.subsystems.ClimberRightSubsystem;

public class ParallelUpCommand extends ParallelCommandGroup{
    public ParallelUpCommand(ClimberLeftSubsystem climberLeftSubsystem, ClimberRightSubsystem climberRightSubsystem){
        super(new LeftClimbCommand(climberLeftSubsystem, 23), new RightClimbCommand(climberRightSubsystem, 23));
    }
}
