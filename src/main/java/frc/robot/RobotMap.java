package frc.robot;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class RobotMap {

	static WPI_TalonFX leftDriveA = new WPI_TalonFX(Constants.kLeftDriveACanID);
	static WPI_TalonFX leftDriveB = new WPI_TalonFX(Constants.kLeftDriveBCanID);
	static WPI_TalonFX leftDriveC = new WPI_TalonFX(Constants.kLeftDriveCCanID);
	public static MotorControllerGroup leftDriveMotors = new MotorControllerGroup(leftDriveA, leftDriveB, leftDriveC);


	static WPI_TalonFX rightDriveA = new WPI_TalonFX(Constants.kRightDriveACanID);
	static WPI_TalonFX rightDriveB = new WPI_TalonFX(Constants.kRightDriveBCanID);
	static WPI_TalonFX rightDriveC = new WPI_TalonFX(Constants.kRightDriveCCanID);
	public static MotorControllerGroup rightDriveMotors = new MotorControllerGroup(rightDriveA, rightDriveB, rightDriveC);

    
	public static MotorControllerGroup getLeftDrive() {

		return leftDriveMotors;
	}
	public static MotorControllerGroup getRightDrive() {

		return rightDriveMotors;
	}
	public static WPI_TalonFX getLeftDriveA() {
		return leftDriveA;
	}

	public static WPI_TalonFX getRightDriveA() {
		return rightDriveA;
	}

	public static WPI_TalonFX getLeftDriveB() {
		return leftDriveB;
	}

	public static WPI_TalonFX getRightDriveB() {
		return rightDriveB;
	}

	public static WPI_TalonFX getLeftDriveC() {
		return leftDriveC;
	}

	public static WPI_TalonFX getRightDriveC() {
		return rightDriveC;
	}

	static WPI_TalonFX shooterBottom = new WPI_TalonFX(Constants.kShooterBottomCanID);
	static WPI_TalonFX shooterTop = new WPI_TalonFX(Constants.kShooterTopCanID);

	public static WPI_TalonFX getShooterBottom() {
		return shooterBottom;
	}

	public static WPI_TalonFX getShooterTop() {
		return shooterTop;
	}

	static Compressor compressor = new Compressor(Constants.kPCMCanID, PneumaticsModuleType.REVPH);

	public static Compressor getCompressor() {
		return compressor;
	}

	static WPI_TalonFX frontIntakeMotor = new WPI_TalonFX(Constants.kFrontIntakeEscCanID);
	static Solenoid frontIntakeSolenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.kFrontIntakeSolenoidChannel);
	
	public static WPI_TalonFX getFrontIntakeESC() {
		return frontIntakeMotor;
	}

	public static Solenoid getFrontIntakeSolenoid() {
		return frontIntakeSolenoid;
	}

	static WPI_TalonFX leftWinch = new WPI_TalonFX(Constants.kLeftWinchCanID);
	static WPI_TalonFX rightWinch = new WPI_TalonFX(Constants.kRightWinchCanID);

	public static WPI_TalonFX getLeftWinch() {
		return leftWinch;
	}

	public static WPI_TalonFX getRightWinch() {
		return rightWinch;
	}

}
