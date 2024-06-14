package com.schoolproject.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;
    
    @Column(name = "lecture_name", length = 100, nullable = false)
    private String lectureName;

    @Column(name = "lecture_major", length = 100, nullable = false, columnDefinition = "VARCHAR DEFAULT '컴퓨터소프트웨어공학과'")
    private String lectureMajor;
    
    @Column(name = "professor_name", length = 30)
    private String professorName;
    
    @Column(name = "professor_email", length = 100)
    private String professorEmail;
    
    @Column(name = "lecture_semester", length = 50)
    private String lectureSemester;
    
    @Column(name = "lecture_capacity")
    private int lectureCapacity;
    
    @Column(name = "lecture_grade", columnDefinition = "int DEFAULT 1")
    private int lectureGrade;
    
    @Column(name = "lecture_type", length = 50)
    private String lectureType;
    
    @Column(name = "lecture_lecture_day", length = 50)
    private String lectureLectureDay;
    
    @Column(name = "lecture_registration_date", columnDefinition = "DATE DEFAULT '1970-01-01'")
    private LocalDate lectureRegistrationDate;
    
    @Column(name="lecture_credit", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int lectureCredit;

    // Getters and Setters

    public Long getLectureId() {
        return lectureId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getLectureMajor() {
		return lectureMajor;
	}

	public void setLectureMajor(String lectureMajor) {
		this.lectureMajor = lectureMajor;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public String getProfessorEmail() {
        return professorEmail;
    }

    public void setProfessorEmail(String professorEmail) {
        this.professorEmail = professorEmail;
    }

    public String getLectureSemester() {
        return lectureSemester;
    }

    public void setLectureSemester(String lectureSemester) {
        this.lectureSemester = lectureSemester;
    }

    public int getLectureGrade() {
		return lectureGrade;
	}

	public void setLectureGrade(int lectureGrade) {
		this.lectureGrade = lectureGrade;
	}

	public int getLectureCapacity() {
        return lectureCapacity;
    }

    public void setLectureCapacity(int lectureCapacity) {
        this.lectureCapacity = lectureCapacity;
    }

    public String getLectureType() {
        return lectureType;
    }

    public void setLectureType(String lectureType) {
        this.lectureType = lectureType;
    }

    public String getLectureLectureDay() {
        return lectureLectureDay;
    }

    public void setLectureLectureDay(String lectureLectureDay) {
        this.lectureLectureDay = lectureLectureDay;
    }

    public LocalDate getLectureRegistrationDate() {
        return lectureRegistrationDate;
    }

    public void setLectureRegistrationDate(LocalDate lectureRegistrationDate) {
        this.lectureRegistrationDate = lectureRegistrationDate;
    }

	public int getLectureCredit() {
		return lectureCredit;
	}

	public void setLectureCredit(int lectureCredit) {
		this.lectureCredit = lectureCredit;
	}
}
