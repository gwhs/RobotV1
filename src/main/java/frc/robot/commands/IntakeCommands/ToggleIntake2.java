// package frc.robot.commands.IntakeCommands;

// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.IntakeMotors;

// public class ToggleIntake extends CommandBase{
//     private IntakeMotors motor;
//     private boolean deploying;
//     private double speed = .3;


//     public ToggleIntake(IntakeMotors motor){
//         this.motor = motor;
//         this.deploying = deploying;
//         motor.setBrakeMode();
//         addRequirements(motor);
//     }

//     @Override
//     public void initialize(){
//         double currentPosition = motor.getPosition();
//         System.out.println(motor.getPosition());
//         if (currentPosition <= 10){
//             deploying = true;
//             motor.undeploy(speed);
//             motor.choke();
//         } else if(currentPosition >= 1700){
//             deploying = false;
//             motor.deploy(speed);
//         } else {
//             deploying = false;
//             motor.undeploy(speed);
//         }


//     }


//     @Override
//     public void execute(){
//         System.out.println("Deploy motor Position: " + motor.getPosition());
//         System.out.println(".");


//     }

//     @Override
//     public void end(boolean interrupted){
//         System.out.println("ENDING INTAKE - DEPLOY");
//         motor.stopDeploy();
//     }

//     @Override
//     public boolean isFinished() {
//         // makes sure arm is at bottom and has shot before ending.
//         double currentPosition = motor.getPosition();
//         if (currentPosition >= 1700 && deploying == true){
//             motor.setIntakeMotorSpeeds(0, 0);
//             return true;
//         } else if (currentPosition <= 10 && deploying == false){
//             motor.choke();
//             return true;
//         }

//         return false;
//     }
// }