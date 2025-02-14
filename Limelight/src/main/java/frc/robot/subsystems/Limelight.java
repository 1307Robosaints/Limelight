package frc.robot.subsystems;



import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

    private final NetworkTable limelightTable;

    public Limelight() {
        //Get NetworkTable for Limelight
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }

    //Check the connection status of NetworkTables
    public void checkNetworkTableConnection() {
        NetworkTableInstance ntInstance = NetworkTableInstance.getDefault();
        if (ntInstance.isConnected()) {
            System.out.println("NetworkTables is connected.");
        } else {
            System.out.println("NetworkTables is NOT connected!");
        }
    }

    //Print all the available keys in the Limelight NetworkTable
    public void printLimelightNetworkTableKeys() {
        System.out.println("Keys in Limelight Table:");
        for (String key : limelightTable.getKeys()) {
            System.out.println(key + ": " + limelightTable.getEntry(key).getValue());
        }
    }

    //Print Limelight data: target visibility, offsets, and area
    public void printLimelightData() {
        NetworkTableEntry tvEntry = limelightTable.getEntry("tv");
        double tv = tvEntry.getDouble(0.0);  // Target visibility
        double tx = limelightTable.getEntry("tx").getDouble(0.0);  // Horizontal offset
        double ty = limelightTable.getEntry("ty").getDouble(0.0);  // Vertical offset
        double ta = limelightTable.getEntry("ta").getDouble(0.0);  // Target area

        System.out.println("tv: " + tv); // Target visible
        System.out.println("tx: " + tx); // Horizontal offset
        System.out.println("ty: " + ty); // Vertical offset
        System.out.println("ta: " + ta); // Target area

        if (tv == 1.0) {
            System.out.println("Target visible! Horizontal offset: " + tx + ", Vertical offset: " + ty + ", Area: " + ta);
        } else {
            System.out.println("No target detected.");
        }
    }

    //Set Camera Mode (Vision or Driver mode)
    public void setCameraMode() {
        limelightTable.getEntry("camMode").setNumber(0);  // 0 = Vision Mode, 1 = Driver Mode
        System.out.println("Camera Mode set to Vision Mode.");
    }

    //LED mode
    public void setLedMode(int ledMode) {
        // 0 for off: 1 for on
        limelightTable.getEntry("ledMode").setNumber(ledMode);
        System.out.println("LED Mode set to: " + ledMode);
    }

    //Printing Data
    public void debugLimelightInfo() {
        // Check NetworkTables connection
        checkNetworkTableConnection();

        // Print all keys in the Limelight table
        printLimelightNetworkTableKeys();

        //Print thed ata
        printLimelightData();
    }

    @Override
    public void periodic() {
        //Repeatedly check the data and network connection
        debugLimelightInfo();
    }
}
