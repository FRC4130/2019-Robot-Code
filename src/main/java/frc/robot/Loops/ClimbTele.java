package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.Climb;
import frc.robot.Subsystems.ClimbAssist;
import frc.robot.Subsystems.DriveTrain;

public class ClimbTele implements ILoopable {

    Climb _climb;
    Joystick _joystick;
    Joystick _driveJoystick;
    ClimbAssist _assist;
    DriveTrain _driveTrain;


    boolean openClimbAssist = false;

    public ClimbTele() {

        _climb = Subsystems.climb;
        _joystick = RobotMap.driverJoystick;
        _assist = Subsystems.climbAssist;
        _driveTrain = Subsystems.driveTrain;

    }

    public void onStart() {

        System.out.println("[INFO] Teleop Climb Controls have started");
        _climb.setNeutralMode(NeutralMode.Brake);
        _assist.set(_assist.closed);

    }

    public void onLoop() {

        input();
        updateClimbAssistSolenoid();

    }

    public void input() {
        openClimbAssist = _joystick.getRawButton(RobotMap.kClimbButtonID);


    }

    private void updateClimbAssistSolenoid() {

    if(openClimbAssist) {
        _assist.set(_assist.open);

        if(Math.abs(_joystick.getThrottle()) < -0.1) {
            _climb.driveDirect(0);
            // _driveTrain.driveDirect(0, 0);
        }
        
        else {
            _climb.driveDirect(_joystick.getRawAxis(1)*-1);
            // _driveTrain.driveDirect(0, 0);
        }
    }
    else {
        _assist.set(_assist.closed);
        _climb.driveDirect(0);

        }

    }

    public boolean isDone() {
        
        return false;
    }

    public void onStop() {

        System.out.println("[WARNING] Climb Teleop Controls have Stopped");
        _climb.setNeutralMode(NeutralMode.Brake);
        _climb.driveDirect(0);
        _assist.set(_assist.closed);

    }

}