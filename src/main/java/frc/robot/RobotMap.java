package frc.robot;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;



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

}
