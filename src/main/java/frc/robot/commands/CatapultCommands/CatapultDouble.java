package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.IntakeCommands.ToggleIntake;
import frc.robot.commands.IntakeCommands.ToggleIntakeCatapult;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.IntakeMotors;

public class CatapultDouble extends SequentialCommandGroup {
    // private CatapultSubsystem m_CatapultSubsystemLeft;
    // private CatapultSubsystem m_CatapultSubsystemRight;
    private IntakeMotors m_IntakeMotors;


    public CatapultDouble(CatapultSubsystem m_CatapultSubsystemLeft, CatapultSubsystem m_CatapultSubsystemRight, int shooterMode) {
        // this.m_CatapultSubsystemLeft = m_CatapultSubsystemLeft;
        // this.m_CatapultSubsystemRight = m_CatapultSubsystemRight;
        if (shooterMode == Constants.SHOOTER_MODE_DOUBLE)        
            addCommands(
                new ParallelCommandGroup(new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED),  
                                        new CatapultCommand(m_CatapultSubsystemRight,Constants.CATAPULT_SPEED)));
        else if (shooterMode == Constants.SHOOTER_MODE_DELAY) {
                addCommands(
                    new ParallelCommandGroup(new CatapultCommand(m_CatapultSubsystemLeft,Constants.CATAPULT_SPEED),
                                            new SequentialCommandGroup(new WaitCommand(2),
                                                    new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED))));
            }
        else if (shooterMode == Constants.SHOOTER_MODE_LOW_HIGH){
            addCommands(
            new ParallelCommandGroup(new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED),
                                        new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_LOW)));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_DOUBLE_LOW){
            addCommands(
            new ParallelCommandGroup(new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED_LOW),
                                    new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_LOW)));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_DUMP){
            addCommands(
            new ParallelCommandGroup(new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED_DUMP), 
                                    new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_DUMP)));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_LEFT){
            addCommands(
                    new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_RIGHT){
            addCommands(
                    new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_LEFT_LOW){
            addCommands(
                    new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED_LOW));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_RIGHT_LOW){
            addCommands(
                    new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_LOW));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_LEFT_DUMP){
            addCommands(
                    new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED_DUMP));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_RIGHT_DUMP){
            addCommands(
                    new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_DUMP));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_DOUBLE_FAR){
            addCommands(
                    new ParallelCommandGroup(new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED_FAR)),
                                    new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_FAR));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_LEFT_FAR){
            addCommands(
                        new CatapultCommand(m_CatapultSubsystemLeft, Constants.CATAPULT_SPEED_FAR));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_RIGHT_FAR){
            addCommands(
                        new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_FAR));
        }
        else if (shooterMode == Constants.SHOOTER_MODE_DOUBLE_FAR_DELAY){
            addCommands(
                new ParallelCommandGroup(
                        new CatapultCommand(m_CatapultSubsystemLeft,Constants.CATAPULT_SPEED_FAR),
                            new SequentialCommandGroup(new WaitCommand(3),
                                new CatapultCommand(m_CatapultSubsystemRight, Constants.CATAPULT_SPEED_FAR))));
        }
    }

}