package com.schoolproject.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "enrolment")
public class Enrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrolment_id")
    private Long enrolmentId;

    @Column(name = "student_number", nullable = false)
    private int studentNumber;

    @Column(name = "student_name", nullable = false, length = 30)
    private String studentName;

    @Column(name = "student_major", nullable = false, length = 100)
    private String studentMajor;

    @Column(name = "student_point", columnDefinition = "int default 15")
    private int studentPoint;

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
    
    public Enrolment() {
		// TODO Auto-generated constructor stub
	}
    
	public Enrolment(Long enrolmentId, int studentNumber, String studentName, String studentMajor, int studentPoint,
			String lectureName, String lectureSemester, LocalDateTime lectureDate, int lectureCredit,
			String lectureType) {
		super();
		this.enrolmentId = enrolmentId;
		this.studentNumber = studentNumber;
		this.studentName = studentName;
		this.studentMajor = studentMajor;
		this.studentPoint = studentPoint;
		this.lectureName = lectureName;
		this.lectureSemester = lectureSemester;
		this.lectureDate = lectureDate;
		this.lectureCredit = lectureCredit;
		this.lectureType = lectureType;
	}

	// Getters and Setters
    public Long getEnrolmentId() {
        return enrolmentId;
    }

    public void setEnrolmentId(Long enrolmentId) {
        this.enrolmentId = enrolmentId;
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

    public int getStudentPoint() {
		return studentPoint;
	}

	public void setStudentPoint(int studentPoint) {
		this.studentPoint = studentPoint;
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