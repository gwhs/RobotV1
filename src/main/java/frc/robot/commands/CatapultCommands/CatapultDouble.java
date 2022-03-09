package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.CatapultSubsystem;
import frc.robot.subsystems.IntakeMotors;

public class CatapultDouble extends ParallelCommandGroup {
    // private CatapultSubsystem m_CatapultSubsystemLeft;
    // private CatapultSubsystem m_CatapultSubsystemRight;
    private IntakeMotors m_IntakeMotors;
    private CatapultSubsystem m_CatapultSubsystemRight;
    private CatapultSubsystem m_CatapultSubsystemLeft;
    private double leftSpeed;
    private double rightSpeed;


    public CatapultDouble(CatapultSubsystem m_CatapultSubsystemLeft, CatapultSubsystem m_CatapultSubsystemRight, double leftSpeed, double rightSpeed, double delay) {
        // this.m_CatapultSubsystemLeft = m_CatapultSubsystemLeft;
        // this.m_CatapultSubsystemRight = m_CatapultSubsystemRight;
        addCommands(new CatapultLeft(m_CatapultSubsystemLeft, leftSpeed));
    }
    public void initialize(){

    }
    public void execute(){

    }

    public void end(boolean interrupted) {

    }
    
    public boolean isFinished() {
        return true;
    }
} 