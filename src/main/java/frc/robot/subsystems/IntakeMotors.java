package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ExternalFollower;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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

        this.setConversionFactor();
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

    public void setConversionFactor(){
        alphaMotor.getEncoder().setPositionConversionFactor(2048);
        betaMotor.getEncoder().setPositionConversionFactor(2048);
    }

    public void choke(){
        upperMotor.set(ControlMode.PercentOutput, 0);
        lowerMotor.set(ControlMode.PercentOutput, 0);
    }

    //setAlphaPosition() sets perceived position to specified value "pos"
    //returns previous position as a double

    public void setCoastMode()
    {
        alphaMotor.setIdleMode(IdleMode.kCoast);
        betaMotor.setIdleMode(IdleMode.kCoast);
    }

    public void setBrakeMode()
    {
        alphaMotor.setIdleMode(IdleMode.kBrake);
        betaMotor.setIdleMode(IdleMode.kBrake);
    }

    public double setAlphaPosition(double pos){
        double prevPos = this.getAlphaPosition();
        alphaMotor.getEncoder().setPosition(pos);
        return prevPos;
    }

    public double getAlphaPosition(){
        return alphaMotor.getEncoder().getPosition();
    }

    public double getBetaPosition(){
        return betaMotor.getEncoder().getPosition();
    }

    //deploy intake
    public void deploy(){
        this.setAlphaPosition(0);
        //alphaMotor.setSoftLimit(SoftLimitDirection.kForward, 4000);
        alphaMotor.set(neoSpeed);
    }

    public void stopDeploy(){
        alphaMotor.set(0);
    }

    //undeploy intake
    public void undeploy(){
        //alphaMotor.setSoftLimit(SoftLimitDirection.kReverse, 4000);
        alphaMotor.set(-neoSpeed);
    }
}
