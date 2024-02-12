package youtubetutorials;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import youtubetutorials.HQL.Avengers;

public class MergeUpdate {
    public static void main(String[] args) {
        Avengers avg1 = null;
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        try (Session session1 = sessionFactory.openSession()) {

            avg1 = session1.get(Avengers.class, 2);
            System.out.println(avg1);

        } catch (Exception e) {
            // TODO: handle exception
        }

        avg1.setAvengerRole("SAVING THE WORLD ----");

        try (Session session2 = sessionFactory.openSession()) {

            Transaction beginTransaction = session2.beginTransaction();
            Avengers avg2 = session2.get(Avengers.class, 2);
            session2.update(avg1);
            beginTransaction.commit();
            System.out.println("success");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getStackTrace());
        }

    }
}
