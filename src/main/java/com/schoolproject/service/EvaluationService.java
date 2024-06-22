package com.schoolproject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.entity.Evaluation;
import com.schoolproject.exception.AlreadyExistsEvaluationException;
import com.schoolproject.exception.EvaluationNotFoundException;
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
		if(evaluation.getStudentNumber() == null) evaluation.setStudentNumber(0);
		if(evaluation.getEvaluationTitle() == null) evaluation.setEvaluationTitle("");
		if(evaluation.getEvaluationContent() == null) evaluation.setEvaluationContent("");
		if(evaluation.getLectureTotalScore() == null) evaluation.setLectureTotalScore("");
		if(evaluation.getLectureScore() == null) evaluation.setLectureScore("");
		
		evaluation.setEvaluationRegistrationDate(LocalDate.now()); // 등록날짜는 현재날짜
		evaluationRepository.updateAutoIncrementValue();
		return evaluationRepository.save(evaluation);
	}

	public List<Evaluation> findByMyEvaluation(int studentNumber) {
		System.out.println(studentNumber);
        return evaluationRepository.findByStudentNumber(studentNumber);
	}

	public Evaluation updateEvaluation(Evaluation evaluation) {
		System.out.println(evaluation.getLectureName());
		Optional<Evaluation> existingEvaluationOpt = evaluationRepository.findByLectureName(evaluation.getLectureName());
		
		System.out.println(existingEvaluationOpt.isPresent());
		
		if(existingEvaluationOpt.isPresent()) {
			Evaluation existingEvaluation = existingEvaluationOpt.get();
			existingEvaluation.setEvaluationTitle(evaluation.getEvaluationTitle());
			existingEvaluation.setEvaluationContent(evaluation.getEvaluationContent());
			existingEvaluation.setLectureTotalScore(evaluation.getLectureTotalScore());
			existingEvaluation.setLectureScore(evaluation.getLectureScore());
			existingEvaluation.setStudentNumber(evaluation.getStudentNumber());
			return evaluationRepository.save(existingEvaluation);
		} else {
            throw new EvaluationNotFoundException("등록된 강의평가를 찾을 수 없습니다.");
        }
	} 
	
	public List<Evaluation> findByProfessorEvaluation(String professorName, String professorDept) {
		return evaluationRepository.findByProfessorNameAndProfessorDept(professorName, professorDept);
	}

	public List<Evaluation> findByEvaluationList(Evaluation evaluation, String professorName, String professorDept) {
		String lectureName = evaluation.getLectureName();
		String lectureSemester = evaluation.getLectureSemester();
		
		System.out.println(lectureName);
		System.out.println(professorName);
		System.out.println(professorDept);
		System.out.println(lectureSemester);
		
		return evaluationRepository.findByLectureNameAndProfessorNameAndProfessorDeptAndLectureSemester(
				lectureName, professorName, professorDept, lectureSemester);
	}
}