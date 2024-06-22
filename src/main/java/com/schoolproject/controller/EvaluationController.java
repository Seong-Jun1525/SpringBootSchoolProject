package com.schoolproject.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.schoolproject.entity.Enrolment;
import com.schoolproject.entity.Evaluation;
import com.schoolproject.entity.Student;
import com.schoolproject.exception.EvaluationNotFoundException;
import com.schoolproject.exception.StudentNotFoundException;
import com.schoolproject.service.EnrolmentService;
import com.schoolproject.service.EvaluationService;
import com.schoolproject.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/evaluations")
public class EvaluationController {
	@Autowired
	private EvaluationService evaluationService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private EnrolmentService enrolmentService;
	
	// 학생 강의평가 등록 페이지
	@GetMapping("/register")
    public String registerEvaluationForm(
    		Model model,
    		HttpSession session,
	        @SessionAttribute("loggedInStudentEmail") String studentEmail,
    		@RequestParam("item") String lectureName) {
        model.addAttribute("evaluation", new Evaluation());
		// 세션에서 가져온 이메일로 학생정보 찾기
        System.out.println(studentEmail); // 정상적으로 저장되어있는지 콘솔에서 확인
        model.addAttribute("loggedInStudentEmail", studentEmail);
        model.addAttribute("lectureName", lectureName);
        return "student/lecture/lectureEvaluation"; // 템플릿 경로
    }
	
	// 학생 강의평가 등록
	@PostMapping("/register")
    public String updateEvaluation(Model model, Evaluation evaluation) {
        model.addAttribute("evaluation", new Evaluation());
        try {
        	evaluationService.registerEvaluation(evaluation);
            return "redirect:/";
        } catch (Exception e) {
        	throw new EvaluationNotFoundException("등록된 강의평가를 찾을 수 없습니다.");
        }
    }
	
	// 교수 평가목록
	@GetMapping("/professor/evaluationList")
	public String showEvaluationListProfessor(Model model) {
        model.addAttribute("evaluation", new Evaluation());
		return "professor/lecture/professorEvaluationList";
	}
	
	@PostMapping("/professor/search")
	public String evaluationSearch(
			Model model,
			@RequestParam("lectureName") String lectureName,
			@ModelAttribute Evaluation evaluation) {
        List<Evaluation> evaluations = evaluationService.findByEvaluationList(lectureName);
        // TODO 교수명과 학과명으로 evaluation 테이블에서 데이터찾기
        model.addAttribute("evaluations", evaluations);
		return "professor/lecture/evaluationListResults :: evaluationListResultsFragment"; // 검색 결과를 보여줄 템플릿 이름과 fragment 지정
	}
	
	// 교수평가목록 현황확인
	@GetMapping("/professor/lectureEvaluationList")
	public String showLectureEvaluationList() {
		return "professor/lecture/lectureEvaluationList";
	}
	
	// 학생 강의평가 해야할 리스트
	@GetMapping("/list")
    public String listEvaluations(
    		Model model,
	        @SessionAttribute("loggedInStudentEmail") String studentEmail,
    		HttpSession session) {
		// 세션에서 가져온 이메일로 학생정보 찾기
        System.out.println(studentEmail); // 정상적으로 저장되어있는지 콘솔에서 확인
        Optional<Student> studentOptional = studentService.findByEnrolmentStudentInfo(studentEmail);
	    Student student = studentOptional.orElseThrow(() -> new StudentNotFoundException("학생 정보를 찾을 수 없습니다: " + studentEmail));
	    
	    // TODO 수강신청테이블에서 학번데이터로 내가 신청한 수강신청데이터들만 뽑아오기 
	    int studentNumber = student.getStudentNumber();
	    session.setAttribute("loggedInStudentNumber", studentNumber);
	    List<Enrolment> enrolments = enrolmentService.findByMyEnrolment(studentNumber);
	    System.out.println(enrolments.size());
	    List<String> lectureList = new ArrayList<>();
	    for(int i = 0; i < enrolments.size(); i++) {
	    	System.out.println(enrolments.get(i).getLectureName());
	    	lectureList.add(i, enrolments.get(i).getLectureName());
	    }

        model.addAttribute("lectureList", lectureList);
        model.addAttribute("studentNumber", studentNumber);
        return "student/lecture/lectureEvaluationList";
    }
	
	// 평가목록
	@GetMapping("/evaluationList")
    public String showEvaluationList (
    		Model model,
	        @SessionAttribute("loggedInStudentEmail") String studentEmail,
    		HttpSession session) {
		// 세션에서 가져온 이메일로 학생정보 찾기
        System.out.println(studentEmail); // 정상적으로 저장되어있는지 콘솔에서 확인
        Optional<Student> studentOptional = studentService.findByEnrolmentStudentInfo(studentEmail);
	    Student student = studentOptional.orElseThrow(() -> new StudentNotFoundException("학생 정보를 찾을 수 없습니다: " + studentEmail));
	    
	    // TODO 등록된 강의평가목록 불러오기
	    int studentNumber = student.getStudentNumber();
	    session.setAttribute("loggedInStudentNumber", studentNumber);

	    List<Evaluation> evaluations = evaluationService.findByMyEvaluation(studentNumber);

        model.addAttribute("studentNumber", studentNumber);
        model.addAttribute("evaluations", evaluations);
        return "student/lecture/myEvaluationList";
    }
}