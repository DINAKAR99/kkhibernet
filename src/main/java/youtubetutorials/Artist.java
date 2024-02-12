package youtubetutorials;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Name;

    @OneToMany(mappedBy = "Ar1", cascade = CascadeType.ALL)
    private List<Address> address1 = new ArrayList<Address>();
    // @ElementCollection
    // @JoinTable(name = "Address_table", joinColumns = @JoinColumn(name =
    // "artist_id"))

    public Artist() {
    }

    public Artist(int id, String name) {
        this.id = id;
        Name = name;
    }

    @Override
    public String toString() {
        return "Artist [id=" + id + ", Name=" + Name + "]";
    }

    // @GenericGenerator(name = "sequencer-gen", strategy = "sequence")
    // @CollectionId(columns = { @Column(name = "Address_Id") }, generator =
    // "hilo-gen", type = @Type(type = "int"))

    // @AttributeOverrides(value = {
    // @AttributeOverride(column = @Column(name = "home-city"), name = "city"),
    // @AttributeOverride(column = @Column(name = "home-pin"), name = "pin")

    public List<Address> getAddress1() {
        return address1;
    }

    public void setAddress1(List<Address> address1) {
        this.address1 = address1;
    }

    // })

    // @CollectionId(columns = { @Column(name = "address_id") }, generator =
    // "sequencer-gen", type = @Type(type = "int"))

    // private Collection<Address> addressCollection = new HashSet<Address>();

    // @AttributeOverrides(value = {
    // @AttributeOverride(column = @Column(name = "office-city"), name = "city"),
    // @AttributeOverride(column = @Column(name = "office-pin"), name = "pin")

    // })

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
