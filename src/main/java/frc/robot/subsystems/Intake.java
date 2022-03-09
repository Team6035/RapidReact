// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Config;
import frc.robot.RobotMap;

/** Add your docs here. */
public class Intake extends Subsystems{

    public static enum IntakeStates {
        IDLE,
        INTAKING,
        UNINTAKING,
        STOWED,
        EXTENDED,
    }

    private IntakeStates currentState = IntakeStates.STOWED;
    private IntakeStates desiredState = IntakeStates.STOWED;
    
    public Intake() {

    }

    private static Intake m_instance;

    public static Intake getInstance() {
        if(m_instance == null) {
            m_instance = new Intake();
        }

        return m_instance;
    }

    @Override
    public void update() {

        switch(currentState) {
            default:
                RobotMap.getIntakeESC().set(ControlMode.PercentOutput, 0);
                currentState = desiredState;

            break;
            case INTAKING: 
                RobotMap.getIntakeSolenoid().set(Value.kForward);

                RobotMap.getIntakeESC().set(ControlMode.PercentOutput, Config.kIntakeSpeed);
                currentState = desiredState;

            break;
            case UNINTAKING: 
                RobotMap.getIntakeSolenoid().set(Value.kForward);

                RobotMap.getIntakeESC().set(ControlMode.PercentOutput, Config.kIntakeSpeed * -1);
                currentState = desiredState;

            break;
            case STOWED:
                RobotMap.getIntakeSolenoid().set(Value.kReverse);
                RobotMap.getIntakeESC().set(ControlMode.PercentOutput, 0);
                currentState = desiredState;
            break;
            case EXTENDED:
                RobotMap.getIntakeSolenoid().set(Value.kForward);
                RobotMap.getIntakeESC().set(ControlMode.PercentOutput, 0);
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
