package youtubetutorials.fetching;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import youtubetutorials.HibernateUtil;
import youtubetutorials.NativeSQL.Department;
import youtubetutorials.NativeSQL.Employee2;
import youtubetutorials.layer.Employee;

public class Batchfecth {

    public static void Batchfecther() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Btach fetching example
            List<Department> departments = session.createQuery(
                    "from Department", Department.class)
                    .getResultList();

            for (Department department : departments) {
                System.out.println("Department details:::::");
                System.out.println(department.getId() + "\t" + department.getName());
                System.out.println("Employees details::::::");
                List<Employee2> employees = department.getEmployees();
                for (Employee2 employee : employees) {
                    System.out.println(employee);
                }
            }
        }
    }

    public static void SelectAndSubselectFetchMode() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Department> departments = session.createQuery("From Department", Department.class)
                    .getResultList();
            System.out.println("Fetched Departments size:" + departments.size());

            for (Department department : departments) {
                System.out.println("Department ID:" + department.getId());
                System.out.println("Employees count:" + department.getEmployees().size());
            }
        }
    }

    public static void JoinFetchMode() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Long departmentId = 1L;
            Department department = session.get(Department.class, departmentId);
            if (department != null) {
                System.out.println("Department ID:" + department.getId());
                List<Employee2> employees = department.getEmployees();
                System.out.println("Employees count:" + employees.size());
            } else {
                System.out.println("Department details not found with ID: " + departmentId);
            }

        }
    }

    public static void lazyCollection() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Long departmentId = 1L;
            Department department = session.get(Department.class, departmentId);
            if (department != null) {
                System.out.println("Department ID:" + department.getId());
                List<Employee2> employees = department.getEmployees();
                System.out.println("Employees count:" + employees.size());
                // for (Employee2 employee : employees) {
                // System.out.println(employee);
                // }
            } else {
                System.out.println("Department details not found with ID: " + departmentId);
            }

        }
    }

    public static void NplusOneProblem() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Department> departments = session

                    .createQuery("From Department d JOIN fetch d.employees", Department.class).getResultList();

            for (Department department : departments) {
                System.out.println("Department details:::::");

                System.out.println(department.getId() + "\t" + department.getName());
                List<Employee2> employees = department.getEmployees();
                System.out.println("Employees details::::::");
                for (Employee2 employee : employees) {

                    System.out.println(employee);
                }

            }
        }
    }

    public static void NPulsOneIssueFixedUsingCriteria() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // CriteriaBuilder builder = session.getCriteriaBuilder();
            // CriteriaQuery<Department> query = builder.createQuery(Department.class);
            // Root<Department> root = query.from(Department.class);
            // root.fetch("employees", JoinType.LEFT);
            // query.select(root);

            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            JpaCriteriaQuery<Department> query = criteriaBuilder.createQuery(Department.class);
            JpaRoot<Department> root = query.from(Department.class);

            root.fetch("employees", JoinType.LEFT);

            query.select(root);

            List<Department> departments = session.createQuery(query).getResultList();

            for (Department department : departments) {
                System.out.println("Department details:::::");

                System.out.println(department.getId() + "\t" + department.getName());
                List<Employee2> employees = department.getEmployees();
                System.out.println("Employees details::::::");
                for (Employee2 employee : employees) {

                    System.out.println(employee);
                }

            }
        }

    }

    public static void main(String[] args) {
        // Batchfecther();
        // SelectAndSubselectFetchMode();
        // JoinFetchMode();
        // lazyCollection();
        // NplusOneProblem();
        NPulsOneIssueFixedUsingCriteria();
    }
}
