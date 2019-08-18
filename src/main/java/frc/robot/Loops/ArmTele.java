
package frc.robot.Loops;

import com.ctre.phoenix.ILoopable;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robots.RobotMap;
import frc.robot.Robots.Subsystems;
import frc.robot.Subsystems.Arm;
import frc.robot.Subsystems.Wrist;

public class ArmTele implements ILoopable {

    Arm  _arm;
    Joystick _joystick;
    Wrist _wrist;

    boolean manualOverride;
    double manualJoystick;

    boolean goHome;
    boolean manualHome;

    boolean goToFloor;
    boolean goToCargoShip;
    boolean goToLevelOne;
    boolean goToLevelTwo;
    boolean goToLevelThree;

    boolean useCargo;

    boolean overrideHatchPanelFromFloor;
    boolean overrideUseCargo;

    boolean hatchPrevious;
    boolean hatch;
    boolean cargoPrevious;
    boolean cargo;

    double intakeThrottle;

    PositionSet currentTarget = Positions.home;
    ScoringPositions currentGamePiecePositions = Positions.hatch;

    public final boolean WristLimitSwitch = RobotMap.wristInput.get();

    public ArmTele() {
        _arm = Subsystems.arm;
        _joystick = RobotMap.operatorJoystick;
        _wrist = Subsystems.wrist;
    }

    public void onStart() {
        System.out.println("[INFO] Teleop Arm Controls are Starting");
        _arm.setNeutralMode(NeutralMode.Brake);
    }

    public void onLoop() {
        updateInputs();
        updateTarget();
        updateArm();
        ManualDrive();

        // if(Subsystems.wrist.getCargoLimitSwitch()) {
        //     _wrist.setIntake(0);
        // }

    }

    public void updateInputs(){
        manualOverride = false;
        manualJoystick = 0;

        hatchPrevious = hatch;
        hatch = _joystick.getRawButton(RobotMap.kFloorHatchOverrideButtonID);
        
        cargoPrevious = cargo;
        cargo = _joystick.getRawButton(RobotMap.kOverrideCargoLimitSwitchButtonID);

        if(hatch && !hatchPrevious){
            if(overrideHatchPanelFromFloor){overrideHatchPanelFromFloor = false;}
            else{overrideHatchPanelFromFloor = true;}
        }

        if(cargo && !cargoPrevious){
            if(overrideUseCargo){overrideUseCargo = false;}
            else{overrideUseCargo = true;}
        }

        SmartDashboard.putBoolean("Hatch Floor Override", overrideHatchPanelFromFloor);

        goHome = _joystick.getRawButton(RobotMap.kGoToHomeButtonID);

        goToFloor = _joystick.getRawButton(RobotMap.kFloorCargoButtonID) || _joystick.getRawButton(RobotMap.kFloorHatchButtonID);
        goToCargoShip = _joystick.getRawButton(RobotMap.kCargoShipButtonID);
        goToLevelOne = _joystick.getRawButton(RobotMap.kLevelOneButtonID);
        goToLevelTwo = _joystick.getRawButton(RobotMap.kLevelTwoButtonID);
        goToLevelThree = _joystick.getRawButton(RobotMap.kLevelThreeButtonID);
        useCargo = overrideUseCargo || Subsystems.wrist.getCargoLimitSwitch() || _joystick.getRawButton(RobotMap.kFloorCargoButtonID);
        SmartDashboard.putBoolean("Cargo  Override", overrideUseCargo);

        goToCargoShip = _joystick.getRawButton(RobotMap.kCargoShipButtonID);
        //Write this to the smart dashboard so we know if we're using Cargo
        SmartDashboard.putBoolean("Use Cargo?", useCargo);
        intakeThrottle = _joystick.getRawAxis(RobotMap.kIntakeJoystickAxis);
    }

    public void updateTarget(){
        if(goHome){
            currentTarget = Positions.home;

            if(WristLimitSwitch) {
                _wrist.resetFWDEncoder();

            }
            else if(!WristLimitSwitch) {
                currentTarget = Positions.home;
                
            }

        }
        else{
            if(useCargo){currentGamePiecePositions = Positions.cargo;}
            else if (overrideHatchPanelFromFloor){
                currentGamePiecePositions = Positions.floorHatch;
            }   
            else {currentGamePiecePositions = Positions.hatch;}

            if(goToFloor){currentTarget = currentGamePiecePositions.floor;}
            else if(goToCargoShip){currentTarget = currentGamePiecePositions.ship;}
            else if(goToLevelOne){currentTarget = currentGamePiecePositions.levelOne;}
            else if(goToLevelTwo){currentTarget = currentGamePiecePositions.levelTwo;}
            else if(goToLevelThree){currentTarget = currentGamePiecePositions.levelThree;}
        }
    }

    public void updateArm(){
        _arm.setTarget(currentTarget);
    }

    public void ManualDrive() {
        if(_joystick.getRawButton(13)) {
            _arm.driveDirect(_joystick.getRawAxis(5)*-1);

        }
    }

    public boolean isDone() {
        return false;
    }

    public void onStop() {

        System.out.println("[WARNING} Teleop Elevator Controls have Stopped");
        _arm.driveDirect(0);
        _arm.setNeutralMode(NeutralMode.Brake);
    }

}