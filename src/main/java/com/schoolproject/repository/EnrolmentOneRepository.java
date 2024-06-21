package com.schoolproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.schoolproject.entity.Enrolment;

import jakarta.transaction.Transactional;

public interface EnrolmentOneRepository extends JpaRepository<Enrolment, Long> {
	boolean existsByLectureNameAndStudentNumber(String lectureName, int studentNumber);
	List<Enrolment> findByStudentNumber(int studentNumber);
	List<Enrolment> findByLectureName(String lectureName);
	
	List<Enrolment> findByLectureNameOrderByStudentPointDesc(String lectureName);
	
	@Modifying
    @Transactional
    @Query(value = "ALTER TABLE Enrolment AUTO_INCREMENT = 1", nativeQuery = true)
    void updateAutoIncrementValue();
}