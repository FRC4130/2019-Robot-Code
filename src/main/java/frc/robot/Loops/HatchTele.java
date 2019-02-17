package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.Hatch;

public class HatchTele implements ILoopable{

    Hatch _hatch;
    Joystick _joystick;

    boolean openHatch = false;

    public HatchTele() {
        _hatch = Subsystems.hatch;
        _joystick = RobotMap.operatorJoystick;
    }

    public void onStart() {
        System.out.println("[INFO] Hatch teleop controls have started");
        _hatch.set(_hatch.closed);
    }

    public void onLoop() {
        updateInputs();
        updateHatchSolenoid();
    }

    private void updateInputs(){
        openHatch = _joystick.getRawButton(RobotMap.kSpitHatchPanelButtonID);
    }
    
    private void updateHatchSolenoid(){
        if(openHatch){_hatch.set(_hatch.open);}
        else{_hatch.set(_hatch.closed);}
    }

    public boolean isDone() {
        return false;
    }

    public void onStop() {
        _hatch.disable();
        System.out.println("[WARNING] Hatch teleoperated has been stopped");
    }

}