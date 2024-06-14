package com.schoolproject.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "evaluation")
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;
    
    @Column(name = "student_email", length = 100)
    private String studentEmail;
    
    @Column(name = "lecture_name", length = 100)
    private String lectureName;
    
    @Column(name = "professor_name", length = 30)
    private String professorName;
    
    @Column(name = "lecture_semester", length = 20)
    private String lectureSemester;

    @Column(name = "lecture_grade", columnDefinition = "int DEFAULT 1")
    private int lectureGrade;
    
    @Column(name = "lecture_type", length = 10)
    private String lectureType;
    
    @Column(name = "evaluation_title", length = 50)
    private String evaluationTitle;
    
    @Column(name = "evaluation_content", length = 2048)
    private String evaluationContent;
    
    @Column(name = "lecture_totalscore", length = 5)
    private String lectureTotalScore;
    
    @Column(name = "lecture_creditscore", length = 5)
    private String lectureCreditScore;
    
    @Column(name = "lecture_score", length = 5)
    private String lectureScore;
    
    @Column(name = "lecture_likecount")
    private int lectureLikeCount;
    
    @Column(name = "lecture_registration_date")
    private LocalDate lectureRegistrationDate;
    
    // Getters and Setters

    public Long getEvaluationId() {
        return evaluationId;
    }

    public LocalDate getLectureRegistrationDate() {
		return lectureRegistrationDate;
	}

	public void setLectureRegistrationDate(LocalDate lectureRegistrationDate) {
		this.lectureRegistrationDate = lectureRegistrationDate;
	}

	public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
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

	public String getLectureType() {
        return lectureType;
    }

    public void setLectureType(String lectureType) {
        this.lectureType = lectureType;
    }

    public String getEvaluationTitle() {
        return evaluationTitle;
    }

    public void setEvaluationTitle(String evaluationTitle) {
        this.evaluationTitle = evaluationTitle;
    }

    public String getEvaluationContent() {
        return evaluationContent;
    }

    public void setEvaluationContent(String evaluationContent) {
        this.evaluationContent = evaluationContent;
    }

    public String getLectureTotalScore() {
        return lectureTotalScore;
    }

    public void setLectureTotalScore(String lectureTotalScore) {
        this.lectureTotalScore = lectureTotalScore;
    }

    public String getLectureCreditScore() {
        return lectureCreditScore;
    }

    public void setLectureCreditScore(String lectureCreditScore) {
        this.lectureCreditScore = lectureCreditScore;
    }

    public String getLectureScore() {
        return lectureScore;
    }

    public void setLectureScore(String lectureScore) {
        this.lectureScore = lectureScore;
    }

    public int getLectureLikeCount() {
        return lectureLikeCount;
    }

    public void setLectureLikeCount(int lectureLikeCount) {
        this.lectureLikeCount = lectureLikeCount;
    }
}