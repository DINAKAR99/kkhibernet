package youtubetutorials.OptimisticLocking;

import org.hibernate.Session;
import org.hibernate.Transaction;

import youtubetutorials.HibernateUtil;

public class PersonEngine {
    public static void main(String[] args) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Person3 person1 = new Person3();
            person1.setPersonName("rohit");
            person1.setUsername("seanmss");
            person1.setPassword("pass#1s23");
            person1.setAccessLevel(1);

            session.persist(person1);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }
    }
}
