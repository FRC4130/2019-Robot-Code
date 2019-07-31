// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.Subsystems;

// import edu.wpi.first.wpilibj.SerialPort;
// import edu.wpi.first.wpilibj.SerialPort.Port;

// public class Arduino {

//     SerialPort serialPort = new SerialPort(4800, Port.kUSB1);
//     int brightness = 225/10;

//     public void send(int red, int green, int blue, String animation) {

//         red = red * (brightness/255);
//         green = green * (brightness/255);
//         blue = blue * (brightness/255);
        
//         serialPort.writeString(Integer.toString(red)+" "+Integer.toString(green)+" "+Integer.toString(blue)+" "+animation);

//     }

//     public void sendDefault() {

//         this.send(0, 0, 255, "wave");

//     }

// }
