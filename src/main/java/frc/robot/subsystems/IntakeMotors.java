package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeMotors extends SubsystemBase{
    private TalonFX upperMotor;
    private TalonFX lowerMotor;
    private TalonFX deployMotor;
    private static final double DEPLOYED_TICKS = 18470;
    private static final double STOWED_TICKS = 36399;
    
    public IntakeMotors(int deployMotorID, int upperMotorID, int lowerMotorID){
        this.upperMotor = new TalonFX(upperMotorID);
        this.lowerMotor = new TalonFX(lowerMotorID);
        this.deployMotor = new TalonFX(deployMotorID);
        this.setSoftLimits();
        this.setZero();
    }


    public void setZero(){
        deployMotor.setSelectedSensorPosition(0);
    }

    public boolean isDeployed(){
        double position = deployMotor.getSelectedSensorPosition();
        if(position <= DEPLOYED_TICKS){
            return true;
        }
        return false;
    }

    public boolean isStowed(){
        double position = deployMotor.getSelectedSensorPosition();
        if(position >= STOWED_TICKS){
            return true;
        }
        return false;
    }

    public boolean isMiddle(){
        double position = deployMotor.getSelectedSensorPosition();
        if(position > DEPLOYED_TICKS && position < STOWED_TICKS){
            return true;
        }
        return false;
    }


    public double getDeployPosition(){
        return deployMotor.getSelectedSensorPosition();
    }


    public void setIntakeMotorSpeeds(double upperSpeed, double lowerSpeed){
        upperMotor.set(ControlMode.PercentOutput, upperSpeed);
        lowerMotor.set(ControlMode.PercentOutput, lowerSpeed);
    }

    public void setDeployMotorSpeed(double speed) {
        deployMotor.set(ControlMode.PercentOutput, speed);
    }

    public void choke(){
        upperMotor.set(ControlMode.PercentOutput, 0);
        lowerMotor.set(ControlMode.PercentOutput, 0);
    }

    //setAlphaPosition() sets perceived position to specified value "pos"
    //returns previous position as a double

    public void setCoastMode()
    {
        deployMotor.setNeutralMode(NeutralMode.Coast);
    }

    public void setBrakeMode()
    {
        deployMotor.setNeutralMode(NeutralMode.Brake);
    }



    //deploy intake
    public void deploy(double speed){
        deployMotor.setSelectedSensorPosition(0);
        //alphaMotor.setSoftLimit(SoftLimitDirection.kForward, 4000);
        deployMotor.set(ControlMode.PercentOutput, speed);
    }

    public void stopDeploy(){
        deployMotor.set(ControlMode.PercentOutput, 0);
    }

    //undeploy intake
    public void undeploy(double speed){
        //alphaMotor.setSoftLimit(SoftLimitDirection.kReverse, 4000);
        deployMotor.set(ControlMode.PercentOutput, -speed);
    }

    public void setSoftLimits(){
        
    }
}
