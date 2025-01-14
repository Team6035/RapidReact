
package frc.robot;

public class Constants {

    public static int kLeftDriveAPort = 0;
    public static int kLeftDriveBPort = 1;

    public static int kRightDriveAPort = 2;
    public static int kRightDriveBPort = 3;

	public static int kShooterBottomCanID = 7;
	public static int kShooterTopCanID = 8;

	public static final int kLeftWinchCanID = 9;
	public static final int kRightWinchCanID = 10;

	public static int kPCMCanID = 50;

	public static int kIntakeMotorCanID = 5;
	public static final int kIntakeSolenoidAChannel = 6;
	public static final int kIntakeSolenoidBChannel = 7;


	public static final int kFeedMotorCanID = 6;

    public static double kDriveMaxSpeed = 4.18;
	public static double kDriveMaxAccel = 4.0;
	public static double kDriveMaxTurnSpeed = 12.12;
	public static double kDriveMaxTurnAccel = 24.24;
    
	public static final double kVisionTurnKp = 0.029;//was 0.024; // 0.012 for 2 centre nitrile
	public static final double kDriveTurnStictionConstant = 0.0; // FIXME
	public static final double kGyroTurnKp = 0.007; // 0.004 for 2 centre nitrile
	public static final double kGyroDriveTurnKp = 0.012;  // FIXME
	public static final double kEncoderDriveKp = 0.7;  // FIXME
	public static final double kDriveEncoderConversionFactor = 0.000023077; //1; //0.00089291;

	/**
	 * Number of pulses per revolution of the drive wheel encoder.
	 * The encoder is connected to the drive wheel axle, so there is no gearbox between it and the wheels.
	 */
	public static final double kEncoderPulsesPerRev = 360;
	/**
	 * Drive wheel diameter. This is approximate at the moment and it is this value that should be adjusted
	 * in order to calibrate the encoders.
	 */
    public static final double kDriveWheelDia = 0.1505;
	/**
	 * Calculated distance in millimetres that the robot is driven per encoder pulse.
	 * To calibrate this value, adjust {@link #kDriveWheelDia}
	 */
    public static final double kEncoderMPerPulse = kDriveWheelDia * Math.PI / kEncoderPulsesPerRev;


	public static final double kShooterP = 0.3;
	public static final double kShooterI = 0.00014;

	public static final double kClimberWinchP = 0.0623;

}