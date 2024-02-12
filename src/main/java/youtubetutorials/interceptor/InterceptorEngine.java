package youtubetutorials.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import youtubetutorials.HibernateUtil;

import youtubetutorials.layer.Employee;

public class InterceptorEngine {
    private static final Logger logger = LogManager.getLogger(InterceptorEngine.class);

    public static void main(String[] args) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory()
                .withOptions()
                .interceptor(new LoggerInterceptor())
                .openSession()) {
            tx = session.beginTransaction();

            Employee e1 = new Employee();
            e1.setEmpname("robot");
            e1.setSalary(90000);

            session.persist(e1);

            logger.info("Person Record is saved successfully");

            tx.commit();

        } catch (Exception e) {
            logger.error("Failed to save Person Records:" + e);
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }

        try (Session session = HibernateUtil.getSessionFactory().withOptions().interceptor(new LoggerInterceptor())
                .openSession()) {
            Employee Employee = session.get(Employee.class, 1L);
            System.out.println(Employee);
        } catch (Exception e) {
            logger.error("Failed to fetch Record:" + e);
            throw e;
        }
    }
}
