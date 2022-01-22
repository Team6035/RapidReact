// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.Constants;
import frc.robot.RobotMap;

/** Add your docs here. */
public class Shooter extends Subsystems{

    public enum ShooterSpeedSlot {
        IDLE, //Shooter in idle state
        SHOOTING, //shooter shooting ball
        EJECT, //shooter ejecting wrong ball colour
    }

    public enum ShooterState {
        IDLE, //Shooter in idle state
        SHOOTING, //shooter shooting ball
        EJECT, //shooter ejecting wrong ball colour
    }

    private static ShooterState currentState = ShooterState.IDLE;
    private static ShooterState desiredState = ShooterState.IDLE;

    private static ShooterSpeedSlot speedSlot = ShooterSpeedSlot.IDLE;

    private double shooterIdleSpeed = 0;
    private double shooterShootSpeed = 1000;
    private double shooterEjectSpeed = 100;

    private double ratio = 1;
    private double wheelRatio = 2;

    @Override
    public void update() {

        switch(currentState) {
            default: //catches 'IDLE'
                setShooterSpeedSlot(ShooterSpeedSlot.IDLE);
                currentState = desiredState;
            break;
            case SHOOTING:
                setShooterSpeedSlot(ShooterSpeedSlot.SHOOTING);
                currentState = desiredState;
            break;
            case EJECT:
                setShooterSpeedSlot(ShooterSpeedSlot.EJECT);
                currentState = desiredState;
            break;
        }

        shooterPID();
    }

    @Override
    public void resetSensors() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean initMechanism() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public diagnosticState getDiagnosticState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void initMotorControllers() {
        RobotMap.getShooterBottom().config_kP(0, Constants.kShooterP);       
        RobotMap.getShooterTop().config_kP(0, Constants.kShooterP);       

        RobotMap.getShooterBottom().config_kI(0, Constants.kShooterI);       
        RobotMap.getShooterTop().config_kI(0, Constants.kShooterI);     
    }

    public void setShooterSpeed(ShooterSpeedSlot speedSlot, double rpm) {
        switch(speedSlot) {
            case IDLE:
                shooterIdleSpeed = rpm;
            break;
            case SHOOTING:
                shooterShootSpeed = rpm;
            break;
            case EJECT:
                shooterEjectSpeed = rpm;
            break;
        }
    }

    /**
     * Set the speed ratio between the top and bottom wheels
     * @param topRatio numerator
     * @param bottomRatio denomonator
     * @param topWheel numerator
     * @param bottomWheel denomonator
     */
    public void setShooterWheelRatio(double topRatio, double bottomRatio, double topWheel, double bottomWheel) {
        ratio = topRatio / bottomRatio;
        if(!(topWheel == -1 || bottomWheel == -1)) {
            wheelRatio = topWheel/bottomWheel;
        }
    }

    /**
     * 
     * @param topRatio numerator
     * @param bottomRatio denomonator
     */
    public void setShooterWheelRatio(double topRatio, double bottomRatio) {
        setShooterWheelRatio(topRatio, bottomRatio, -1, -1);
    }

    /**
     * Returns the set speed of the shooter at the selected speed slot. If not specified, defaults to shooting speed.
     * @param speedSlot which speed value to return
     * @return shooter set speed in RPM
     */
    public double getShooterSetSpeed(ShooterSpeedSlot speedSlot) {
        switch(speedSlot) {
            case IDLE:
                return shooterIdleSpeed;
            default:
                return shooterShootSpeed;
            case EJECT:
                return shooterEjectSpeed;
        }
    }

    /**
     * Returns the set speed of the shooter at the selected speed slot. If not specified, defaults to shooting speed.
     * @return shooter set speed in RPM
     */
    public double getShooterSetSpeed() {
        return getShooterSetSpeed(ShooterSpeedSlot.SHOOTING);
    }

    private void setShooterSpeedSlot(ShooterSpeedSlot slot) {
        switch(slot) {
            case SHOOTING: 
                speedSlot = ShooterSpeedSlot.SHOOTING;
            break;
            case IDLE: 
                speedSlot = ShooterSpeedSlot.IDLE;
            break;
            case EJECT:
                speedSlot = ShooterSpeedSlot.EJECT;
            break;
        }
    }

    private void shooterPID() {
        RobotMap.getShooterBottom().set(ControlMode.Velocity, getShooterSetSpeed(speedSlot));
        RobotMap.getShooterTop().set(ControlMode.Velocity, getShooterSetSpeed(speedSlot) * ratio * wheelRatio);

    }

    @Override
    public diagnosticState test() {
        // TODO Auto-generated method stub
        return null;
    }
    
}