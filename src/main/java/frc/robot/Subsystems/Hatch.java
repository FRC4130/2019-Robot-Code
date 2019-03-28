package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robots.RobotMap;

public class Hatch {

    public DoubleSolenoid hatch;
    public final Value closed = Value.kForward;
    public final Value open = Value.kReverse;

    public Hatch() {

        hatch = RobotMap.hatch;
        hatch.set(open);

    }

    public void set(Value vl) {

        hatch.set(vl);

    }

    public void disable() {

        hatch.set(Value.kOff);

    }

}