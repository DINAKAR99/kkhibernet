package youtubetutorials;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {


private static StandardServiceRegistry c1 ;

private static SessionFactory  s1;


  static {
    try {

        if(s1==null){

            c1= new StandardServiceRegistryBuilder().configure().build();
            MetadataSources m1=new MetadataSources(c1);
            Metadata metadata=m1.getMetadataBuilder().build();
            s1=metadata.getSessionFactoryBuilder().build();
        }
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        if (c1!=null) {
            StandardServiceRegistryBuilder.destroy(c1);
            
        }
    }
 }
   
 

 public static SessionFactory getSessionFactory(){
    return s1;
 }
}
