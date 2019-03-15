package frc.robot.Robots;

import frc.robot.Subsystems.Arm;
import frc.robot.Subsystems.Climb;
import frc.robot.Subsystems.ClimbAssist;
import frc.robot.Subsystems.DriveTrain;
import frc.robot.Subsystems.Hatch;
import frc.robot.Subsystems.Limelight;
import frc.robot.Subsystems.Wrist;

//This is pulling the individual subsystem classes out of the subsystem folder in the directory to the left
public class Subsystems {

    public static Arm arm;
    public static DriveTrain driveTrain;
    public static Climb climb;
    public static Wrist wrist;
    public static Hatch hatch;
    public static Limelight limelight;
    public static ClimbAssist climbAssist;

    // This is tagging the values i created above to the actual methods from the classes i already made in the Subsystems folder
    public static void Init() {

        arm = new Arm();
        driveTrain = new DriveTrain();
        climb = new Climb();
        wrist = new Wrist();
        hatch = new Hatch();
        limelight = new Limelight();
        // climbAssist = new ClimbAssist();

    }

}