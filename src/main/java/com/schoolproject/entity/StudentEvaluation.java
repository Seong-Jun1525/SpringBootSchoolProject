package com.schoolproject.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "studentevaluation")
public class StudentEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;

    @Column(name = "lecture_name", length = 100)
    private String lectureName;

    @Column(name = "professor_name", length = 30)
    private String professorName;

    @Column(name = "lecture_semester", length = 20)
    private String lectureSemester;

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
    private Integer lectureLikeCount;

    @Column(name = "lecture_grade", columnDefinition = "int default 1")
    private Integer lectureGrade;

    @Column(name = "student_number", columnDefinition = "int default 0")
    private Integer studentNumber;

    @Column(name = "professor_dept", length = 255)
    private String professorDept;

    @Column(name = "evaluation_registration_date")
    @Temporal(TemporalType.DATE)
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

    public Integer getLectureLikeCount() {
        return lectureLikeCount;
    }

    public void setLectureLikeCount(Integer lectureLikeCount) {
        this.lectureLikeCount = lectureLikeCount;
    }

    public Integer getLectureGrade() {
        return lectureGrade;
    }

    public void setLectureGrade(Integer lectureGrade) {
        this.lectureGrade = lectureGrade;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getProfessorDept() {
        return professorDept;
    }

    public void setProfessorDept(String professorDept) {
        this.professorDept = professorDept;
    }

    public LocalDate getEvaluationRegistrationDate() {
        return evaluationRegistrationDate;
    }

    public void setEvaluationRegistrationDate(LocalDate localDate) {
        this.evaluationRegistrationDate = localDate;
    }
}