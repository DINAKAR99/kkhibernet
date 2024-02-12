package youtubetutorials.NativeSQL;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import youtubetutorials.HibernateUtil;
import youtubetutorials.HQL.Avengers2;

public class SQLEngine {

    public static void SelectFrom(SessionFactory s1) {
        Session session1 = s1.openSession();
        String command = "select * from avengers2";
        List<Object[]> list = session1.createNativeQuery(command).list();
        for (Object[] objects : list) {
            System.out.println(objects[0]);
            System.out.println(objects[1]);
            System.out.println(objects[2]);
        }

        session1.close();
    }

    public static void SelectFromCertainCols(SessionFactory s1) {
        Session session1 = s1.openSession();
        String command = "select avengerRole,avengername from avengers2";
        List<Object[]> list = session1.createNativeQuery(command).list();
        for (Object[] objects : list) {
            System.out.println(objects[0]);
            System.out.println(objects[1]);
            // System.out.println(objects[2]);
        }

        session1.close();
    }

    public static void addEntity(SessionFactory s1) {
        Session session1 = s1.openSession();
        String command = "select  * from avengers2";
        List<Avengers2> list = session1.createNativeQuery(command).addEntity(Avengers2.class).list();

        for (Avengers2 avengers2 : list) {
            System.out.println(avengers2);
        }

        session1.close();
    }

    public static void CallSqlFunctionInHibernateClientTest(SessionFactory s1) {
        try (Session session = s1.openSession()) {
            int personId = 1;
            final AtomicReference<Integer> phoneCount = new AtomicReference<>();
            session.doWork(connection -> {
                try (CallableStatement callableStatement = connection.prepareCall(
                        "{ ? = call  count_phones(?) }")) {
                    callableStatement.registerOutParameter(1, Types.INTEGER);
                    callableStatement.setInt(2, personId);
                    callableStatement.execute();
                    phoneCount.set(callableStatement.getInt(1));
                }
            });
            if (phoneCount != null) {
                System.out.println("Phone Count:" + phoneCount.get());
            }

        }

    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        // SelectFrom(sessionFactory);
        // SelectFromCertainCols(sessionFactory);
        // addEntity(sessionFactory);

    }
}
