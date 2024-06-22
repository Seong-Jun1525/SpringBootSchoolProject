package com.schoolproject.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.schoolproject.entity.Evaluation;
import com.schoolproject.entity.StudentEvaluation;
import com.schoolproject.exception.EvaluationNotFoundException;
import com.schoolproject.repository.EvaluationRepository;
import com.schoolproject.repository.StudentEvaluationRepository;

@Service
public class StudentEvaluationService {
	private final StudentEvaluationRepository studentEvaluationRepository;
	private EvaluationRepository evaluationRepository;
	
	public StudentEvaluationService(StudentEvaluationRepository studentEvaluationRepository) {
		this.studentEvaluationRepository = studentEvaluationRepository;
	}
	
	@SuppressWarnings("null")
	public StudentEvaluation registerEvaluation(Evaluation evaluation) {
		System.out.println(evaluation.getLectureName());
		
		Optional<Evaluation> existingEvaluationOpt = evaluationRepository.findByLectureName(evaluation.getLectureName());
		
		System.out.println(existingEvaluationOpt.isPresent());
		
		if(existingEvaluationOpt.isPresent()) {
			StudentEvaluation studEvaluation = null;
			studEvaluation.setLectureName(evaluation.getLectureName());
			studEvaluation.setLectureName(evaluation.getProfessorName());
			studEvaluation.setProfessorDept(evaluation.getProfessorDept());
			studEvaluation.setLectureType(evaluation.getLectureType());
			studEvaluation.setLectureSemester(evaluation.getLectureSemester());
			studEvaluation.setLectureGrade(evaluation.getLectureGrade());
			studEvaluation.setEvaluationTitle(evaluation.getEvaluationTitle());
			studEvaluation.setEvaluationContent(evaluation.getEvaluationContent());
			studEvaluation.setLectureTotalScore(evaluation.getLectureTotalScore());
			studEvaluation.setLectureScore(evaluation.getLectureScore());
			studEvaluation.setStudentNumber(evaluation.getStudentNumber());
			studEvaluation.setEvaluationRegistrationDate(LocalDate.now()); // 등록날짜는 현재날짜
			return studentEvaluationRepository.save(studEvaluation);
		} else {
            throw new EvaluationNotFoundException("등록된 강의평가를 찾을 수 없습니다.");
        }
	}
}
