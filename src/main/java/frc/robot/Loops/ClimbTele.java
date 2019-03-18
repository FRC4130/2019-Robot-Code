package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay.Value;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.Climb;
import frc.robot.Subsystems.ClimbAssist;

public class ClimbTele implements ILoopable {

    Climb _climb;
    Joystick _joystick;
    Joystick _driveJoystick;
    ClimbAssist _assist;


    boolean openClimbAssist = false;

    public ClimbTele() {

        _climb = Subsystems.climb;
        _joystick = RobotMap.driverJoystick;
        _assist = Subsystems.climbAssist;

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
        _climb.driveDirect(_joystick.getRawAxis(1)*-1);

        }
        else {
           _assist.set(_assist.closed);

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