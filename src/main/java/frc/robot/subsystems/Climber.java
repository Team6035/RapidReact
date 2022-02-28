// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;

import frc.robot.Config;
import frc.robot.Constants;

import frc.robot.RobotMap;

/** Add your docs here. */
public class Climber extends Subsystems {

    public enum ClimberStates {
        STOWED,
        EXTENDED,
        HOOKED,
        IDLE,
    }

    public enum ClimberBarStates {
        LOW,
        MEDIUM,
        HIGH,
        TRAVERSAL,
        STOWED,
    }

    private ClimberStates currentClimberState = ClimberStates.IDLE;
    private ClimberStates desiredClimberState = ClimberStates.IDLE;
    private SparkMaxPIDController leftWinchPIDController = RobotMap.getLeftWinch().getPIDController();
    private SparkMaxPIDController rightWinchPIDController = RobotMap.getRightWinch().getPIDController();
    private RelativeEncoder leftWinchEncoder = RobotMap.getLeftWinch().getEncoder();
    private RelativeEncoder rightWinchEncoder = RobotMap.getRightWinch().getEncoder();


    private static Climber m_instance;

    public static Climber getInstance() {
        if(m_instance == null) {
            m_instance = new Climber();
        }
        return m_instance;
    }

    private Climber() {

    }

    @Override
    public void update() {

        System.out.println(rightWinchEncoder.getPosition());
        switch(currentClimberState) {
            default: //stowed
                leftWinchPIDController.setReference(Config.kClimberStowedPos, ControlType.kPosition);  
                rightWinchPIDController.setReference(Config.kClimberStowedPos, ControlType.kPosition);                      
                    currentClimberState = desiredClimberState;
            break;
            case EXTENDED:
                leftWinchPIDController.setReference(Config.kClimberUpPos, ControlType.kPosition);  
                rightWinchPIDController.setReference(Config.kClimberUpPos, ControlType.kPosition);                      
                    currentClimberState = desiredClimberState;
            break;
            case HOOKED:
                leftWinchPIDController.setReference(Config.kClimberStowedPos, ControlType.kPosition);  
                rightWinchPIDController.setReference(Config.kClimberStowedPos, ControlType.kPosition);                      
                    currentClimberState = desiredClimberState;
            break;
            case IDLE:
                currentClimberState = desiredClimberState;
            break;
        } 
    

    }
    @Override
    public void resetSensors() {

        leftWinchEncoder.setPosition(0);
        rightWinchEncoder.setPosition(0);


    }
    @Override
    public boolean initMechanism() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public diagnosticState getDiagnosticState() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void initMotorControllers() {
        
        RobotMap.getLeftWinch().restoreFactoryDefaults();
        RobotMap.getLeftWinch().setIdleMode(IdleMode.kBrake);
        RobotMap.getRightWinch().restoreFactoryDefaults();
        RobotMap.getRightWinch().setIdleMode(IdleMode.kBrake);
        RobotMap.getLeftWinch().setInverted(false);
        RobotMap.getRightWinch().setInverted(false);


        RobotMap.getLeftWinch().enableSoftLimit(SoftLimitDirection.kForward, true);
        RobotMap.getLeftWinch().enableSoftLimit(SoftLimitDirection.kReverse, true);

        RobotMap.getRightWinch().enableSoftLimit(SoftLimitDirection.kForward, true);
        RobotMap.getRightWinch().enableSoftLimit(SoftLimitDirection.kReverse, true);

        RobotMap.getLeftWinch().setSoftLimit(SoftLimitDirection.kForward, 0);
        RobotMap.getLeftWinch().setSoftLimit(SoftLimitDirection.kReverse, -Config.kWinchMaxPos);

        RobotMap.getRightWinch().setSoftLimit(SoftLimitDirection.kForward, Config.kWinchMaxPos);
        RobotMap.getRightWinch().setSoftLimit(SoftLimitDirection.kReverse, 0);
    

        leftWinchPIDController.setP(Constants.kClimberWinchP);
        leftWinchPIDController.setP(Constants.kClimberWinchP);


    }
    @Override
    public diagnosticState test() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the current state of the climber
     */
    public ClimberStates getClimberCurrentState() {
        return currentClimberState;
    }

    /**
     * 
     * @param desiredState the desired positon of the climber
     */
    public void  setClimberDesiredState(ClimberStates desiredState) {
        desiredClimberState = desiredState;
    }

    public void climbManualPower(double leftPower, double rightPower) {
        RobotMap.getLeftWinch().set(leftPower);
        RobotMap.getRightWinch().set(-rightPower);
    }

    public void climbManualPower(double power) {
        climbManualPower(power, power);
    }
    
    public void setSoftLimits(boolean enabled) {
        RobotMap.getLeftWinch().enableSoftLimit(SoftLimitDirection.kForward, enabled);
        RobotMap.getLeftWinch().enableSoftLimit(SoftLimitDirection.kReverse, enabled);
        RobotMap.getRightWinch().enableSoftLimit(SoftLimitDirection.kForward, enabled);
        RobotMap.getRightWinch().enableSoftLimit(SoftLimitDirection.kReverse, enabled);

    }

}
