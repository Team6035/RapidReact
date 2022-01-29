// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import frc.robot.Config;
import frc.robot.Constants;

import frc.robot.RobotMap;

/** Add your docs here. */
public class Climber extends Subsystems {

    public enum ClimberStates {
        STOWED,
        EXTENDED,
        HOOKED,
    }

    public enum ClimberBarStates {
        LOW,
        MEDIUM,
        HIGH,
        TRAVERSAL,
        STOWED,
    }

    private ClimberStates currentClimberState = ClimberStates.STOWED;
    private ClimberStates desiredClimberState = ClimberStates.STOWED;

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


        switch(currentClimberState) {
            default: //stowed
                RobotMap.getWinch().set(ControlMode.Position, Config.kClimberStowedPos);
            
                if(RobotMap.getWinch().getSelectedSensorPosition() <= Config.kClimberStowedPos + Config.kClimberHysteresis) {
                    currentClimberState = desiredClimberState;
                }
            break;
            case EXTENDED:
                RobotMap.getWinch().set(ControlMode.Position, Config.kClimberUpPos);
            
                if(RobotMap.getWinch().getSelectedSensorPosition() >= Config.kClimberUpPos - Config.kClimberHysteresis) {
                    currentClimberState = desiredClimberState;
                }
            break;
            case HOOKED:
                RobotMap.getWinch().set(ControlMode.Position, Config.kClimberHookedPos);
            
                if(RobotMap.getWinch().getSelectedSensorPosition() <= Config.kClimberHookedPos + Config.kClimberHysteresis) {
                    currentClimberState = desiredClimberState;
                }
            break;
        } 
    

    }
    @Override
    public void resetSensors() {

        RobotMap.getWinch().setSelectedSensorPosition(0);

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
        
        RobotMap.getWinch().configFactoryDefault();

        RobotMap.getWinch().config_kP(0, Constants.kClimberWinchP);

        RobotMap.getWinch().setNeutralMode(NeutralMode.Brake);



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
    

}
