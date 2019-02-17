package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;

import frc.robot.Subsystems.Arm;

public class Arms implements ILoopable {

    Arm _arm;
    double height;
    double acceptableError;

    @Deprecated
    public Arms(Arm arm, double heightInches) {

        _arm = arm;
        height = _arm.chainHeightToNative(heightInches);
        
    }

    public void onStart() {

        System.out.println("[INFO] Elevating to");
        System.out.println(_arm.nativeToChainHeight(height));
        System.out.println("\"");

    }

    public void onLoop() {

        _arm.setHeight(height);
        

    }

    public boolean isDone() {

        if(Math.abs(_arm.getError()) < acceptableError) {

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