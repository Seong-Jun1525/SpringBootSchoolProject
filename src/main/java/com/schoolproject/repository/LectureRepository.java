package com.schoolproject.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.schoolproject.entity.Lecture;

import jakarta.transaction.Transactional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
	List<Lecture> findByLectureRegistrationDateAndLectureSemesterAndLectureMajorAndLectureGrade(
	        LocalDate lectureRegistrationDate, String lectureSemester, String lectureMajor, int lectureGrade);
	
	List<Lecture> findByLectureMajorAndLectureGradeAndLectureType(String lectureMajor, int lectureGrade, String lectureType);

    @Modifying
    @Transactional
    @Query("UPDATE Lecture l SET l.deadline = :deadline WHERE l.lectureName = :lectureName") // lecture 테이블 deadline 값 변경
    void updateDeadline(@Param("lectureName") String lectureName, @Param("deadline") int deadline);

	List<Lecture> findByLectureName(String lectureName);
}