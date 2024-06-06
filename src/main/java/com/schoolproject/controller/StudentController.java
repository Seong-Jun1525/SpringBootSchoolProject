package com.schoolproject.controller;

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
}