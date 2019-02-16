package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Robots.RobotMap;

public class Climb {

    private VictorSPX lift;
    private VictorSPX lift2;

    public Climb() {

        lift = RobotMap.liftMotor;
        lift2 = RobotMap.liftMotor2;

        lift.configAllSettings(Configs.climber);
        lift2.configFactoryDefault();

        lift.setInverted(false);
        lift2.follow(lift);
        lift2.setInverted(InvertType.FollowMaster);
    }

    //Setting the neutral mode of the elevator, which is set in another class
    public void setNeutralMode(NeutralMode nm) {

        lift.setNeutralMode(nm);

    }

    //Setting the drive control, which is set in another class
    public void driveDirect (double percentOuput) {
        lift.set(ControlMode.PercentOutput, percentOuput);
        lift2.set(ControlMode.PercentOutput, percentOuput);

    }
}