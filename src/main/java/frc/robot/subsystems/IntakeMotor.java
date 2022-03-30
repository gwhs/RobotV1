package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeMotor extends SubsystemBase{
    private TalonFX deployMotor;
    private static boolean deployed = false;
    private static final double DEPLOYED_TICKS = 22000;
    private static final double STOWED_TICKS = 0;
    
    public IntakeMotor(int deployMotorID){
        this.deployMotor = new TalonFX(deployMotorID);
        //deployMotor.setNeutralMode(NeutralMode.Brake);
        this.setZero();
    }

    public void setZero(){
        deployMotor.setSelectedSensorPosition(0);
    }

    public int isFWDLIMIT(){
        deployMotor.clearStickyFaults();
        //deployMotor.
        TalonFXSensorCollection info = deployMotor.getSensorCollection();
        return deployMotor.isFwdLimitSwitchClosed();
    }

    public boolean isFWDLIMITbool(){
        return isFWDLIMIT() == 1;
    }

    public boolean isREVLIMITbool(){
        return (isREVLIMIT() == 1);
    }

    public int isREVLIMIT(){
        deployMotor.clearStickyFaults();
        TalonFXSensorCollection info = deployMotor.getSensorCollection();
        return deployMotor.isRevLimitSwitchClosed();
    }

    public double isOtherData(){
        TalonFXSensorCollection info = deployMotor.getSensorCollection();
        return info.getIntegratedSensorAbsolutePosition();
    }

    public boolean isDeployed(){
        if(isFWDLIMIT() == 1){
            deployed = false;
          }
          else if (isREVLIMIT() == 1){
            deployed = true;

          }
          else{
            deployed = false;
          }
          return deployed;
        }

    public boolean isStowed(){
        double position = deployMotor.getSelectedSensorPosition();
        if(position <= STOWED_TICKS + 1000){
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


    public void setDeployMotorSpeed(double speed) {
        deployMotor.set(ControlMode.PercentOutput, speed);
    }


    //setAlphaPosition() sets perceived position to specified value "pos"
    //returns previous position as a double


    public void setBrakeMode()
    {
        deployMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void goToPosition(int ticks)
    {
        setZero();
        deployMotor.set(ControlMode.Position, ticks);
    }


    //deploy intake
    public void deploy(double speed){
        deployMotor.setSelectedSensorPosition(0);
        //alphaMotor.setSoftLimit(SoftLimitDirection.kForward, 4000);
        deployMotor.set(ControlMode.PercentOutput, speed);
    }

    public void stop(){
        deployMotor.set(ControlMode.PercentOutput, 0);
    }

    //undeploy intake
    public void undeploy(double speed){
        //alphaMotor.setSoftLimit(SoftLimitDirection.kReverse, 4000);
        deployMotor.set(ControlMode.PercentOutput, -speed);
    }
}
