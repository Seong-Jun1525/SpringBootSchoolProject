package com.schoolproject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolproject.entity.Student;
import com.schoolproject.exception.StudentNotFoundException;
import com.schoolproject.service.StudentService;

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
            return "redirect:/error";
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
    public String showLoginForm(Model model) {
    	model.addAttribute("studentEmail", new Student()); // 이메일 필드를 위한 빈 추가
    	model.addAttribute("studentPw", new Student()); // 비밀번호 필드를 위한 빈 추가
        return "student/login";
    }
    
    @PostMapping("/login")
    public String login(@ModelAttribute Student student, Model model) {
    	
    	
        String email = student.getStudentEmail();
        String password = student.getStudentPw();

        // 이메일과 비밀번호를 이용하여 사용자를 찾습니다.
        Optional<Student> authenticatedStudentOpt = studentService.login(email, password);

        if (authenticatedStudentOpt.isPresent()) {
            // 로그인 성공 시 홈 페이지로 이동
            model.addAttribute("message", "로그인 성공!");
            return "redirect:/";
        } else {
            // 로그인 실패 시 다시 로그인 페이지로 이동
            model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었습니다.");
            return "login";
        }
    }
}
