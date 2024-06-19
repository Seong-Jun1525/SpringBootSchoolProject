package com.schoolproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.entity.Enrolment;

public interface EnrolmentOneRepository extends JpaRepository<Enrolment, Long> {
	boolean existsByLectureNameAndStudentNumber(String lectureName, int studentNumber);
	List<Enrolment> findByStudentNumber(int studentNumber);
	List<Enrolment> findByLectureName(String lectureName);
	
	List<Enrolment> findByLectureNameOrderByStudentPointDesc(String lectureName);
}