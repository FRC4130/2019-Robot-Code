package frc.robot.Loops;

public class Positions{

    public static ScoringPositions cargo;
    public static ScoringPositions hatch;
    public static ScoringPositions floorHatch;

    private static Positions _instance = new Positions();

    private Positions(){
        cargo = new ScoringPositions();
        hatch = new ScoringPositions();
        floorHatch = new ScoringPositions();

        /* --- Positions when handling Cargo --- */
        cargo.floor.arm = 0;
        cargo.floor.wrist = 0;

        cargo.loadingStation.arm = 0;
        cargo.loadingStation.wrist = 0;

        cargo.ship.arm = 0;
        cargo.ship.wrist = 0;

        cargo.levelOne.arm = 0;
        cargo.levelOne.wrist = 0;

        cargo.levelTwo.arm = 0;
        cargo.levelTwo.wrist = 0;

        cargo.levelThree.arm = 0;
        cargo.levelThree.wrist = 0;
        
        /* --- Positions when handling Hatch Panels --- */
        hatch.floor.arm = 0;
        hatch.floor.wrist = 0;

        hatch.loadingStation.arm = 0;
        hatch.loadingStation.wrist = 0;

        hatch.ship.arm = 0;
        hatch.ship.wrist = 0;

        hatch.levelOne.arm = 0;
        hatch.levelOne.wrist = 0;

        hatch.levelTwo.arm = 0;
        hatch.levelTwo.wrist = 0;

        hatch.levelThree.arm = 0;
        hatch.levelThree.wrist = 0;

        /* --- Positions when handling Hatch Panels from the Floor --- */
        floorHatch.floor.arm = 0;
        floorHatch.floor.wrist = 0;

        floorHatch.loadingStation.arm = 0;
        floorHatch.loadingStation.wrist = 0;

        floorHatch.ship.arm = 0;
        floorHatch.ship.wrist = 0;

        floorHatch.levelOne.arm = 0;
        floorHatch.levelOne.wrist = 0;

        floorHatch.levelTwo.arm = 0;
        floorHatch.levelTwo.wrist = 0;
        
        floorHatch.levelThree.arm = 0;
        floorHatch.levelThree.wrist = 0;
    }
}