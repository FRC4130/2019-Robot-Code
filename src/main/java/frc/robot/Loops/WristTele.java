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
    WristMode _actualMode;
    WristMode _desiredmode;

    double wristJoystick;
    boolean suckButton;
    boolean blowButton;
    boolean manualOverride;

    //We can initialize target to 0 since that's our home position.
    double _closedLoopTarget = 0;
    double _setpoint = 0;

    public WristTele() {
        _wrist = Subsystems.wrist;
        _joystick = RobotMap.operatorJoystick;
        _actualMode = WristMode.Encoder;
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
        updateClosedLoopTarget();
        updateWrist();
        updateIntake();
    }

    private void updateInputs() {
        wristJoystick = _joystick.getRawAxis(1)*-1;
        suckButton = _joystick.getRawButton(7);
        blowButton = _joystick.getRawButton(8);
        manualOverride = _joystick.getRawButton(5);
    }

    private void updateMode() {
        if(manualOverride) {
            _actualMode = WristMode.Manual;
        }
        else _actualMode = WristMode.Encoder;
    }

    private void updateWrist() {
        switch(_actualMode) {
            case Encoder:
                /* Do nothing for now.  We'll need to 
                implement a case structure/value checking when we start using
                Pigeon and switching back and forth between modes.
                */
                _setpoint = _closedLoopTarget;
                break;
            case Pitch:
                //This mode currently isn't supported.
                _actualMode = WristMode.Manual;
                _setpoint = 0;
                break;
            case Manual:
                _setpoint = wristJoystick;
                break;
        }
        _wrist.set(_actualMode, _setpoint);
    }

    private void updateIntake() {
        if(suckButton) {
            _wrist.suck();
        }
        else if(blowButton) {
            _wrist.spit();
        }
    }

    private void updateClosedLoopTarget(){

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