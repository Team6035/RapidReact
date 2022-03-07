package frc.sequencer.owen;

import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Shooter.ShooterState;
import frc.sequencer.SequenceStepIf;

public class owenShooter implements SequenceStepIf{

    @Override
    public void stepStart() {
        Shooter.getInstance().setDesiredState(ShooterState.SHOOTING);
    }

    @Override
    public void stepEnd() {
        Shooter.getInstance().setDesiredState(ShooterState.IDLE);
    }

    @Override
    public void stepUpdate() {
    }

    @Override
    public String stepName() {
        return "SHOOTING";
    }
    
}
