package com.schoolproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.schoolproject.entity.Student;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
	boolean existsByStudentNumber(Integer studentNumber);
    Optional<Student> findByStudentNumberAndStudentMajor(Integer studentNumber, String studentMajor);
    
    Optional<Student> findByStudentEmail(String email);

    Student findByStudentNumber(int studentNumber);
    
}

