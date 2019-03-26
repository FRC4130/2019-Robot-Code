package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.DriveTrain;

public class NavX  implements ILoopable, PIDOutput{

    Joystick _joystick;
    DriveTrain _drive;
    AHRS ahrs;

    PIDController turnController;
    double rotateToAngleRate;

    static final double kP = 0.0225;
    static final double kI = 0;
    static final double kD = 0.02;
    static final double kF = 0.0;

    double kTargetAngleDegrees = 0.0;
    
    
    public NavX() {


        _drive = Subsystems.driveTrain;
        _joystick = RobotMap.driverJoystick;

        try {
            ahrs = new AHRS(SPI.Port.kMXP);
        }
        catch(RuntimeException ex) {
            DriverStation.reportError("Error instantiating NavX MXP : " + ex.getMessage(), true);
        }

        turnController = new PIDController(kP, kI, kD, ahrs, this);
        turnController.setInputRange(-180.0, 180.0);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kTargetAngleDegrees);
        turnController.setContinuous(true);
        turnController.disable();
        turnController.setName("Drive System", "Rotate Controller");

    }

    public void onStart() {

    }

    public void onLoop() {

        SmartDashboard.putBoolean("IMU Connected", ahrs.isConnected());
        SmartDashboard.putBoolean("IMU is Calibrating", ahrs.isCalibrating());
        SmartDashboard.putNumber("IMU Yaw", ahrs.getYaw());
        SmartDashboard.putNumber("IMU Pitch", ahrs.getPitch());
        SmartDashboard.putNumber("IMU Roll", ahrs.getRoll());
        SmartDashboard.putNumber("IMU Compass Heading", ahrs.getCompassHeading());
        SmartDashboard.putNumber("IMU Fused Heading", ahrs.getFusedHeading());
        SmartDashboard.putNumber("IMU Total Yaw", ahrs.getAngle());
        SmartDashboard.putNumber("IMU Yaw Rate DPS", ahrs.getRate());
        SmartDashboard.putNumber("IMU Acceleration X", ahrs.getWorldLinearAccelX());
        SmartDashboard.putNumber("IMU Acceleration Y", ahrs.getWorldLinearAccelY());
        SmartDashboard.putBoolean("Is IMU Moving", ahrs.isMoving());
        SmartDashboard.putBoolean("IMU is Rotating", ahrs.isRotating());
        SmartDashboard.putNumber("Velocity X", ahrs.getVelocityX());
        SmartDashboard.putNumber("Velocity Y", ahrs.getVelocityY());
        SmartDashboard.putNumber("Displacement X", ahrs.getDisplacementX());
        SmartDashboard.putNumber("Displacement Y", ahrs.getDisplacementY());
        /**To be continued */
            
        /**Zeros the Yaw on the joystick */
        if(_joystick.getRawButton(1) && _joystick.getRawButton(7)) {   //changed 3/23 by sjp
            ahrs.zeroYaw();

        }
        
        /**Rotate to Angle */
        if(_joystick.getPOV()>-1) {   //changed 3/23 by sjp

            if(!turnController.isEnabled()) {

                kTargetAngleDegrees = _joystick.getPOV();   //changed 3/23 by sjp
                
                if(kTargetAngleDegrees>180) {
                    kTargetAngleDegrees-=360;
                }
                
                SmartDashboard.putNumber("get POV", _joystick.getPOV());
                turnController.setSetpoint(kTargetAngleDegrees);
                rotateToAngleRate = 0;
                turnController.enable();
            }
            SmartDashboard.putNumber("kTarget Angle Degrees", kTargetAngleDegrees);
            SmartDashboard.putNumber("X Rotate To Angle Rate", rotateToAngleRate);
            double leftStickValue = rotateToAngleRate;
            double rightStickValue = -rotateToAngleRate;
            _drive.driveDirect(leftStickValue, rightStickValue);
            SmartDashboard.putNumber("get POV", _joystick.getPOV());
        }

        else if(_joystick.getRawButton(8)) {

            if(!turnController.isEnabled()) {

                turnController.setSetpoint(ahrs.getYaw());
                rotateToAngleRate = 0;
                turnController.enable();
            }
            double magnitude = (-_joystick.getRawAxis(1) + -_joystick.getRawAxis(5)) /2;
            double leftStickValue = magnitude + rotateToAngleRate;
            double rightStickValue = magnitude - rotateToAngleRate;
            _drive.driveDirect(leftStickValue, rightStickValue);
        }

        else {

            if(turnController.isEnabled()) {
                turnController.disable();
            }
            //_drive.driveDirect(_joystick.getRawAxis(1)*-1, _joystick.getRawAxis(5)*-1);
        }
        Timer.delay(0.005);

        //pidWrite(rotateToAngleRate);

    }
    @Override
    public void pidWrite(double output) {
        rotateToAngleRate = output;
    }

    public boolean isDone() {
        return false;
    }

    public void onStop() {
        System.out.println("NavX Auto Controls Have Stopped!");

    }
}