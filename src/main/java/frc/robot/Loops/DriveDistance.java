package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.DriveTrain;

public class DriveDistance implements ILoopable {

    private double distanceNative;
    private double distanceInches;
    private DriveTrain _drive;
    private double acceptablError = 1000;

    private int cruiseVelocity = 0;
    private int acceleration = 0;

    @Deprecated
    public DriveDistance(DriveTrain DriveTrain, double inches) {

        System.out.println("Drive Distance task has been created");

        distanceInches = inches;
        _drive = DriveTrain;

    }

    public DriveDistance(double inches, int cruiseVelocity1, int acceleration1) {

        System.out.println("Custom Drive Distance task has been created");

        _drive = Subsystems.driveTrain;

        distanceInches = inches;
        cruiseVelocity1 = cruiseVelocity;
        acceleration1 = acceleration;

    }

    public void onStart() {

        System.out.println("[INFO] Driving");
        System.out.println(distanceInches);
        _drive.setNuetralMode(NeutralMode.Brake);

        distanceNative = _drive.distanceToRotations(distanceInches);
        
        if (cruiseVelocity > 0 && acceleration > 0) {
            _drive.setMagic(cruiseVelocity, acceleration);
        }

    }

    public void onLoop() {

        _drive.setPosLeft(distanceNative);
        _drive.setPosRight(distanceNative);
        _drive.putDash();

    }

    public boolean isDone() {

        boolean leftAtPos = Math.abs(distanceNative - _drive.getLeftPos()) <= acceptablError;
        boolean rightAtPos = Math.abs(distanceNative - _drive.getRightPos()) <= acceptablError;

        if (leftAtPos && rightAtPos) {
            System.out.println("[INFO] Driving for distance was stopped");
            System.out.println("[WARNING] The DriveTrain is still in the Motion Magic Control Mode!");
            return true;
        }

        return false;

    }

    public void onStop() {

        System.out.println("[WARNING] Driving for Distance has Stopped");
        System.out.println("[WARNING] The DriveTrain is still in Motion Magic Control Mode");

    }


}