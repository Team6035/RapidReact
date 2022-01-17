package frc.robot;


import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import com.ctre.phoenix.motorcontrol.can.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

public class RobotMap {

	static WPI_TalonFX leftDriveA = new WPI_TalonFX(Constants.kLeftDriveACanID);
	static WPI_TalonFX leftDriveB = new WPI_TalonFX(Constants.kLeftDriveBCanID);
	public static MotorControllerGroup leftDriveMotors = new MotorControllerGroup(leftDriveA, leftDriveB);


	static WPI_TalonFX rightDriveA = new WPI_TalonFX(Constants.kRightDriveACanID);
	static WPI_TalonFX rightDriveB = new WPI_TalonFX(Constants.kRightDriveBCanID);
	public static MotorControllerGroup rightDriveMotors = new MotorControllerGroup(rightDriveA, rightDriveB);

    
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

}
