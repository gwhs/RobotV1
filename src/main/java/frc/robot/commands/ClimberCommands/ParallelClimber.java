package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.ClimberCommands.ClimberCommand;
import frc.robot.commands.ClimberCommands.ClimberCommand;
import frc.robot.subsystems.ClimberSubsystem;


public class ParallelClimber extends ParallelCommandGroup{
    public ParallelClimber(ClimberSubsystem climberLeftSubsystem, ClimberSubsystem climberRightSubsystem, double distance){
        super(new ClimberCommand(climberLeftSubsystem, distance), new ClimberCommand(climberRightSubsystem, distance));
    }
}
