package com.schoolproject.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	// 등록되어 있지 않은 학번 exception -> 회원가입
    @ExceptionHandler(StudentNotFoundException.class)
    public ModelAndView handleStudentNotFoundException(StudentNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

    // 이미 존재하는 학번 exception -> 회원가입
    @ExceptionHandler(AlreadyExistsStudentException.class)
    public ModelAndView handleStudentNotFoundException(AlreadyExistsStudentException ex) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }

    // 교수 로그인 exception
    @ExceptionHandler(ProfessorLoginException.class)
    public ModelAndView handleStudentNotFoundException(ProfessorLoginException ex) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
    
    // 강의평가 등록 exception -> 교수
    @ExceptionHandler(RegisterEvaluationProfessorException.class)
    public ModelAndView handleRegisterEvaluationProfessorException(RegisterEvaluationProfessorException ex) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
    
    // 이미 존재하는 강의평가 exception -> 교수
    @ExceptionHandler(AlreadyExistsEvaluationException.class)
    public ModelAndView handleAlreadyExistsEvaluationException(AlreadyExistsEvaluationException ex) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
    
    // 학생 포인트 없을 때 exception
    @ExceptionHandler(StudentPointException.class)
    public ModelAndView handleStudentPointException(StudentPointException ex) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
    
    // 수강신청 데이터가 없을 때 exception
    @ExceptionHandler(IsEmptyEnrolmentException.class)
    public ModelAndView handleIsEmptyEnrolmentException(IsEmptyEnrolmentException ex) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
}