package shaksat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "student_buspass_details")
@SecondaryTable(name = "student_details", pkJoinColumns = @PrimaryKeyJoinColumn(name = "bp_appl_id1"))
public class PassDetails {

  @Id
  @Column(name = "bp_appl_id1", length = 14, nullable = false)
  private String bpApplId;

  @Column(name = "inst_dist_id", nullable = false)
  private int instDistId;

  @Column(name = "inst_mandal_id", nullable = false)
  private int instMandalId;

  @Column(name = "inst_univ_id", length = 25)
  private String instUnivId;

  @Column(name = "inst_id", length = 200, nullable = false)
  private String instId;

  @Column(name = "student_course_id", length = 100, nullable = false)
  private String studentCourseId;

  @Column(name = "present_course_year", length = 25, nullable = false)
  private String presentCourseYear;

  @Column(name = "student_admission_no", length = 25, nullable = false)
  private String studentAdmissionNo;

  @Column(name = "jrny_fromplace", length = 125, nullable = false)
  private String jrnyFromPlace;

  @Column(name = "jrny_toplace", length = 125, nullable = false)
  private String jrnyToPlace;

  @Column(name = "jrny_via", length = 125, nullable = false)
  private String jrnyVia;

  @Column(name = "student_resaddress", length = 250, nullable = false)
  private String studentResAddress;

  @Column(name = "student_resadd_dist_id", nullable = false)
  private int studentResAddDistId;

  @Column(name = "student_resadd_mandal_id", nullable = false)
  private int studentResAddMandalId;

  @Column(name = "student_resadd_village_id", nullable = false)
  private int studentResAddVillageId;

  @Column(name = "student_cellno", nullable = false, precision = 15, scale = 0)
  private Long studentCellNo;

  @Column(name = "student_qualification", nullable = false, precision = 3, scale = 0)
  private String studentQualification;

  @Column(name = "center_id", nullable = false)
  private int center_id;
  /// secondary

  @Column(table = "student_details", name = "student_name", length = 75)
  private String studentName;

  @Column(table = "student_details", name = "student_fname", length = 75)
  private String studentFname;

  @Column(table = "student_details", name = "student_dob")
  private Date studentDob;

  @Column(table = "student_details", name = "student_ssc_htno", precision = 15, scale = 0)
  private Long studentSscHtno;

  @Column(table = "student_details", name = "student_ssc_board", length = 25)
  private String studentSscBoard;

  @Column(table = "student_details", name = "student_ssc_passyear", precision = 4, scale = 0)
  private Long studentSscPassyear;

  @Column(table = "student_details", name = "student_ssc_passtype", length = 25)
  private String studentSscPasstype;

  @Column(table = "student_details", name = "student_gender", length = 1)
  private char studentGender;

  @Column(table = "student_details", name = "student_aadharno", length = 100)
  private String studentAadharno;

  public PassDetails() {
  }

  public PassDetails(
      String bpApplId,
      int instDistId,
      int instMandalId,
      String instUnivId,
      String instId,
      String studentCourseId,
      String presentCourseYear,
      String studentAdmissionNo,
      String jrnyFromPlace,
      String jrnyToPlace,
      String jrnyVia,
      String studentResAddress,
      int studentResAddDistId,
      int studentResAddMandalId,
      int studentResAddVillageId,
      Long studentCellNo,
      String studentQualification,
      String studentName,
      String studentFname,
      Date studentDob,
      Long studentSscHtno,
      String studentSscBoard,
      Long studentSscPassyear,
      String studentSscPasstype,
      char studentGender,
      String studentAadharno) {
    this.bpApplId = bpApplId;
    this.instDistId = instDistId;
    this.instMandalId = instMandalId;
    this.instUnivId = instUnivId;
    this.instId = instId;
    this.studentCourseId = studentCourseId;
    this.presentCourseYear = presentCourseYear;
    this.studentAdmissionNo = studentAdmissionNo;
    this.jrnyFromPlace = jrnyFromPlace;
    this.jrnyToPlace = jrnyToPlace;
    this.jrnyVia = jrnyVia;
    this.studentResAddress = studentResAddress;
    this.studentResAddDistId = studentResAddDistId;
    this.studentResAddMandalId = studentResAddMandalId;
    this.studentResAddVillageId = studentResAddVillageId;
    this.studentCellNo = studentCellNo;
    this.studentQualification = studentQualification;
    this.studentName = studentName;
    this.studentFname = studentFname;
    this.studentDob = studentDob;
    this.studentSscHtno = studentSscHtno;
    this.studentSscBoard = studentSscBoard;
    this.studentSscPassyear = studentSscPassyear;
    this.studentSscPasstype = studentSscPasstype;
    this.studentGender = studentGender;
    this.studentAadharno = studentAadharno;
  }

