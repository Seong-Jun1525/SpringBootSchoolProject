package com.schoolproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.entity.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
	List<Evaluation> findByStudentEmail(String studentEmail);

	boolean existsByEvaluationId(Long evluationId);
}