package frc.robot.subsystems;

import com.revrobotics.CANSparkMax.IdleMode;

import frc.robot.subsystems.Limelight;

public class VisionTrack {
    private static VisionTrack mInstance;
    private static Limelight m_lime;
    private VisionState currentState;
    private VisionState desiredState;

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
      break;
      case TURNING:
      case FINDINGSPEED:

    
  }
}



    public void turnToTarget(){
       if(m_lime.getXOffset() > 0.1 ||m_lime.getXOffset() < -0.1){
       double xCorrect = m_lime.getXOffset();
       xCorrect = xCorrect * .029;
       Drive.getInstance().arcadeDrive(1.0, xCorrect, 0.0);
       }
       else{
           Drive.getInstance().arcadeDrive(0, 0, 0);
       }
    }
    public enum VisionState {
      IDLE,
      TURNING,
      FINDINGSPEED,
      SHOOTING,
  }

}
