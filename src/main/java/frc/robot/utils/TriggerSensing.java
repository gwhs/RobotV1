package frc.robot.utils;
import edu.wpi.first.wpilibj.GenericHID;
import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;


public class TriggerSensing {
    private GenericHID genericHID;
    private int position;

    public TriggerSensing(GenericHID genericHID, int position){
        requireNonNullParam(genericHID, "joystick", "TriggerAxisButton");
        this.genericHID = genericHID;
        this.position = position;
        this.get();
    }
    public boolean get() {
        return Math.abs(genericHID.getRawAxis(position)) > 0.2;
        }
    
}
