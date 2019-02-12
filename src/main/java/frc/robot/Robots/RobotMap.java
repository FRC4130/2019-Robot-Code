package frc.robot.Robots;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;

//Creating all of the devices needed for this robot code
public class RobotMap {

    public static TalonSRX leftDrive;
    public static TalonSRX leftDrive2;

    public static TalonSRX rightDrive;
    public static TalonSRX rightDrive2;

    public static TalonSRX wristMotor;
    public static TalonSRX wristMotor2;

    public static TalonSRX elevateMotor;
    public static TalonSRX elevateMotor2;

    public static VictorSPX liftMotor;
    public static VictorSPX liftMotor2;

    public static VictorSPX Intake;

    public static DoubleSolenoid hatch;

    public static Joystick driverJoystick;
    public static Joystick operatorJoystick;

    //initiating all of the devices coded above so they are now connected to a physical device on the robot
    public static void Init() {

        leftDrive = new TalonSRX(1);
        leftDrive2 = new TalonSRX(2);
        
        rightDrive = new TalonSRX(3);
        rightDrive2 = new TalonSRX(4);

        wristMotor = new TalonSRX(5);
        wristMotor2 = new TalonSRX(6);

        //Elevator
        elevateMotor = new TalonSRX(7);
        elevateMotor2 = new TalonSRX(8);

        //Climber
        liftMotor = new VictorSPX(1);
        liftMotor2 = new VictorSPX(2);

        //Intake Motors
        Intake = new VictorSPX(3);

        //Solenoid for hatch panels
        hatch = new DoubleSolenoid(1, 2);

        driverJoystick = new Joystick(0);
        operatorJoystick = new Joystick(1);

    }
}