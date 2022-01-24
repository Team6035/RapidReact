package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.subsystems.Limelight;
import frc.robot.DriverInterface;
import edu.wpi.first.wpilibj.Joystick;

public class VisionTrack {
    private static VisionTrack mInstance;
    private static Limelight m_lime;
    private VisionState currentState;
    private VisionState desiredState;
    private static DriverInterface m_driverInterface;
    private Joystick stick = new Joystick(0);


    public static VisionTrack getInstance() {
        if (mInstance == null) {
          mInstance = new VisionTrack();
        }
        return mInstance;
      }

    private VisionTrack(){
        m_lime = new Limelight();
        currentState = VisionState.IDLE;
        desiredState = VisionState.IDLE;
    }
public void update(){

  switch(desiredState){
      case IDLE:
      if(stick.getRawButton(9)){
        desiredState = VisionState.TURNING;
      }
      currentState = desiredState;
      break;
      case TURNING:
      if(m_lime.getXOffset() > 0.1 ||m_lime.getXOffset() < -0.1){
        double xCorrect = m_lime.getXOffset();
        xCorrect = xCorrect * .029;
        Drive.getInstance().arcadeDrive(1.0, xCorrect, 0.0);
        }
      else{
            desiredState = VisionState.FINDINGSPEED;
        }
      currentState = desiredState;
      break;
      case FINDINGSPEED:
      currentState = desiredState;
      break;
      case SHOOTING:
      currentState = desiredState;
      break;

    
  }
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
