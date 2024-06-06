package com.schoolproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolproject.entity.Professor;
import com.schoolproject.service.ProfessorService;

@Controller
@RequestMapping("/professors")
public class ProfessorController {
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/professorRegister";
    }

    @PostMapping("/register")
    public String registerProfessor(@ModelAttribute Professor professor) {
        try {
        	professorService.registerProfessor(professor);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/error/error";
        }
    }
    
    @GetMapping("/list")
    public String listProfessors(Model model) {
        model.addAttribute("professors", professorService.findAll());
        return "professor/professorList";
    }
}
