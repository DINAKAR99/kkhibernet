package shaksat;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;

import secondarytable.StudPass;
import youtubetutorials.HibernateUtil;

public class StudentData {

  public static void main(String[] args) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      Transaction transaction = session.beginTransaction();

      // Generate and persist 20 sample instances
      for (int i = 1; i <= 20; i++) {
        StudPass studPass = new StudPass();
        session.save(studPass);

        // Flush and clear the session after every 5 instances
        if (i % 5 == 0) {
          session.flush();
          session.clear();
          transaction.commit();
        }
      }

    }
  }

  private static PassDetails insertdata(int index) {
    return new PassDetails(
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
  }
}
