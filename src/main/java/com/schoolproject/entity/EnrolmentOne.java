package com.schoolproject.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "enrolmentone")
public class EnrolmentOne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_enrolment_id")
    private int majorEnrolmentId;

    @Column(name = "student_number", nullable = false)
    private int studentNumber;

    @Column(name = "student_name", nullable = false, length = 30)
    private String studentName;

    @Column(name = "student_major", nullable = false, length = 100)
    private String studentMajor;

    @Column(name = "lecture_name", nullable = false, length = 100)
    private String lectureName;

    @Column(name = "lecture_semester", nullable = false, length = 50)
    private String lectureSemester;

    @Column(name = "lecture_date", nullable = false)
    private LocalDateTime lectureDate;

    @Column(name = "lecture_credit", nullable = false, columnDefinition = "int default 3")
    private int lectureCredit = 3;

    @Column(name = "lecture_type", nullable = false, length = 20, columnDefinition = "varchar(20) default '전공'")
    private String lectureType = "전공";

    // Getters and Setters
    public int getMajorEnrolmentId() {
        return majorEnrolmentId;
    }

    public void setMajorEnrolmentId(int majorEnrolmentId) {
        this.majorEnrolmentId = majorEnrolmentId;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getLectureSemester() {
        return lectureSemester;
    }

    public void setLectureSemester(String lectureSemester) {
        this.lectureSemester = lectureSemester;
    }

    public LocalDateTime getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(LocalDateTime lectureDate) {
        this.lectureDate = lectureDate;
    }

    public int getLectureCredit() {
        return lectureCredit;
    }

    public void setLectureCredit(int lectureCredit) {
        this.lectureCredit = lectureCredit;
    }

    public String getLectureType() {
        return lectureType;
    }

    public void setLectureType(String lectureType) {
        this.lectureType = lectureType;
    }
}