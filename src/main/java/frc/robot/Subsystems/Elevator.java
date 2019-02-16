package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import frc.robot.Robots.RobotMap;

public class Elevator {

   private TalonSRX elevate;
   private TalonSRX elevate2;

   private final int kTimeout = 5;
   private final int kPosBandwidth = 10;

   double targetHeightNativeUnits = 0;

    public Elevator() {

        elevate = RobotMap.elevateMotor;
        elevate2 = RobotMap.elevateMotor2;

        elevate2.follow(elevate);

        //Control gains, full up or full down
        elevate.configPeakOutputForward(1.0, kTimeout);
        elevate.configPeakOutputReverse(-1.0, kTimeout);
        elevate.configNominalOutputForward(0.0 , kTimeout);
        elevate.configNominalOutputReverse(0.0, kTimeout);

        //Motion Magic
        elevate.configMotionAcceleration(150);
        elevate.configMotionCruiseVelocity(150);
        elevate.config_kF(0, 3);
         elevate.config_kP(0, 3);

        //TODO: See if these need to be changed
        elevate.setInverted(false);
        elevate2.setInverted(false);

        //Using Mag Encoder
        elevate.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, kTimeout);
        elevate.setSensorPhase(true);

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

    public boolean setHeightInches(ElevatorPosition pos) {
        return setHeightInches(pos.value);

    }

    public boolean setHeight(double valueNativeUnits) {

        if (valueNativeUnits > targetHeightNativeUnits) {
            elevate.configMotionAcceleration(2000, kTimeout);
            elevate.configMotionCruiseVelocity(1500, kTimeout);
        }

        else if (valueNativeUnits < targetHeightNativeUnits) {
            elevate.configMotionAcceleration(6000, kTimeout);
            elevate.configMotionCruiseVelocity(1400*6, kTimeout);

        }

        targetHeightNativeUnits = valueNativeUnits;

        elevate.set(ControlMode.MotionMagic, valueNativeUnits);

        //TODO: Debounce this
        if(elevate.getClosedLoopError(0) < kPosBandwidth) {
            return true;
        }

        return false;

    }

    public double getError() {

        return elevate.getSelectedSensorPosition(0) - targetHeightNativeUnits;

    }

    //Teleop Controls
    public void driveDirect(double percentOuput) {
        elevate.set(ControlMode.PercentOutput, percentOuput);
        
    }

    public void setNeutralMode(NeutralMode nm) {
        elevate.setNeutralMode(nm);

    }

    public void Gravity(double percentOuput) {

        elevate.set(ControlMode.MotionMagic, 0, DemandType.ArbitraryFeedForward, percentOuput);

    }

}