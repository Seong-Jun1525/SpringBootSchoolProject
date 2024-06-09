package com.schoolproject.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.entity.Grade;
import com.schoolproject.entity.Lecture;
import com.schoolproject.entity.Student;
import com.schoolproject.repository.GradeRepository;
import com.schoolproject.repository.StudentRepository;
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
}
    