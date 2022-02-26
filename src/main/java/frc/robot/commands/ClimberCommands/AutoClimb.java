package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimberSubsystem;

public class AutoClimb extends SequentialCommandGroup {
    private ClimberSubsystem climberSubsystem;
    public AutoClimb(ClimberSubsystem climberSubsystem){
        this.climberSubsystem = climberSubsystem;

        //low ticks - 73k, mid ticks - 272.5k change this below
    
    addCommands(new ClimberCommand(climberSubsystem, 272500).withTimeout(5), 
    new RetractArm(climberSubsystem));
    
    }
}
