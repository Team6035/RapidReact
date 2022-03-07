package frc.sequencer.owen;

import frc.robot.subsystems.Drive;
import frc.sequencer.SequenceStep;
import frc.sequencer.SequenceStepIf;
import frc.sequencer.SequenceTransition;

public class OwenDrive extends SequenceTransition implements SequenceStepIf {

    @Override
    public void stepStart() {
    }

    @Override
    public void stepEnd() {
        Drive.getInstance().autoArcadeDrive(0.0, 0.0);

    }

    @Override
    public void stepUpdate() {
        double distError = myEndDist - Drive.getInstance().getAvgEncoderDistance();
        double error = (myAngle - Drive.getInstance().getYaw()) % 360;
        double speed = Math.sqrt(Math.abs(distError)) * myDistGain; 

        if (error > 180)
        {error = error - 360;}
        else if (error < -180)
        {error = error + 360;}

        speed = Math.min(speed, myMaxSpeed);
        if (distError < 0)
        {
            speed = -speed;
        }
        System.out.println( "error "+distError+" speed " + speed);
        Drive.getInstance().autoArcadeDrive(error * myGain, speed);
        
    }
    
    private double mySpeed = 0;
    private double myGain = 0.01;
    private double myAngle = 0;
    private double myDist = 0;
    private double myEndDist = 0;
    private double myDistGain = 0.56;
    private double myDeadband = 0.1;
    private double myMaxSpeed = 0.7;

    public void setAngle (double aAngle)
    {
        myAngle = aAngle;
    }
    public void setDist (double aDist)
    {
        myDist = aDist;
    }

    @Override
    public String stepName() {
        // TODO Auto-generated method stub
        return "OwenDrive - d" + myDist + " a" + myAngle;
    }

	@Override
	public void transStart() {
        myEndDist = myDist + Drive.getInstance().getAvgEncoderDistance();
	}

	@Override
	public boolean transUpdate() {
		return isTransComplete();
	}

	@Override
	public boolean isTransComplete() {
        if ( Math.abs (myEndDist - Drive.getInstance().getAvgEncoderDistance()) < myDeadband)
        {
            return true;
        }
		return false;
	}
    
} 


