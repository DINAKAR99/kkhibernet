package youtubetutorials.Log4J;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import youtubetutorials.HibernateUtil;
import youtubetutorials.layer.Employee;

public class LoggerEngine {

    private static final Logger logger = LogManager.getLogger(LoggerEngine.class);

    public static void main(String[] args) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            tx = session.beginTransaction();

            Employee e1 = new Employee();
            e1.setEmpname("loggerEmployyer");
            e1.setSalary(90000);

            session.persist(e1);

            logger.info("employeee Records saved successfully");

            tx.commit();
        } catch (Exception e) {
            logger.error("Failed to save employee Records:" + e);
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }
    }
}
