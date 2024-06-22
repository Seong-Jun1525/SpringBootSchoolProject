package com.schoolproject.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.entity.Evaluation;
import com.schoolproject.exception.AlreadyExistsEvaluationException;
import com.schoolproject.repository.EvaluationRepository;

@Service
public class EvaluationService {
	@Autowired
	private final EvaluationRepository evaluationRepository;
	
	public EvaluationService(EvaluationRepository evaluationRepository) {
		this.evaluationRepository = evaluationRepository;
	}

	public List<Evaluation> findByMyEvaluation(int studentNumber) {
		System.out.println(studentNumber);
        return evaluationRepository.findByStudentNumber(studentNumber);
	}

	public Evaluation registerEvaluation(Evaluation evaluation) {
		System.out.println(evaluation.getLectureName());
		
		// 만약 학번이 이미 등록되어 있을 경우
		if (evaluationRepository.existsByEvaluationId(evaluation.getEvaluationId())) {
			throw new AlreadyExistsEvaluationException("해당 교과목의 강의 평가는 등록되어 있습니다.");
		}
		
		evaluation.setEvaluationRegistrationDate(LocalDate.now()); // 등록날짜는 현재날짜
		evaluationRepository.updateAutoIncrementValue();
		return evaluationRepository.save(evaluation);
	}

	public List<Evaluation> findByEvaluationList(String lectureName) {
		return evaluationRepository.findByLectureName(lectureName);
	}
}