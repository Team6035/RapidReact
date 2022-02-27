package frc.robot;


import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

public class RobotMap {

	static VictorSP leftDriveA = new VictorSP(Constants.kLeftDriveAPort);
	static VictorSP leftDriveB = new VictorSP(Constants.kLeftDriveBPort);


	static VictorSP rightDriveA = new VictorSP(Constants.kRightDriveAPort);
	static VictorSP rightDriveB = new VictorSP(Constants.kRightDriveBPort);

	public static VictorSP getLeftDriveA() {
		return leftDriveA;
	}

	public static VictorSP getRightDriveA() {
		return rightDriveA;
	}

	public static VictorSP getLeftDriveB() {
		return leftDriveB;
	}

	public static VictorSP getRightDriveB() {
		return rightDriveB;
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

	static VictorSPX intakeMotor = new VictorSPX(Constants.kIntakeMotorCanID);
	static DoubleSolenoid intakeSolenoid = new DoubleSolenoid(Constants.kPCMCanID, PneumaticsModuleType.REVPH, Constants.kIntakeSolenoidAChannel, Constants.kIntakeSolenoidBChannel);	
	public static VictorSPX getIntakeESC() {
		return intakeMotor;
	}

	public static DoubleSolenoid getIntakeSolenoid() {
		return intakeSolenoid;
	}

	static VictorSPX feedMotor = new VictorSPX(Constants.kFeedMotorCanID);
	public static VictorSPX getFeedEsc() {
		return feedMotor;
	}


	static CANSparkMax leftWinch = new CANSparkMax(Constants.kLeftWinchCanID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);
	static CANSparkMax rightWinch = new CANSparkMax(Constants.kRightWinchCanID, com.revrobotics.CANSparkMaxLowLevel.MotorType.kBrushless);

	public static CANSparkMax getLeftWinch() {
		return leftWinch;
	}

	public static CANSparkMax getRightWinch() {
		return rightWinch;
	}
}
