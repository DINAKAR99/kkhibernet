package youtubetutorials.HQL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import youtubetutorials.Address;
import youtubetutorials.Artist;
import youtubetutorials.layer.Employee;

public class HQL_Engine {

    public static void getArtistsByhql(SessionFactory s) {

        Session session1 = s.openSession();

        // hql goes here
        String command = "from Artist";

        Query<Artist> query = session1.createQuery(command, Artist.class);

        List<Artist> list = query.list();

        list.forEach(e -> System.out.println(e));
        // hql ends here

    }

    public static void getArtistsIdAndName(SessionFactory s) {

        Session session1 = s.openSession();

        // hql goes here
        String command = "select id,Name from Artist";

        Query query = session1.createQuery(command);

        List<Object[]> list = query.list();
        // System.out.println(list);

        for (Object[] object : list) {
            Integer i1 = (Integer) object[0];
            System.out.println(i1);

        }
        // hql ends here

    }

    public static void AvengerCreatorEngine(SessionFactory s) {
        Session session1 = s.openSession();
        Transaction beginTransaction = session1.beginTransaction();

        // avenger table 1 creation goes here

        Avengers a1 = new Avengers();
        a1.setAvengerName("IRON MAN");
        a1.setAvengerRole("FLYING WITH SUIT");
        Avengers a2 = new Avengers();
        a2.setAvengerName("SUPER MAN");
        a2.setAvengerRole("FLYING");
        Avengers a3 = new Avengers();
        a3.setAvengerName("THOR");
        a3.setAvengerRole("MERUPU DEVUDU");

        session1.persist(a3);
        session1.persist(a2);
        session1.persist(a1);

        beginTransaction.commit();
    }

    public static void InsertIntoAvenger2ByHQL(SessionFactory s) {
        Session session1 = s.openSession();

        String command = "insert into Avengers2(AvengerName,AvengerRole) select AvengerName,AvengerRole  from Avengers ";
        Query query = session1.createQuery(command);

        Transaction beginTransaction = session1.beginTransaction();
        int executeUpdate = query.executeUpdate();
        System.out.println("no of rows inserted :" + executeUpdate);

        beginTransaction.commit();

        session1.close();
        s.close();

    }

    public static void DeleteAvengerByHQL(SessionFactory s) {
        Session session1 = s.openSession();
        String command = "DELETE from Avengers2 where AvengerName='IRON MAN' ";
        Query query = session1.createQuery(command);

        Transaction beginTransaction = session1.beginTransaction();

        int executeUpdate = query.executeUpdate();

        System.out.println(" AVENGER HAS BEEN UPDATED :" + executeUpdate);

        beginTransaction.commit();

        session1.close();
        s.close();

    }

    public static void UpdateAvenger2ByHQL(SessionFactory s) {
        Session session1 = s.openSession();
        String role = "THUNDER GOD  POWERFUL";
        String name = "THOR";
        String command = "UPDATE Avengers2 set AvengerRole=?  where AvengerName=?";
        Query query = session1.createQuery(command);
        query.setParameter(0, role);
        query.setParameter(1, name);

        Transaction beginTransaction = session1.beginTransaction();
        int executeUpdate = query.executeUpdate();
        System.out.println(" AVENGER HAS BEEN UPDATED :" + executeUpdate);

        beginTransaction.commit();

        session1.close();
        s.close();

    }

    public static void fetchAddressFromArtistByHQLonetone(SessionFactory s) {
        Session session1 = s.openSession();

        String command = "FROM Artist a1 LEFT JOIN FETCH a1.address1 WHERE a1.id=1";

        Query<Artist> query = session1.createQuery(command, Artist.class);
        // Transaction beginTransaction = session1.beginTransaction();

        Artist uniqueResult = query.uniqueResult();
        System.out.println(uniqueResult);
        List<Address> address1 = uniqueResult.getAddress1();

        for (Address address : address1) {
            System.out.println(address);

        }

    }

