package frc.robot.Subsystems;

public enum ElevatorPosition {
    /**
     * Height of "home" position.
     * 
     */
    Home(0),

    /**
     * Height of Elevator for Lower Position
     */
    Ground(618),

    /**
     * Height of Elevator for Middle Position
     */
    Middle(1513),

    /** 
     * Height of Elevator for Highest Position.
     */
    Max(2539);


    public final double value;

    ElevatorPosition(double initValue) {

        this.value = initValue;

    }

}