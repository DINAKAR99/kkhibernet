package youtubetutorials.Inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "bike")
public class Twowheeler extends Vehicle {

    public Twowheeler(String steeringWheel) {
        SteeringWheel = steeringWheel;
    }

    public Twowheeler(int vehicleId, String vehicleName, String steeringWheel) {
        super(vehicleId, vehicleName);
        SteeringWheel = steeringWheel;
    }

    public Twowheeler() {

    }

    private String SteeringWheel;

    public String getSteeringWheel() {
        return SteeringWheel;
    }

    public void setSteeringWheel(String steeringWheel) {
        SteeringWheel = steeringWheel;
    }

    @Override
    public String toString() {
        return "Twowheeler [SteeringWheel=" + SteeringWheel + super.toString() + "]";
    }

}
