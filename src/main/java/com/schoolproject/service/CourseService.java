package com.schoolproject.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolproject.entity.Course;
import com.schoolproject.repository.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository courseRepository;
	
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	
	// 강의 등록
	public Course registerCourse(Course course) {
		course.setCourseRegistrationDate(LocalDate.now()); // 등록날짜는 현재날짜
		return courseRepository.save(course);
	}
	
	// 강의 목록
	public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
