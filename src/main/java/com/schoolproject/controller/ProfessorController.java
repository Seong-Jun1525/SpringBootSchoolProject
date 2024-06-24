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

import jakarta.servlet.http.HttpSession;

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
	
	// 교수 등록 페이지
	@GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/professorRegister";
    }

	// 교수 등록
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
    
    // 교수 로그인
    @PostMapping("/login")
    public String login(@ModelAttribute("professor") Professor professor,
                        BindingResult bindingResult,
                        Model model,
                        HttpSession session) {
        if (bindingResult.hasErrors()) throw new ProfessorLoginException("유효성 검사 오류 발생");
        
        // 입력받은 교수 이메일과 교수 패스워드값 확인
        System.out.println(professor.getProfessorEmail() + " " + professor.getProfessorPw());
        try {
        	boolean loginSuccess = professorService.login(professor.getProfessorEmail(), professor.getProfessorPw());
        	if (loginSuccess) {
                // 로그인 성공 시 다음 페이지로 리다이렉트 또는 모델에 추가 정보 전달 가능
        		System.out.println("이메일 : " + professor.getProfessorEmail());
        		
        		String professorEmail = (String) professor.getProfessorEmail();
        		String professorName = professorService.findByProfessorName(professorEmail);
        		System.out.println("이름 : " + professorName);
        		
        		// 세션에 저장
                session.setAttribute("loggedInProfessor", professor);
                session.setAttribute("loggedInProfessorEmail", professor.getProfessorEmail());
                session.setAttribute("loggedInProfessorName", professorName);
                return "redirect:/";
            } else {
                // 이 부분은 도달하지 않음, 예외가 발생할 것이기 때문
                throw new ProfessorLoginException("이메일 또는 패스워드가 잘못되었습니다.");
            }
        } catch (ProfessorLoginException e) {
            throw new ProfessorLoginException("이메일 또는 패스워드가 잘못되었습니다.2");
        }
    }
    
    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/"; // 홈 페이지 또는 로그아웃 후 이동할 페이지로 리다이렉트
    }

    // 교수 목록 불러오기
    @GetMapping("/list")
    public String listProfessors(Model model) {
        model.addAttribute("professors", professorService.findAll());
        return "professor/professorList";
    }
}