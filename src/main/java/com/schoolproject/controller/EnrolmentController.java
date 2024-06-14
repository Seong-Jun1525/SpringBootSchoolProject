package com.schoolproject.controller;

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

import com.schoolproject.entity.Lecture;
import com.schoolproject.entity.Student;
import com.schoolproject.exception.StudentNotFoundException;
import com.schoolproject.service.LectureService;
import com.schoolproject.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/enrolments")
public class EnrolmentController {
	@Autowired
	LectureService lectureService;
	@Autowired
	StudentService studentService;

	@GetMapping("/lectureList")
    public String showEnrolmentPage(Model model) {
        model.addAttribute("lecture", new Lecture()); // 새로운 Lecture 객체를 모델에 추가
        return "student/enrolment/lectureList";
    }
	
	@PostMapping("/search")
	public String searchLectures(@ModelAttribute Lecture lecture, Model model) {
	    System.out.println("입력받은 값 : " + lecture.getLectureGrade());
	    System.out.println("입력받은 값 : " + lecture.getLectureMajor());
	    System.out.println("입력받은 값 : " + lecture.getLectureSemester());
	    System.out.println("입력받은 값 : " + lecture.getLectureRegistrationDate());
	    List<Lecture> lectures = lectureService.findByEnrolmentLecture(lecture);
	    model.addAttribute("lectures", lectures);
	    return "student/enrolment/lectureResults :: lectureResultsFragment"; // 검색 결과를 보여줄 템플릿 이름과 fragment 지정
	}
	
	// 전공
	@GetMapping("/majorsEnrolment")
	public String showMajorsEnrolment(Model model) {
        model.addAttribute("lecture", new Lecture()); // 새로운 Lecture 객체를 모델에 추가
		return "student/enrolment/majorsEnrolment";
	}
	
	@PostMapping("/majorsEnrolment/search")
	public String searchMajorLectures(
			@ModelAttribute Lecture lecture,
			Model model,
			HttpSession session) {
	    System.out.println("학년 값 : " + lecture.getLectureGrade());
	    System.out.println("전공 값 : " + lecture.getLectureMajor());
	    
	    // 입력한 전공과 학년을 세션에 저장
	    session.setAttribute("inputLectureMajorSession", lecture.getLectureMajor());
	    session.setAttribute("inputLectureGradeSession", lecture.getLectureGrade());
	    
	    List<Lecture> lectures = lectureService.findByMajorEnrolmentLecture(lecture);
	    System.out.println("강의 갯수 : " + lectures.size());
	    model.addAttribute("lectures", lectures);
	    return "student/enrolment/majorResults :: majorResultsFragment"; // 검색 결과를 보여줄 템플릿 이름과 fragment 지정
	}
	
	@GetMapping("/major/register")
	public String registerMajorEnrolment(
	        @ModelAttribute Lecture lecture,
	        @RequestParam String lectureId,
	        @SessionAttribute("loggedInStudentEmail") String studentEmail,
	        Model model,
	        HttpSession session) {
		
		/* TODO
		 * 전공수강신청페이지에서 입력한 학과명을 세션으로 저장
		 * 
		 * 해당 데이터로 강의테이블에서 lecture_major명이 현재 세션강의명과 일치하는 값을 찾기
		 */
		
		// 세션에 저장한 학과명과 학년을 확인
		System.out.println("입력받은 학과명 : " + session.getAttribute("inputLectureMajorSession"));
		System.out.println("입력받은 학년 : " + session.getAttribute("inputLectureGradeSession"));
		
		String inputLectureMajorSession = (String) session.getAttribute("inputLectureMajorSession");
		int inputLectureGradeSession = (int) session.getAttribute("inputLectureGradeSession");
		
		List<Lecture> enrolmentLectureInfo = lectureService.findByLectureMajorAndLectureGrade(inputLectureMajorSession, inputLectureGradeSession);
		System.out.println("수강신청 정보 : " + enrolmentLectureInfo.get(0).getLectureMajor() + ", " + enrolmentLectureInfo.get(0).getLectureName());
		System.out.println("강의ID : " + lectureId);
		// TODO : 강의ID 가져오기 성공. 강의ID의 값으로 get(0) 부분 수정하고 이어서 전공수강신청 테이블에 저장할 데이터 정리 후 저장
		// 학생 메일로 수강신청에 필요한 학생정보 가져오기
	    System.out.println("학생메일: " + studentEmail);

	    Optional<Student> studentOptional = studentService.findByEnrolmentStudentInfo(studentEmail);
	    try {
	    	Student student = studentOptional.get();
	        System.out.println("학번: " + student.getStudentNumber());
	        System.out.println("학생명: " + student.getStudentName());
	        System.out.println("학과: " + student.getStudentMajor());
	    } catch(Exception e) {
	    	throw new StudentNotFoundException(e.getMessage());
	    }

	    return "redirect:/";
	}
	
	// 교양
	@GetMapping("/liberalArtsEnrolment")
	public String showLiberalArtsEnrolment() {
		return "student/enrolment/liberalArtsEnrolment";
	}
	
	// 추가수강신청
	@GetMapping("/secondEnrolment")
	public String showSecondEnrolment() {
		return "student/enrolment/secondEnrolment";
	}
}