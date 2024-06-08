package com.schoolproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.schoolproject.entity.Course;
import com.schoolproject.service.CourseService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/courses")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	// 강의 등록 페이지
	@GetMapping("/register")
    public String showRegistrationForm(Model model, HttpSession session) {
        model.addAttribute("course", new Course());
        // 세션에서 이메일 가져오기
        String loggedInProfessorEmail = (String) session.getAttribute("loggedInProfessorEmail");
        System.out.println(loggedInProfessorEmail); // 정상적으로 저장되어있는지 콘솔에서 확인
        model.addAttribute("loggedInProfessorEmail", loggedInProfessorEmail);
        return "professor/course/courseRegister";
    }
	
	// 강의 등록
    @PostMapping("/register")
    public String registerCourse(@ModelAttribute Course course) {
    	courseService.registerCourse(course);
    	System.out.println(course);
        try {
        	courseService.registerCourse(course);
        	System.out.println(course);
            return "redirect:/";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }
    
    // 강의 목록 불러오기
    @GetMapping("/list")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "professor/course/courseList";
    }
}
