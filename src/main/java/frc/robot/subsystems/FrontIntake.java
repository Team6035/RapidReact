// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.Config;
import frc.robot.RobotMap;

/** Add your docs here. */
public class FrontIntake extends Subsystems{

    public static enum IntakeStates {
        STOWED,
        INTAKING,
        UNINTAKING,
    }

    private IntakeStates currentState = IntakeStates.STOWED;
    private IntakeStates desiredState = IntakeStates.STOWED;

    @Override
    public void update() {

        switch(currentState) {
            default:

                RobotMap.getFrontIntakeESC().set(ControlMode.PercentOutput, 0);
                RobotMap.getFrontIntakeSolenoid().set(false);
                currentState = desiredState;

            break;
            case INTAKING: 

                RobotMap.getFrontIntakeESC().set(ControlMode.PercentOutput, Config.kIntakeSpeed);
                RobotMap.getFrontIntakeSolenoid().set(true);
                currentState = desiredState;

            break;
            case UNINTAKING: 

                RobotMap.getFrontIntakeESC().set(ControlMode.PercentOutput, Config.kIntakeSpeed * -1);
                RobotMap.getFrontIntakeSolenoid().set(true);
                currentState = desiredState;

            break;
        }

    }

    @Override
    public void resetSensors() {
        // TODO Auto-generated method stub
        
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public diagnosticState test() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return The current state of the intakes
     */
    public IntakeStates getCurrentState() {
        return currentState;
    }

    /**
     * @param State to become the intake desired state.
     */
    public void setDesiredState(IntakeStates state) {
        desiredState = state;
    }

}
