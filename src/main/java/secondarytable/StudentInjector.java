package secondarytable;

import java.util.Date;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import youtubetutorials.HibernateUtil;

public class StudentInjector {

    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Generate and persist 20 sample instances
            Scanner sc = new Scanner(System.in);
            Transaction transaction = session.beginTransaction();
            for (int i = 1; i <= 5; i++) {
                System.out.println("enter center id");
                int center_id = sc.nextInt();
                StudPass studPass = createSampleStudPass(i, center_id);
                session.persist(studPass);

                // Flush and clear the session after every 5 instances
                if (i % 5 == 0) {
                    session.flush();
                    session.clear();
                }

            }
            sc.close();

            transaction.commit();
        }
    }

    public static Center createCenter(int index, StudPass student, int center_id) {

        Center updatedCenter = new Center(center_id, index, new Date());
        updatedCenter.getStudent().add(student);
        return updatedCenter;
    }

    private static StudPass createSampleStudPass(int index, int center_id) {
        StudPass stud = new StudPass(
                "BP" + index,
                1, // Sample instDistId
                2, // Sample instMandalId
                "Univ" + index,
                "Inst" + index,
                "Course" + index,
                "Year" + index,
                "AdmissionNo" + index,
                "FromPlace" + index,
                "ToPlace" + index,
                "Via" + index,
                "ResAddress" + index,
                3, // Sample studentResAddDistId
                4, // Sample studentResAddMandalId
                5, // Sample studentResAddVillageId
                123456789012345L + index, // Sample studentCellNo
                "Qualification" + index,
                "Name" + index,
                "FatherName" + index,
                new Date(), // Sample studentDateOfBirth
                987654321012345L + index, // Sample studentSSCHallTicketNumber
                "SSCBoard" + index,
                2022L + index, // Sample studentSSCPassYear
                "SSCPassType" + index,
                'M', // Sample studentGender
                "AadharNo" + index);
        Center c1 = createCenter(index, stud, center_id);

        stud.setCenter(c1);
        return stud;

    }
}
