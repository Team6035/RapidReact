package frc.sequencer.owen;

import edu.wpi.first.wpilibj.Timer;
import frc.sequencer.SequenceTransition;

public class OwenTimer extends SequenceTransition
{

    @Override
    public void transStart() {
        myEndTime = Timer.getFPGATimestamp() + myDelay;

    }

    @Override
    public boolean transUpdate() {
        return isTransComplete();
    }

    @Override
    public boolean isTransComplete() {
        if (Timer.getFPGATimestamp() >= myEndTime)
        {
            return true;
        }
        return false;
    }

    private double myDelay = 0;
    private double myEndTime; 
    
    public void setDelay(double aDelay)
    {
        myDelay = aDelay;
    }
    
}
