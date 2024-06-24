package com.schoolproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolproject.entity.Student;
import com.schoolproject.exception.StudentNotFoundException;
import com.schoolproject.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/students")
public class StudentController {

	// 서비스 등록
    @Autowired
    private StudentService studentService;

    // 회원가입 페이지
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/studentRegister";
    }

    // 학생등록기능
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        try {
            studentService.registerStudent(student);
            return "redirect:/";
        } catch (Exception e) {
        	// 등록된 학번이 없을 경우 예외 처리
        	throw new StudentNotFoundException("Error : " + e.getMessage());
        }
    }
    
    // 학생회원가입 페이지
    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/register";
    }

    // 학생회원가입 기능
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {
        try {
        	// 교수가 먼저 등록한 기본정보에서 추가로 회원개인정보를 등록. 즉, UPDATE
            studentService.updateStudent(student);
            return "redirect:/";
        } catch (Exception e) {
        	throw new StudentNotFoundException("등록되어 있지 않은 학번입니다.");
        }
    }

    // 회원목록 페이지
    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "student/studentList";
    }
    
    // 로그인 페이지
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("studentEmail", "");
        model.addAttribute("studentPw", "");
        return "student/studentLogin";
    }

    // 로그인 기능
    @PostMapping("/login")
    public String login(
    		@ModelAttribute("student") Student student,
    		BindingResult bindingResult,
    		Model model,
    		HttpSession session) {
        if (bindingResult.hasErrors()) throw new StudentNotFoundException("유효성 검사 오류 발생");
        try {
            boolean loginSuccess = studentService.login(student.getStudentEmail(), student.getStudentPw());
            if (loginSuccess) {
                System.out.println(student + " " + student.getStudentEmail()); // TEST
                String studentEmail = (String) student.getStudentEmail();
                String studentName = studentService.findBystudentName(studentEmail);
                // 로그인 성공 시 세션에 저장               
                session.setAttribute("loggedInStudent", student);
                session.setAttribute("loggedInStudentEmail", student.getStudentEmail());
                session.setAttribute("loggedInStudentName", studentName);
                session.setAttribute("loggedInStudentNumber", student.getStudentNumber());
                return "redirect:/";
            } else {
                // 이 부분은 도달하지 않음, 예외가 발생할 것이기 때문
                throw new StudentNotFoundException("이메일 또는 비밀번호가 잘못되었습니다.");
            }
        } catch (StudentNotFoundException e) {
            throw new StudentNotFoundException("이메일 또는 비밀번호가 잘못되었습니다.");
        }
    }
    
    // 로그아웃 기능
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/"; // 홈 페이지 또는 로그아웃 후 이동할 페이지로 리다이렉트
    }
}
