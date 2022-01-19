package frc.robot;


import edu.wpi.first.wpilibj.Joystick;

public class DriverControls
{
    Joystick stick;
    public DriverControls() {
        stick = new Joystick(0);
   }
   double steering;
   double power;

   public double deadZoneY(double input) {
    double output = input;

    if(stick.getY() <= 0.05 && stick.getY() >= -0.05) {
        output = 0;
    }
    return output;
    
}

public double deadZoneX(double input) {
    double output = input;

    if(stick.getX() <= 0.1 && stick.getX() >= -0.1) {
        output = 0;
    }
    return output;
    
}



	/**
	 * Get driver power command.
	 * @return Y from -1 to 1.
	 */
	public double getDrivePower() {
        power = -(deadZoneY(stick.getY()));
       return power;

    }
   /**
    * Get driver steering command.
    * @return X from -1 to 1.
    */
   public double getDriveSteering() {
       steering = (deadZoneX(stick.getX())*.7);
       return steering;
   }

   /**
    * Get driver throttle command.
    * @return Throttle from 0 to 1.
    */
   public double getDriveThrottle() {
       return ((-stick.getThrottle() + 1) / 2);
   }


}