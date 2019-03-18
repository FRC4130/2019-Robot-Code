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
        home.arm = -75;
        home.wrist = 0;

        /* --- Positions when handling Cargo --- */
        cargo.floor.arm = 282;
        cargo.floor.wrist = -2564;

        cargo.ship.arm = 1800;
        cargo.ship.wrist = -3400;

        cargo.levelOne.arm = 650;
        cargo.levelOne.wrist = -2033;

        cargo.levelTwo.arm = 1550;
        cargo.levelTwo.wrist = -2400;

        cargo.levelThree.arm = 2325;
        cargo.levelThree.wrist = -2650;
        
        /* --- Positions when handling Hatch Panels --- */

        //higher negative value on wirst = lower wrist position
        hatch.floor.arm = 0;
        hatch.floor.wrist = -0;

        hatch.ship.arm = 500;
        hatch.ship.wrist = -2200;

        hatch.levelOne.arm = 450;
        hatch.levelOne.wrist = -2200;

        hatch.levelTwo.arm = 1350;
        hatch.levelTwo.wrist = -2600;

        hatch.levelThree.arm = 2370;
        hatch.levelThree.wrist = -3100;

        /* --- Positions when handling Hatch Panels from the Floor --- */
        floorHatch.floor.arm = 107;
        floorHatch.floor.wrist = -2139;

        floorHatch.ship.arm = 0;
        floorHatch.ship.wrist = 0;

        floorHatch.levelOne.arm = 110;
        floorHatch.levelOne.wrist = -1076;

        floorHatch.levelTwo.arm = 0;
        floorHatch.levelTwo.wrist = 0;
        
        floorHatch.levelThree.arm = 0;
        floorHatch.levelThree.wrist = 0;
    }
    
}