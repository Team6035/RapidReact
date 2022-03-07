/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Config;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drive extends Subsystems {
    
    MotorControllerGroup mLeftDrive;
    MotorControllerGroup mRightDrive;
    RelativeEncoder mLeftEnc;
    RelativeEncoder mRightEnc;
    public static AHRS _imu;

    Encoder leftEnc = new Encoder(0, 1);
    Encoder rightEnc = new Encoder(2, 3);

    public double getLeftEnc()
    {
        return leftEnc.getDistance();
    }

    public double getRightEnc()
    {
        return rightEnc.getDistance();
    }

    
    ProfiledPIDController profiledDriveController = new ProfiledPIDController(
        Constants.kEncoderDriveKp, 0.0, 0.0,
        new TrapezoidProfile.Constraints(Constants.kDriveMaxSpeed, Constants.kDriveMaxAccel));
    
    ProfiledPIDController profiledTurnController = new ProfiledPIDController(
        Constants.kGyroTurnKp, 0.0, 0.0, 
        new TrapezoidProfile.Constraints(Constants.kDriveMaxTurnSpeed, Constants.kDriveMaxTurnAccel));


    public static Drive driveInstance;
    public static Drive getInstance() {
        if (driveInstance == null) {
            driveInstance = new Drive();
        }
        return driveInstance;
    }

    private Drive() {
        _imu = getImuInstance();
        leftEnc.setDistancePerPulse(-Constants.kEncoderMPerPulse);
        rightEnc.setDistancePerPulse(Constants.kEncoderMPerPulse);
    }
    
    public void setSystem(MotorControllerGroup leftDrive, MotorControllerGroup rightDrive, RelativeEncoder leftEncoder, RelativeEncoder rightEncoder) {
        mLeftDrive = leftDrive;
        mRightDrive = rightDrive;
        mLeftEnc = leftEncoder;
        mRightEnc = rightEncoder;
    }
  

    //Drive control

    private double lastLeftSPeed = 0;
    private double lastRightSPeed = 0;
    private double autoRampRateFwd = 0.02;
    private double autoRampRateRev = 0.02;
    private double manRampRateFwd = 0.05;
    private double manRampRateRev = 0.5;
    static public enum gameMode {
        DISABLED,
        TELEOP,
        AUTO,
        TEST
    };
    private gameMode currentMode = gameMode.DISABLED;
    public void setGameMode(gameMode aMode)
    {
        currentMode = aMode;
    }

    public void setMotors(double leftPower, double rightPower) {
        if (currentMode == gameMode.AUTO)
        {
            double leftDiff = leftPower - lastLeftSPeed;
            leftDiff = Math.min(leftDiff, autoRampRateRev);
            leftDiff = Math.max(leftDiff, -autoRampRateFwd);
            leftPower = lastLeftSPeed + leftDiff;

            double rightDiff = rightPower - lastRightSPeed;
            rightDiff = Math.min(rightDiff, autoRampRateRev);
            rightDiff = Math.max(rightDiff, -autoRampRateFwd);
            rightPower = lastRightSPeed + rightDiff;
        }
        else
        {
            double leftDiff = leftPower - lastLeftSPeed;
            leftDiff = Math.min(leftDiff, manRampRateFwd);
            leftDiff = Math.max(leftDiff, -manRampRateRev);
            leftPower = lastLeftSPeed + leftDiff;

            double rightDiff = rightPower - lastRightSPeed;
            rightDiff = Math.min(rightDiff, manRampRateFwd);
            rightDiff = Math.max(rightDiff, -manRampRateRev);
            rightPower = lastRightSPeed + rightDiff;
        }

       //System.out.println("Mode - " + currentMode);
        //set motors
       // System.out.println("leftpower " + leftPower);
        RobotMap.getLeftDriveA().set(leftPower * Config.kInvertDir);
        RobotMap.getLeftDriveB().set(leftPower * Config.kInvertDir);


      // System.out.println("rightpower " + rightPower);

        RobotMap.getRightDriveA().set(rightPower * Config.kInvertDir);
        RobotMap.getRightDriveB().set(rightPower * Config.kInvertDir);


        lastLeftSPeed = leftPower;
        lastRightSPeed = rightPower;
    }

    public void arcadeDrive(double throttle, double steering, double power, double microAdjust) {
        //Left
        double leftPower = (power + (steering + microAdjust)) * throttle;
        //Right
        double rightPower = (power - (steering + microAdjust)) * throttle;
        //Write to motors
        setMotors(leftPower, -rightPower);
    }

    public void arcadeDrive(double throttle, double steering, double power) {
        arcadeDrive(throttle, steering, power, 0);
    }

   /**
    * Drives the robot, calculating other settings
    *Stall detection disabled currently
    * @param throttle
    * @param steering
    * @param power
    * @param stallSense
    */
    public void smartDrive(double throttle, double steering, double power, boolean stallSense) {
        double pitch = _imu.getPitch();
        //double stallThreshold = (Config.kStallThreshold);
        double tipThreshold = (Config.kTipThreshold);
        if(pitch > tipThreshold && stallSense == true) {
            setMotors(-Config.kTipCorrectionPower, -Config.kTipCorrectionPower);
            System.out.println("PITCH > THRESHOLD");
        } else if(pitch < -tipThreshold && stallSense == true) {
            setMotors(Config.kTipCorrectionPower, Config.kTipCorrectionPower);
            System.out.println("PITCH < THRESHOLD");
        } else {
            //Normal
              arcadeDrive(throttle, steering, power);
        }
    }
    /*
    private void steerPriority(double left, double right)
    {
        if (left - right > 2)
        {
            left = 1;
            right = -1;
        }
        else if (right - left > 2)
        {
            left = -1;
            right = 1;
        }
        else if (Math.max(right, left) > 1)
        {
            left = left - (Math.max(right,left) - 1);
            right = right - (Math.max(right,left) - 1);
        }
        else if (Math.min(right, left) < -1)
        {
            left = left - (Math.min(right,left) + 1);
            right = right - (Math.min(right,left) + 1);
        }
        SmartDashboard.putNumber("leftPower", left);
        SmartDashboard.putNumber("rightPower", left);
        SmartDashboard.putNumber("SteerLeft", left-right);
        // leftDrive.set(-left);
        // rightDrive.set(right);
        setMotors(left, -right);
    }


*/

   /**
    * @param targetHeading
    * @param maxRate not used
    * @return true when done
    */
    public boolean actionGyroTurn(double targetHeading, int maxRate) {
		double currentRate = _imu.getRate();
		double currentHeading = _imu.getYaw();
        double steering = (targetHeading - currentHeading) * Constants.kGyroTurnKp;
        // profiledTurnController.setConstraints(new TrapezoidProfile.Constraints(maxRate, Constants.kDriveMaxTurnAccel));
        // double steering = profiledTurnController.calculate(currentHeading, targetHeading);
		arcadeDrive(1.0, steering, 0.0);

		return (Math.abs(targetHeading - currentHeading) <= Config.kDriveGyroTurnThresh) && (Math.abs(currentRate) <= Config.kDriveGyroRateThresh);

    }

    /**
	 * Drive at a given distance and gyro heading.
	 * @param speed Maximum speed in m/s
	 * @param targetHeading in degrees.
	 * @param distance in metres.
	 * @return True when driven to given distance, within a threshold. @see getEncoderWithinDistance()
	 */
	public boolean actionSensorDrive(double maxPower, double targetHeading, double distance) {
		double steering = (targetHeading - _imu.getYaw()) * Constants.kGyroDriveTurnKp;
        double power = profiledDriveController.calculate(getAvgEncoderDistance(), distance);
		if (power >= 0 && power > maxPower) {
			power = maxPower;
		} else if (power < 0 && power < -maxPower) {
			power = -maxPower;
		}
        
		arcadeDrive(1.0, steering, power);
		return encoderIsWithinDistance(distance, 0.05);
    }



    public AHRS getImuInstance() {
		if (_imu == null) {
			_imu = new AHRS(SPI.Port.kMXP); // Must be over SPI so the JeVois can communicate through UART Serial.
		}
		return _imu;
    }
    /**
	 * Returns true if the average of the two encoders are within a certain range.
	 * @param distance is metres
	 * @param threshRange +/- in metres
	 * @return Boolean true when within range.
	 */
	public boolean encoderIsWithinDistance(double distance, double threshRange) {
		return (distance - getAvgEncoderDistance()) < threshRange;
	}

	/**
	 * Returns the average of the two drive encoders.
	 * @return average distance since reset in metres.
	 */
	public double getAvgEncoderDistance() {
        return (leftEnc.getDistance() + rightEnc.getDistance())/2;
    }

    /**
     * Returns gyro in degrees
     * @return gyro angle in degrees
     */
    public double getYaw() {
        return _imu.getYaw() + myAngleOffset;
    }

    public void update() {

    }
    
    public void resetSensors() {
        _imu.reset();
    }

        /**
     * The offset between the gyro's angle and the robot's angle on the field.
     * This allows us to 'calibrate' the robot to a non-zero angle.
     * For the 2022 game, this will allow us to set the gyro's angle based on
     * the starting position on the field and still have 0 degrees being
     * forward facing.
     */
    private double myAngleOffset = 0;

    /**
     * Calibrates the robots angle to be the specified value at the current
     * position on the field.
     */
    public void setAngle(double anAngle)
    {
        zeroPosition();
        myAngleOffset = anAngle;
    }

    public boolean zeroPosition() {
        _imu.zeroYaw();
        return false;
    }

    /**
     * Set the motor controllers' brakes on or off.
     * @param brake True to enable brake mode, false to set to coast.
     */ 
	public void setBrakes(boolean brake) {
        if (brake == myBrakeState)
        {
            return;
        }
        if (brake) {


        } else {
        }
        myBrakeState = brake;       
	}
    private boolean myBrakeState = false;

    /**
     * 
     * @return True if motor controllers are both set to brake.
     */
	public boolean getBrakes() {
		return myBrakeState;
	}

    @Override
    public boolean initMechanism() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public diagnosticState getDiagnosticState() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void initMotorControllers() {
        // TODO Auto-generated method stub
        
    }
    @Override
    public diagnosticState test() {
        
        return null;
    }

        /**
     * Steering rate as set by the auto code
     */
    private double myAutoSteer = 0.0;
    /**
     * Drive speed as set by the auto code
     */
    private double myAutoPower = 0.0;
    /**
     * Steering rate as used during the last update
     */
    private double myLastAutoSteer = 0.0;
    /**
     * Drive speed as used during the last update
     */
    private double myLastAutoPower = 0.0;
    /**
     * The maximum steering command that the auto code is allowed to give.
     * Any steering command is limited to +/- this value.
     */
    private final static double AUTO_STEER_LIMIT = 0.5;
    /**
     * The maximum power output that the auto code is allowed to give.
     * Any power command is limited to +/- this calue.
     */
    private final static double AUTO_POWER_LIMIT = 1.0;
    /**
     * The maximum change per scan that the steering command can change.
     * At a 20ms scan rate with this set to 0.1, it will take 100ms
     * for the steering command to change from 0 to 0.5 (the limit)
     */
    private final static double STEER_RATE_LIMIT = 0.1;
    /**
     * The maximum change per scan that the power command can increase.
     * At 20ms scan rate with this set to 0.1, it will take 200ms
     * to accelerate from zero to 1 (100%)
     * @see #ACC_REV_LIMIT for the reverse equivalent.
     */
    private final static double ACC_FWD_LIMIT = 0.1;
    /**
     * The maximum change per scan that the power command can decrease.
     * At 20ms scan rate with this set to 0.2, it will take 100ms
     * to accelerate from zero to -1 (-100% or full reverse)
     * @see #ACC_FWD_LIMIT for the forwards equivalent.
     */
    private final static double ACC_REV_LIMIT = 0.2;

    /**
     * Sets the desired speed and steering for auto.
     * This is called by the sequence steps. This does not update the actual
     * drive motors until {@link #autoUpdate()} is called, so a sequence step
     * can set the speed to zero when exiting and the next sequence step can
     * set the speed to a different value upon starting and the last speed
     * set will be used when the update is called.
     * @param steering
     * @param power
     */
    public void autoArcadeDrive(double steering, double power)
    {
        // Limit the power and steering commands to within +/- the maximums.
        myAutoPower = Math.max(-AUTO_POWER_LIMIT, Math.min(AUTO_POWER_LIMIT, -power));
        myAutoSteer = Math.max(-AUTO_STEER_LIMIT, Math.min(AUTO_STEER_LIMIT, steering));

        // If the power + steering commands will exceed the +/- 1 range, then reduce the power
        // so that the steering command still has full effect.
        if ((myAutoPower + Math.abs(myAutoSteer)) > 1)
        {
            myAutoPower = 1 - Math.abs(myAutoSteer);
        }
        if ((myAutoPower - Math.abs(myAutoSteer)) < -1)
        {
            myAutoPower = -1 + Math.abs(myAutoSteer);
        }
    }

    /**
     * Separates the ramping into a periodically updated method. This keeps the
     * ramp rate constant even when the speed is set multiple times per scan
     * via {@link #autoArcadeDrive(double, double)} and also to allow ramping
     * to zero when the auto sequence sets the speed to zero and moves onto a
     * sequence step that doesn't update the drive speeds any more.
     * This method needs to be called every scan during update, and needs to be
     * called after the sequencer update is run.
     */
    public void autoUpdate()
    {
        // Find out how much the power and steering commands want to change, then limit this
        double steerDiff = myAutoSteer - myLastAutoSteer;
        steerDiff = Math.max(-STEER_RATE_LIMIT, Math.min(STEER_RATE_LIMIT, steerDiff));
        myLastAutoSteer = myLastAutoSteer + steerDiff;
        double powerDiff = myAutoPower - myLastAutoPower;
        powerDiff = Math.max(-ACC_REV_LIMIT, Math.min(ACC_FWD_LIMIT, powerDiff));
        myLastAutoPower = myLastAutoPower + powerDiff;

        // Calculate the individual motor outputs and send to motors.
        double leftPower = (myLastAutoPower + myLastAutoSteer);
        double rightPower = -(myLastAutoPower - myLastAutoSteer);
        RobotMap.getLeftDriveA().set(leftPower * Config.kInvertDir);
        RobotMap.getLeftDriveB().set(leftPower * Config.kInvertDir);
        RobotMap.getRightDriveA().set(rightPower * Config.kInvertDir);
        RobotMap.getRightDriveB().set(rightPower * Config.kInvertDir);
        lastLeftSPeed = leftPower;
        lastRightSPeed = rightPower;
        SmartDashboard.putNumber("Left Auto Drive", leftPower);
        SmartDashboard.putNumber("Right Auto Drive", rightPower);
    }

}