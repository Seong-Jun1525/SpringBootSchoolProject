package com.schoolproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolproject.entity.Professor;
import com.schoolproject.exception.ProfessorLoginException;
import com.schoolproject.service.ProfessorService;

@Controller
@RequestMapping("/professors")
public class ProfessorController {
	@Autowired
	private ProfessorService professorService;
	
	@ModelAttribute("professorEmail")
	public String professorEmail() {
	    return "";
	}
	@ModelAttribute("professorPw")
	public String professorPw() {
	    return "";
	}
	
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
    
    // 교수 로그인 페이지
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("professor", new Professor());
        model.addAttribute("professorEmail", "");
        model.addAttribute("professorPw", "");
        return "professor/professorLogin";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute("professor") Professor professor,
                        BindingResult bindingResult,
                        Model model) {
        if (bindingResult.hasErrors()) {
            return "professor/professorLogin"; // 유효성 검사 오류가 발생한 경우 로그인 페이지 다시 표시
        }

        try {
            boolean loginSuccess = professorService.login(professor.getProfessorEmail(), professor.getProfessorPw());
            if (loginSuccess) {
                // 로그인 성공 시 다음 페이지로 리다이렉트 또는 모델에 추가 정보 전달 가능
                return "redirect:/"; // 예시: 교수 대시보드 페이지로 리다이렉트
            } else {
                // 로그인 실패 시 에러 메시지 표시
                throw new ProfessorLoginException("이메일 또는 패스워드가 잘못되었습니다.");
            }
        } catch (Exception e) {
            throw new ProfessorLoginException("등록된 Email이 없습니다.");
        }
    }
    
    @GetMapping("/list")
    public String listProfessors(Model model) {
        model.addAttribute("professors", professorService.findAll());
        return "professor/professorList";
    }
}
