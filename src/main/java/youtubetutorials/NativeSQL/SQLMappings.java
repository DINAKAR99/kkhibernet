package youtubetutorials.NativeSQL;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.NativeQuery;

import jakarta.persistence.ParameterMode;

public class SQLMappings {
    public static void PersonGenerator(SessionFactory s1) {

        Person p1 = new Person();
        p1.setName("abrar");
        p1.setOccupation("mechanic");

        Phone ph1 = new Phone();
        ph1.setNumber(11111);
        ph1.setType(PhoneType.MOBILE);
        ph1.setPerson_linked(p1);
        Phone ph2 = new Phone();
        ph2.setNumber(22222);
        ph2.setType(PhoneType.SMARTPHONE);
        ph2.setPerson_linked(p1);

        ///
        p1.getPhones().add(ph1);
        p1.getPhones().add(ph2);
        ///

        Call c1 = new Call();
        c1.setDuration(1);
        c1.setPhone(ph1);
        Call c2 = new Call();
        c2.setPhone(ph1);
        c2.setDuration(2);

        Call c3 = new Call();
        c3.setPhone(ph2);
        c3.setDuration(4);
        Call c4 = new Call();
        c4.setPhone(ph2);
        c4.setDuration(5);

        ph1.getCalls().add(c1);
        ph1.getCalls().add(c2);

        ph2.getCalls().add(c3);
        ph2.getCalls().add(c4);

        Partner part1 = new Partner();
        part1.setName("yash angulam");
        part1.setVersion(10);

        Session openSession = s1.openSession();

        Transaction beginTransaction = openSession.beginTransaction();

        // openSession.persist(p1);
        openSession.persist(part1);
        ;

        beginTransaction.commit();

        openSession.close();

    }

    public static void NativeSQLManytoOne(SessionFactory s1) {
        Session openSession = s1.openSession();
        String command = "select * from Phone";
        List<Phone> list = openSession.createNativeQuery(command).addEntity(Phone.class).list();

        for (Phone phone : list) {
            System.out.println(phone);

            Person person_linked = phone.getPerson_linked();
            System.out.println(person_linked);

        }

        openSession.close();

    }

    // public static void NativeSQLManytoOneWithResultTransformer(SessionFactory s1)
    // {
    // Session openSession = s1.openSession();
    // String command = "select * from Phone ph left join person p on
    // ph.person_linked_id=p.id";
    // openSession.createNativeQuery(command).addEntity("phone", Phone.class)
    // .addJoin("p", "phone.person").setResultListTransformer().list();

    // for (Phone phone : list) {
    // System.out.println(phone);

    // Person person_linked = phone.getPerson_linked();
    // System.out.println(person_linked);

    // }

    // openSession.close();

    // }

    public static void callStoredProcedure(SessionFactory s1) {
        Session openSession = s1.openSession();
        ProcedureCall storedProcedureQuery = openSession.createStoredProcedureQuery("sp_count_phones");
        storedProcedureQuery.registerStoredProcedureParameter("personId", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("phoneCount", Integer.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("personId", 1);

        storedProcedureQuery.execute();
        Integer outputParameterValue = (Integer) storedProcedureQuery.getOutputParameterValue("phoneCount");

        System.out.println("phone count" + outputParameterValue);

    }

    public static void main(String[] args) {
        SessionFactory s1 = new Configuration().configure().buildSessionFactory();

        PersonGenerator(s1);
        // NativeSQLManytoOne(s1);
        // callStoredProcedure(s1);

    }

}
