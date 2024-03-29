package youtubetutorials.OptimisticLocking;

import org.hibernate.Session;
import org.hibernate.Transaction;

import youtubetutorials.HibernateUtil;
import youtubetutorials.NativeSQL.Person;

public class SimulateOptimisticLockingTest {
    public static void main(String[] args) {
        Long perosnId = 1L;
        Thread t1 = new Thread(new Runnable() {
            Session session1 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            @Override
            public void run() {
                Person3 person = session1.get(Person3.class, perosnId);
                if (person != null) {
                    tx = session1.beginTransaction();

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    person.setPassword("pass@34");
                    session1.update(person);
                    tx.commit();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            Session session2 = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            @Override
            public void run() {
                Person3 person = session2.get(Person3.class, perosnId);
                if (person != null) {
                    tx = session2.beginTransaction();
                    person.setPassword("pass#234");
                    session2.update(person);
                    tx.commit();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
