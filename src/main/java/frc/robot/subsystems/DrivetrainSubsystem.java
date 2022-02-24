// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import com.ctre.phoenix.sensors.PigeonIMU;
import frc.robot.DrivetrainConstants;

import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
//import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper;
//import com.swervedrivespecialties.swervelib.Mk4SwerveModuleHelper;
//import com.swervedrivespecialties.swervelib.Mk4iSwerveModuleHelper;
import frc.swervelib.Mk4iSwerveModuleHelper;
//import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import frc.swervelib.SdsModuleConfigurations;
//import com.swervedrivespecialties.swervelib.SwerveModule;
import frc.swervelib.SwerveModule;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {
  /**
   * The maximum voltage that will be delivered to the drive motors.
   * <p>A
   * This can be reduced to cap the robot's maximum speed. Typically, this is useful during initial testing of the robot.
   */
  public static final double MAX_VOLTAGE = 12.0;
  // FIXME Measure the drivetrain's maximum velocity or calculate the theoretical.
  //  The formula for calculating the theoretical maximum velocity is:
  //   <Motor free speed RPM> / 60 * <Drive reduction> * <Wheel diameter meters> * pi
  //  By default this value is setup for a Mk3 standard module using Falcon500s to drive.
  //  An example of this constant for a Mk4 L2 module with NEOs to drive is:
  //   5880.0 / 60.0 / SdsModuleConfigurations.MK4_L2.getDriveReduction() * SdsModuleConfigurations.MK4_L2.getWheelDiameter() * Math.PI
  /**
   * The maximum velocity of the robot in meters per second.
   * <p>
   * This is a measure of how fast the robot should be able to drive in a straight line.
   */
  //TODO: FIND CORRECT VALUE OF RPM CONSTANT(6380)
   public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0 *
           SdsModuleConfigurations.MK4I_L3.getDriveReduction() *
           SdsModuleConfigurations.MK4I_L3.getWheelDiameter() * Math.PI; // ~5 M/S
  /**
   * The maximum angular velocity of the robot in radians per second.
   * <p>
   * This is a measure of how fast the robot can rotate in place.
   */
  // Here we calculate the theoretical maximum angular velocity. You can also replace this with a measured amount.
  public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
          Math.hypot(DrivetrainConstants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DrivetrainConstants.DRIVETRAIN_WHEELBASE_METERS / 2.0); //~15 RAD/S or 2.3 rotations per sec

  private final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
          // Front left
          new Translation2d(DrivetrainConstants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DrivetrainConstants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Front right
          new Translation2d(DrivetrainConstants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DrivetrainConstants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Back left
          new Translation2d(-DrivetrainConstants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DrivetrainConstants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Back right
          new Translation2d(-DrivetrainConstants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DrivetrainConstants.DRIVETRAIN_WHEELBASE_METERS / 2.0)
  );

  // By default we use a Pigeon for our gyroscope. But if you use another gyroscope, like a NavX, you can change this.
  // The important thing about how you configure your gyroscope is that rotating the robot counter-clockwise should
  // cause the angle reading to increase until it wraps back over to zero.
  // FIXME Remove if you are using a Pigeon
  //private final PigeonIMU m_pigeon = new PigeonIMU(DRIVETRAIN_PIGEON_ID);
  // FIXME Uncomment if you are using a NavX
     private final AHRS m_navx = new AHRS(SPI.Port.kMXP, (byte) 200); // NavX connected over MXP

  // These are our modules. We initialize them in the constructor.
  private final SwerveModule m_frontLeftModule;
  private final SwerveModule m_frontRightModule;
  private final SwerveModule m_backLeftModule;
  private final SwerveModule m_backRightModule;
  private Rotation2d m_angleInRad;
  private double aIR; // keep track of angle in rad
  private boolean driveMode; // if not drive mode then calibration mode

  private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

  private SwerveDriveOdometry m_odometry = new SwerveDriveOdometry(m_kinematics, m_navx.getRotation2d());

  private TrapezoidProfile.Constraints kThetaControllerConstraints =
        new TrapezoidProfile.Constraints(Math.PI, Math.PI);
  private ProfiledPIDController thetaController = new ProfiledPIDController(1, 0, 0, kThetaControllerConstraints);
  private SwerveModuleState[] m_states;

  private int FL = 0;
  private int FR = 1;
  private int BL = 2;
  private int BR = 3;

  private CANCoder m_frontLeftCanCoder;
  private CANCoder m_frontRightCanCoder;
  private CANCoder m_backLeftCanCoder;
  private CANCoder m_backRightCanCoder;

  private ShuffleboardTab speedTab = Shuffleboard.getTab("Motor Speed");

  NetworkTableEntry motorFL = speedTab.add("Speed FL moto", 0).getEntry();
  NetworkTableEntry motorFR = speedTab.add("Speed FR moto", 0).getEntry();
  NetworkTableEntry motorBL = speedTab.add("Speed BL moto", 0).getEntry();
  NetworkTableEntry motorBR = speedTab.add("Speed BR moto", 0).getEntry();

  public DrivetrainSubsystem() {
    ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");
    m_angleInRad = new Rotation2d(0);
    aIR = 0.0;
    driveMode = true;
    thetaController.enableContinuousInput(-Math.PI, Math.PI);
    drive(new ChassisSpeeds(0,0,0));

//     m_frontLeftCanCoder = new CANCoder(DrivetrainConstants.FRONT_LEFT_MODULE_STEER_ENCODER);
//     m_frontRightCanCoder = new CANCoder(DrivetrainConstants.FRONT_LEFT_MODULE_STEER_ENCODER);
//     m_backLeftCanCoder = new CANCoder(DrivetrainConstants.BACK_LEFT_MODULE_STEER_ENCODER);
//     m_backRightCanCoder = new CANCoder(DrivetrainConstants.BACK_RIGHT_MODULE_STEER_ENCODER);

//     m_frontLeftCanCoder.configFactoryDefault();
//     m_frontRightCanCoder.configFactoryDefault();
//     m_backLeftCanCoder.configFactoryDefault();
//     m_backRightCanCoder.configFactoryDefault();

//    m_frontLeftCanCoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
//    m_frontRightCanCoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
//    m_backLeftCanCoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);
//    m_backRightCanCoder.configSensorInitializationStrategy(SensorInitializationStrategy.BootToAbsolutePosition);

//    m_frontLeftCanCoder.configSensorDirection(true);
//    m_frontRightCanCoder.configSensorDirection(true);
//    m_backLeftCanCoder.configSensorDirection(true);
//    m_backRightCanCoder.configSensorDirection(true);

//     m_frontLeftCanCoder.configAbsoluteSensorRange(AbsoluteSensorRange.Signed_PlusMinus180);
//     m_frontRightCanCoder.configAbsoluteSensorRange(AbsoluteSensorRange.Signed_PlusMinus180);
//     m_backLeftCanCoder.configAbsoluteSensorRange(AbsoluteSensorRange.Signed_PlusMinus180);
//     m_backRightCanCoder.configAbsoluteSensorRange(AbsoluteSensorRange.Signed_PlusMinus180);

    // There are 4 methods you can call to create your swerve modules.
    // The method you use depends on what motors you are using.
    //
    // Mk3SwerveModuleHelper.createFalcon500(...)
    //   Your module has two Falcon 500s on it. One for steering and one for driving.
    //
    // Mk3SwerveModuleHelper.createNeo(...)
    //   Your module has two NEOs on it. One for steering and one for driving.
    //
    // Mk3SwerveModuleHelper.createFalcon500Neo(...)
    //   Your module has a Falcon 500 and a NEO on it. The Falcon 500 is for driving and the NEO is for steering.
    //
    // Mk3SwerveModuleHelper.createNeoFalcon500(...)
    //   Your module has a NEO and a Falcon 500 on it. The NEO is for driving and the Falcon 500 is for steering.
    //
    // Similar helpers also exist for Mk4 modules using the Mk4SwerveModuleHelper class.

    // By default we will use Falcon 500s in standard configuration. But if you use a different configuration or motors
    // you MUST change it. If you do not, your code will crash on startup.
    // FIXME Setup motor configuration
    m_frontLeftModule = Mk4iSwerveModuleHelper.createFalcon500(
            // This parameter is optional, but will allow you to see the current state of the module on the dashboard.
            // tab.getLayout("Front Left Module", BuiltInLayouts.kList)
            //        .withSize(2, 4)
            //       .withPosition(0, 0),
            // This can either be STANDARD or FAST depending on your gear configuration
            Mk4iSwerveModuleHelper.GearRatio.L3,
            // This is the ID of the drive motor
            DrivetrainConstants.FRONT_LEFT_MODULE_DRIVE_MOTOR,
            // This is the ID of the steer motor
            DrivetrainConstants.FRONT_LEFT_MODULE_STEER_MOTOR,
            // This is the ID of the steer encoder
            DrivetrainConstants.FRONT_LEFT_MODULE_STEER_ENCODER,
            // This is how much the steer encoder is offset from true zero (In our case, zero is facing straight forward)
            DrivetrainConstants.FRONT_LEFT_MODULE_STEER_OFFSET,
            "frontLeft"
    );

    // We will do the same for the other modules
    m_frontRightModule = Mk4iSwerveModuleHelper.createFalcon500( 
           // tab.getLayout("Front Right Module", BuiltInLayouts.kList)
           //         .withSize(2, 4)
           //         .withPosition(2, 0),
            Mk4iSwerveModuleHelper.GearRatio.L3,
            DrivetrainConstants.FRONT_RIGHT_MODULE_DRIVE_MOTOR,
            DrivetrainConstants.FRONT_RIGHT_MODULE_STEER_MOTOR,
            DrivetrainConstants.FRONT_RIGHT_MODULE_STEER_ENCODER,
            DrivetrainConstants.FRONT_RIGHT_MODULE_STEER_OFFSET,
    "frontRight");

    m_backLeftModule = Mk4iSwerveModuleHelper.createFalcon500(
           // tab.getLayout("Back Left Module", BuiltInLayouts.kList)
           //         .withSize(2, 4)
           //         .withPosition(4, 0),
            Mk4iSwerveModuleHelper.GearRatio.L3,
            DrivetrainConstants.BACK_LEFT_MODULE_DRIVE_MOTOR,
            DrivetrainConstants.BACK_LEFT_MODULE_STEER_MOTOR,
            DrivetrainConstants.BACK_LEFT_MODULE_STEER_ENCODER,
            DrivetrainConstants.BACK_LEFT_MODULE_STEER_OFFSET,
    "backLeft");

    m_backRightModule = Mk4iSwerveModuleHelper.createFalcon500(
           // tab.getLayout("Back Right Module", BuiltInLayouts.kList)
           //         .withSize(2, 4)
           //         .withPosition(6, 0),
            Mk4iSwerveModuleHelper.GearRatio.L3,
            DrivetrainConstants.BACK_RIGHT_MODULE_DRIVE_MOTOR,
            DrivetrainConstants.BACK_RIGHT_MODULE_STEER_MOTOR,
            DrivetrainConstants.BACK_RIGHT_MODULE_STEER_ENCODER,
            DrivetrainConstants.BACK_RIGHT_MODULE_STEER_OFFSET,
            "backRight"
    );

       
  }

  public Pose2d getPose() {
    System.out.println(m_odometry.getPoseMeters());
    return m_odometry.getPoseMeters();
  }

  public void resetOdometry(Pose2d pose) {
    m_odometry.resetPosition(pose, m_navx.getRotation2d());
  }
  

  public SwerveDriveKinematics getKinematics() {
    
    return m_kinematics;
  }

  public ProfiledPIDController getThetaController() {
    return thetaController;
  }
  

  /**
   * Sets the gyroscope angle to zero. This can be used to set the direction the robot is currently facing to the
   * 'forwards' direction.
   */
  public void zeroGyroscope() {
    // FIXME Remove if you are using a Pigeon
    //m_pigeon.setFusedHeading(0.0);

    // FIXME Uncomment if you are using a NavX
    m_navx.zeroYaw();
  }

  public Rotation2d getGyroscopeRotation() {
    // FIXME Remove if you are using a Pigeon
    //return Rotation2d.fromDegrees(m_pigeon.getFusedHeading());

    // FIXME Uncomment if you are using a NavX
//     if (m_navx.isMagnetometerCalibrated()) {
//       // We will only get valid fused headings if the magnetometer is calibrated
//       return Rotation2d.fromDegrees(m_navx.getFusedHeading());
//     }
    
    // We have to invert the angle of the NavX so that rotating the robot counter-clockwise makes the angle increase.
    return Rotation2d.fromDegrees(360.0 - m_navx.getYaw());
  }

  public void drive(ChassisSpeeds chassisSpeeds) {
    m_states = m_kinematics.toSwerveModuleStates(chassisSpeeds);
  }

  public void setWheelAngle(double angleInRad) {
     m_angleInRad = new Rotation2d(angleInRad);
     aIR = angleInRad;
  }

  public void changeWheelAngleBy45() {
          setWheelAngle(aIR + Math.PI / 4);
  }

  public boolean toggleDriveMode() {
          driveMode = !driveMode;
          //System.out.println("setting drive mode to " + driveMode);
          return driveMode;
  }


  private SwerveModuleState[] calibrateModeCalculateStates() {
        SwerveModuleState[] states = new SwerveModuleState[4];
        states[FL] = new SwerveModuleState(0, m_angleInRad);
        states[FR] = new SwerveModuleState(0, m_angleInRad);
        states[BL] = new SwerveModuleState(0, m_angleInRad);
        states[BR] = new SwerveModuleState(0, m_angleInRad);
        return states;
     }


  public void setStates(SwerveModuleState[] states) {
//      System.out.println("states: " + states[FL] + " " + states[FR] + " " + states[BL] + " " + states[BR]);
        setStatesInternal(states);
  }


  private void setStatesInternal(SwerveModuleState[] states) {// 1.12, 0.95
        m_states = states;
        m_frontLeftModule.set(m_states[FL].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[FL].angle.getRadians());
        m_frontRightModule.set(m_states[FR].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[FR].angle.getRadians());
        m_backLeftModule.set(m_states[BL].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[BL].angle.getRadians());
        m_backRightModule.set(m_states[BR].speedMetersPerSecond * 1.3 / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[BR].angle.getRadians());      
     }
   
  @Override
  public void periodic() {
    m_odometry.update(
        m_navx.getRotation2d(),
        m_states[FL],
        m_states[FR],
        m_states[BL],
        m_states[BR]
    );

    SwerveModuleState[] states;
    if(driveMode) {
        states = m_states;
    }
    else {
        states = calibrateModeCalculateStates();
    }
    SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);

    setStatesInternal(states);

    motorFL.setDouble(m_states[FL].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE);
    motorFR.setDouble(m_states[FR].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE);
    motorBL.setDouble(m_states[BL].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE);
    motorBR.setDouble(m_states[BR].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE);

  }
}
