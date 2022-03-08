package frc.sequencer;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.IntakeStates;

public class IndexInStep implements SequenceStepIf {
    

    public void stepStart()
    {
        Intake.getInstance().setDesiredState(IntakeStates.INTAKING);
    }

    public void stepEnd()
    {
        Intake.getInstance().setDesiredState(IntakeStates.IDLE);
    }

    public void stepUpdate()
    {
        // Do nothing.
    }

    public String stepName()
    {
        return "ShootStep";
    }

}
