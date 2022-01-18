
package frc.robot;

public class Constants {

    public static int kLeftDriveACanID = 1;
    public static int kLeftDriveBCanID = 2;
	public static int kLeftDriveCCanID = 3;

    public static int kRightDriveACanID = 5;
    public static int kRightDriveBCanID = 6;
	public static int kRightDriveCCanID = 7;


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

}