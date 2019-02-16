package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.Elevator;

public class ElevateTele implements ILoopable {

    Elevator  _elevate;
    Joystick _joystick;

    public ElevateTele() {

        _elevate = Subsystems.elevator;
        _joystick = RobotMap.operatorJoystick;

    }

    public void onStart() {

        System.out.println("[INFO] Teleop Elevator Controls are Starting");

        _elevate.setNeutralMode(NeutralMode.Brake);

    }

    public void onLoop() {

        _elevate.setNeutralMode(NeutralMode.Brake);

        //Home Position
        if(_joystick.getRawButton(1)) {
            _elevate.setHeight(0);

        }

        //Ground Position
        else if(_joystick.getRawButton(2)) {
            _elevate.setHeight(618);

        }

        //Middle Position
        else if(_joystick.getRawButton(3)) {
            _elevate.setHeight(1513);

        }

        //Top Position
        else if(_joystick.getRawButton(4)) {
            _elevate.setHeight(2539);

        }

        else _elevate.driveDirect(_joystick.getRawAxis(1)*-1);
    
    }

    public boolean isDone() {

        return false;

        
    }

    public void onStop() {

        System.out.println("[WARNING} Teleop Elevator Controls have Stopped");
        _elevate.driveDirect(0);
        _elevate.setNeutralMode(NeutralMode.Brake);
    }

}