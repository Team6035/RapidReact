package frc.robot;


import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;

public class RobotMap {

	static TalonFX leftDriveA = new TalonFX(Constants.kLeftDriveACanID);
	static TalonFX leftDriveB = new TalonFX(Constants.kLeftDriveBCanID);
	static TalonFX leftDriveC = new TalonFX(Constants.kLeftDriveCCanID);
	//public static MotorControllerGroup leftDriveMotors = new MotorControllerGroup(leftDriveA, leftDriveB, leftDriveC);


	static TalonFX rightDriveA = new TalonFX(Constants.kRightDriveACanID);
	static TalonFX rightDriveB = new TalonFX(Constants.kRightDriveBCanID);
	static TalonFX rightDriveC = new TalonFX(Constants.kRightDriveCCanID);
	//public static MotorControllerGroup rightDriveMotors = new MotorControllerGroup(rightDriveA, rightDriveB, rightDriveC);

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

	static Compressor compressor = new Compressor(Constants.kPCMCanID, PneumaticsModuleType.REVPH);

	public static Compressor getCompressor() {
		return compressor;
	}


	static TalonFX intakeMotor = new TalonFX(Constants.kBackIntakeEscCanID);
	public static TalonFX getIntakeESC() {
		return intakeMotor;
	}

	static TalonFX leftWinch = new TalonFX(Constants.kLeftWinchCanID);

	public static TalonFX getWinch() {
		return leftWinch;
	}


	static PowerDistribution pdh = new PowerDistribution(Constants.kPDHCanID, ModuleType.kCTRE);

	public static PowerDistribution getPDH() {
		return pdh;
	}

}
