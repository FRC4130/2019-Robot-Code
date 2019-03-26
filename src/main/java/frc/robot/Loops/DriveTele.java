package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.DriveTrain;

public class DriveTele implements ILoopable {

    DriveTrain _drive;
    Joystick _joystick;

    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tv = table.getEntry("tv");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ta = table.getEntry("ta");

    double m_LimelightDriveCommand = 0.0;
    double m_LimelightSteerCommand = 0.0;
    boolean m_LimelightHasValidTarget = false;

    public DriveTele() {

        _drive = Subsystems.driveTrain;
        _joystick = RobotMap.driverJoystick;

    }

    public void onStart() {

        System.out.println("[INFO] Teleop Drive Controls Starting");

        _drive.setNuetralMode(NeutralMode.Brake);

        NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(0);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }

    public void onLoop() {

        //Driver Control Input
        //Limelight - read variables tv, tx, ty, ta, ts, tl, tshort, tlong, thor, and tvert
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ts").getDouble(0);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(0);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("tshort").getDouble(0);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("tlong").getDouble(0);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("thor").getDouble(0);
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("tvert").getDouble(0);

        //Limelight - Limelight Autonomous Aim/Seek/Range w/ button
        LimelightTracking();
        double steer = -_joystick.getX(Hand.kRight);
        double drive = -_joystick.getY(Hand.kLeft);
        boolean auto = _joystick.getRawButton(2);
        steer *= 1;
        drive *= 1;
        if (auto) {
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
            if (m_LimelightHasValidTarget) {
                _drive.arcadeDrive(m_LimelightDriveCommand, -m_LimelightSteerCommand);
            }
            else {
                NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(0);
                NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
                _drive.arcadeDrive(drive,steer);
            }
        }
        else {
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("camMode").setNumber(1);
            NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);

            if(_joystick.getRawButton(3)) {
                _drive.driveDirect(0, 0);
            }
            else if (_joystick.getRawButton(6)) {
                _drive.driveDirect(_joystick.getRawAxis(1)*-.50, _joystick.getRawAxis(5)*-.50);
            }
            else if (_joystick.getRawButton(5)) {
                _drive.driveDirect(_joystick.getRawAxis(1)*-.20, _joystick.getRawAxis(5)*-.20);
            }
            else { 
                _drive.driveDirect(_joystick.getRawAxis(1)*-1, _joystick.getRawAxis(5)*-1);
            }

        }
    }


    public void LimelightTracking() {

        // These numbers must be tuned...
        final double STEER_K = 0.025; // How hard to turn toward the target
        final double DRIVE_K = 0.20; // How hard to drive fwd toward the target
        final double DESIRED_TARGET_AREA = 2.5; // Area of the target when the robot reaches the wall
        final double MAX_DRIVE = .70; // Speed limit so we don't drive too fast
        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

        // Function to determine autonomous drive and steering settings
        if (tv < 0.5) {
            m_LimelightHasValidTarget = false;
            m_LimelightDriveCommand = 0.0;
            m_LimelightSteerCommand = 0.0;
            return;
        }
        m_LimelightHasValidTarget = true;
        // Start with proportional steering
        double steer_cmd = tx * STEER_K;
        m_LimelightSteerCommand = steer_cmd;
        // Try to drive forward until the target area reaches our desired area
        double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;
        // Don't let the robot drive too fast into the target
        if (drive_cmd > MAX_DRIVE) {
            drive_cmd = MAX_DRIVE;
        }
        m_LimelightDriveCommand = drive_cmd;
    }

    public boolean isDone() {
        return false;
    }

    public void onStop() {
        _drive.driveDirect(0, 0);
    } 
}