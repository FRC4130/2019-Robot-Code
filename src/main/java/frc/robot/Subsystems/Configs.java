package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;

import frc.robot.Robots.RobotMap;

public class Configs{

    /* Static Configurations for Subsystem Master motor controllers. */
    public static TalonSRXConfiguration arm = new TalonSRXConfiguration();
    public static TalonSRXConfiguration wrist = new TalonSRXConfiguration();
    public static TalonSRXConfiguration leftDrive = new TalonSRXConfiguration();
    public static TalonSRXConfiguration rightDrive = new TalonSRXConfiguration();
    public static VictorSPXConfiguration intake = new VictorSPXConfiguration();
    public static VictorSPXConfiguration climber = new VictorSPXConfiguration();

    /* Singleton instance, do not remove - this line MUST be after the other variables. */
    private static Configs _instance = new Configs();

    /* Designated "private" for Singleton instance.  DO NOT change to public.  */
    private Configs(){

        /* --- Arm --- */
        arm.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
        arm.slot0.kF = 3;
        arm.slot0.kP = 3;
        arm.motionAcceleration = 150;
        arm.motionCruiseVelocity = 150;


        /* --- Wrist --- */
        arm.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
        arm.slot0.kF = 2.04;
        arm.slot0.kP = 7;
        arm.slot0.kD = 70;
        arm.motionAcceleration = 600;
        arm.motionCruiseVelocity = 600;
        arm.remoteFilter0.remoteSensorDeviceID = RobotMap.kWristMotor2ID;
        arm.remoteFilter0.remoteSensorSource = RemoteSensorSource.GadgeteerPigeon_Pitch;

        /* --- Intake --- */


        /* --- Left Drivetrain --- */
        leftDrive.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;


        /* --- Right Drivetrain --- */
        rightDrive.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;


        /* --- Climber --- */
    }

    /* Accessor for Singleton.  We shouldn't need to use this since member variables are static. */
    public Configs get(){
        return _instance;
    }
}