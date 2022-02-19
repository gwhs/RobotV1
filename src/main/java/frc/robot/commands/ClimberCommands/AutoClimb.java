package frc.robot.commands.ClimberCommands;

import javax.sound.midi.Sequencer;

import edu.wpi.first.math.interpolation.TimeInterpolatableBuffer;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.CatapultContainer;
import frc.robot.Constants;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.ClimberSubsystem;

public class AutoClimb extends SequentialCommandGroup {
    private ClimberSubsystem climberSubsystem;
    public AutoClimb(ClimberSubsystem climberSubsystem, int climberMode){
        this.climberSubsystem = climberSubsystem;

        double low_tick = 73000;
        double mid_tick = 272500; 
    if(climberMode == 1)
    addCommands(new ClimberCommand(climberSubsystem, low_tick).withTimeout(5), 
    new RetractArm(climberSubsystem));
    else if (climberMode == 2) {
        new ClimberCommand(climberSubsystem, mid_tick).withTimeout(5);
    };
    }
}
