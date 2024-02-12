package youtubetutorials.NativeSQL;

import java.sql.Blob;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Person2 {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Lob

    private Blob profilepicc;

    public Blob getProfilepicc() {
        return profilepicc;
    }

    public void setProfilepicc(Blob profilepicc) {
        this.profilepicc = profilepicc;
    }

    public Person2() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person2 [id=" + id + ", profilePic=" + "--" + "]";
    }
}
