package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robots.RobotMap;


//Creating the DriveTrain Class
public class DriveTrain {

    private TalonSRX leftDrive;
    private TalonSRX leftDrive2;

    private TalonSRX rightDrive;
    private TalonSRX rightDrive2;

    AHRS ahrs;

    static final double kToleranceDegrees = 2.0f;

    private final int kTimeoutMS = 10;
    double kTargetAngleDegrees = 0.0f;

    static final double kP = 3;
    static final double kI = 1;
    static final double kD = 8;
    static final double kF = 0.00;

    PIDController turController;

    //Configuring all of the differnent things used in the Drivetrain Class
    public DriveTrain() {
        leftDrive = RobotMap.leftDrive;
        leftDrive2 = RobotMap.leftDrive2;

        rightDrive = RobotMap.rightDrive;
        rightDrive2 = RobotMap.rightDrive2;

        //Configs
        leftDrive.configAllSettings(Configs.leftDrive);
        leftDrive2.configFactoryDefault();
        rightDrive.configAllSettings(Configs.rightDrive);
        rightDrive2.configFactoryDefault();

        //Setting Inverted
        leftDrive.setInverted(false);
        leftDrive2.follow(leftDrive);
        leftDrive2.setInverted(InvertType.FollowMaster);

        rightDrive.setInverted(true);
        rightDrive2.follow(rightDrive);
        rightDrive2.setInverted(InvertType.FollowMaster);

        //Encoders
        leftDrive.setSensorPhase(true);
        rightDrive.setSensorPhase(true);

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

    public void arcadeDrive(double throttle, double turn) {

        driveDirect(throttle-turn, throttle+turn);

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