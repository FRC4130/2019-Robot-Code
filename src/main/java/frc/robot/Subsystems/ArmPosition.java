package frc.robot.Subsystems;

public enum ArmPosition{
    /**
     * Height of "home" position.
     * 
     */
    Home(0),

    /**
     * Height of the Arm for Lower Position
     */
    Ground(618),

    /**
     * Height of Arm for Middle Position
     */
    Middle(1513),

    /** 
     * Height of Arm for Highest Position.
     */
    Max(2539);


    public final double value;

    ArmPosition(double initValue) {

        this.value = initValue;

    }

}