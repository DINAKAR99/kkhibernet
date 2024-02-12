package youtubetutorials.HQL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Avengers {
    @Id
    @GeneratedValue
    private int Avengerid;
    private String AvengerName;
    private String AvengerRole;

    public Avengers() {
    }

    public int getAvengerid() {
        return Avengerid;
    }

    public void setAvengerid(int avengerid) {
        Avengerid = avengerid;
    }

    public String getAvengerName() {
        return AvengerName;
    }

    public void setAvengerName(String avengerName) {
        AvengerName = avengerName;
    }

    public String getAvengerRole() {
        return AvengerRole;
    }

    public void setAvengerRole(String avengerRole) {
        AvengerRole = avengerRole;
    }

    @Override
    public String toString() {
        return "Avengers [Avengerid=" + Avengerid + ", AvengerName=" + AvengerName + ", AvengerRole=" + AvengerRole
                + "]";
    }
}
