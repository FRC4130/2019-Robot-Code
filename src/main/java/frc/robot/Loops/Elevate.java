package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;

import frc.robot.Subsystems.Elevator;

public class Elevate implements ILoopable {

    Elevator _elevator;
    double height;
    double acceptableError;

    @Deprecated
    public Elevate(Elevator ele, double heightInches) {

        _elevator = ele;
        height = _elevator.chainHeightToNative(heightInches);
        
    }

    public void onStart() {

        System.out.println("[INFO] Elevating to");
        System.out.println(_elevator.nativeToChainHeight(height));
        System.out.println("\"");

    }

    public void onLoop() {

        _elevator.setHeight(height);
        

    }

    public boolean isDone() {

        if(Math.abs(_elevator.getError()) < acceptableError) {

            System.out.print("[INFO] Finished elevating to");
            System.out.print(height);
            System.out.println("\"");
            return true;

        }

        return false;

    }

    public void onStop() {

        System.out.print("[WARNING] Elevate was stopped");

    }

}