package com.schoolproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
	List<Board> findByProfessorEmail(String ProfessorEmail);

}
