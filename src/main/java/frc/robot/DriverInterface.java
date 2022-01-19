// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Add your docs here. */
public class DriverInterface {

    private static DriverInterface m_instance;

    public DriverInterface() {
        
    }

    public static DriverInterface getInstance() {
        if(m_instance == null) {
            m_instance = new DriverInterface();
        }
        return m_instance;
    }

    public static enum JoystickAxisType{
        X,
        Y,
        THROTTLE,
        ROTATION,
    }

    public static enum MessageType {
        DEBUG,
        STATUS,
        CAUTION,
        WARNING,
        CRITICAL,
        SYSTEM,
    }

    public static enum VersionType {
        BETA,
        RELEASECANDIDATE,
        RELEASE,
    }

    boolean debugOutput = Config.kDebugOutputDefault;
    boolean verboseOutput = Config.kVerboseOutputDefault;


    Joystick joystick1 = new Joystick(Config.kJoystick1Port);
    GenericHID xbox1 = new GenericHID(Config.kXbox1Port);

    /**
     * Method to set Xbox controller vibrate/rumble
     * @param leftRumble value from 0 to 1 to set left rumble intensity
     * @param rightRumble value from 0 to 1 to set right rumble intensity
     */
    public void setRumble(double leftRumble, double rightRumble) {
        xbox1.setRumble(RumbleType.kLeftRumble, leftRumble);
        xbox1.setRumble(RumbleType.kRightRumble, rightRumble);

    }

    private double joystickAxisReturn = 0; 

    /**
     * Method for getting Joystick Axis values
     * @param axisType
     * @return axis value
     */
    public double getJoystickAxis(JoystickAxisType axisType) {
        switch (axisType) {
            case X:
                joystickAxisReturn = joystick1.getX();
            break;
            case Y:
                joystickAxisReturn = joystick1.getY();
            break;
            case THROTTLE:
                joystickAxisReturn = joystick1.getThrottle();
            break;
            case ROTATION:
                joystickAxisReturn = joystick1.getTwist();
            break;
        }
        return joystickAxisReturn;
    }

    public boolean getDebugOutput() {
        return debugOutput;
    }

    public boolean getVerboseOutput() {
        return verboseOutput;
    }

    public void consoleOutput(MessageType messageType, String message) {
        switch(messageType) {
            case DEBUG:
                if(getDebugOutput()) {
                    System.out.println("DEBUG: " + message);
                } 
            break;
            case STATUS:
                if(getVerboseOutput()) {
                    System.out.println("STATUS " + message);
                }
            break;
            case SYSTEM:
                System.out.println("SYSTEM " + message);
            break;
            case CAUTION:
                System.out.println("CAUTION " + message);
            break;
            case WARNING:
                System.out.println("WARNING " + message);
            break;
            case CRITICAL:
                System.out.println("⚠  CRITICAL " + message + " ⚠ ");
            break;
        }
    }

    public void printVersionNumber(VersionType versonType, String version) {
        switch(versonType) {
            case BETA:
                SmartDashboard.putString("Software Version", "BETA " + version);
                consoleOutput(MessageType.SYSTEM, "Software version: BETA " + version);
            break;
            case RELEASECANDIDATE:
                SmartDashboard.putString("Software Version", "Release Candidate " + version);
                consoleOutput(MessageType.SYSTEM, "Software version: Release Candidate " + version);
            break;
            case RELEASE:
                SmartDashboard.putString("Software Version", "Release " + version);
                consoleOutput(MessageType.SYSTEM, "Software version: Release " + version);
            break;

        }
    }


}
