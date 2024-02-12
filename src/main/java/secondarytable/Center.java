package secondarytable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "center_wise")

public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_id")
    private Integer seqId;

    public Center(int center_id, Integer registeredStudents, Date insertedTime) {
        this.center_id = center_id;

        this.registeredStudents = registeredStudents;
        this.insertedTime = insertedTime;
    }

    @Column(name = "center_id_real", nullable = false)
    private int center_id;

    @OneToMany(mappedBy = "center")
    private List<StudPass> student = new ArrayList<>();

    public List<StudPass> getStudent() {
        return student;
    }

    public void setStudent(List<StudPass> student) {
        this.student = student;
    }

    @Column(name = "capacity")

    private Integer capacity = 5000;

    @Column(name = "registered_students")
    private Integer registeredStudents;

    @Column(name = "inserted_time", nullable = false)
    private Date insertedTime;

    @Column(name = "passid")
    private Integer passid = 5000;

    public Center() {
    }

    // Constructors, getters, setters, and other methods...

}
