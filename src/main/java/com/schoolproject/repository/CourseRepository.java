package com.schoolproject.repository;

import com.schoolproject.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    // 추가적인 쿼리 메서드를 여기에 정의
}
