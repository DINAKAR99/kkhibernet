package youtubetutorials.Inheritance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class VehicleEngine {

    public static void FetchVehiclesSingleTable(SessionFactory s1) {
        Session session1 = s1.openSession();

        Vehicle vehicle = session1.get(Vehicle.class, 2);

        if (vehicle instanceof Vehicle && !(vehicle instanceof Twowheeler) && !(vehicle instanceof FourWheeler)) {
            System.out.println(vehicle);

        } else if ((vehicle instanceof Twowheeler)) {
            System.out.println(vehicle);
        }

        else if ((vehicle instanceof FourWheeler)) {
            System.out.println(vehicle);
        }

        session1.close();
    }

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // Session openSession = sessionFactory.openSession();

        // Vehicle v1 = new Vehicle();
        // v1.setVehicleName("sample ");

        // Twowheeler t1 = new Twowheeler();

        // t1.setVehicleName("herohonda");
        // t1.setSteeringWheel(" default ");

        // FourWheeler f1 = new FourWheeler();
        // f1.setVehicleName("porsche");
        // f1.setSteeringHandle("default handle");

        // Transaction beginTransaction = openSession.beginTransaction();

        // openSession.persist(v1);
        // openSession.persist(t1);
        // openSession.persist(f1);

        // beginTransaction.commit();
        // openSession.close();

        FetchVehiclesSingleTable(sessionFactory);

        sessionFactory.close();

    }
}
