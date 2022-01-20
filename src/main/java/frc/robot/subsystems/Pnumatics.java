// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Config;
import frc.robot.RobotMap;

/** Add your docs here. */
public class Pnumatics extends Subsystems{

    boolean compressor = true;

    @Override
    public void update() {

        if(compressor) {
            RobotMap.getCompressor().enableAnalog(Config.kCompressorMin, Config.kCompressorMax);
        } else {
            RobotMap.getCompressor().disable();
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

    public void setCompressorStatus(boolean compressorStatus) {
        compressorStatus = compressor;
    }

}
