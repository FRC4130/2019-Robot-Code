package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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

        /**
     * Set the target of the wrist.  Can be used for any mode.
     * 
     * @param mode Operation mode of the wrist.  Can be Manual, Encoder, or Pitch.
     * @param setpoint Target setpoint for the wrist.  Units depend on the mode.
     *                  Manual: Percent Output [-1, 1]
     *                  Encoder: Position in Encoder ticks.
     *                  Pitch: Position in Pigeon Pitch units.
     */
    public void set(ArmMode mode, double setpoint){
        updateMode(mode); //manage config changes if mode has changed.
        switch(mode){
            case Manual:
                arm.set(ControlMode.PercentOutput, setpoint);
                break;
            case Active:
                arm.set(ControlMode.MotionMagic, setpoint);
                break;
            case Stowed:
                //Don't use this for now - tuning not complete yet.
                arm.set(ControlMode.Position, setpoint);
                break;
        }
    }

    /**
     * Manages the configuration changes when the wrist mode of operation changes.
     * Should be called before all Set() calls.
     * 
     * @param newMode Mode being commanded by caller.  May or may not be the same as the current mode.
     */
    private void updateMode(ArmMode newMode){
        if(newMode != currentMode){
            switch(newMode){
                case Active:
                    arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
//                  arm.selectProfileSlot(encSlot, 0);
                    break;
                case Stowed:
                    arm.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0);
 //                   arm.selectProfileSlot(pitchSlot, 0);
                    break;
                case Manual:
                    break;
            }
            currentMode = newMode;
        }
    }

}