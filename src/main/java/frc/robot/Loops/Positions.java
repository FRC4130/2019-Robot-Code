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
        cargo.floor.wrist = -0;

        cargo.ship.arm = 500;
        cargo.ship.wrist = -2000;

        cargo.levelOne.arm = 500;
        cargo.levelOne.wrist = -0;

        cargo.levelTwo.arm = 13500;
        cargo.levelTwo.wrist = -0;

        cargo.levelThree.arm = 2370;
        cargo.levelThree.wrist = -0;
        
        /* --- Positions when handling Hatch Panels --- */
        hatch.floor.arm = 0;
        hatch.floor.wrist = -0;

        hatch.ship.arm = 500;
        hatch.ship.wrist = -2200;

        hatch.levelOne.arm = 425;
        hatch.levelOne.wrist = -2000;

        hatch.levelTwo.arm = 1350;
        hatch.levelTwo.wrist = -2400;

        hatch.levelThree.arm = 2370;
        hatch.levelThree.wrist = -2850;

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