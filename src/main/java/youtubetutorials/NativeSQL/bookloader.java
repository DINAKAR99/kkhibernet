package youtubetutorials.NativeSQL;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.BlobProxy;

import youtubetutorials.HibernateUtil;

public class bookloader {

    private static byte[] getImage() throws IOException {

        Path p1 = Paths.get("src\\main\\java\\inputPic\\edge.jpg");
        byte[] data = Files.readAllBytes(p1);

        return data;
    }

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().withOptions().jdbcTimeZone(TimeZone.getTimeZone("UTC"))
                .openSession()) {

            Person1 author1 = new Person1();
            author1.setName("Gavin King");

            book book1 = new book();
            book1.setIsbn("978-9730228236");
            book1.setTitle("Hibernate High-Performance Java Persistence");
            book1.setAuthor(author1);

            book book2 = new book();
            book2.setIsbn("900-9730228524");
            book2.setTitle("Hibernate Persistence Contexts");
            book2.setAuthor(author1);

            author1.getBooks().add(book1);
            author1.getBooks().add(book2);

            session.beginTransaction();
            // session.save(author1);
            // session.persist(author1);
            // session.saveOrUpdate(author1);
            Person2 per2 = new Person2();
            Date d1 = new Date();
            per2.setDate(d1);

            session.persist(per2);

            session.doWork(conn -> {
                try {
                    per2.setProfilepicc(BlobProxy.generateProxy(getImage()));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
