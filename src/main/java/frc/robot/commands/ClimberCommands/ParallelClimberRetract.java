package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimberSubsystem;


public class ParallelClimberRetract extends SequentialCommandGroup{
    public ParallelClimberRetract(ClimberSubsystem climberLeftSubsystem, ClimberSubsystem climberRightSubsystem, double distance1, double distance2, double speed1, double speed2){
        super(
                new ParallelCommandGroup(new ClimberCommand(climberLeftSubsystem, distance1, speed1), 
                                         new ClimberCommand(climberRightSubsystem, distance1, speed1)),
                new ParallelCommandGroup(new ClimberCommand(climberLeftSubsystem, distance2, speed2), 
                                         new ClimberCommand(climberRightSubsystem, distance2, speed2))
            );
    }
}
