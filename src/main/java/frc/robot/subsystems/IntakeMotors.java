package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ExternalFollower;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.swervedrivespecialties.swervelib.ctre.CanCoderAbsoluteConfiguration;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeMotors extends SubsystemBase{
    private TalonFX upperMotor;
    private TalonFX lowerMotor;
    private CANSparkMax alphaMotor;
    private CANSparkMax betaMotor;
    private double upperSpeed;
    private double lowerSpeed;
    private double neoSpeed;

    
    public IntakeMotors(int upperMotorID, int lowerMotorID, int leaderID, int followerID, double upperSpeed, double lowerSpeed, double neoSpeed){
        this.upperMotor = new TalonFX(upperMotorID);
        this.lowerMotor = new TalonFX(lowerMotorID);
        this.alphaMotor = new CANSparkMax(leaderID, MotorType.kBrushless);
        this.betaMotor = new CANSparkMax(followerID, MotorType.kBrushless);
        this.upperSpeed = upperSpeed;
        this.lowerSpeed = lowerSpeed;
        this.neoSpeed = neoSpeed;

        betaMotor.follow(ExternalFollower.kFollowerSparkMax, leaderID, true);
    }

    public void suck(){
        upperMotor.set(ControlMode.PercentOutput, upperSpeed);
        lowerMotor.set(ControlMode.PercentOutput, lowerSpeed);
    }

    public void spit(){
        upperMotor.set(ControlMode.PercentOutput, -upperSpeed);
        lowerMotor.set(ControlMode.PercentOutput, lowerSpeed);
    }

    public void choke(){
        upperMotor.set(ControlMode.PercentOutput, 0);
        lowerMotor.set(ControlMode.PercentOutput, 0);
    }

    public double getAlphaPosition(){
        return alphaMotor.getEncoder().getPosition();
    }

    public double getBetaPosition(){
        return betaMotor.getEncoder().getPosition();
    }

    //deploy intake
    public void deploy(){
        //alphaMotor.setSoftLimit(SoftLimitDirection.kForward, 4000);
        alphaMotor.set(neoSpeed);
    }
    
    //undeploy intake
    public void undeploy(){
        //alphaMotor.setSoftLimit(SoftLimitDirection.kReverse, 4000);
        alphaMotor.set(-neoSpeed);
    }
}
