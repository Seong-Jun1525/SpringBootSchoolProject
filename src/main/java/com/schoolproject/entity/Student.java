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
    
    @Column(name = "student_name")
    private String studentName;
    
    @Column(name = "student_number")
    private String studentNumber;
    
    @Column(name = "student_major")
    private String studentMajor;
    
    @Column(name = "student_grade")
    private Integer studentGrade;
    
    @Column(name = "student_class")
    private String studentClass;
    
    @Column(name = "student_gender")
    private String studentGender;
    
    @Column(name = "student_email")
    private String studentEmail;
    
    @Column(name = "student_pw")
    private String studentPw;
    
    @Column(name = "student_point")
    private Integer studentPoint;
    
    @Column(name = "student_phone")
    private String studentPhone; // 추후 int로 수정하기
    
    @Column(name = "student_birthdate")
    private LocalDate studentBirthdate;
    
    @Column(name = "student_registration_date")
    private LocalDate studentRegistrationDate;
    
    // Getters and Setters
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

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
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

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPw() {
		return studentPw;
	}

	public void setStudentPw(String studentPw) {
		this.studentPw = studentPw;
	}

	public Integer getStudentPoint() {
        return studentPoint;
    }

    public void setStudentPoint(Integer studentPoint) {
        this.studentPoint = studentPoint;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public LocalDate getStudentBirthdate() {
        return studentBirthdate;
    }

    public void setStudentBirthdate(LocalDate studentBirthdate) {
        this.studentBirthdate = studentBirthdate;
    }

    public LocalDate getStudentRegistrationDate() {
        return studentRegistrationDate;
    }

    public void setStudentRegistrationDate(LocalDate studentRegistrationDate) {
        this.studentRegistrationDate = studentRegistrationDate;
    }
}