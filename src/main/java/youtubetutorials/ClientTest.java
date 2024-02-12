package youtubetutorials;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ClientTest {
    public static void main(String[] args) {
        
        try(   Session openSession = HibernateUtil.getSessionFactory().openSession();) 
        {
            // String SQL="SELECT version()";
            // String singleResult = (String) openSession.createNativeQuery(SQL).getSingleResult();
            //     System.out.println("my sql version -> "+singleResult);

            Student s1=new Student();
            s1.setId(1);
            s1.setName("bheem2");
            s1.setCity("armoor");

             Student student = openSession.get(Student.class, 1);
            //  System.out.println(student);
            openSession.beginTransaction();
          
            openSession.remove(student);
            openSession.getTransaction().commit();



            // openSession.merge(s1);




        } catch (HibernateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
