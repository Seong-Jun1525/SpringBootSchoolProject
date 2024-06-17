package com.schoolproject.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolproject.entity.Lecture;
import com.schoolproject.repository.LectureRepository;

@Service
public class LectureService {
	private final LectureRepository lectureRepository;
	
	public LectureService(LectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}
	
	// 강의 등록
	public Lecture registerLecture(Lecture lecture) {
		lecture.setLectureRegistrationDate(LocalDate.now()); // 등록날짜는 현재날짜
		return lectureRepository.save(lecture);
	}
	
	// 강의 목록
	public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }
	
	// 검색 조건에 맞는 강의 목록
    public List<Lecture> findByEnrolmentLecture(Lecture lecture) {
        LocalDate year = lecture.getLectureRegistrationDate();
        String semester = lecture.getLectureSemester();
        String major = lecture.getLectureMajor();
        int grade = lecture.getLectureGrade();

        System.out.println("-----");
        
        System.out.println("입력받은 값 : " + lecture.getLectureGrade());
        System.out.println("입력받은 값 : " + lecture.getLectureMajor());
        System.out.println("입력받은 값 : " + lecture.getLectureSemester());
        System.out.println("입력받은 값 : " + lecture.getLectureRegistrationDate());

        return lectureRepository.findByLectureRegistrationDateAndLectureSemesterAndLectureMajorAndLectureGrade(
            year, semester, major, grade);
    }
    
    // 검색 조건에 맞는 전공 강의 목록
    public List<Lecture> findByMajorEnrolmentLecture(Lecture lecture) {
        String major = lecture.getLectureMajor();
        int grade = lecture.getLectureGrade();
        String type = lecture.getLectureType();

        System.out.println("-----");
        
        return lectureRepository.findByLectureMajorAndLectureGradeAndLectureType(major, grade, type);
    }
    
    // 전공수강신청 강의데이터 불러오기
    public List<Lecture> findByLectureMajorAndLectureGrade(String major, int grade, String type) {
    	return lectureRepository.findByLectureMajorAndLectureGradeAndLectureType(major, grade, type);
    }
}