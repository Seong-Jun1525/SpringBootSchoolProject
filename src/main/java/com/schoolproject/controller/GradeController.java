package com.schoolproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.schoolproject.entity.Grade;
import com.schoolproject.service.GradeService;


@Controller
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    // 성적 입력 페이지
    @GetMapping("/input")
    public String showGradeInputForm(Model model) {
        model.addAttribute("grade", new Grade());
        return "grade/gradeInput"; 
    }

    // 성적 입력 처리
    @PostMapping("/input")
    public String inputGrade(@ModelAttribute Grade grade) {
        try {
            gradeService.inputGrade(grade);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }
    
 // 강의 목록 불러오기
    @GetMapping("/list")
    public String listgrade(Model model) {
        model.addAttribute("grdes", gradeService.findAll());
        return "grade/gradelist";
    }
    
}