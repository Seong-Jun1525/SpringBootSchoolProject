package com.schoolproject.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    
    @Column(name = "student_name", nullable = false, columnDefinition = "varchar(30) default ''")
    private String studentName;
    
    @Column(name = "student_number", nullable = false, columnDefinition = "int default 0")
    private Integer studentNumber;
    
    @Column(name = "student_major", nullable = false, columnDefinition = "varchar(100) default ''")
    private String studentMajor;

    @Column(name = "student_grade", nullable = false, columnDefinition = "int default 1")
    private Integer studentGrade;
    
    @Column(name = "student_class", nullable = false, columnDefinition = "int default 0")
    private Integer studentClass;
    
    @Column(name = "student_email", nullable = false, columnDefinition = "varchar(100) default ''")
    private String studentEmail;

    @Column(name = "student_pw", nullable = false, columnDefinition = "varchar(20) default ''")
    private String studentPw;
    
    @Column(name = "student_birthdate", nullable = false, columnDefinition = "varchar(10) default ''")
    private String studentBirthdate;
    
    @Column(name = "student_phone", nullable = false, columnDefinition = "varchar(15) default ''")
    private String studentPhone;
    
    @Column(name = "student_gender", nullable = false, columnDefinition = "varchar(10) default ''")
    private String studentGender;
    
    @Column(name = "student_point", nullable = false, columnDefinition = "int default 0")
    private Integer studentPoint;
    
    @Column(name = "student_registration_date", nullable = false, columnDefinition = "date default '1970-01-01'")
    private LocalDate studentRegistrationDate;

    // Getters and setters

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public Integer getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(Integer studentGrade) {
        this.studentGrade = studentGrade;
    }

    public Integer getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(Integer studentClass) {
		this.studentClass = studentClass;
	}

	public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
	
	public String getStudentEmail() {
        return studentEmail;
    }

	public void setStudentPw(String studentPw) {
        this.studentPw = studentPw;
    }
	
    public String getStudentPw() {
        return studentPw;
    }

    public String getStudentBirthdate() {
        return studentBirthdate;
    }

    public void setStudentBirthdate(String studentBirthdate) {
        this.studentBirthdate = studentBirthdate;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public Integer getStudentPoint() {
        return studentPoint;
    }

    public void setStudentPoint(Integer studentPoint) {
        this.studentPoint = studentPoint;
    }

    public LocalDate getStudentRegistrationDate() {
        return studentRegistrationDate;
    }

    public void setStudentRegistrationDate(LocalDate studentRegistrationDate) {
        this.studentRegistrationDate = studentRegistrationDate;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentNumber=" + studentNumber
                + ", studentMajor=" + studentMajor + ", studentGrade=" + studentGrade + ", studentClass=" + studentClass
                + ", studentEmail=" + studentEmail + ", studentPw=" + studentPw + ", studentBirthdate="
                + studentBirthdate + ", studentPhone=" + studentPhone + ", studentGender=" + studentGender
                + ", studentPoint=" + studentPoint + ", studentRegistrationDate=" + studentRegistrationDate + "]";
    }
}