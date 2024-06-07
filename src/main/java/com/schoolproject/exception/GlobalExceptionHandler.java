package com.schoolproject.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ModelAndView handleStudentNotFoundException(StudentNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
    
    @ExceptionHandler(AlreadyExistsStudentException.class)
    public ModelAndView handleStudentNotFoundException(AlreadyExistsStudentException ex) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
    
    @ExceptionHandler(ProfessorLoginException.class)
    public ModelAndView handleStudentNotFoundException(ProfessorLoginException ex) {
        ModelAndView modelAndView = new ModelAndView("error/error");
        modelAndView.addObject("errorMessage", ex.getMessage());
        return modelAndView;
    }
}