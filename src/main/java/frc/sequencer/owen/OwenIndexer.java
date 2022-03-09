package frc.sequencer.owen;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Intake.IntakeStates;
import frc.sequencer.SequenceStepIf;

public class OwenIndexer implements SequenceStepIf {

    @Override
    public void stepStart() {
        Intake.getInstance().setDesiredState(IntakeStates.INTAKING);
    }

    @Override
    public void stepEnd() {
        Intake.getInstance().setDesiredState(IntakeStates.IDLE);
    }

    @Override
    public void stepUpdate() {
    }

    @Override
    public String stepName() {
        return "OwenIndexer";
    }
    
}
