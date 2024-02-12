package youtubetutorials.Inheritance;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "vehicle type")
@DiscriminatorValue(value = "sample_vehicle")
public class Vehicle {
    @Override
    public String toString() {
        return "Vehicle [VehicleId=" + VehicleId + ", VehicleName=" + VehicleName + "]";
    }

    public Vehicle() {
    }

    @Id
    @GeneratedValue
    private int VehicleId;
    private String VehicleName;

    public Vehicle(int vehicleId, String vehicleName) {
        VehicleId = vehicleId;
        VehicleName = vehicleName;
    }

    public int getVehicleId() {
        return VehicleId;
    }

    public void setVehicleId(int vehicleId) {
        VehicleId = vehicleId;
    }

    public String getVehicleName() {
        return VehicleName;
    }

    public void setVehicleName(String vehicleName) {
        VehicleName = vehicleName;
    }

}
