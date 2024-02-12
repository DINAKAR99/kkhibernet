package secondarytable;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;

@Entity
@Table(name = "stud_buspass_det")
@SecondaryTable(name = "stud_details", pkJoinColumns = @PrimaryKeyJoinColumn(name = "bp_appl_id"))
public class StudPass {

    @Id
    @Column(name = "bp_appl_id", length = 14, nullable = false)
    private String bpApplId;

    @Column(name = "inst_dist_id", nullable = false)
    private Integer instDistId;

    @Column(name = "inst_mandal_id", nullable = false)
    private Integer instMandalId;

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
    private String journeyFromPlace;

    @Column(name = "jrny_toplace", length = 125, nullable = false)
    private String journeyToPlace;

    @Column(name = "jrny_via", length = 125, nullable = false)
    private String journeyVia;

    @Column(name = "student_resaddress", length = 250, nullable = false)
    private String studentResAddress;

    @Column(name = "student_resadd_dist_id", nullable = false)
    private Integer studentResAddDistId;

    @Column(name = "student_resadd_mandal_id", nullable = false)
    private Integer studentResAddMandalId;

    @Column(name = "student_resadd_village_id", nullable = false)
    private Integer studentResAddVillageId;

    @Column(name = "student_cellno", precision = 15, scale = 0, nullable = false)
    private Long studentCellNo;

    @Column(name = "student_qualification") // Add the appropriate annotations or constraints if needed
    private String studentQualification;

    // @ManyToOne(mappedBy = "center", cascade = CascadeType.ALL)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "center_id")
    private Center center;

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    // secondary table fields
    @Column(name = "student_name", length = 75, table = "stud_details")
    private String studentName;

    @Column(name = "student_fname", length = 75, table = "stud_details")
    private String studentFatherName;

    @Column(name = "student_dob", table = "stud_details")
    private Date studentDateOfBirth;

    @Column(name = "student_ssc_htno", precision = 15, scale = 0, table = "stud_details")
    private Long studentSSCHallTicketNumber;

    @Column(name = "student_ssc_board", length = 25, table = "stud_details")
    private String studentSSCBoard;

    @Column(name = "student_ssc_passyear", precision = 4, scale = 0, table = "stud_details")
    private Long studentSSCPassYear;

    @Column(name = "student_ssc_passtype", length = 25, table = "stud_details")
    private String studentSSCPassType;

    @Column(name = "student_gender", length = 1, table = "stud_details")
    private char studentGender;

    @Column(name = "student_aadharno", length = 100, table = "stud_details")
    private String studentAadharNumber;
    // Constructors, getters, and setters

    public StudPass(String bpApplId, Integer instDistId, Integer instMandalId, String instUnivId, String instId,
            String studentCourseId, String presentCourseYear, String studentAdmissionNo, String journeyFromPlace,
            String journeyToPlace, String journeyVia, String studentResAddress, Integer studentResAddDistId,
            Integer studentResAddMandalId, Integer studentResAddVillageId, Long studentCellNo,
            String studentQualification, String studentName, String studentFatherName, Date studentDateOfBirth,
            Long studentSSCHallTicketNumber, String studentSSCBoard, Long studentSSCPassYear, String studentSSCPassType,
            char studentGender, String studentAadharNumber) {
        this.bpApplId = bpApplId;
        this.instDistId = instDistId;
        this.instMandalId = instMandalId;
        this.instUnivId = instUnivId;
        this.instId = instId;
        this.studentCourseId = studentCourseId;
        this.presentCourseYear = presentCourseYear;
        this.studentAdmissionNo = studentAdmissionNo;
        this.journeyFromPlace = journeyFromPlace;
        this.journeyToPlace = journeyToPlace;
        this.journeyVia = journeyVia;
        this.studentResAddress = studentResAddress;
        this.studentResAddDistId = studentResAddDistId;
        this.studentResAddMandalId = studentResAddMandalId;
        this.studentResAddVillageId = studentResAddVillageId;
        this.studentCellNo = studentCellNo;
        this.studentQualification = studentQualification;
        this.studentName = studentName;
        this.studentFatherName = studentFatherName;
        this.studentDateOfBirth = studentDateOfBirth;
        this.studentSSCHallTicketNumber = studentSSCHallTicketNumber;
        this.studentSSCBoard = studentSSCBoard;
        this.studentSSCPassYear = studentSSCPassYear;
        this.studentSSCPassType = studentSSCPassType;
        this.studentGender = studentGender;
        this.studentAadharNumber = studentAadharNumber;
    }

