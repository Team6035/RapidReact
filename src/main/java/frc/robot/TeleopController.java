// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.DriverInterface.JoystickAxisType;
import frc.robot.DriverInterface.MessageType;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Climber.ClimberBarStates;
import frc.robot.subsystems.Climber.ClimberStates;
import frc.robot.subsystems.FrontIntake.FrontIntakeStates;
import frc.robot.subsystems.Shooter.ShooterSpeedSlot;
import frc.robot.subsystems.Shooter.ShooterState;

/** Add your docs here. */
public class TeleopController {

    private static Drive m_drive;
    private static FrontIntake m_frontIntake;
    private static Pneumatics m_pneumatics;
    private static Shooter m_shooter;
    private static DriverInterface m_driverInterface;
    private static TeleopController m_instance;
    private static Climber m_climber;

    private TeleopController() {
        m_driverInterface = new DriverInterface();
        m_drive = Drive.getInstance();
        m_pneumatics = Pneumatics.getInstance();
        m_shooter = Shooter.getInstance();
        m_frontIntake = FrontIntake.getInstance();
        m_climber = Climber.getInstance();

    }

    public static TeleopController getInstance() {
        if(m_instance == null) {
            m_instance = new TeleopController();
        }
        return m_instance;

    }

    public void callTeleopController() {

        if(m_driverInterface.getShootCommand()) {
            m_shooter.setDesiredState(ShooterState.SHOOTING);
        } else {
            m_shooter.setDesiredState(ShooterState.IDLE);
        }

        if(m_driverInterface.getIntakeCommand()) {
            m_frontIntake.setDesiredState(FrontIntakeStates.INTAKING);
        } else {
            m_frontIntake.setDesiredState(FrontIntakeStates.STOWED);
        }

        m_driverInterface.consoleOutput(MessageType.CRITICAL, m_climber.getClimberBarCurrentState().toString());
        if(m_driverInterface.getClimbAdvanceCommand() && m_climber.getClimberBarCurrentState() == ClimberBarStates.LOW && m_climber.getClimberDone()) {

            m_climber.setClimberBarDesiredState(ClimberBarStates.MEDIUM);
            m_driverInterface.consoleOutput(MessageType.WARNING, "clib");

        } else if(m_driverInterface.getClimbAdvanceCommand()) { //is the climber not on any bar?

            m_climber.setClimberBarDesiredState(ClimberBarStates.LOW);
        }

        if(m_driverInterface.getClimbUpCommand()) {
            m_climber.setClimberDesiredState(ClimberStates.EXTENDED);
        } else if(m_driverInterface.getClimbDownCommand()) {
            m_climber.setClimberDesiredState(ClimberStates.HOOKED);
        }

        callDrive();
        m_pneumatics.setCompressorStatus(true);

        //Update shooter values
        m_shooter.setShooterSpeed(ShooterSpeedSlot.SHOOTING, m_driverInterface.getShooterSpeedField());

    }

    public void callDrive() {
        m_drive.arcadeDrive(m_driverInterface.getJoystickAxis(JoystickAxisType.THROTTLE), m_driverInterface.getX(), m_driverInterface.getY(), m_driverInterface.getJoystickAxis(JoystickAxisType.ROTATION) * 0.1);
    }


}
