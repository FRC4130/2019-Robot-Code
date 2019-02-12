package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.Wrist;

public class WristTele implements ILoopable {

    Wrist _wrist;
    Joystick _joystick;

    public WristTele() {

        _wrist = Subsystems.wrist;
        _joystick = RobotMap.operatorJoystick;

    }

    public void onStart() {

        System.out.println("[INFO] Teleop Wrist cnontrols have started");

        _wrist.setNeutralMode(NeutralMode.Brake);

    }

    public void onLoop() {

        _wrist.driveDirect(_joystick.getRawAxis(1)*-1);

        if(_joystick.getRawButton(7)) {

            _wrist.suck();

        }

        else if(_joystick.getRawButton(8)) {

            _wrist.blow();

        }

    }

    public boolean isDone() {

        return false;

    }

    public void onStop() {

        System.out.println("[WARNING] Wrist Tele controls have Stopped");

        _wrist.setNeutralMode(NeutralMode.Brake);
        _wrist.driveDirect(0);

    }
}