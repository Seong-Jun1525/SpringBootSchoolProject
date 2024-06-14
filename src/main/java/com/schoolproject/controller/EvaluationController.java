package com.schoolproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolproject.entity.Evaluation;
import com.schoolproject.exception.RegisterEvaluationProfessorException;
import com.schoolproject.service.EvaluationService;
import com.schoolproject.service.ProfessorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/evaluations")
public class EvaluationController {
	@Autowired
	private EvaluationService evaluationService;
	@Autowired
	private ProfessorService professorService;
	
	// 학생 강의평가 등록 페이지
	@GetMapping("/register")
    public String registerEvaluationForm(Model model, HttpSession session) {
        model.addAttribute("evaluation", new Evaluation());
        
        // 세션에서 이메일 가져오기
        String loggedInStudentEmail = (String) session.getAttribute("loggedInStudentEmail");
        System.out.println(loggedInStudentEmail); // 정상적으로 저장되어있는지 콘솔에서 확인
        model.addAttribute("loggedInStudentEmail", loggedInStudentEmail);
        return "student/lecture/lectureEvaluation"; // 템플릿 경로
    }
	
	@PostMapping("/register")
    public String registerEvaluation(@ModelAttribute("evaluation") Evaluation evaluation) {
        evaluationService.registerEvaluation(evaluation);
        return "redirect:/";
    }
	
	// 교수 강의평가 등록 페이지
	@GetMapping("/professor/register")
    public String registerEvaluationFormProfessor(Model model, HttpSession session) {
        model.addAttribute("evaluation", new Evaluation());
        // 세션에서 교수명 가져오기
        String loggedInProfessorEmail = (String) session.getAttribute("loggedInProfessorEmail");
		String professorName = professorService.findByProfessorName(loggedInProfessorEmail);
        System.out.println("교수 강의평가 등록 페이지 : " + professorName); // 정상적으로 저장되어있는지 콘솔에서 확인
        model.addAttribute("loggedInProfessorName", professorName);
        return "professor/lecture/lectureEvaluationProfessor"; // 템플릿 경로
    }
	
	// 교수 강의 평가 등록
	@PostMapping("/professor/register")
    public String registerEvaluationProfessor(@ModelAttribute("evaluation") Evaluation evaluation) {
		try {
			evaluationService.registerEvaluation(evaluation);
	        return "redirect:/";
		} catch(Exception e) {
			throw new RegisterEvaluationProfessorException("RegisterEvaluationProfessor 에러 : " + e.getMessage());
		}
    }
	
	@GetMapping("/list")
    public String listEvaluations(Model model, HttpSession session) {
		// 세션에서 이메일 가져오기
        String loggedInStudentEmail = (String) session.getAttribute("loggedInStudentEmail");
        System.out.println(loggedInStudentEmail); // 정상적으로 저장되어있는지 콘솔에서 확인
        model.addAttribute("loggedInStudentEmail", loggedInStudentEmail);
        List<Evaluation> evaluations = evaluationService.findByStudentEmail(loggedInStudentEmail);
        
        model.addAttribute("evaluations", evaluations);
        
        return "student/lecture/lectureEvaluationList";
    }
}