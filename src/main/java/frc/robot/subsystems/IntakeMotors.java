package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ExternalFollower;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.swervedrivespecialties.swervelib.ctre.CanCoderAbsoluteConfiguration;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeMotors extends SubsystemBase{
    private TalonFX upperMotor;
    private TalonFX lowerMotor;
    private CANSparkMax leaderMotor;
    private CANSparkMax followerMotor;
    private double upperSpeed;
    private double lowerSpeed;
    private double neoSpeed;

    
    public IntakeMotors(int upperMotor, int lowerMotor, int leader, int follower, double upperSpeed, double lowerSpeed, double neoSpeed){
        this.upperMotor = new TalonFX(upperMotor);
        this.lowerMotor = new TalonFX(lowerMotor);
        this.leaderMotor = new CANSparkMax(leader, MotorType.kBrushless);
        this.followerMotor = new CANSparkMax(follower, MotorType.kBrushless);
        this.upperSpeed = upperSpeed;
        this.lowerSpeed = lowerSpeed;
        this.neoSpeed = neoSpeed;

        followerMotor.follow(ExternalFollower.kFollowerSparkMax, leader, true);
    }

    public void setSpeed(){
        upperMotor.set(ControlMode.PercentOutput, upperSpeed);
        lowerMotor.set(ControlMode.PercentOutput, lowerSpeed);
    }

    public void stop(){
        upperMotor.set(ControlMode.PercentOutput, 0);
        lowerMotor.set(ControlMode.PercentOutput, 0);
    }

    //deploy intake
    public void sendItOut(){
        
        leaderMotor.set(neoSpeed);
    }
    
    //undeploy intake
    public void takeItBack(){
        leaderMotor.set(-neoSpeed);
    }
}
