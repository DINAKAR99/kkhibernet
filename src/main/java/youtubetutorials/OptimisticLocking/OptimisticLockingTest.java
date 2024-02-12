package youtubetutorials.OptimisticLocking;

import org.hibernate.Session;
import org.hibernate.Transaction;

import youtubetutorials.HibernateUtil;
import youtubetutorials.NativeSQL.Person;

public class OptimisticLockingTest {
    public static void main(String[] args) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Long perosnId = 1L;
            Person3 person = session.get(Person3.class, perosnId);
            if (person != null) {
                tx = session.beginTransaction();
                person.setPassword("pass@1234");
                session.update(person);
                tx.commit();
            } else {
                System.out.println("Person details not found with ID: " + perosnId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
