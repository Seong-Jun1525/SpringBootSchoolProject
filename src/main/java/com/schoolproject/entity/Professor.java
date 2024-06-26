package com.schoolproject.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long professorId;
    
    @Column(name = "professor_name", length = 30)
    private String professorName;
    
    @Column(name = "professor_dept", length = 50)
    private String professorDept;
    
    @Column(name = "professor_email", nullable = false, unique = true, length = 100)
    private String professorEmail;
    
    @Column(name = "professor_phone", length = 20)
    private String professorPhone;
    
    @Column(name = "professor_registration_date")
    private LocalDate professorRegistrationDate;
    
    @Column(name = "professor_pw", length = 20)
    private String professorPw;
    
    // Getters and Setters
    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorDept() {
        return professorDept;
    }

    public void setProfessorDept(String professorDept) {
        this.professorDept = professorDept;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public void setProfessorEmail(String professorEmail) {
        this.professorEmail = professorEmail;
    }

    public String getProfessorPhone() {
        return professorPhone;
    }

    public void setProfessorPhone(String professorPhone) {
        this.professorPhone = professorPhone;
    }

    public LocalDate getProfessorRegistrationDate() {
        return professorRegistrationDate;
    }

    public void setProfessorRegistrationDate(LocalDate professorRegistrationDate) {
        this.professorRegistrationDate = professorRegistrationDate;
    }

    public String getProfessorPw() {
        return professorPw;
    }

    public void setProfessorPw(String professorPw) {
        this.professorPw = professorPw;
    }
}