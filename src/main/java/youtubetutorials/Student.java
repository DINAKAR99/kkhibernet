package youtubetutorials;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@DynamicUpdate
public class Student {
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", city=" + city + "]";
    }
    public Student(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE )
    @Column(name = "std_id")
    private int id;
    @Column(unique = true)
    private String name;
    @Column(nullable = false)
    private String city;





    public Student() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    





 
}
