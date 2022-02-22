package frc.robot.commands.CatapultCommands;

import javax.sound.midi.Sequencer;

import edu.wpi.first.math.interpolation.TimeInterpolatableBuffer;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.subsystems.CatapultSubsystem;

public class CatapultDouble extends SequentialCommandGroup {
    private CatapultSubsystem m_CatapultSubsystemLeft;
    private CatapultSubsystem m_CatapultSubsystemRight;


    public CatapultDouble(CatapultSubsystem m_CatapultSubsystemLeft, CatapultSubsystem m_CatapultSubsystemRight, int shooterMode) {
        this.m_CatapultSubsystemLeft = m_CatapultSubsystemLeft;
        this.m_CatapultSubsystemRight = m_CatapultSubsystemRight;
        if (shooterMode == Constants.SHOOTER_MODE_DOUBLE)        
        addCommands(
            new ParallelCommandGroup(new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED)),
            new CatapultCommand(m_CatapultSubsystemRight,Constants.CATAPULT_SPEED));
        else if (shooterMode == Constants.SHOOTER_MODE_DELAY) {
            new SequentialCommandGroup(new CatapultCommand(m_CatapultSubsystemLeft,Constants.CATAPULT_SPEED).withTimeout(0.1),
            new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED));
            }
        else if (shooterMode == Constants.SHOOTER_MODE_LOW_HIGH){
            new ParallelCommandGroup(new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED),
            new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_LOW));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_DOUBLE_LOW){
            new ParallelCommandGroup(new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED_LOW),
            new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_LOW));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_DUMP){
            new ParallelCommandGroup(new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED_DUMP),
            new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_DUMP));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_LEFT){
            new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED);
        }
        else if (shooterMode == Constants.SHOOTER_MODE_RIGHT){
            new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED);
        }
        else if (shooterMode == Constants.SHOOTER_MODE_LEFT_LOW){
            new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED_LOW);
        }
        else if (shooterMode == Constants.SHOOTER_MODE_RIGHT_LOW){
            new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_LOW);
        }
        else if (shooterMode == Constants.SHOOTER_MODE_LEFT_DUMP){
            new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED_DUMP);
        }
        else if (shooterMode == Constants.SHOOTER_MODE_RIGHT_DUMP){
            new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_DUMP);
        }
    }

}