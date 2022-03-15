package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.ClimberSubsystem;


public class ParallelClimber extends ParallelCommandGroup{
    public ParallelClimber(ClimberSubsystem climberLeftSubsystem, ClimberSubsystem climberRightSubsystem, double distance, double speed){
        super(new ClimberCommand(climberLeftSubsystem, distance, speed), new ClimberCommand(climberRightSubsystem, distance, speed));
    }
}
