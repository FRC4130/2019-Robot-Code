package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.Arm;;

public class ArmTele implements ILoopable {

    Arm  _arm;
    Joystick _joystick;

    ArmMode ModeofArm;

    public ArmTele() {

        _arm = Subsystems.arm;
        _joystick = RobotMap.operatorJoystick;

    }

    public void onStart() {

        System.out.println("[INFO] Teleop Elevator Controls are Starting");

        _arm.setNeutralMode(NeutralMode.Brake);

    }

    public void onLoop() {

        _arm.setNeutralMode(NeutralMode.Brake);

        switch(ModeofArm) {

            case Stowed:
            
            break;

            case Active:

            break;

        }

        //Home Position
        if(_joystick.getRawButton(1)) {
            _arm.setHeight(0);

        }

        //Ground Position
        else if(_joystick.getRawButton(2)) {
            _arm.setHeight(618);

        }

        //Middle Position
        else if(_joystick.getRawButton(3)) {
            _arm.setHeight(1513);

        }

        //Top Position
        else if(_joystick.getRawButton(4)) {
            _arm.setHeight(2539);

        }

        else _arm.driveDirect(_joystick.getRawAxis(1)*-1);
    
    }

    public boolean isDone() {

        return false;

        
    }

    public void onStop() {

        System.out.println("[WARNING} Teleop Elevator Controls have Stopped");
        _arm.driveDirect(0);
        _arm.setNeutralMode(NeutralMode.Brake);
    }

}