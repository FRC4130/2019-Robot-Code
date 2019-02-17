package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Loops.ArmMode;
import frc.robot.Robots.RobotMap;

public class Arm {

   private TalonSRX arm;
   private TalonSRX arm2;

   private final int kTimeout = 5;
   private final int kPosBandwidth = 10;

   double targetHeightNativeUnits = 0;

   private ArmMode currentMode = ArmMode.Stowed;

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

    public double chainHeightToNative(double inches) {

        return (inches/79)*36764;

    }

    public double nativeToChainHeight(double n) {

        return (n/36764)*79;

    }

    public boolean setHeightInches(double inches) {

        return setHeight(chainHeightToNative(inches));
    }

    public boolean setHeightInches(ArmPosition pos) {
        return setHeightInches(pos.value);

    }

    public boolean setHeight(double valueNativeUnits) {

        if (valueNativeUnits > targetHeightNativeUnits) {
            arm.configMotionAcceleration(2000, kTimeout);
            arm.configMotionCruiseVelocity(1500, kTimeout);
        }

        else if (valueNativeUnits < targetHeightNativeUnits) {
            arm.configMotionAcceleration(6000, kTimeout);
            arm.configMotionCruiseVelocity(1400*6, kTimeout);

        }

        targetHeightNativeUnits = valueNativeUnits;

        arm.set(ControlMode.MotionMagic, valueNativeUnits);

        //TODO: Debounce this
        if(arm.getClosedLoopError(0) < kPosBandwidth) {
            return true;
        }

        return false;

    }

    public double getError() {

        return arm.getSelectedSensorPosition(0) - targetHeightNativeUnits;

    }

    //Teleop Controls
    public void driveDirect(double percentOuput) {
        arm.set(ControlMode.PercentOutput, percentOuput);
        
    }

    public void setNeutralMode(NeutralMode nm) {
        arm.setNeutralMode(nm);

    }

}