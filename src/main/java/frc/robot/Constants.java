package frc.robot;

public class Constants {
    // for the catapults, speed is -1.0 to 1.0 as decimal
    public static final int CATAPULT_LEFT_ID = 37;
    public static final int CATAPULT_RIGHT_ID = 21;
    public static double CATAPULT_SPEED = .35;
    public static double CATAPULT_SPEED_LOW = 0.05;
    public static double CATAPULT_SPEED_DUMP = 0.015;
    public static double CATAPULT_SPEED_FAR = 1;


    public static final int INTAKE_UPPERTALON_ID = 55;
    public static final int INTAKE_LOWERTALON_ID = 47;
    public static final int INTAKE_DEPLOY_ID = 31;

    public static final double INTAKE_SPEED_TALON1 = .4;
    public static final double INTAKE_SPEED_TALON2 = -.2;
    public static final double INTAKE_DEPLOY_SPEED = .1;


    //Climbing Modes

    public static final int CLIMBER_MODE_LOW = 1;
    public static final int CLIMBER_MODE_MID = 2;

    //SHOOTER MODES

    public static final int SHOOTER_MODE_DOUBLE = 1;
    public static final int SHOOTER_MODE_DELAY = 2;
    public static final int SHOOTER_MODE_LOW_HIGH = 3;
    public static final int SHOOTER_MODE_DOUBLE_LOW = 4;
    public static final int SHOOTER_MODE_DUMP = 5;
    public static final int SHOOTER_MODE_LEFT = 6;
    public static final int SHOOTER_MODE_RIGHT = 7;
    public static final int SHOOTER_MODE_LEFT_LOW = 8;
    public static final int SHOOTER_MODE_RIGHT_LOW =9;
    public static final int SHOOTER_MODE_LEFT_DUMP = 10;
    public static final int SHOOTER_MODE_RIGHT_DUMP = 11;
    public static final int SHOOTER_MODE_DOUBLE_FAR = 12;
    public static final int SHOOTER_MODE_RIGHT_FAR = 13;
    public static final int SHOOTER_MODE_LEFT_FAR = 14;
    public static final int SHOOTER_MODE_DOUBLE_FAR_DELAY = 15;

    //constants added for limelight
    public static final double LL_HEIGHT = 29.5; //needs to be changed to v1 measurements
    public static final double LL_MOUNT_ANGLE = 45;
    public static final double VISION_TARGET_HEIGHT = 104;
    
    public static final double MAX_ANGLE_VELOCITY = 90;
    public static final double MAX_ANGLE_ACCELERATION = 180;

    public static final double MAX_DISTANCE_VELOCITY = 96; //24 inches per second
    public static final double MAX_DISTANCE_ACCELERATION = 96;
    public static final double DISTANCE_TOLERANCE = 3;  //inches
    public static final double DISTANCE_PID_P = 0.03;
    public static final double DISTANCE_PID_I = 0.001;
    public static final double DISTANCE_PID_D = 0.00237;

    public static final double TURN_TOLERANCE = 0.5;
    public static final double ANGLE_PID_P = 0.2;
    public static final double ANGLE_PID_I = 0.00;  //dont change this number
    public static final double ANGLE_PID_D = 0.008;

    public static final int STAGE_1_DISTANCE = 50;
    public static final int STAGE_2_DISTANCE = 20;
    public static final int STAGE_3_DISTANCE = 10;
    public static final int STAGE_4_DISTANCE = 5;
    
    public static final double STAGE_1_SPEED = 1.0;
    public static final double STAGE_2_SPEED = 0.8;
    public static final double STAGE_3_SPEED = 0.5;
    public static final double STAGE_4_SPEED = 0.3;
    public static final double STAGE_5_SPEED = 0.1;

    public static final int TURN_CAP = 30;

}
