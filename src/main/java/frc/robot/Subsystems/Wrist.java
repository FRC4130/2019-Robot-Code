package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.Loops.WristMode;
import frc.robot.Robots.RobotMap;

public class  Wrist {

    private TalonSRX wrist;
    private TalonSRX wrist2;
    
    private VictorSPX intake;

    private WristMode currentMode = WristMode.Encoder;
    private int encSlot = 0;
    private int pitchSlot = 1;

    public Wrist() {

        wrist = RobotMap.wristMotor;
        wrist2 = RobotMap.wristMotor2;

        intake = RobotMap.Intake;

        wrist.configAllSettings(Configs.wrist);
        wrist2.configFactoryDefault();

        wrist.setInverted(false);

        wrist2.follow(wrist);
        wrist2.setInverted(InvertType.OpposeMaster);

        wrist.setSensorPhase(false);

        intake.configFactoryDefault();
        intake.setInverted(false);

        //Setting control mode to 0, that way it is stuck at 0 when first enabled
        wrist.set(ControlMode.PercentOutput, 0);
        intake.set(ControlMode.PercentOutput, 0);
    }

    //Setting the neutral mode to be used in another class. 
    public void setNeutralMode(NeutralMode nm) {

        wrist.setNeutralMode(nm);
        intake.setNeutralMode(nm);
    }

    /**
     * Drives the wrist directly with percent output.
     * Not recommended for use except for tuning.
     * 
     * @param percentOutput The percent output to drive the wrist.  +output is up, -output is down.
     */
    public void driveDirect(double percentOutput) {

        this.set(WristMode.Manual, percentOutput);
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
    public void set(WristMode mode, double setpoint){
        updateMode(mode); //manage config changes if mode has changed.
        switch(mode){
            case Manual:
                wrist.set(ControlMode.PercentOutput, setpoint);
                break;
            case Encoder:
                wrist.set(ControlMode.MotionMagic, setpoint);
                break;
            case Pitch:
                //Don't use this for now - tuning not complete yet.
                wrist.set(ControlMode.Position, setpoint);
                break;
        }
    }

    /**
     * Manages the configuration changes when the wrist mode of operation changes.
     * Should be called before all Set() calls.
     * 
     * @param newMode Mode being commanded by caller.  May or may not be the same as the current mode.
     */
    private void updateMode(WristMode newMode){
        if(newMode != currentMode){
            switch(newMode){
                case Encoder:
                    wrist.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
                    wrist.selectProfileSlot(encSlot, 0);
                    break;
                case Pitch:
                    wrist.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0);
                    wrist.selectProfileSlot(pitchSlot, 0);
                    break;
                case Manual:
                    break;
            }
            currentMode = newMode;
        }
    }

    public void setIntake(double throttle){
        intake.set(ControlMode.PercentOutput, throttle);
    }

    // public void driveDirectWrist(double throttle) {
    //     wrist.set(ControlMode.PercentOutput, throttle);

    // }

    /**
     * Gets the state of the cargo presence limit switch.
     * Assumes switch is normally open.
     * @return true if limit switch is closed.
     */
    public boolean getCargoLimitSwitch(){
        return wrist.getSensorCollection().isRevLimitSwitchClosed();
    }

    public int getEncoderPosition(){
        return wrist.getSelectedSensorPosition();
    }

    public boolean getWristFwdLim() {
        return wrist.getSensorCollection().isFwdLimitSwitchClosed();

    }

    public void resetFWDEncoder() {
        wrist.setSelectedSensorPosition(0);

    }

    // public void enableZeroOnForwardLim(int TimeoutMS) {
    //     wrist.configClearPositionOnLimitF(true, TimeoutMS);
    // }

    // public void disableZeroOnForwardLim() {
    //     wrist.configClearPositionOnLimitF(true, 0);
    // }
}