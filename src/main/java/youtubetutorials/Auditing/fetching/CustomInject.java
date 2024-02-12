package youtubetutorials.Auditing.fetching;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import youtubetutorials.HibernateUtil;
import youtubetutorials.Auditing.Customer;

public class CustomInject {

    public static void main(String[] args) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Customer customer1 = new Customer();
            customer1.setFirstName("Sean");
            customer1.setLastName("Murphy");
            customer1.setCreatedOn(new Date());

            session.save(customer1);

            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }

        System.out.println("----------Update a Record--------------------");

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            long customerId = 1L;

            Customer customer = session.get(Customer.class, customerId);
            if (customer != null) {
                tx = session.beginTransaction();
                customer.setLastName("Murphy 2");
                session.update(customer);

                tx.commit();
            } else {
                System.out.println("Customer details not found in database with ID:" + customerId);
            }

        } catch (Exception e) {
            if (tx != null && tx.isActive())
                tx.rollback();
            throw e;
        }

    }

}
