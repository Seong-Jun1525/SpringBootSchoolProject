package com.schoolproject.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.entity.Evaluation;
import com.schoolproject.repository.EvaluationRepository;

@Service
public class EvaluationService {
	@Autowired
	private final EvaluationRepository evaluationRepository;
	
	public EvaluationService(EvaluationRepository evaluationRepository) {
		this.evaluationRepository = evaluationRepository;
	}
	
	// 강의평가 등록
	public Evaluation registerEvaluation(Evaluation evaluation) {
		evaluation.setLectureRegistrationDate(LocalDate.now()); // 등록날짜는 현재날짜
		return evaluationRepository.save(evaluation);
	}
	
	public List<Evaluation> findByStudentEmail(String studentEmail) {
        return evaluationRepository.findByStudentEmail(studentEmail);
    }
}
