package frc.sequencer;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake.IntakeStates;
import frc.robot.subsystems.Shooter.ShooterState;

public class ShootStep extends SequenceTransition implements SequenceStepIf {
    

    public void stepStart()
    {
        Shooter.getInstance().setDesiredState(Shooter.ShooterState.SHOOTING);
        Intake.getInstance().setDesiredState(IntakeStates.EXTEND_ONLY);
    }

    public void stepEnd()
    {
        Shooter.getInstance().setDesiredState(Shooter.ShooterState.IDLE);
        Intake.getInstance().setDesiredState(IntakeStates.IDLE);
        Shooter.getInstance().runFeed(0);
    }

    public void stepUpdate()
    {
        if (Shooter.getInstance().getShooterAtSpeed())
        {
            Shooter.getInstance().runFeed(1);
        }
    }

    public String stepName()
    {
        return "ShootStep";
    }

    @Override
    public void transStart() {
        // Do nothing
    }

    @Override
    public boolean transUpdate() {
        // Do nothing
        return isTransComplete();
    }

    @Override
    public boolean isTransComplete() {
        return Shooter.getInstance().getShooterAtSpeed();
    }

}
