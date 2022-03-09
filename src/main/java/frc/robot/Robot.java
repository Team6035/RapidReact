// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  Drive drivetrain = Drive.getInstance();
  static DriverInterface m_driverInterface = new DriverInterface();

  static Pneumatics m_pneumatics;
  static Shooter m_shooter;
  static Drive m_drive;
  static Intake m_intake;
  static TeleopController m_teleopController;
  static Climber m_Climber;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    DriverInterface.getInstance().initSmartDashboard();
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    Shooter.getInstance().initMotorControllers();
    Climber.getInstance().initMotorControllers();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    if (isEnabled() && !Drive.getInstance().getBrakes()) { // set to brake when enabled if not already set to brake
      Drive.getInstance().setBrakes(true);
    }
    DriverInterface.getInstance().update();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    Climber.getInstance().setSoftLimits(false);

    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {

    Climber.getInstance().resetSensors();
    Climber.getInstance().setSoftLimits(false);
    DriverInterface.getInstance().printVersionNumber(Config.versionType, Config.version);

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    Shooter.getInstance().update();
    Pneumatics.getInstance().update();
    Drive.getInstance().update();
    TeleopController.getInstance().callTeleopController();
    Intake.getInstance().update();
    Climber.getInstance().update();

  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    Climber.getInstance().setSoftLimits(true);

  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
    Climber.getInstance().setSoftLimits(false);

  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    if(m_driverInterface.getLeftClimbPower() >= 0.25 || m_driverInterface.getLeftClimbPower() <= -0.25) {
        if(m_driverInterface.getRightClimbPower() >= 0.25 || m_driverInterface.getRightClimbPower() <= -0.25) {
          Climber.getInstance().climbManualPower(m_driverInterface.getLeftClimbPower(), m_driverInterface.getRightClimbPower());
        } else {
          Climber.getInstance().climbManualPower(m_driverInterface.getLeftClimbPower(), 0);
        }
    } else if(m_driverInterface.getRightClimbPower() >= 0.25 || m_driverInterface.getRightClimbPower() <= -0.25) {
      Climber.getInstance().climbManualPower(0, m_driverInterface.getRightClimbPower());
    } else {
      Climber.getInstance().climbManualPower(0, 0);
    }
  }
}
