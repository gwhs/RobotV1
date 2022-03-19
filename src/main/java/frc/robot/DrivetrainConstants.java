// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its in                                   ner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class DrivetrainConstants {
    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.570; // 22.25 inches
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.570; // 22.25 inches

    //public static final int DRIVETRAIN_PIGEON_ID = 30; 

    //offsets go to nearest 180 and inverts so they spin the same direction
    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 1; 
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 2; 
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 9; 
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(282.8); // FIXME Measure and set front left steer offset 141 48
                                                                        //280
    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 3; 
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 4; 
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 11; 
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(298.3); // FIXME Measure and set front right steer offset 235 130
                                                                                //299
    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 7; 
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 8; 
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 12; 
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(313.0); // FIXME Measure and set back left steer offset 136 134
                                                                                //313
    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 5; 
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 6; 
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 13; 
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(286.3); // FIXME Measure and set back right steer offset 37 340
}                                                                               //7.6 old correct: 13.6
