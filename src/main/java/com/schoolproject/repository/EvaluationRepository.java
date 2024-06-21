package com.schoolproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.schoolproject.entity.Evaluation;

import jakarta.transaction.Transactional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
	List<Evaluation> findByStudentEmail(String studentEmail);

	boolean existsByEvaluationId(Long evluationId);
	
	@Modifying
    @Transactional
    @Query(value = "ALTER TABLE Evaluation AUTO_INCREMENT = 1", nativeQuery = true)
    void updateAutoIncrementValue();
}