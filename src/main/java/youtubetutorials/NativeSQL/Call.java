package youtubetutorials.NativeSQL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Call {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Phone phone;

    private int duration;

    public Call() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Call [id=" + id + ", phone=" + phone + ", duration=" + duration + "]";
    }

}
