package com.schoolproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.entity.Grade;
import com.schoolproject.repository.GradeRepository;

import jakarta.transaction.Transactional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Transactional
    public void inputGrade(Grade grade) {
        gradeRepository.save(grade);
    }

	public List<Grade> findByStudentEmail(String loggedInStudentEmail) {
		return gradeRepository.findByStudentEmail(loggedInStudentEmail);
	}
    
	public List<Grade> findByProfessorEmail(String loggedInProfessorEmail) {
		return gradeRepository.findByProfessorEmail(loggedInProfessorEmail);
	}
	
	public Grade getGradeById(Long gradeId) {
        return gradeRepository.findById(gradeId).orElseThrow();
    }

	@Transactional
    public void updateGrade(Grade updateGrade) {
        // 기존 데이터 가져오기
        Grade existingGrade = getGradeById(updateGrade.getGradeId());
        // 업데이트할 필드들 설정
        existingGrade.setCredit(updateGrade.getCredit());
        existingGrade.setGradeDate(updateGrade.getGradeDate());
        existingGrade.setLectureName(updateGrade.getLectureName());
        existingGrade.setProfessorEmail(updateGrade.getProfessorEmail());
        existingGrade.setScore(updateGrade.getScore());
        existingGrade.setStudentEmail(updateGrade.getStudentEmail());
        existingGrade.setStudentNumber(updateGrade.getStudentNumber());

        // 업데이트된 성적 정보를 저장
        gradeRepository.save(existingGrade);
    }
}
    