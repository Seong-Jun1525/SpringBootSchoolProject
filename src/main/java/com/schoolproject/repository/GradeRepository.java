package com.schoolproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.schoolproject.entity.Grade;


@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
	
    List<Grade> findByStudentEmail(String studentEmail);
    List<Grade> findByProfessorEmail(String ProfessorEmail);
    
    
    
}
