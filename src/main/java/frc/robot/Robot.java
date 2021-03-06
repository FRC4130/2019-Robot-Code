/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.schedulers.ConcurrentScheduler;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robots.Loops;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;


public class Robot extends TimedRobot {

    ConcurrentScheduler teleop;

  @Override
  public void robotInit() {

    RobotMap.Init();
    Subsystems.Init();
    Subsystems.wrist.resetFWDEncoder();

  }

  @Override
  public void robotPeriodic() {

    SmartDashboard.putNumber("Battery Voltage", RobotController.getBatteryVoltage());
    SmartDashboard.putBoolean("Wrist Limit Switch", RobotMap.wristInput.get());
    SmartDashboard.putNumber("Wrist Encoder Count", Subsystems.wrist.getEncoderPosition());
  }


  @Override
  public void autonomousInit() {

    teleop = new ConcurrentScheduler();
    Loops.sTeleop(teleop);
    teleop.startAll();
    Subsystems.wrist.resetFWDEncoder();
    // Subsystems.wrist.disableZeroOnForwardLim();
  }


  @Override
  public void autonomousPeriodic() {

    teleop.process();

  }

  public void teleopInit() {

    teleop = new ConcurrentScheduler();
    Loops.sTeleop(teleop);
    teleop.startAll();
    Subsystems.wrist.resetFWDEncoder();
    // Subsystems.wrist.disableZeroOnForwardLim();

  }

  @Override
  public void teleopPeriodic() {

    teleop.process();
  }

  @Override
  public void disabledInit(){
    // Subsystems.wrist.enableZeroOnForwardLim(0);
  }
}
