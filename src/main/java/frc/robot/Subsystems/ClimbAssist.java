package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robots.RobotMap;

public class ClimbAssist {

    public DoubleSolenoid assist;
    public final Value closed = Value.kForward;
    public final Value open = Value.kReverse;

    public ClimbAssist() {

        assist = RobotMap.climb;
        assist.set(closed);

    }

    public void set(Value vl) {

        assist.set(vl);

    }

    public void disable() {

        assist.set(Value.kOff);

    }

}
