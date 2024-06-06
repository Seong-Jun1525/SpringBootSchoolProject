package com.schoolproject.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.schoolproject.entity.Professor;
import com.schoolproject.repository.ProfessorRepository;

@Service
public class ProfessorService {
	private final ProfessorRepository professorRepository;
	
	public ProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}
	
	public Professor registerProfessor(Professor professor) {
		professor.setProfessorRegistrationDate(LocalDate.now());
		return professorRepository.save(professor);
	}
	
	public List<Professor> findAll() {
        return professorRepository.findAll();
    }
}