package frc.robot.commands.ClimberCommands;

import javax.sound.midi.Sequencer;

import edu.wpi.first.math.interpolation.TimeInterpolatableBuffer;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimberSubsystem;

public class AutoClimbLow extends SequentialCommandGroup {
    private ClimberSubsystem climberSubsystem;

    public AutoClimbLow(ClimberSubsystem climberSubsystem){
        this.climberSubsystem = climberSubsystem;
    
    addCommands(new ClimberCommand(climberSubsystem).withTimeout(5), new RetractArm(climberSubsystem).withTimeout(2), new ExtendArm(climberSubsystem));
    }
}
