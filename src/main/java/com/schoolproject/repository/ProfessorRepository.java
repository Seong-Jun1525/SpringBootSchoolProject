package com.schoolproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.schoolproject.entity.Professor;

import jakarta.transaction.Transactional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // 교수 번호로 교수 찾기
    Optional<Professor> findByProfessorId(Long professorId);
    
    String findByProfessorName(String professorEmail);
    
    // 교수 이메일 주소 찾기
    Optional<Professor> findByProfessorEmail(String email);
    
    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE Professor AUTO_INCREMENT = 1", nativeQuery = true)
    void updateAutoIncrementValue();
}