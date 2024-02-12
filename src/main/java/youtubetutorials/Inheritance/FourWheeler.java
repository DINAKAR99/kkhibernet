package youtubetutorials.Inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "car")
public class FourWheeler extends Vehicle {

    @Override
    public String toString() {
        return "FourWheeler [SteeringHandle=" + SteeringHandle + super.toString() + "]";
    }

    public FourWheeler(String steeringHandle) {
        SteeringHandle = steeringHandle;
    }

    public FourWheeler(int vehicleId, String vehicleName, String steeringHandle) {
        super(vehicleId, vehicleName);
        SteeringHandle = steeringHandle;
    }

    public FourWheeler() {

    }

    private String SteeringHandle;

    public String getSteeringHandle() {
        return SteeringHandle;
    }

    public void setSteeringHandle(String steeringHandle) {
        SteeringHandle = steeringHandle;
    }

}
