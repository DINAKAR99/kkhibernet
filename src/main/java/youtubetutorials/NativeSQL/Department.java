package youtubetutorials.NativeSQL;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "department_table")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private long id;

    @Column(name = "dept_name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "dept_location", nullable = false, length = 100)
    private String location;

    // @BatchSize(size = 2)
    @OrderColumn(name = "order_id")
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    // @Fetch(value = FetchMode.JOIN)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Employee2> employees = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee2> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee2> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + ", location=" + location + "]";
    }
}