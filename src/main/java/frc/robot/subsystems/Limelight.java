package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends Subsystems{
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

    public Limelight() {
   }
   public static Limelight mLimelightInstance;

   public static Limelight getLimelightInstance() {
       if (mLimelightInstance == null) {
        mLimelightInstance  = new Limelight();
       }
       return mLimelightInstance;
       
   }

   

NetworkTableEntry tx = table.getEntry("tx");
NetworkTableEntry ty = table.getEntry("ty");
NetworkTableEntry ta = table.getEntry("ta");
NetworkTableEntry tv = table.getEntry("tv");
private void setPipeline(int pipelineId) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("pipeline").setNumber(pipelineId);
    }
public double getXOffset(){
    double xOffset = tx.getDouble(0.0);
    return xOffset;
}
public double getYOffset(){
    double yOffset = ty.getDouble(0.0);
    return yOffset;
}
public double getTargetArea(){
    double targetArea = ta.getDouble(0.0);
    return targetArea;
}
public double getStatus(){
    double status = tv.getDouble(0.0);
    return status;
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
@Override
public void update() {

}

}
