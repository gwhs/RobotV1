package frc.robot.utils;


public class Utilities {

    public static double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
          if (value > 0.0) {
            return (value - deadband) / (1.0 - deadband);
          } else {
            return (value + deadband) / (1.0 - deadband);
          }
        } else {
          return 0.0;
        }
      }
    
      public static double modifyAxis(double value) {
        // Deadband
        value = deadband(value, 0.2); //0.05, 0.1 seems to work
    
    
        return value;
      }   
}
