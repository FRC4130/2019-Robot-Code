package frc.robot.Robots;

import com.ctre.phoenix.schedulers.ConcurrentScheduler;

import frc.robot.Loops.ClimbTele;
import frc.robot.Loops.DriveTele;
import frc.robot.Loops.ElevateTele;
import frc.robot.Loops.HatchTele;
// import frc.robot.Loops.LimelightTele;
import frc.robot.Loops.WristTele;

public class Loops {

    //Teleop Loops
    public static void sTeleop(ConcurrentScheduler teleop) {

        System.out.println("Scheduling Teleop.");

        //Scheduling all tasks for Teleop
        teleop.add(new ElevateTele());
        teleop.add(new DriveTele());
        teleop.add(new ClimbTele());
        teleop.add(new WristTele());
        teleop.add(new HatchTele());
        // teleop.add(new LimelightTele());

        System.out.println("Scheduled");

    }

}