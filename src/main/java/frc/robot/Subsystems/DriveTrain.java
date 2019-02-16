package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robots.RobotMap;


//Creating the DriveTrain Class
public class DriveTrain {

    private TalonSRX leftDrive;
    private TalonSRX leftDrive2;

    private TalonSRX rightDrive;
    private TalonSRX rightDrive2;

    private final int kTimeoutMS = 10;

    //Configuring all of the differnent things used in the Drivetrain Class
    public DriveTrain() {
        leftDrive = RobotMap.leftDrive;
        leftDrive2 = RobotMap.leftDrive2;

        rightDrive = RobotMap.rightDrive;
        rightDrive2 = RobotMap.rightDrive2;

        //Setting Inverted
        leftDrive.setInverted(false);
        leftDrive2.setInverted(false);

        rightDrive.setInverted(true);
        rightDrive2.setInverted(true);

        leftDrive2.follow(leftDrive);
        rightDrive2.follow(rightDrive);

        //Speed Control 
        leftDrive.configPeakOutputForward(1.0, kTimeoutMS);
        leftDrive.configPeakOutputReverse(-1.0, kTimeoutMS);
        leftDrive.configNominalOutputForward(0.0, kTimeoutMS);
        leftDrive.configNominalOutputReverse(0.0, kTimeoutMS);


        rightDrive.configPeakOutputForward(1.0, kTimeoutMS);
        rightDrive.configPeakOutputReverse(-1.0, kTimeoutMS);
        rightDrive.configNominalOutputForward(0.0, kTimeoutMS);
        rightDrive.configNominalOutputReverse(0.0, kTimeoutMS);

        //Encoders
        leftDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMS);
        leftDrive.setSensorPhase(true);
        
        rightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeoutMS);
        rightDrive.setSensorPhase(true);


        //Motion Magic PID
         leftDrive.config_kP(0, 0, kTimeoutMS);
         leftDrive.config_kI(0, 0, kTimeoutMS);
         leftDrive.config_kD(0, 0, kTimeoutMS);

         rightDrive.config_kP(0, 0, kTimeoutMS);
         rightDrive.config_kI(0, 0, kTimeoutMS);
         rightDrive.config_kD(0, 0, kTimeoutMS);

        leftDrive.set(ControlMode.PercentOutput, 0);
        rightDrive.set(ControlMode.PercentOutput, 0);

        leftDrive.setNeutralMode(NeutralMode.Brake);
        rightDrive.setNeutralMode(NeutralMode.Brake);
    }

    //Sets Neutral Mode, but we create nm as Neutral Mode in the construct so we can use this in the teleop class.
    public void setNuetralMode(NeutralMode nm) {

        leftDrive.setNeutralMode(nm);
        leftDrive2.setNeutralMode(nm);
        rightDrive.setNeutralMode(nm);
        rightDrive2.setNeutralMode(nm);

    }

    //Putting values to the SmartDashboard to be read
    public void putDash() {

        SmartDashboard.putNumber("Left Velocity", leftDrive.getSelectedSensorVelocity(0));
        SmartDashboard.putNumber("Left Position", leftDrive.getSelectedSensorVelocity(0));
        SmartDashboard.putNumber("Left Target Position", leftDrive.getClosedLoopTarget(0));

        SmartDashboard.putNumber("Right Velocity", rightDrive.getSelectedSensorVelocity(0));
        SmartDashboard.putNumber("Right Position", rightDrive.getSelectedSensorPosition(0));
        SmartDashboard.putNumber("Right Target Position", rightDrive.getClosedLoopTarget(0));

    }

    // Creating a drive method to be used in another tele class at a later date
    public void driveDirect(double leftThrottle, double rightThrottle) {

        leftDrive.set(ControlMode.PercentOutput, leftThrottle);
        rightDrive.set(ControlMode.PercentOutput, rightThrottle);

    }

    // Motion Magic setting, but this will be used in another class at a later date
    public void setMagic(int cruiseVelocity, int acceleration) {

        leftDrive.configMotionCruiseVelocity(cruiseVelocity, kTimeoutMS);
        leftDrive.configMotionAcceleration(acceleration, kTimeoutMS);

        rightDrive.configMotionCruiseVelocity(cruiseVelocity, kTimeoutMS);
        rightDrive.configMotionAcceleration(acceleration, kTimeoutMS);

    }

    //Set the Position of leftDrive to a specific value of native units
    public void setPosLeft(double nativeUnits) {

        leftDrive.set(ControlMode.MotionMagic, nativeUnits);

    }

    //Set the Position of rightDrive to a specific value in native units
    public void setPosRight(double nativeUnits) {

        rightDrive.set(ControlMode.MotionMagic, nativeUnits);

    }

    public double distanceToRotations(double inches) {

        return ( ( (16800) * inches) / 80);

    }

    // Giving us the leftDrive Position
    public double getLeftPos() {

        return leftDrive.getSelectedSensorPosition(0);

    }

    // Giving us the rightDrive Position
    public double getRightPos() {

        return rightDrive.getSelectedSensorPosition(0);

    }

    // Reset the Encoder sensor position to 0
    public void resetSensors(int Timeout) {

        leftDrive.setSelectedSensorPosition(0, 0, Timeout);
        rightDrive.setSelectedSensorPosition(0, 0, Timeout);

    }

}