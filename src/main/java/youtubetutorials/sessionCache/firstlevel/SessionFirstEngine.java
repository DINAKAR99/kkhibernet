package youtubetutorials.sessionCache.firstlevel;

import org.hibernate.Session;
import org.hibernate.Transaction;

import youtubetutorials.HibernateUtil;
import youtubetutorials.NativeSQL.Employee2;
import youtubetutorials.NativeSQL.book;
import youtubetutorials.layer.Employee;

public class SessionFirstEngine {
    private static void sesionCacheForDeleteRecord() {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long employeeId = 2L;
            Employee employee1 = session.get(Employee.class, employeeId);
            System.out.println(employee1);
            if (employee1 != null) {
                tx = session.beginTransaction();
                session.delete(employee1);
                tx.commit();
            }
            System.out.println("-------------------------------------------------------------");
            Employee employee2 = session.get(Employee.class, employeeId);
            System.out.println(employee2);
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }
    }

    private static void sesionCacheForUpdateRecord() {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long employeeId = 1L;
            String name = "password$23";
            Employee employee1 = session.get(Employee.class, employeeId);
            System.out.println(employee1);
            if (employee1 != null) {
                tx = session.beginTransaction();
                employee1.setEmpname(name);
                tx.commit();
            }
            System.out.println("-------------------------------------------------------------");
            Employee employee2 = session.get(Employee.class, employeeId);
            System.out.println(employee2);
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }

    }

    private static void sesionCacheReadByNaturalId() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String username = "978-9730228236";

            book book1 = session.bySimpleNaturalId(book.class).load(username);
            System.out.println(book1);

            System.out.println("-------------------------------------------------------------");
            book book2 = session.bySimpleNaturalId(book.class).load(username);
            System.out.println(book2);
        } catch (Exception e) {
            throw e;
        }
    }

    private static void sesionCacheReadByPrimaryKey() throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long employeeId = 1L;
            Employee employee1 = session.get(Employee.class, employeeId);
            System.out.println(employee1);

            System.out.println("-------------------------------------------------------------");
            Employee employee2 = session.get(Employee.class, employeeId);
            System.out.println(employee2);
        } catch (Exception e) {
            throw e;
        }
    }

    private static void sesionCacheForInsertRecord() {

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Employee2 employee = new Employee2();
            employee.setName("jambo jalak");
            employee.setSalary(900000.00);

            tx = session.beginTransaction();
            Long employeeId = (Long) session.save(employee);

            tx.commit();
            System.out.println("-------------------------------------------------------------");
            Employee2 employee2 = session.get(Employee2.class, employeeId);
            System.out.println(employee2);
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }
    }

    public static void main(String[] args) throws Exception {
        // sesionCacheForInsertRecord();
        sesionCacheReadByPrimaryKey();
        // sesionCacheReadByNaturalId();
        // sesionCacheForUpdateRecord();
        // sesionCacheForDeleteRecord();
    }
}
