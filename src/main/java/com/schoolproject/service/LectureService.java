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
}
