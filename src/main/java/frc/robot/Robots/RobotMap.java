package frc.robot.Robots;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

//Creating all of the devices needed for this robot code
public class RobotMap {

    /* --- Motor Objects --- */
    public static TalonSRX leftDrive;
    public static TalonSRX leftDrive2;

    public static TalonSRX rightDrive;
    public static TalonSRX rightDrive2;

    public static TalonSRX wristMotor;
    public static TalonSRX wristMotor2;

    public static TalonSRX armMotor;
    public static TalonSRX armMotor2;

    public static VictorSPX liftMotor;
    public static VictorSPX liftMotor2;

    public static VictorSPX Intake;

    /* --- Solenoid Objects --- */
    public static DoubleSolenoid hatch;
    public static DoubleSolenoid climb;

    /* Joystick Objects --- */
    public static Joystick driverJoystick;
    public static Joystick operatorJoystick;

    /* --- Motor Controller CAN IDs --- */
    public static final int kLeftDriveID = 1;
    public static final int kLeftDrive2ID = 2;
    public static final int kRightDriveID = 3;
    public static final int kRightDrive2ID = 4;
    public static final int kWristMotorID = 5;
    public static final int kWristMotor2ID = 6;
    public static final int kArmMotorID = 7;
    public static final int kArmMotor2ID = 8;
    public static final int kLiftMotorID = 1;
    public static final int kLiftMotor2ID = 2;
    public static final int kIntakeMotorID = 3;

    /* --- Solenoid IDs --- */
    public static final int kHatchForwardSolenoidID = 5;
    public static final int kHatchReverseSolenoidID = 6;

    public static final int kClimbForwardSolenoidID = 2;
    public static final int kClimbReverseSolenoidID = 3;

    /* --- Joystick IDs --- */
    public static final int kDriverJoystickID = 0;
    public static final int kOperatorJoystickID = 1;

    /* --- Driver Button/Axis IDs --- */
    //TODO: Map Driver Gamepad inputs here.
    public static final int kLeftDriveAxisID = 1;
    public static final int kRightDriveAxisID = 5;
    public static final int kLimeLightAssistButtonID = 0;
    public static final int kLimeLightOverrideButtonID = 0;
    public static final int kClimbButtonID = 14;

    /* --- Operator Button/Axis IDs --- */
    //TODO: Map Operator Gamepad inputs here.
    public static final int kFloorCargoButtonID = 7;
    public static final int kFloorHatchButtonID = 5;
    public static final int kCargoShipButtonID = 1;
    public static final int kLevelOneButtonID = 2;
    public static final int kLevelTwoButtonID = 3;
    public static final int kLevelThreeButtonID = 4;
    public static final int kSpitHatchPanelButtonID = 6;
    public static final int kOverrideCargoLimitSwitchButtonID = 10;
    public static final int kFloorHatchOverrideButtonID = 9;
    public static final int kIntakeJoystickAxis = 1;
    public static final int kGoToHomeButtonID = 14;

    //initiating all of the devices coded above so they are now connected to a physical device on the robot
    public static void Init() {

        leftDrive = new TalonSRX(kLeftDriveID);
        leftDrive2 = new TalonSRX(kLeftDrive2ID);
        
        rightDrive = new TalonSRX(kRightDriveID);
        rightDrive2 = new TalonSRX(kRightDrive2ID);

        wristMotor = new TalonSRX(kWristMotorID);
        wristMotor2 = new TalonSRX(kWristMotor2ID);

        //Elevator
        armMotor = new TalonSRX(kArmMotorID);
        armMotor2 = new TalonSRX(kArmMotor2ID);

        //Climber
        liftMotor = new VictorSPX(kLiftMotorID);
        liftMotor2 = new VictorSPX(kLiftMotor2ID);

        //Intake Motors
        Intake = new VictorSPX(kIntakeMotorID);

        //Solenoid for hatch panels
        hatch = new DoubleSolenoid(kHatchForwardSolenoidID, kHatchReverseSolenoidID);
        climb = new DoubleSolenoid(kClimbForwardSolenoidID, kClimbReverseSolenoidID);

        driverJoystick = new Joystick(kDriverJoystickID);
        operatorJoystick = new Joystick(kOperatorJoystickID);

    }
}