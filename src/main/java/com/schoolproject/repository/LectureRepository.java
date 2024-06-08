package com.schoolproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.entity.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    // 추가적인 쿼리 메서드를 여기에 정의
}
