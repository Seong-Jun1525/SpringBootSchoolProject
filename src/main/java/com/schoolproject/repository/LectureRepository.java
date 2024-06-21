package com.schoolproject.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.schoolproject.entity.Lecture;

import jakarta.transaction.Transactional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
	List<Lecture> findByLectureSemesterAndLectureMajorAndLectureGrade(
	        String lectureSemester, String lectureMajor, int lectureGrade);
	
	List<Lecture> findByLectureMajorAndLectureGradeAndLectureType(String lectureMajor, int lectureGrade, String lectureType);

	@Modifying
    @Transactional
    @Query(value = "ALTER TABLE Lecture AUTO_INCREMENT = 1", nativeQuery = true)
    void updateAutoIncrementValue();
	
    @Modifying
    @Transactional
    @Query("UPDATE Lecture l SET l.deadline = :deadline WHERE l.lectureName = :lectureName") // lecture 테이블 deadline 값 변경
    void updateDeadline(@Param("lectureName") String lectureName, @Param("deadline") int deadline);
    
    @Modifying
    @Transactional
    @Query("UPDATE Lecture l SET l.studentCount = l.studentCount + 1 WHERE l.lectureName = :lectureName") // lecture 테이블 student_count 값 증가
    void updateLectureNameAndStudentCount(@Param("lectureName") String lectureName);

	List<Lecture> findByLectureName(String lectureName);
	
	// 추가 수강신청 목록 불러오기
	List<Lecture> findByLectureMajorAndLectureGradeAndLectureTypeAndDeadline(String lectureMajor, int lectureGrade, String lectureType, int deadline);
}