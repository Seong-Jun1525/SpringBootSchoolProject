package com.schoolproject.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolproject.entity.Lecture;
import com.schoolproject.exception.LectureNotFoundException;
import com.schoolproject.repository.LectureRepository;

import jakarta.transaction.Transactional;

@Service
public class LectureService {
	private final LectureRepository lectureRepository;
	
	public LectureService(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}
	
	// 강의 등록
	public Lecture registerLecture(Lecture lecture) {
		lecture.setLectureRegistrationDate(LocalDate.now()); // 등록날짜는 현재날짜
		lectureRepository.updateAutoIncrementValue();
		return lectureRepository.save(lecture);
	}
	
	// 강의 목록
	public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }
	
	// 검색 조건에 맞는 강의 목록
    public List<Lecture> findBySearchEnrolmentLecture(Lecture lecture) {
    	try {
            String semester = lecture.getLectureSemester();
            String major = lecture.getLectureMajor();
            int grade = lecture.getLectureGrade();
        	return lectureRepository.findByLectureSemesterAndLectureMajorAndLectureGrade(
        			semester, major, grade);
    	}
        catch(Exception e) {
        	throw new LectureNotFoundException("개설된 강의가 없습니다.");
        }
    }
    
    // 검색 조건에 맞는 강의 목록(전공, 교양)
    public List<Lecture> findByEnrolmentLecture(Lecture lecture) {
        String major = lecture.getLectureMajor();
        int grade = lecture.getLectureGrade();
        String type = lecture.getLectureType();
        int deadline = lecture.getDeadline();
        System.out.println("마감 값 : " + deadline); // 체크
        return lectureRepository.findByLectureMajorAndLectureGradeAndLectureTypeAndDeadline(major, grade, type, deadline);
    }
    
    // 검색 조건에 맞는 강의 목록(추가)
    public List<Lecture> findBySecondEnrolmentLecture(Lecture lecture) {
        String major = lecture.getLectureMajor();
        int grade = lecture.getLectureGrade();
        String type = lecture.getLectureType();
        int deadline = lecture.getDeadline();
        System.out.println("마감 값 : " + deadline); // 체크
        return lectureRepository.findByLectureMajorAndLectureGradeAndLectureTypeAndDeadline(major, grade, type, deadline);
    }
    
    // 전공수강신청 강의데이터 불러오기
    public List<Lecture> findByLectureMajorAndLectureGrade(String major, int grade, String type) {
    	return lectureRepository.findByLectureMajorAndLectureGradeAndLectureType(major, grade, type);
    }

    // deadline 컬럼값 수정
    @Transactional
    public void updateLectureDeadline(String lectureName, int i) {
        lectureRepository.updateDeadline(lectureName, i);
    }
    
    // student_count 컬럼값 1증가
    @Transactional
    public void updateLectureStudentCount(String lectureName) {
    	lectureRepository.updateLectureNameAndStudentCount(lectureName);
    }

    // 강의명으로 강의 찾기
	public List<Lecture> findByLectureName(String lectureName) {
		return lectureRepository.findByLectureName(lectureName);
	}
}