    public static void fetchArtistFromAddressByHQLonetone(SessionFactory s) {
        Session session1 = s.openSession();

        String command = "FROM Address a1 LEFT JOIN FETCH a1.Ar1 WHERE a1.addressId=2";

        Query<Address> query = session1.createQuery(command, Address.class);

        Address uniqueResult = query.uniqueResult();
        System.out.println(uniqueResult);

        Artist ar1 = uniqueResult.getAr1();

        System.out.println(ar1);

    }

    public static void fetchCertainColumnsByHQL(SessionFactory s) {
        Session session1 = s.openSession();

        String command = " SELECT A1.Name, ad1.city FROM Artist  A1 LEFT JOIN A1.address1 ad1 WHERE ad1.addressId=1";

        List<Object[]> list = session1.createQuery(command).list();

        for (Object[] objects : list) {
            System.out.println(objects[0]);
            System.out.println(objects[1]);

        }

    }

    public static void fetchbyOneToManyByHQL(SessionFactory s) {

        Session session1 = s.openSession();

        String command = "  from Artist WHERE  id=1";

        Query<Artist> query = session1.createQuery(command, Artist.class);
        // Transaction beginTransaction = session1.beginTransaction();
        Artist uniqueResult = query.uniqueResult();
        System.out.println(uniqueResult);

        uniqueResult.getAddress1().forEach(e -> System.out.println(e));
        ;

    }

    public static void fetchSomeColsOnetoManyByHQL(SessionFactory s) {

        Session session1 = s.openSession();

        String command = "  SELECT a1.Name ,add1.city from Artist a1 LEFT JOIN  a1.address1 add1 where a1.id=1 ";

        Query query = session1.createQuery(command);

        List<Object[]> list = query.list();

        for (Object[] objects : list) {

            System.out.println(objects[0]);
            System.out.println(objects[1]);

        }

    }

    public static void createEmployees(SessionFactory s) {

        Session session1 = s.openSession();

        Employee e1 = new Employee("jhon1", 10000);
        Employee e2 = new Employee("jhon2", 20000);
        Employee e3 = new Employee("jhon3", 30000);
        Employee e4 = new Employee("jhon4", 3000);

        Transaction beginTransaction = session1.beginTransaction();

        session1.persist(e1);
        session1.persist(e2);
        session1.persist(e3);
        session1.persist(e4);

        beginTransaction.commit();
    }

    public static void AggregateFunctions(SessionFactory s) {

        Session session1 = s.openSession();

        String command = "SELECT COUNT(e),SUM(e.salary),AVG(e.salary),MIN(e.salary),MAX(e.salary) FROM Employee e";

        Query<Object[]> query = session1.createQuery(command);

        List<Object[]> list = query.list();

        for (Object[] objects : list) {
            System.out.println(objects[0]);
            System.out.println(objects[1]);
            System.out.println(objects[2]);
            System.out.println(objects[3]);
            System.out.println(objects[4]);

        }

    }

    public static void AggregateFunctionsWithClass(SessionFactory s) {

        Session session1 = s.openSession();

        String command = "SELECT new youtubetutorials.HQL.EmployeeStats(  COUNT(e),SUM(e.salary),AVG(e.salary),MIN(e.salary),MAX(e.salary)) FROM Employee e";

        Query<EmployeeStats> query = session1.createQuery(command, EmployeeStats.class);
        EmployeeStats uniqueResult = query.uniqueResult();

        System.out.println(uniqueResult);
        s.close();

    }

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        // getArtistsByhql(sessionFactory);
        // getArtistsIdAndName(sessionFactory);
        // InsertIntoAvenger2ByHQL(sessionFactory);
        // UpdateAvenger2ByHQL(sessionFactory);
        // DeleteAvengerByHQL(sessionFactory);
        // fetchAddressFromArtistByHQLonetone(sessionFactory);
        // fetchArtistFromAddressByHQLonetone(sessionFactory);
        // fetchCertainColumnsByHQL(sessionFactory);
        // fetchbyOneToManyByHQL(sessionFactory);
        // fetchSomeColsOnetoManyByHQL(sessionFactory);

        // createEmployees(sessionFactory);
        // AggregateFunctions(sessionFactory);
        AggregateFunctionsWithClass(sessionFactory);

        sessionFactory.close();

    }
}
