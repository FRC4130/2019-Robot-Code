package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Loops.PositionSet;
import frc.robot.Robots.RobotMap;

public class Arm {

   private TalonSRX arm;
   private TalonSRX arm2;

   private final int kTimeout = 5;
   private final int kPosBandwidth = 10;

   PositionSet currentTarget;

    public Arm() {

        arm = RobotMap.armMotor;
        arm2 = RobotMap.armMotor2;

        arm.configAllSettings(Configs.arm);
        arm2.configFactoryDefault();

        //TODO: See if these need to be changed
        arm.setInverted(false);
        arm2.follow(arm);
        arm2.setInverted(InvertType.FollowMaster);

        //Using Mag Encoder
        arm.setSensorPhase(true);

    }

    public boolean setTarget(PositionSet target) {

        currentTarget = target;

        arm.set(ControlMode.MotionMagic, currentTarget.arm);

        //TODO: Re-Write this
        if(arm.getClosedLoopError(0) < kPosBandwidth) {
            return true;
        }

        return false;

    }

    public double getError() {
        return arm.getSelectedSensorPosition(0) - currentTarget.arm;
    }

    public PositionSet getCurrentTarget(){
        return  currentTarget;
    }

    //Teleop Controls
    public void driveDirect(double percentOuput) {
        arm.set(ControlMode.PercentOutput, percentOuput);
    }

    public void setNeutralMode(NeutralMode nm) {
        arm.setNeutralMode(nm);

    }

}