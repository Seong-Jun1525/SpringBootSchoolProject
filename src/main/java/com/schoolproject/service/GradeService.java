package com.schoolproject.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolproject.entity.Grade;
import com.schoolproject.entity.Lecture;
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
    
 // 강의 목록
 	public List<Grade> findAll() {
         return gradeRepository.findAll();
     }
}
    