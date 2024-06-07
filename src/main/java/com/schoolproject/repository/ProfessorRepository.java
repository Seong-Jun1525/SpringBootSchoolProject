package com.schoolproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // 교수 번호로 교수 찾기
    Optional<Professor> findByProfessorId(Long professorId);
    Professor findByProfessorEmail(String email);
}