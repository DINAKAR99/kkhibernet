package youtubetutorials.layer;

public interface EmployeeDao {

    public abstract void addEmp(Employee e1);

    public abstract void deleteEmp(int e1);

    public abstract void updateEmp(int e1, double sal);

    public abstract Employee fetchEmp(int e1);

}
