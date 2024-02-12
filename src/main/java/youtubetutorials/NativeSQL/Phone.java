package youtubetutorials.NativeSQL;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Phone {
    @Id
    @GeneratedValue
    private int id;
    private int number;

    @ManyToOne
    private Person person_linked;
    @Enumerated
    private PhoneType type;
    @OneToMany(mappedBy = "phone", cascade = CascadeType.ALL)
    private List<Call> calls = new ArrayList<>();

    public Phone() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Person getPerson_linked() {
        return person_linked;
    }

    public void setPerson_linked(Person person_linked) {
        this.person_linked = person_linked;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

    @Override
    public String toString() {
        return "Phone [id=" + id + ", number=" + number + ", person_linked=" + person_linked + ", type=" + type
                + "]";
    }

}
