//needs command file: to do later

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {

    Spark elevatorMotor = new Spark(8);

    public Elevator() {

        //not precise

    }
    
}
