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
	
	public boolean login(String email, String password) {
        // 이메일을 통해 교수 정보 가져오기
        Professor professor = professorRepository.findByProfessorEmail(email);
        
        // 교수 정보가 없거나 비밀번호가 일치하지 않으면 로그인 실패
        if (professor == null || !professor.getProfessorPw().equals(password)) {
            return false;
        }
        
        // 로그인 성공
        return true;
    }
}