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
    public AutoClimb(ClimberSubsystem climberSubsystem){
        this.climberSubsystem = climberSubsystem;

        //low ticks - 73k, mid ticks - 272k change this below
    
    addCommands(new ClimberCommand(climberSubsystem, 272500).withTimeout(5), 
    new RetractArm(climberSubsystem));
    
    }
}
