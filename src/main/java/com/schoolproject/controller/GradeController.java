package com.schoolproject.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.schoolproject.entity.Grade;
import com.schoolproject.service.GradeService;
import com.schoolproject.service.StudentService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/grades")
public class GradeController {

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