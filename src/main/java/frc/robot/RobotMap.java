package frc.robot;


import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
public class RobotMap {

	static WPI_TalonFX leftDriveA = new WPI_TalonFX(Constants.kLeftDriveACanID);
	static WPI_TalonFX leftDriveB = new WPI_TalonFX(Constants.kLeftDriveBCanID);
	static TalonFX leftDriveC = new TalonFX(Constants.kLeftDriveCCanID);
	public static MotorControllerGroup leftDriveMotors = new MotorControllerGroup(leftDriveA, leftDriveB);


	static WPI_TalonFX rightDriveA = new WPI_TalonFX(Constants.kRightDriveACanID);
	static WPI_TalonFX rightDriveB = new WPI_TalonFX(Constants.kRightDriveBCanID);
	static TalonFX rightDriveC = new TalonFX(Constants.kRightDriveCCanID);
	public static MotorControllerGroup rightDriveMotors = new MotorControllerGroup(rightDriveA, rightDriveB);

	public static TalonFX getLeftDriveA() {
		return leftDriveA;
	}

	public static TalonFX getRightDriveA() {
		return rightDriveA;
	}

	public static TalonFX getLeftDriveB() {
		return leftDriveB;
	}

	public static TalonFX getRightDriveB() {
		return rightDriveB;
	}

	public static TalonFX getLeftDriveC() {
		return leftDriveC;
	}

	public static TalonFX getRightDriveC() {
		return rightDriveC;
	}

	static TalonFX shooterBottom = new TalonFX(Constants.kShooterBottomCanID);
	static TalonFX shooterTop = new TalonFX(Constants.kShooterTopCanID);

	public static TalonFX getShooterBottom() {
		return shooterBottom;
	}

	public static TalonFX getShooterTop() {
		return shooterTop;
	}

	static Compressor compressor = new Compressor(Constants.kPCMCanID, PneumaticsModuleType.CTREPCM);

	public static Compressor getCompressor() {
		return compressor;
	}

	static TalonFX frontIntakeMotor = new TalonFX(Constants.kFrontIntakeEscCanID);
	static Solenoid frontIntakeSolenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.kFrontIntakeSolenoidChannel);
	
	public static TalonFX getFrontIntakeESC() {
		return frontIntakeMotor;
	}

	public static Solenoid getFrontIntakeSolenoid() {
		return frontIntakeSolenoid;
	}

	static TalonFX leftWinch = new TalonFX(Constants.kLeftWinchCanID);
	static TalonFX rightWinch = new TalonFX(Constants.kRightWinchCanID);

	public static TalonFX getLeftWinch() {
		return leftWinch;
	}

	public static TalonFX getRightWinch() {
		return rightWinch;
	}
	static WPI_TalonFX indexerMotor = new WPI_TalonFX(26);
	public static WPI_TalonFX getIndexerMotor(){
		return indexerMotor;
	}
	static Solenoid indexerFlap = new Solenoid(50, PneumaticsModuleType.CTREPCM, 5);
	public static Solenoid getIndexerSolenoid(){
		return indexerFlap;
	}
	static WPI_VictorSPX throatMotor = new WPI_VictorSPX(29);
	public static WPI_VictorSPX getThroat(){
		return throatMotor;
	}
}
