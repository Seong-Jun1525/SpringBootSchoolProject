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

    @Autowired
    private StudentService studentService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/studentRegister";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student) {
        try {
            studentService.registerStudent(student);
            return "redirect:/";
        } catch (Exception e) {
        	throw new StudentNotFoundException("Error : " + e.getMessage());
        }
    }

    @GetMapping("/update")
    public String showUpdateForm(Model model) {
        model.addAttribute("student", new Student());
        return "student/register";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {
        try {
            studentService.updateStudent(student);
            return "redirect:/";
        } catch (Exception e) {
        	throw new StudentNotFoundException("등록되어 있지 않은 학번입니다.");
        }
    }

    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        
        return "student/studentList";
    }
    
    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("studentEmail", "");
        model.addAttribute("studentPw", "");
        return "student/studentLogin";
    }

    @PostMapping("/login")
    public String login(
    		@ModelAttribute("student") Student student,
    		BindingResult bindingResult,
    		Model model,
    		HttpSession session) 
    {
        if (bindingResult.hasErrors()) {
            throw new StudentNotFoundException("유효성 검사 오류 발생");
        }
        try {
            boolean loginSuccess = studentService.login(student.getStudentEmail(), student.getStudentPw());
            if (loginSuccess) {
                // 로그인 성공 시 다음 페이지로 리다이렉트 또는 모델에 추가 정보 전달 가능
                System.out.println(student + " " + student.getStudentEmail());
                
                String studentEmail = (String) student.getStudentEmail();
                String studentName = studentService.findBystudentName(studentEmail);
                
                
                session.setAttribute("loggedInStudent", student);
                // 로그인 성공 시 세션에 이메일 저장               
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
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/"; // 홈 페이지 또는 로그아웃 후 이동할 페이지로 리다이렉트
    }
}