    public StudPass() {
    }

    public String getBpApplId() {
        return bpApplId;
    }

    public void setBpApplId(String bpApplId) {
        this.bpApplId = bpApplId;
    }

    public Integer getInstDistId() {
        return instDistId;
    }

    public void setInstDistId(Integer instDistId) {
        this.instDistId = instDistId;
    }

    public Integer getInstMandalId() {
        return instMandalId;
    }

    public void setInstMandalId(Integer instMandalId) {
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

    public String getJourneyFromPlace() {
        return journeyFromPlace;
    }

    public void setJourneyFromPlace(String journeyFromPlace) {
        this.journeyFromPlace = journeyFromPlace;
    }

    public String getJourneyToPlace() {
        return journeyToPlace;
    }

    public void setJourneyToPlace(String journeyToPlace) {
        this.journeyToPlace = journeyToPlace;
    }

    public String getJourneyVia() {
        return journeyVia;
    }

    public void setJourneyVia(String journeyVia) {
        this.journeyVia = journeyVia;
    }

    public String getStudentResAddress() {
        return studentResAddress;
    }

    public void setStudentResAddress(String studentResAddress) {
        this.studentResAddress = studentResAddress;
    }

    public Integer getStudentResAddDistId() {
        return studentResAddDistId;
    }

    public void setStudentResAddDistId(Integer studentResAddDistId) {
        this.studentResAddDistId = studentResAddDistId;
    }

    public Integer getStudentResAddMandalId() {
        return studentResAddMandalId;
    }

    public void setStudentResAddMandalId(Integer studentResAddMandalId) {
        this.studentResAddMandalId = studentResAddMandalId;
    }

    public Integer getStudentResAddVillageId() {
        return studentResAddVillageId;
    }

    public void setStudentResAddVillageId(Integer studentResAddVillageId) {
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

    public String getStudentFatherName() {
        return studentFatherName;
    }

    public void setStudentFatherName(String studentFatherName) {
        this.studentFatherName = studentFatherName;
    }

    public Date getStudentDateOfBirth() {
        return studentDateOfBirth;
    }

    public void setStudentDateOfBirth(Date studentDateOfBirth) {
        this.studentDateOfBirth = studentDateOfBirth;
    }

    public Long getStudentSSCHallTicketNumber() {
        return studentSSCHallTicketNumber;
    }

    public void setStudentSSCHallTicketNumber(Long studentSSCHallTicketNumber) {
        this.studentSSCHallTicketNumber = studentSSCHallTicketNumber;
    }

    public String getStudentSSCBoard() {
        return studentSSCBoard;
    }

    public void setStudentSSCBoard(String studentSSCBoard) {
        this.studentSSCBoard = studentSSCBoard;
    }

    public Long getStudentSSCPassYear() {
        return studentSSCPassYear;
    }

    public void setStudentSSCPassYear(Long studentSSCPassYear) {
        this.studentSSCPassYear = studentSSCPassYear;
    }

    public String getStudentSSCPassType() {
        return studentSSCPassType;
    }

    public void setStudentSSCPassType(String studentSSCPassType) {
        this.studentSSCPassType = studentSSCPassType;
    }

    public char getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(char studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentAadharNumber() {
        return studentAadharNumber;
    }

    public void setStudentAadharNumber(String studentAadharNumber) {
        this.studentAadharNumber = studentAadharNumber;
    }

    // ...
}
