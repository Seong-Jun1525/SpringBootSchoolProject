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
    @Column(name = "evaluation_id")
    private Long evaluationId;

    @Column(name = "lecture_name", length = 100)
    private String lectureName;

    @Column(name = "lecture_type", length = 10)
    private String lectureType;

    @Column(name = "evaluation_title", length = 50)
    private String evaluationTitle;

    @Column(name = "evaluation_content", length = 2048)
    private String evaluationContent;

    @Column(name = "lecture_totalscore", length = 5)
    private String lectureTotalScore;

    @Column(name = "lecture_score", length = 5)
    private String lectureScore;

    @Column(name = "student_number")
    private int studentNumber = 0;

    @Column(name = "evaluation_registration_date")
    private LocalDate evaluationRegistrationDate;

    // Getters and Setters

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
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

    public String getLectureScore() {
        return lectureScore;
    }

    public void setLectureScore(String lectureScore) {
        this.lectureScore = lectureScore;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public LocalDate getEvaluationRegistrationDate() {
        return evaluationRegistrationDate;
    }

    public void setEvaluationRegistrationDate(LocalDate evaluationRegistrationDate) {
        this.evaluationRegistrationDate = evaluationRegistrationDate;
    }
}