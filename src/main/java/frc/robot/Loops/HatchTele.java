package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.Hatch;

public class HatchTele implements ILoopable{

    Hatch _hatch;
    Joystick _joystick;

    public HatchTele() {

        _hatch = Subsystems.hatch;
        _joystick = RobotMap.operatorJoystick;

    }

    public void onStart() {

        System.out.println("[INFO] Hatch teleop controls have started");
        _hatch.driveDirect(_hatch.closed);

    }

    public void onLoop() {

        if(_joystick.getRawButtonPressed(5)) {

            _hatch.driveDirect(_hatch.closed);
        }

        else if(_joystick.getRawButtonPressed(6)) {

            _hatch.driveDirect(_hatch.open);

        }
    }

    public boolean isDone() {

        return false;

    }

    public void onStop() {

        _hatch.disable();
        System.out.println("[WARNING] Hatch teleoperated has been stopped");
        

    }

}