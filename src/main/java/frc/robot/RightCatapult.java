package frc.robot;

//this file is where the catapult is ran
//find the SpinMotor's to change id and speed




import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.SpinMotor;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Motor;


public class RightCatapult{
    private final Motor motor = new Motor(2);
    private final XboxController m_controller = new XboxController(0);

    public RightCatapult(){

        configureButtonBindings();
    }

    public void configureButtonBindings(){
        
        JoystickButton buttonX = new JoystickButton(m_controller, XboxController.Button.kX.value);

        // SpinMotor s
        // change motor and speed here. SpinMotor(motor id, percent output[-1 to 1 as double])
        buttonX.whenPressed(() -> new SpinMotor(motor, .9));
        
    }

    public Object getAutonomousCommand() {
        return null;
    }
}

