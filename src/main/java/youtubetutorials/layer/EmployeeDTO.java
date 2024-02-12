package youtubetutorials.layer;

public class EmployeeDTO {

    private String Empname;
    private double salary;

    public EmployeeDTO(String empname, double salary) {

        Empname = empname;
        this.salary = salary;
    }

    public String getEmpname() {
        return Empname;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "EmployeeDTO [  Empname=" + Empname + ", salary=" + salary + "]";
    }
}
