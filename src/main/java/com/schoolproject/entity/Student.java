package com.schoolproject.entity;

import java.time.LocalDate;
import java.util.Date;

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
    
    @Column(name = "student_name")
    private String studentName;
    
    @Column(name = "student_number")
    private Integer studentNumber;
    
    @Column(name = "student_major")
    private String studentMajor;

    @Column(name = "student_grade")
    private Integer studentGrade;
    
    @Column(name = "student_class")
    private Integer studentClass;
    
    @Column(name = "student_email")
    private String studentEmail;

    @Column(name = "student_pw")
    private String studentPw;
    
    @Column(name = "student_birthdate")
    private String studentBirthdate;
    
    @Column(name = "student_phone")
    private Integer studentPhone;
    
    @Column(name = "student_gender")
    private String studentGender;
    
    @Column(name = "student_point")
    private Integer studentPoint;
    
    @Column(name = "student_registration_date")
    private LocalDate studentRegistrationDate;

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

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public void setStudentPassword(String studentPw) {
		this.studentPw = studentPw;
	}


	public Integer getStudentPhone() {
		return studentPhone;
	}

	public void setStudentPhone(Integer studentPhone) {
		this.studentPhone = studentPhone;
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

	public void setStudentRegistrationDate(LocalDate localDate) {
		this.studentRegistrationDate = localDate;
	}

	public String getStudentGender() {
		return studentGender;
	}

	public void setStudentGender(String studentGender) {
		this.studentGender = studentGender;
	}

	public Integer getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(Integer studentClass) {
		this.studentClass = studentClass;
	}

	public String getStudentPw() {
		return studentPw;
	}

	public void setStudentPw(String studentPw) {
		this.studentPw = studentPw;
	}

	public String getStudentBirthdate() {
		return studentBirthdate;
	}

	public void setStudentBirthdate(String studentBirthdate) {
		this.studentBirthdate = studentBirthdate;
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