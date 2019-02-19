package frc.robot.Loops;

public class Positions{

    public static ScoringPositions cargo;
    public static ScoringPositions hatch;
    public static ScoringPositions floorHatch;

    public static PositionSet home;

    private static Positions _instance = new Positions();

    private Positions(){
        home = new PositionSet();
        cargo = new ScoringPositions();
        hatch = new ScoringPositions();
        floorHatch = new ScoringPositions();

        /* --- Home Position --- */
        home.arm = 0;
        home.wrist = 0;

        /* --- Positions when handling Cargo --- */
        cargo.floor.arm = 0;
        cargo.floor.wrist = 0;

        cargo.ship.arm = 1396;
        cargo.ship.wrist = -3061;

        cargo.levelOne.arm = 702;
        cargo.levelOne.wrist = -2226;

        cargo.levelTwo.arm = 1401;
        cargo.levelTwo.wrist = -2613;

        cargo.levelThree.arm = 2252;
        cargo.levelThree.wrist = -2730;
        
        /* --- Positions when handling Hatch Panels --- */
        hatch.floor.arm = 44;
        hatch.floor.wrist = -297;

        hatch.ship.arm = 563;
        hatch.ship.wrist = -1913;

        hatch.levelOne.arm = 563;
        hatch.levelOne.wrist = -1913;

        hatch.levelTwo.arm = 1303;
        hatch.levelTwo.wrist = -2264;

        hatch.levelThree.arm = 2216;
        hatch.levelThree.wrist = -2601;

        /* --- Positions when handling Hatch Panels from the Floor --- */
        floorHatch.floor.arm = 0;
        floorHatch.floor.wrist = 0;

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