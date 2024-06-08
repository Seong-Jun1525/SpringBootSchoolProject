package com.schoolproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;

    @Column(name = "course_name", nullable = false, length = 100)
    private String courseName;

    @Column(name = "professor_email", nullable = false, length = 100)
    private String professorEmail;

    @Column(name = "course_semester", length = 50)
    private String courseSemester;

    @Column(name = "course_capacity")
    private Integer courseCapacity;

    @Column(name = "course_type", length = 50)
    private String courseType;

    @Column(name = "course_lecture_day", length = 50)
    private String courseLectureDay;

    @Column(name = "course_registration_date")
    private LocalDate courseRegistrationDate;

    // Getters and Setters

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public void setProfessorEmail(String professorEmail) {
        this.professorEmail = professorEmail;
    }

    public String getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(String courseSemester) {
        this.courseSemester = courseSemester;
    }

    public Integer getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(Integer courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseLectureDay() {
        return courseLectureDay;
    }

    public void setCourseLectureDay(String courseLectureDay) {
        this.courseLectureDay = courseLectureDay;
    }

    public LocalDate getCourseRegistrationDate() {
        return courseRegistrationDate;
    }

    public void setCourseRegistrationDate(LocalDate courseRegistrationDate) {
        this.courseRegistrationDate = courseRegistrationDate;
    }
}