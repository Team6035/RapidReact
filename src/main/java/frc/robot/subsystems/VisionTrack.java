package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.subsystems.Limelight;
import frc.robot.Constants;
import frc.robot.DriverInterface;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionTrack {
    private static VisionTrack mInstance;
    private static Limelight m_lime;
    private VisionState currentState;
    private VisionState desiredState;
    private static DriverInterface m_driverInterface;
    private Joystick stick = new Joystick(0);
    private static Drive m_drive;
    private int timesLooped;
    public static VisionTrack getInstance() {
        if (mInstance == null) {
          mInstance = new VisionTrack();
        }
        return mInstance;
      }

    private VisionTrack(){
        m_lime = new Limelight();
        m_drive = Drive.getInstance();
        currentState = VisionState.IDLE;
        desiredState = VisionState.IDLE;
    }
public void update(){

  switch(desiredState){
      case IDLE:
      if(stick.getRawButton(1) == true){
        setDesiredState(VisionState.TURNING);
        timesLooped = 0;
      }

      currentState = desiredState;
      break;
      case TURNING:
      double tx = m_lime.getAngleToTarget();
          // System.out.println("tx: " + tx);
          double visionSteering = (tx * Constants.kVisionTurnKp);
          SmartDashboard.putNumber("vision Steer", visionSteering);
          m_drive.arcadeDrive(0.4, visionSteering, 0.0);      
          if(tx <1 && tx >-1){
            timesLooped++;
            if(timesLooped == 20){
            desiredState = VisionState.FINDINGSPEED;
              }
            }
      currentState = desiredState;
      break;
      case FINDINGSPEED:
      m_drive.arcadeDrive(0, 0, 0);
      SmartDashboard.putNumber("Distance to target", m_lime.getDistanceToTarget());
      currentState = desiredState;
      break;
      case SHOOTING:
      currentState = desiredState;
      break;

    
  }
}



    private double min(double d, double e) {
  return 0;
}

    public void turnToTarget(){

    }
    public enum VisionState {
      IDLE,
      TURNING,
      FINDINGSPEED,
      SHOOTING,
  }

  public void setDesiredState(VisionState state) {
    desiredState = state;
}
  public VisionState getCurrentState() {
    return currentState;
  }
}
