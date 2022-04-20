package frc.robot.commands.CatapultCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.CatapultSubsystem;


public class ChangePower extends CommandBase{
    private double change;
    private CatapultSubsystem leftCatapult;
    private CatapultSubsystem rightCatapult;

    public ChangePower(double change, CatapultSubsystem leftCatapult, CatapultSubsystem rightCatapult){
        this.change = change;
        this.leftCatapult = leftCatapult;
        this.rightCatapult = rightCatapult;
    }

    public void initialize(){
        leftCatapult.changePower(change);
        rightCatapult.changePower(change);
        //System.out.println("changed power by " + change);
        //System.out.println("left" + (leftCatapult.getPower() + Constants.CATAPULT_SPEED_LOW));
        //System.out.println("right" + (rightCatapult.getPower() + Constants.CATAPULT_SPEED_LOW));
    }
    
    public void execute(){

    }

    public void end(){
        //System.out.println("dome");
    }

    public boolean isFinished(){
        return true;
    }
}