  public String getBpApplId() {
    return bpApplId;
  }

  public void setBpApplId(String bpApplId) {
    this.bpApplId = bpApplId;
  }

  public int getInstDistId() {
    return instDistId;
  }

  public void setInstDistId(int instDistId) {
    this.instDistId = instDistId;
  }

  public int getInstMandalId() {
    return instMandalId;
  }

  public void setInstMandalId(int instMandalId) {
    this.instMandalId = instMandalId;
  }

  public String getInstUnivId() {
    return instUnivId;
  }

  public void setInstUnivId(String instUnivId) {
    this.instUnivId = instUnivId;
  }

  public String getInstId() {
    return instId;
  }

  public void setInstId(String instId) {
    this.instId = instId;
  }

  public String getStudentCourseId() {
    return studentCourseId;
  }

  public void setStudentCourseId(String studentCourseId) {
    this.studentCourseId = studentCourseId;
  }

  public String getPresentCourseYear() {
    return presentCourseYear;
  }

  public void setPresentCourseYear(String presentCourseYear) {
    this.presentCourseYear = presentCourseYear;
  }

  public String getStudentAdmissionNo() {
    return studentAdmissionNo;
  }

  public void setStudentAdmissionNo(String studentAdmissionNo) {
    this.studentAdmissionNo = studentAdmissionNo;
  }

  public String getJrnyFromPlace() {
    return jrnyFromPlace;
  }

  public void setJrnyFromPlace(String jrnyFromPlace) {
    this.jrnyFromPlace = jrnyFromPlace;
  }

  public String getJrnyToPlace() {
    return jrnyToPlace;
  }

  public void setJrnyToPlace(String jrnyToPlace) {
    this.jrnyToPlace = jrnyToPlace;
  }

  public String getJrnyVia() {
    return jrnyVia;
  }

  public void setJrnyVia(String jrnyVia) {
    this.jrnyVia = jrnyVia;
  }

  public String getStudentResAddress() {
    return studentResAddress;
  }

  public void setStudentResAddress(String studentResAddress) {
    this.studentResAddress = studentResAddress;
  }

  public int getStudentResAddDistId() {
    return studentResAddDistId;
  }

  public void setStudentResAddDistId(int studentResAddDistId) {
    this.studentResAddDistId = studentResAddDistId;
  }

  public int getStudentResAddMandalId() {
    return studentResAddMandalId;
  }

  public void setStudentResAddMandalId(int studentResAddMandalId) {
    this.studentResAddMandalId = studentResAddMandalId;
  }

  public int getStudentResAddVillageId() {
    return studentResAddVillageId;
  }

  public void setStudentResAddVillageId(int studentResAddVillageId) {
    this.studentResAddVillageId = studentResAddVillageId;
  }

  public Long getStudentCellNo() {
    return studentCellNo;
  }

  public void setStudentCellNo(Long studentCellNo) {
    this.studentCellNo = studentCellNo;
  }

  public String getStudentQualification() {
    return studentQualification;
  }

  public void setStudentQualification(String studentQualification) {
    this.studentQualification = studentQualification;
  }

  public String getStudentName() {
    return studentName;
  }

  public void setStudentName(String studentName) {
    this.studentName = studentName;
  }

  public String getStudentFname() {
    return studentFname;
  }

  public void setStudentFname(String studentFname) {
    this.studentFname = studentFname;
  }

  public Date getStudentDob() {
    return studentDob;
  }

  public void setStudentDob(Date studentDob) {
    this.studentDob = studentDob;
  }

  public Long getStudentSscHtno() {
    return studentSscHtno;
  }

  public void setStudentSscHtno(Long studentSscHtno) {
    this.studentSscHtno = studentSscHtno;
  }

  public String getStudentSscBoard() {
    return studentSscBoard;
  }

  public void setStudentSscBoard(String studentSscBoard) {
    this.studentSscBoard = studentSscBoard;
  }

  public Long getStudentSscPassyear() {
    return studentSscPassyear;
  }

  public void setStudentSscPassyear(Long studentSscPassyear) {
    this.studentSscPassyear = studentSscPassyear;
  }

  public String getStudentSscPasstype() {
    return studentSscPasstype;
  }

  public void setStudentSscPasstype(String studentSscPasstype) {
    this.studentSscPasstype = studentSscPasstype;
  }

  public char getStudentGender() {
    return studentGender;
  }

  public void setStudentGender(char studentGender) {
    this.studentGender = studentGender;
  }

  public String getStudentAadharno() {
    return studentAadharno;
  }

  public void setStudentAadharno(String studentAadharno) {
    this.studentAadharno = studentAadharno;
  }
}
