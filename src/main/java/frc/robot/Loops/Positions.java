package frc.robot.Loops;

public class Positions{

    public static ScoringPositions cargo;
    public static ScoringPositions hatch;
    public static ScoringPositions floorHatch;

    public static PositionSet home;
    public static PositionSet manual;

    private static Positions _instance = new Positions();

    private Positions(){
        home = new PositionSet();
        manual = new PositionSet();
        cargo = new ScoringPositions();
        hatch = new ScoringPositions();
        floorHatch = new ScoringPositions();

        /* --- Home Position --- */
        home.arm = -70;
        home.wrist = 0;

        /*---Manual Home---*/
        manual.arm = 500;
        manual.wrist = 3000;

        /* --- Positions when handling Cargo --- */
        cargo.floor.arm = 200;
        cargo.floor.wrist = -2550;

        cargo.ship.arm = 1750;
        cargo.ship.wrist = -3525;

        cargo.levelOne.arm = 650;
        cargo.levelOne.wrist = -2055;

        cargo.levelTwo.arm = 1550;
        cargo.levelTwo.wrist = -2450;

        cargo.levelThree.arm = 2300;
        cargo.levelThree.wrist = -2675;
        
        /* --- Positions when handling Hatch Panels --- */

        //higher negative value on wirst = lower wrist position
        hatch.floor.arm = 0;
        hatch.floor.wrist = -0;

        hatch.ship.arm = 500;
        hatch.ship.wrist = -2200;

        hatch.levelOne.arm = 250;
        hatch.levelOne.wrist = -2175;   

        hatch.levelTwo.arm = 1200;
        hatch.levelTwo.wrist = -2550;

        hatch.levelThree.arm = 2275;
        hatch.levelThree.wrist = -3125;

        /* --- Positions when handling Hatch Panels from the Floor --- */
        floorHatch.floor.arm = 0;
        floorHatch.floor.wrist = -0;

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