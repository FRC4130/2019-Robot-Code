package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.Climb;

public class ClimbTele implements ILoopable {

    Climb _climb;
    Joystick _joystick;

    public ClimbTele() {

        _climb = Subsystems.climb;
        _joystick = RobotMap.operatorJoystick;

    }

    public void onStart() {

        System.out.println("[INFO] Teleop Climb Controls have started");
        _climb.setNeutralMode(NeutralMode.Coast);

    }

    public void onLoop() {

        _climb.driveDirect(_joystick.getRawAxis(5)*-1);
        _climb.setNeutralMode(NeutralMode.Brake);

    }

    public boolean isDone() {
        
        return false;
    }

    public void onStop() {

        System.out.println("[WARNING] Climb Teleop Controls have Stoped");
        _climb.setNeutralMode(NeutralMode.Brake);
        _climb.driveDirect(0);

    }

}