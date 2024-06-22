package com.schoolproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.schoolproject.entity.Evaluation;

import jakarta.transaction.Transactional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
	boolean existsByEvaluationId(Long evluationId);
	
	@Modifying
    @Transactional
    @Query(value = "ALTER TABLE Evaluation AUTO_INCREMENT = 1", nativeQuery = true)
    void updateAutoIncrementValue();

	List<Evaluation> findByStudentNumber(int studentNumber);
	
	Optional<Evaluation> findByLectureName(String lectureName);

	List<Evaluation> findByProfessorNameAndProfessorDept(String professorName, String professorDept);

	List<Evaluation> findByLectureNameAndProfessorNameAndProfessorDeptAndLectureSemester(String lectureName,
			String professorName, String professorDept, String lectureSemester);
}