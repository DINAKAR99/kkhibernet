package youtubetutorials.layer;

public class Client1 {

    private static void createEmployee(EmployeeService employeeService) {
        employeeService.createEmp(getEmployee());
    }

    // getEmployeebyId(employeeService);
    // updateEmployeeById(employeeService);
    // deleteEmployeeById(employeeService);

    private static void deleteEmployeeById(EmployeeService employeeService) {
        employeeService.deleteEmp(1);
    }

    private static void updateEmployeeById(EmployeeService employeeService) {
        employeeService.updateEmp(1, 60000.00);
    }

    private static void getEmployeebyId(EmployeeService employeeService) {
        Employee employee = employeeService.getEmploy(1);
        System.out.println(employee);
    }

    private static Employee getEmployee() {
        Employee employee = new Employee();
        employee.setEmpname("Martin Bingel");

        employee.setSalary(50000.00);

        return employee;
    }

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServImple();
        createEmployee(employeeService);
    }

}
