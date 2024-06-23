package com.schoolproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolproject.entity.Grade;
import com.schoolproject.service.GradeService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/grades")
public class GradeControllerTest {

    @Autowired
    private GradeService gradeService;

    // 성적 입력 페이지
    @GetMapping("/input")
    public String showGradeInputForm(Model model, HttpSession session) {
        model.addAttribute("grade", new Grade());
        String loggedInProfessorEmail = (String) session.getAttribute("loggedInProfessorEmail");
        System.out.println(loggedInProfessorEmail); // 정상적으로 저장되어있는지 콘솔에서 확인
        model.addAttribute("loggedInProfessorEmail", loggedInProfessorEmail);
        return "professor/grade/gradeInput";
    }

    // 성적 입력 처리
    @PostMapping("/input")
    public String inputGrade(@ModelAttribute Grade grade, Model model) {
        try {
            gradeService.inputGrade(grade);
            return "redirect:/";
        } catch (Exception e) {
        	 model.addAttribute("errorMessage", "이미 정보가 들어간 학생입니다.");
            return "redirect:/error";
        }
    }
    
    // 성적 수정 처리
    @GetMapping("/edit/{id}")
    public String editGrade(@PathVariable("id") Long gradeId, Model model) {
        Grade grade = gradeService.getGradeById(gradeId);
        model.addAttribute("grade", grade);
        return "professor/grade/gradeUpdate"; //
    }

    // 수정된 성적 정보 저장
    @PostMapping("/update")
    public String updateGrade(@ModelAttribute("grade") Grade updateGrade) {
    	
    	if (updateGrade.getGradeId() == null) {
            throw new IllegalArgumentException("Grade ID cannot be null");
        }
    	
    	Grade existingGrade = gradeService.getGradeById(updateGrade.getGradeId());
    	existingGrade.setCredit(updateGrade.getCredit());
        existingGrade.setGradeDate(updateGrade.getGradeDate());
        existingGrade.setLectureName(updateGrade.getLectureName());
        existingGrade.setProfessorEmail(updateGrade.getProfessorEmail());
        existingGrade.setScore(updateGrade.getScore());
        existingGrade.setStudentEmail(updateGrade.getStudentEmail());
        existingGrade.setStudentNumber(updateGrade.getStudentNumber());
        gradeService.updateGrade(existingGrade);
        return "redirect:/"; // 수정 후 성적 목록 페이지로 리다이렉트
    }
    
    // 성적 목록 불러오기
    @GetMapping("/list")
    public String listGrade(Model model, HttpSession session) {
        String loggedInStudentEmail = (String) session.getAttribute("loggedInStudentEmail");
        String loggedInProfessorEmail = (String) session.getAttribute("loggedInProfessorEmail");
        
        List<Grade> grades;
        if (loggedInProfessorEmail != null) {
        	System.out.println("LoggedInStudentEmail: " + loggedInStudentEmail);
        	grades = gradeService.findByProfessorEmail(loggedInProfessorEmail);
        	model.addAttribute("grades", grades);
        	return "professor/grade/gradelist";
        }
        
        if (loggedInStudentEmail != null) {
        	
            model.addAttribute("loggedInStudentEmail", loggedInStudentEmail);
            grades = gradeService.findByStudentEmail(loggedInStudentEmail);;
            model.addAttribute("grades", grades);
            return "professor/grade/gradelist";
        }
        return "redirect:/login";
    }
}