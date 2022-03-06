package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimberSubsystem;

public class AutoClimb extends SequentialCommandGroup {
    // private ClimberSubsystem climberSubsystem;
    public AutoClimb(ClimberSubsystem climberSubsystem){
        // this.climberSubsystem = climberSubsystem;

        //98304 ticks per 4.55 inches per 30 teeth for gear
        //98304 ticks : 1 gear rotation : 30 teeth : 4.55 inches
    
        // add a move backwards like 2 inches after extend
    addCommands(new ClimberCommand(climberSubsystem, 482000).withTimeout(5), 
    new RetractArm(climberSubsystem), new ExtendArm(climberSubsystem).withTimeout(5), new DownClimber(climberSubsystem));
    
    }
}
