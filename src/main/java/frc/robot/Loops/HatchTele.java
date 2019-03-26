package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.Hatch;

public class HatchTele implements ILoopable{

    Hatch _hatch;
    Joystick _joystick;

    //boolean openHatch = false; //changed 3/23 by sjp
    boolean grabHatch = false;   //added 3/23 by sjp

    public HatchTele() {
        _hatch = Subsystems.hatch;
        _joystick = RobotMap.operatorJoystick;
    }

    public void onStart() {
        System.out.println("[INFO] Hatch teleop controls have started");
        _hatch.set(_hatch.open); //changed 3/23 by sjp
    }

    public void onLoop() {
        updateInputs();
        updateHatchSolenoid();
    }

    private void updateInputs(){
        //openHatch = _joystick.getRawButton(RobotMap.kSpitHatchPanelButtonID); //changed 3/23 by sjp
        grabHatch = _joystick.getRawButton(RobotMap.kSpitHatchPanelButtonID); //changed 3/23 by sjp
    }
    
    private void updateHatchSolenoid(){
        if(grabHatch){_hatch.set(_hatch.closed);} //changed 3/23 by sjp
        else{_hatch.set(_hatch.open);} //changed 3/23 by sjp
    }

    public boolean isDone() {
        return false;
    }

    public void onStop() {
        _hatch.disable();
        System.out.println("[WARNING] Hatch teleoperated has been stopped");
    }

}