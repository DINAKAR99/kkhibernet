package youtubetutorials;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ArtistEngine {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        Session openSession = sessionFactory.openSession();

        // openSession.beginTransaction();

        Artist s1 = new Artist();
        s1.setName("chota bheem1");

        Address a1 = new Address();
        a1.setCity("armoor");
        a1.setPin("233");
        Address a2 = new Address();
        a2.setCity("hyd");
        a2.setPin("536");

        s1.getAddress1().add(a2);
        s1.getAddress1().add(a1);

        a1.setAr1(s1);
        a2.setAr1(s1);

        // openSession.persist(s1);

        // openSession.persist(s2);
        // openSession.persist(s3);

        // FETCHING

        // Artist artist = openSession.get(Artist.class, 71);

        // if (artist != null) {
        // artist.getAddressCollection().forEach(System.out::println);
        // System.out.println(artist);

        // fetching bidirection mapping

        Address address = openSession.get(Address.class, 2);
        if (address != null) {
            System.out.println(address);
            Artist ar1 = address.getAr1();
            System.out.println(ar1);

        }

        // openSession.getTransaction().commit();

        openSession.close();
        sessionFactory.close();
    }

}
