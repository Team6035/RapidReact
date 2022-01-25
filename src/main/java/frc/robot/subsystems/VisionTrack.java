package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;
import frc.robot.DriverInterface;
import frc.robot.RobotMap;
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
    private String feedback;
    private int timesLooped;
    private double yOffset;
    private boolean newState;
    private double speed;
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
  SmartDashboard.putString("state", stateToString());
  switch(desiredState){
      case IDLE:
      if(m_lime.getAngleToTarget() != 0.0){
        if(stick.getRawButton(Constants.KVisionCommandID) == true){
          timesLooped++;
          if(timesLooped >=30){
          setDesiredState(VisionState.TURNING);
          timesLooped = 0;
          }
        }
      }
      if(newState){
      RobotMap.getShooterBottom();
      RobotMap.getShooterTop();
      RobotMap.getShooterBottom().set(ControlMode.PercentOutput,0);
      RobotMap.getShooterTop().set(ControlMode.PercentOutput, 0);
      RobotMap.getIndexerMotor().set(ControlMode.PercentOutput, .0);
      RobotMap.getIndexerSolenoid().set(false);
      RobotMap.getThroat().set(ControlMode.PercentOutput,0.0);
      }
      currentState = desiredState;
      break;
      case TURNING:
      if(stick.getRawButton(Constants.KVisionCommandID) == true){
        timesLooped++;
        if(timesLooped >=50){
        setDesiredState(VisionState.IDLE);
        timesLooped = 0;
        }
      }
      if(newState == true){
        newState = false;
        timesLooped = 0;
      }
      double tx = m_lime.getAngleToTarget();
          // System.out.println("tx: " + tx);
          double visionSteering = (tx * Constants.kVisionTurnKp);
          if(tx <4 && tx >-4){
            double isn = visionSteering * 2;
            m_drive.arcadeDrive(0.5, isn, 0.0); 
          }
          else{
            m_drive.arcadeDrive(0.5, visionSteering, 0.0); 
          }
          if(tx <1 && tx >-1){
            if(timesLooped >= 15){
            desiredState = VisionState.FINDINGSPEED;
            }
            else{
              timesLooped++;
            }
            }
          if(tx == 0.00000){
            if(timesLooped >= 10){
              desiredState = VisionState.IDLE;
            }
            else{
              timesLooped++;
            }
          }
      currentState = desiredState;
      break;
      case FINDINGSPEED:
      if(stick.getRawButton(Constants.KVisionCommandID) == true){
        timesLooped++;
        if(timesLooped >=50){
        setDesiredState(VisionState.IDLE);
        timesLooped = 0;
        }
      }
      if(newState == true){
        newState = false;
        speed = 0.0;
      }
      m_drive.arcadeDrive(0, 0, 0);
      yOffset = m_lime.getDistanceToTarget();


      //FIXME 
      //A calculation between yOffset,distance and motor speed needs to be implemented once we have a finalized shooter.
      if(speed != 0.0){
      setDesiredState(VisionState.SHOOTING);
      }
      currentState = desiredState;
      break;
      case SHOOTING:
      if(stick.getRawButton(Constants.KVisionCommandID) == true){
        timesLooped++;
        if(timesLooped >=50){
        setDesiredState(VisionState.IDLE);
        timesLooped = 0;
        }
      }
      if(newState == true){
        timesLooped = 0;
        newState = false;
      }
      RobotMap.getShooterBottom();
      RobotMap.getShooterTop();
      RobotMap.getShooterBottom().set(ControlMode.PercentOutput,speed);
      RobotMap.getShooterTop().set(ControlMode.PercentOutput, speed);
      if(timesLooped >= 400){
      RobotMap.getIndexerMotor().set(ControlMode.PercentOutput, .5);
      RobotMap.getIndexerSolenoid().set(true);
      RobotMap.getThroat().set(ControlMode.PercentOutput,-1.0);
      }
      else{
      timesLooped++;
      }
      System.out.println(yOffset);
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
    newState = true;
    desiredState = state;
}
  public VisionState getCurrentState() {
    return currentState;
  }
  public String stateToString(){
    switch(getCurrentState()){
      case IDLE:
      return "idle";
      case TURNING:
      return "turning";
      case FINDINGSPEED:
      return "finding speed";
      case SHOOTING:
      return "shooting";
      default:
      return "default";
    }
  }
}
