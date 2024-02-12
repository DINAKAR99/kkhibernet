package youtubetutorials.layer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int empid;
    private String Empname;
    private double salary;

    public Employee() {
    }

    public Employee(String empname, double salary) {

        Empname = empname;
        this.salary = salary;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return Empname;
    }

    public void setEmpname(String empname) {
        Empname = empname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [empid=" + empid + ", Empname=" + Empname + ", salary=" + salary + "]";
    }

}
