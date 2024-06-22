package com.schoolproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.schoolproject.entity.Student;

import jakarta.transaction.Transactional;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
	boolean existsByStudentNumber(Integer studentNumber);
    Optional<Student> findByStudentNumberAndStudentMajor(Integer studentNumber, String studentMajor);
    
    Optional<Student> findByStudentEmail(String email);

    Student findByStudentNumber(int studentNumber);
    
    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE Student AUTO_INCREMENT = 1", nativeQuery = true)
    void updateAutoIncrementValue();
    
    int countByStudentMajor(String studentMajor);
    
    String findByStudentName(String StudentEmail);
    
}

