package com.schoolproject.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.entity.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
	List<Lecture> findByLectureRegistrationDateAndLectureSemesterAndLectureMajorAndLectureGrade(
	        LocalDate lectureRegistrationDate, String lectureSemester, String lectureMajor, int lectureGrade);
	
	List<Lecture> findByLectureMajorAndLectureGrade(String lectureMajor, int lectureGrade);
}