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
    WristMode _actualmode;
    WristMode _desiredmode;

    double wristJoystick;
    boolean suckButton;
    boolean blowButton;
    boolean manualOverride;

    public WristTele() {

        _wrist = Subsystems.wrist;
        _joystick = RobotMap.operatorJoystick;
        _actualmode = WristMode.Encoder;
        _desiredmode = WristMode.Encoder;



    }

    public void onStart() {

        System.out.println("[INFO] Teleop Wrist cnontrols have started");

        updateInputs();
        _wrist.setNeutralMode(NeutralMode.Brake);

    }

    public void onLoop() {

        updateInputs();
        updateMode();

        switch(_actualmode) {

            case Encoder:
                _wrist.driveDirect(0);
                break;

            case Pitch:
                _wrist.driveDirect(0);
                break;

            case Manual:
                _wrist.driveDirect(wristJoystick);
                break;

        }









 

        if(suckButton) {

            _wrist.suck();

        }

        else if(blowButton) {

            _wrist.spit();

        }

    }

    private void updateInputs() {

        wristJoystick = _joystick.getRawAxis(1)*-1;
        suckButton = _joystick.getRawButton(7);
        blowButton = _joystick.getRawButton(8);
        manualOverride = _joystick.getRawButton(5);
        

    }

    private void updateMode() {

        if(manualOverride) {
            _actualmode = WristMode.Manual;

        }

        else _actualmode = WristMode.Encoder;

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