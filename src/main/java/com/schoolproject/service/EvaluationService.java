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
	
	// 강의평가 등록
	public Evaluation registerEvaluation(Evaluation evaluation) {
    	// 만약 학번이 이미 등록되어 있을 경우
        if (evaluationRepository.existsByEvaluationId(evaluation.getEvaluationId())) {
            throw new AlreadyExistsEvaluationException("해당 교과목의 강의 평가는 등록되어 있습니다.");
        }
		
        /** 
         * 교수가 강의평가 등록할 때 강의를 평가하는 컬럼 값은 입력하지 않음
         * 그래서 해당 컬럼들을 기본값으로 DB에 등록
         *  */
		if(evaluation.getStudentEmail() == null) evaluation.setStudentEmail("");
		if(evaluation.getEvaluationTitle() == null) evaluation.setEvaluationTitle("");
		if(evaluation.getEvaluationContent() == null) evaluation.setEvaluationContent("");
		if(evaluation.getLectureTotalScore() == null) evaluation.setLectureTotalScore("");
		if(evaluation.getLectureScore() == null) evaluation.setLectureScore("");
		
		evaluation.setLectureRegistrationDate(LocalDate.now()); // 등록날짜는 현재날짜
		evaluationRepository.updateAutoIncrementValue();
		return evaluationRepository.save(evaluation);
	}
	
	public List<Evaluation> findByStudentEmail(String studentEmail) {
        return evaluationRepository.findByStudentEmail(studentEmail);
    }
}