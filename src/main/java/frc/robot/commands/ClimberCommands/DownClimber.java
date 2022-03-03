package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;


public class DownClimber extends CommandBase{
    private ClimberSubsystem motors;

    public DownClimber(ClimberSubsystem motors){
        this.motors = motors;

        addRequirements(motors);
    }

      // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    motors.setSpeed(-.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    motors.setSpeed(0);
    System.out.println("done");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (motors.getRightArmPosition() < 5000){
      return true;
    }
    return false;
  }
}

