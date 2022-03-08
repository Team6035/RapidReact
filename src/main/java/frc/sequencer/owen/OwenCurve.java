package frc.sequencer.owen;

import frc.robot.subsystems.Drive;
import frc.sequencer.SequenceStepIf;
import frc.sequencer.SequenceTransition;

public class OwenCurve extends SequenceTransition implements SequenceStepIf
{

    @Override
    public void transStart() {
        startDist = Drive.getInstance().getAvgEncoderDistance();
        startAngle = Drive.getInstance().getYaw();

    }

    @Override
    public boolean transUpdate() {
        // TODO Auto-generated method stub
        return isTransComplete();
    }

    @Override
    public boolean isTransComplete() {
        // TODO Auto-generated method stub
        double error = (startAngle + angle - Drive.getInstance().getYaw()) % 360;
        
        if (error > 180)
        {error = error - 360;}
        else if (error < -180)
        {error = error + 360;}
        if (Math.abs(error) <myDeadband)
        {
            return true;
        }
        return false;
    }
        

    @Override
    public void stepStart() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stepEnd() {
        Drive.getInstance().autoArcadeDrive(0.0, 0.0);

    }

    @Override
    public void stepUpdate() {
        double nowDist = Drive.getInstance().getAvgEncoderDistance() - startDist;
        double nowDeltaAngle = (nowDist / (2 * Math.PI * radius)) * 360;
        double nowAngle;
        if (angle > 0)
        {
            nowAngle = startAngle + nowDeltaAngle;
        }
        else
        {
            nowAngle = startAngle - nowDeltaAngle;
        }
        double error = (nowAngle - Drive.getInstance().getYaw()) % 360;

        if (error > 180)
        {error = error - 360;}
        else if (error < -180)
        {error = error + 360;}

        Drive.getInstance().autoArcadeDrive(error * myGain, mySpeed);

    }

    @Override
    public String stepName() {
        return "OwenCurve - r" + radius + " a" + angle;
    }
    private double radius = 1.5;
    private double angle = 1;
    private double startDist = 1;
    private double startAngle = 1;
    private double myGain = 0.01;
    private double myDeadband = 5 ;
    private double mySpeed = 0.3 ;

    public void setAngle (double aAngle)
    {
        angle =aAngle;
    }
    public void setRadius (double aRadius)
    {
        radius =aRadius;
    }
    public void setSpeed (double aSpeed)
    {
        mySpeed =aSpeed;
    }


}  
