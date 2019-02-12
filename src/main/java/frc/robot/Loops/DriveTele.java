package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.DriveTrain;

public class DriveTele implements ILoopable {

    DriveTrain _drive;
    Joystick _joystick;

    public DriveTele() {

        _drive = Subsystems.driveTrain;
        _joystick = RobotMap.driverJoystick;

    }

    public void onStart() {

        System.out.println("[INFO] Teleop Drive Controls Starting");

        _drive.setNuetralMode(NeutralMode.Brake);

    }

    public void onLoop() {

        //Driver Control Input


        //Brake
        if(_joystick.getRawButtonPressed(3)) {
            _drive.driveDirect(0, 0);

        }

        else{ 

            _drive.driveDirect(_joystick.getRawAxis(1)*-1, _joystick.getRawAxis(5)*-1);

        }
    }

    public boolean isDone() {
        return false;
    
    }
    
    public void onStop() {

        System.out.println("[WARNING] Teleop Drive Controls have Stopped");

        _drive.setNuetralMode(NeutralMode.Brake);
        _drive.driveDirect(0, 0);
    }


}