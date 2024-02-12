package youtubetutorials.layer;

public class EmployeeServImple implements EmployeeService {

    @Override
    public void createEmp(Employee e1) {
        new EmployeeDaoImpl().addEmp(e1);
    }

    @Override
    public void deleteEmp(int e1) {
        new EmployeeDaoImpl().deleteEmp(e1);

    }

    @Override
    public void updateEmp(int e1, double sal) {
        new EmployeeDaoImpl().updateEmp(e1, sal);
    }

    @Override
    public Employee getEmploy(int e1) {
        return new EmployeeDaoImpl().fetchEmp(e1);
    }

}
