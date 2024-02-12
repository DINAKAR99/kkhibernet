package youtubetutorials.layer;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import youtubetutorials.HibernateUtil;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public void addEmp(Employee e1) {

        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

                Session openSession = sessionFactory.openSession()) {
            openSession.beginTransaction();

            Integer i1 = (Integer) openSession.save(e1);
            System.out.println("employee is ceatedwith  id " + i1);
            openSession.getTransaction().commit();

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void deleteEmp(int e1) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

                Session openSession = sessionFactory.openSession()) {
            Employee employee = openSession.get(Employee.class, e1);

            if (employee != null) {

                openSession.beginTransaction();
                openSession.remove(employee);
                openSession.getTransaction().commit();
            } else {
                System.out.println("employee doesnt exist");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void updateEmp(int e1, double sal) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

                Session openSession = sessionFactory.openSession()) {
            Employee employee = openSession.get(Employee.class, e1);

            if (employee != null) {

                employee.setSalary(sal);
                openSession.beginTransaction();
                openSession.merge(employee);
                openSession.getTransaction().commit();
            } else {
                System.out.println("employee doesnt exist");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public Employee fetchEmp(int e1) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

                Session openSession = sessionFactory.openSession()) {
            Employee empl = openSession.get(Employee.class, e1);

            if (empl != null) {

                System.out.println(empl);
            } else {
                System.out.println("employee doesnt exist");
            }
        } catch (

        Exception e) {
            // TODO: handle exception
        }

        return null;
    }

}
