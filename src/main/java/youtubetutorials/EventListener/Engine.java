package youtubetutorials.EventListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import youtubetutorials.HibernateUtil;
import youtubetutorials.NativeSQL.Person;
import youtubetutorials.OptimisticLocking.Person3;

public class Engine {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Person3 person11 = new Person3();
            person11.setPersonName("rohitand");
            person11.setUsername("skons6");
            person11.setPassword("pass#1s23");
            person11.setAccessLevel(1);

            // session.saveOrUpdate(person11);

            logger.info("Person Record is saved successfully");

            tx.commit();
            System.out.println("------------------------------------------------------------------");
            long perosnId = 1L;
            Person3 person1 = session.get(Person3.class, perosnId);
            if (person1 != null) {
                tx = session.beginTransaction();
                person1.setAccessLevel(2);

                session.update(person1);
                tx.commit();
            }
            System.out.println("------------------------------------------------------------------");

            session.refresh(person1);

        } catch (Exception e) {
            logger.error("Failed to save Person Records:" + e);
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }
    }
}
