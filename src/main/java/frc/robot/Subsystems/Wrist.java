package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Robots.RobotMap;

public class  Wrist {

    private TalonSRX wrist;
    private TalonSRX wrist2;
    
    private VictorSPX intake;

    private final int intakeSpeed = 1;
    private final int outtakeSpeed = -1;

    public Wrist() {

        wrist = RobotMap.wristMotor;
        wrist2 = RobotMap.wristMotor2;

        intake = RobotMap.Intake;

        wrist.configAllSettings(Configs.wrist);
        wrist2.configFactoryDefault();

        wrist.setInverted(false);

        wrist2.follow(wrist);
        wrist2.setInverted(InvertType.FollowMaster);

        //Setting control mode to 0, that way it is stuck at 0 when first enabled
        wrist.set(ControlMode.PercentOutput, 0);
    }

    //Setting the neutral mode to be used in another class. 
    public void setNeutralMode(NeutralMode nm) {

        wrist.setNeutralMode(nm);

    }

    //The drive method, that will give you control over the power in teleop control
    public void driveDirect(double percentOutput) {

        wrist.set(ControlMode.PercentOutput, percentOutput);
    }

    public void suck() {

        intake.set(ControlMode.PercentOutput, intakeSpeed);

    }

    public void spit() {

        intake.set(ControlMode.PercentOutput, outtakeSpeed);

    }

}