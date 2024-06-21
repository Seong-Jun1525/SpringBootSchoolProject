package com.schoolproject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.schoolproject.entity.Professor;
import com.schoolproject.exception.ProfessorLoginException;
import com.schoolproject.repository.ProfessorRepository;

@Service
public class ProfessorService {
	private final ProfessorRepository professorRepository;
	
	public ProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}
	
	// 교수 등록
	public Professor registerProfessor(Professor professor) {
		professor.setProfessorRegistrationDate(LocalDate.now()); // 가입날짜는 현재날짜
		professorRepository.updateAutoIncrementValue();
		return professorRepository.save(professor);
	}
	
	// 교수 목록
	public List<Professor> findAll() {
        return professorRepository.findAll();
    }
	
	public String findByProfessorName(String professorEmail) {
        Optional<Professor> professorOptional = professorRepository.findByProfessorEmail(professorEmail);
        System.out.println("professorOptional의 값 : " + professorOptional.get().getProfessorName());
        return professorOptional.get().getProfessorName();
	}
	
	public boolean login(String email, String password) throws ProfessorLoginException {
        // 이메일을 통해 교수 정보 가져오기
        Optional<Professor> professorOptional = professorRepository.findByProfessorEmail(email);
        System.out.println("professorOptional의 값 : " + professorOptional.get().getProfessorName());
        // 교수 정보가 없거나 비밀번호가 일치하지 않으면 로그인 실패
        if (professorOptional.isEmpty() || !professorOptional.get().getProfessorPw().equals(password)) {
            throw new ProfessorLoginException("이메일 또는 패스워드가 잘못되었습니다. " + professorOptional);
        }
        // 로그인 성공
        return true;
    }
}