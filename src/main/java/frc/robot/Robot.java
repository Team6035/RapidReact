// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.LinkedList;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;
import frc.sequencer.Sequence;
import frc.sequencer.SequenceTest;
import frc.sequencer.Sequencer;
import frc.sequencer.owen.OwenSequence;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  SendableChooser<Sequence> seqChooser;
  Sequencer mySeq;

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
  // START Sequence setup section
    // This grabs the different sequences and adds them to the
    // SmartDashboard menu selection.
    // As an example, SequenceTest.getSequences() has been setup
    // to provide some sequences, however others can be added as
    // well. Simply create a list of Sequence objects in seqList
    // and they will be added to the chooser menu.
    // Note that the very first item in the list will become the
    // default selection.
    LinkedList<Sequence> seqList = new LinkedList<Sequence>();
    // seqList.addAll(SequenceTest.getSequences());
    seqList.addAll(OwenSequence.getSequences());
    seqChooser = new SendableChooser<Sequence>();
    boolean first = true;
    for (Sequence s : seqList)
    {
      if (first)
      {
        first = false;
        seqChooser.setDefaultOption(s.getName(), s);
      }
      else
      {
        seqChooser.addOption(s.getName(), s);
      }
    }
    SmartDashboard.putData(seqChooser);
    //END Sequence setup section


    DriverInterface.getInstance().initSmartDashboard();
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
    SmartDashboard.putNumber("Yaw", Drive.getInstance().getYaw());
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
    Drive.getInstance().setGameMode(Drive.gameMode.AUTO);

    // The following code gets whichever sequence is selected
    // from the SmartDashboard and sets up the sequencer to
    // run it.
    Sequence selectedAuto = seqChooser.getSelected();
    Drive.getInstance().setAngle(getFieldAngle(selectedAuto.getStartPos()));
    mySeq = new Sequencer();
    mySeq.setInitialSteps(selectedAuto.getInitialSteps());
    mySeq.setInitialTransitions(selectedAuto.getInitialTransitions());
    mySeq.sequenceStart();


    Climber.getInstance().setSoftLimits(false);

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    mySeq.update();
    SmartDashboard.putString("Auto Step", mySeq.getStepName());
    Drive.getInstance().autoUpdate();
    Shooter.getInstance().update();
    Intake.getInstance().update();
    SmartDashboard.putString("IntakeMode", Intake.getInstance().getCurrentState()+"");
    Pneumatics.getInstance().update();

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
    Pneumatics.getInstance().update();
    Drive.getInstance().update();
    TeleopController.getInstance().callTeleopController();
    Intake.getInstance().update();
    Shooter.getInstance().update();
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

  private static double getFieldAngle(int aPosition)
  {
    if (aPosition == 1)
    {
      return -91.5;
    }
    if (aPosition == 2)
    {
      return -46.5;
    }
    if (aPosition == 3)
    {
      return -1.5;
    }
    if (aPosition == 4)
    {
      return 43.5;
    }
    return 0.0;
  }

}
