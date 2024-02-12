package youtubetutorials;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String city;
    private String pin;
    @ManyToOne
    private Artist Ar1;

    public Artist getAr1() {
        return Ar1;
    }

    public void setAr1(Artist ar1) {
        Ar1 = ar1;
    }

    @Override
    public String toString() {
        return "Address [city=" + city + ", pin=" + pin + "]";
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
