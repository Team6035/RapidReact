package frc.sequencer.owen;

import frc.robot.subsystems.Drive;
import frc.sequencer.SequenceStep;
import frc.sequencer.SequenceStepIf;
import frc.sequencer.SequenceTransition;

public class OwenTurn extends SequenceTransition implements SequenceStepIf {

    public String stepName() {
        // TODO Auto-generated method stub
        return "OwenTurn - " + myAngle;
    }

    public void stepStart() {
        

    }

    public void stepEnd() {
        Drive.getInstance().autoArcadeDrive(0.0, 0.0);

    }

    public void stepUpdate() {
        // TODO Auto-generated method stub
        double error = myAngle - Drive.getInstance().getYaw();
        
        if (error > 180)
        {error = error - 360;}
        else if (error < -180)
        {error = error + 360;}

        double steer = 0.1 + Math.sqrt(Math.abs(error)) * myGain;

        if (error < 0)
        {
            steer = -steer;
        }
        Drive.getInstance().autoArcadeDrive(steer, 0);

    }
    private double myAngle = 0;
    public void setAngle (double aAngle)
    {
        myAngle = aAngle;
    }
    
    private double myGain = 0.1;
    private double myDeadband = 5;

    public void setGain(double aGain)
    {
        myGain = aGain;
    }

    public double getGain()
    {
        return myGain;
    }

    public void setDeadband(double aDeadband)
    {
        myDeadband = aDeadband;
    }

    public double getDeadband()
    {
        return myDeadband;
    }

    @Override
    public void transStart() {
    }

    @Override
    public boolean transUpdate() {
        return isTransComplete();
    }

    @Override
    public boolean isTransComplete() {
        double error = myAngle - Drive.getInstance().getYaw();
        
        if (error > 180)
        {error = error - 360;}
        else if (error < -180)
        {error = error + 360;}
        if (Math.abs(error) < myDeadband)
        {
            return true;
        }
        return false;
    }

 
}
