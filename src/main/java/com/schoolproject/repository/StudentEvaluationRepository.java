package com.schoolproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.entity.StudentEvaluation;

public interface StudentEvaluationRepository extends JpaRepository<StudentEvaluation, Long> {

}
