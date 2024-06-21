package com.schoolproject.controller;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Comparator;
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
import com.schoolproject.entity.Lecture;
import com.schoolproject.entity.Student;
import com.schoolproject.exception.EnrolmentException;
import com.schoolproject.exception.IsEmptyEnrolmentException;
import com.schoolproject.exception.MajorNotEqualException;
import com.schoolproject.exception.StudentNotFoundException;
import com.schoolproject.exception.StudentPointException;
import com.schoolproject.service.EnrolmentService;
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
	@Autowired
	EnrolmentService enrolmentService;

	// 개설강좌
	@GetMapping("/lectureList")
    public String showEnrolmentPage(Model model) {
		int currentYear = Year.now().getValue();
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("lecture", new Lecture()); // 새로운 Lecture 객체를 모델에 추가
        return "student/enrolment/lectureList";
    }
	
	@PostMapping("/search")
	public String searchLectures(
			@ModelAttribute Lecture lecture,
			Model model) {
	    List<Lecture> lectures = lectureService.findBySearchEnrolmentLecture(lecture);
	    model.addAttribute("lectures", lectures);
	    return "student/enrolment/lectureResults :: lectureResultsFragment"; // 검색 결과를 보여줄 템플릿 이름과 fragment 지정
	}
	
	@GetMapping("/enrolmentList")
	public String showMyEnrolmentPage(Model model) {
		model.addAttribute("enrolment", new Enrolment());
		return "student/enrolment/enrolmentList";
	}
	
	@PostMapping("/enrolmentList/search")
	public String searchEnrolments(
			@ModelAttribute Enrolment enrolment,
			@SessionAttribute("loggedInStudentEmail") String studentEmail,
			Model model,
			HttpSession session) {
	    System.out.println("입력받은 값 : " + enrolment.getStudentNumber());
	    System.out.println("입력받은 값 : " + enrolment.getLectureSemester());
	    Optional<Student> studentInfo = studentService.findByEnrolmentStudentInfo(studentEmail);
	    
	    Student student = studentInfo.orElseThrow(() -> new StudentNotFoundException("학생 정보를 찾을 수 없습니다: " + studentEmail));;
	    System.out.println(student.getStudentNumber());
	    int myStudentNumber = student.getStudentNumber();
	    List<Enrolment> enrolments = enrolmentService.findByMyEnrolment(myStudentNumber);
	    model.addAttribute("enrolments", enrolments);
	    return "student/enrolment/enrolmentResults :: enrolmentResultsFragment"; // 검색 결과를 보여줄 템플릿 이름과 fragment 지정
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
	    System.out.println("분류 값 : " + lecture.getLectureType());
	    
	    // 입력한 전공과 학년을 세션에 저장
	    session.setAttribute("inputLectureMajorSession", lecture.getLectureMajor());
	    session.setAttribute("inputLectureGradeSession", lecture.getLectureGrade());
	    session.setAttribute("inputLectureTypeSession", lecture.getLectureType());
	    
	    List<Lecture> lectures = lectureService.findByEnrolmentLecture(lecture);
	    int lectureListSize = lectures.size();
	    for(int i = 0; i < lectureListSize; i++) {
		    System.out.println("전공강의명 : " + lectures.get(i).getLectureName());
	    }
	    System.out.println("강의 갯수 : " + lectures.size());
	    model.addAttribute("lectures", lectures);
	    return "student/enrolment/majorResults :: majorResultsFragment"; // 검색 결과를 보여줄 템플릿 이름과 fragment 지정
	}
	
	@GetMapping("/major/register")
	public String registerMajorEnrolment(
	        @ModelAttribute Lecture lecture,
	        @RequestParam int lectureId,
	        @RequestParam("lectureName") String lectureName,
	        @RequestParam("lectureMajor") String lectureMajor,
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
		System.out.println("입력받은 분류 : " + session.getAttribute("inputLectureTypeSession"));
		
		System.out.println(lectureId);
		String inputLectureMajorSession = (String) session.getAttribute("inputLectureMajorSession");
		int inputLectureGradeSession = (int) session.getAttribute("inputLectureGradeSession");
		String inputLectureTypeSession = (String) session.getAttribute("inputLectureTypeSession");
		
		// TODO : 강의ID 가져오기 성공. 강의ID의 값으로 get(0) 부분 수정하고 이어서 수강신청 테이블에 저장할 데이터 정리 후 저장
		// 학생 메일로 수강신청에 필요한 학생정보 가져오기
		List<Lecture> enrolmentLectureInfo = lectureService.findByLectureMajorAndLectureGrade(inputLectureMajorSession, inputLectureGradeSession, inputLectureTypeSession);		
		int enrolmentListSize = enrolmentLectureInfo.size();
		
		System.out.println("전공 enrolmentListSize " + enrolmentListSize);
		
	    Optional<Student> studentOptional = studentService.findByEnrolmentStudentInfo(studentEmail);
	    Student student = studentOptional.orElseThrow(() -> new StudentNotFoundException("학생 정보를 찾을 수 없습니다: " + studentEmail));
	    
		System.out.println("강의명 : " + lectureName);
		String studentMajor = student.getStudentMajor();
	    try {
			Lecture selectedLecture = enrolmentLectureInfo.stream()
	                .filter(l -> l.getLectureName().equals(lectureName))
	                .findFirst()
	                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 강의명: " + lectureName));
			
	        LocalDateTime currentDate = LocalDateTime.now();
        	Enrolment enrolmentMajor = new Enrolment(
	                null,  // majorEnrolmentId는 자동 생성되므로 null로 설정합니다.
	                student.getStudentNumber(),
	                student.getStudentName(),
	                studentMajor,
	                0,
	                lectureName,
	                selectedLecture.getLectureSemester(),
	                currentDate,  // 현재 날짜로 설정
	                selectedLecture.getLectureCredit(),
	                selectedLecture.getLectureType()
	        );
        	
        	System.out.println("studentMajor : " + studentMajor);
        	System.out.println("lectureMajor : " + lectureMajor);
        	System.out.println(studentMajor == lectureMajor); // 값은 같지만 참조하는 객체가 다르기때문에 비교연산자로 비교할 시 값은 false

        	if(studentMajor.equals(lectureMajor)) {
            	// 수강신청 등록
    	        enrolmentService.registerEnrolment(enrolmentMajor);
    	        // TODO 등록한 강의의 student_count 값 증가시키기
    	        lectureService.updateLectureStudentCount(lectureName);
    	        return "redirect:/";
        	}
        	else {
        		throw new MajorNotEqualException("당신의 전공과목이 아닙니다.");
        	}
	    } catch(Exception e) {
	    	throw new EnrolmentException(e.getMessage());
	    }
	}
	
	// 교양
	@GetMapping("/liberalArtsEnrolment")
	public String showLiberalArtsEnrolment(Model model) {
		model.addAttribute("lecture", new Lecture()); // 새로운 Lecture 객체를 모델에 추가
		return "student/enrolment/liberalArtsEnrolment";
	}
	
	@PostMapping("/liberalArtsEnrolment/search")
	public String searchliberalArtsLectures(
			@ModelAttribute Lecture lecture,
			Model model,
			HttpSession session) {
	    System.out.println("학년 값 : " + lecture.getLectureGrade());
	    System.out.println("전공 값 : " + lecture.getLectureMajor());
	    System.out.println("분류 : " + lecture.getLectureType());
	    
	    // 입력한 전공과 학년을 세션에 저장
	    session.setAttribute("inputLectureMajorSession", lecture.getLectureMajor());
	    session.setAttribute("inputLectureGradeSession", lecture.getLectureGrade());
	    session.setAttribute("inputLectureTypeSession", lecture.getLectureType());
	    
	    List<Lecture> lectures = lectureService.findByEnrolmentLecture(lecture);
	    System.out.println("강의 갯수 : " + lectures.size());
	    model.addAttribute("lectures", lectures);
	    return "student/enrolment/liberalArtsResults :: liberalArtsResultsFragment"; // 검색 결과를 보여줄 템플릿 이름과 fragment 지정
	}
	
	@GetMapping("/liberalArts/register")
	public String registerLiberalArtsEnrolment(Model model) {
		model.addAttribute("lecture", new Lecture()); // 새로운 Lecture 객체를 모델에 추가
	    return "student/enrolment/liberalArtsEnrolment";
	}

	@PostMapping("/liberalArts/register")
	public String registerLiberalArtsEnrolment(
	        @RequestParam("lectureId") int lectureId,
	        @RequestParam("lectureName") String lectureName,
	        @RequestParam("bettingPoints") int bettingPoints,
	        @SessionAttribute("loggedInStudentEmail") String studentEmail,
	        Model model,
	        HttpSession session) {

	    // 세션 값 가져오기
	    String inputLectureMajorSession = (String) session.getAttribute("inputLectureMajorSession");
	    int inputLectureGradeSession = (int) session.getAttribute("inputLectureGradeSession");
	    String inputLectureTypeSession = (String) session.getAttribute("inputLectureTypeSession");

	    // 학생 메일로 수강신청에 필요한 학생정보 가져오기
	    List<Lecture> enrolmentLectureInfo = lectureService.findByLectureMajorAndLectureGrade(inputLectureMajorSession, inputLectureGradeSession, inputLectureTypeSession);
	    int enrolmentListSize = enrolmentLectureInfo.size();
	    System.out.println("교양 enrolmentListSize " + enrolmentListSize);
	    Optional<Student> studentOptional = studentService.findByEnrolmentStudentInfo(studentEmail);
	    Student student = studentOptional.orElseThrow(() -> new StudentNotFoundException("학생 정보를 찾을 수 없습니다: " + studentEmail));
	    
	    try {
	        System.out.println("학생메일: " + studentEmail);
	        System.out.println("학번: " + student.getStudentNumber());
	        System.out.println("학생명: " + student.getStudentName());
	        System.out.println("학과: " + student.getStudentMajor());
	        System.out.println("강의포인트 : " + student.getStudentPoint());

	        // 선택한 강의 정보 출력
	        Lecture selectedLecture = enrolmentLectureInfo.stream()
	                .filter(l -> l.getLectureName().equals(lectureName))
	                .findFirst()
	                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 강의명: " + lectureName));

	        System.out.println("강의명 : " + selectedLecture.getLectureName());
	        System.out.println("강의학기 : " + selectedLecture.getLectureSemester());
	        System.out.println("강의등록일 : " + selectedLecture.getLectureRegistrationDate());
	        System.out.println("강의학점 : " + selectedLecture.getLectureCredit());
	        System.out.println("강의분류 : " + selectedLecture.getLectureType());
	        System.out.println("강의ID : " + lectureId);
	        System.out.println("배팅 포인트 : " + bettingPoints);

	        LocalDateTime currentDate = LocalDateTime.now();

	        // 교양 수강신청 정보 저장
        	Enrolment enrolmentLiberalArt = new Enrolment(
	                null,  // majorEnrolmentId는 자동 생성되므로 null로 설정합니다.
	                student.getStudentNumber(),
	                student.getStudentName(),
	                student.getStudentMajor(),
	                0, 
	                selectedLecture.getLectureName(),
	                selectedLecture.getLectureSemester(),
	                currentDate,  // 현재 날짜로 설정
	                selectedLecture.getLectureCredit(),
	                selectedLecture.getLectureType()
	        );
	        System.out.println(student.getStudentPoint());
	        if(student.getStudentPoint()-bettingPoints >= 0) {
	        	// 배팅 포인트 설정
	        	enrolmentLiberalArt.setStudentPoint(bettingPoints);
		        student.setStudentPoint(student.getStudentPoint()-bettingPoints);
		        enrolmentService.registerEnrolment(enrolmentLiberalArt);
	        }
	        else {
		        throw new StudentPointException("포인트가 부족합니다.");
	        }

	        // TODO 등록한 강의의 student_count 값 증가시키기
	        lectureService.updateLectureStudentCount(lectureName);
	        return "redirect:/"; // 성공적으로 수강신청 후 메인 페이지로 리다이렉트
	    } catch (Exception e) {
	        throw new StudentNotFoundException(e.getMessage());
	    }
	}
	
	// 교수 내 과목 수강신청 현황보기
	/** TODO
	 * 여기서 해야할 것 '마감'버튼을 클릭하면
	 * lecture 테이블의 lecture_capacity(수강정원)의 값을 가져와서
	 * 그 값만큼 수강신청현황에서 짜르고
	 * 나머지 인원들에게 알림을 보낸다
	 */
	@GetMapping("/professor/list")
    public String showEnrolmentStudentList(
            Model model,
            @RequestParam("lectureId") int lectureId,
            @RequestParam("lectureName") String lectureName,
            @RequestParam("lectureType") String lectureType) {
        System.out.println("강의ID : " + lectureId);
        System.out.println("강의명 : " + lectureName);
        System.out.println("강의분류 : " + lectureType);
        
        List<Enrolment> enrolmentStudentList = enrolmentService.findByEnrolmentStudentList(lectureName);
        /** studentPoint 값을 기준으로 내림차순 정렬
         * Comparator : 자바 객체 비교 인터페이스
         * comparingInt: 정수 값을 기준으로 비교
         * 메서드 레퍼런스 (T :: 값) : 정수값을 반환(여기서는 studentPoint)
         * reversed : 내림차순
         */
    	if(!enrolmentStudentList.isEmpty()) {
            enrolmentStudentList.sort(Comparator.comparingInt(Enrolment::getStudentPoint).reversed());
            model.addAttribute("lectureName", enrolmentStudentList.get(lectureId-1).getLectureName());
            model.addAttribute("enrolments", enrolmentStudentList);
            return "professor/enrolment/myStudentList";
    	} else {
    		System.out.println("수강신청 데이터가 없습니다.");
        	throw new IsEmptyEnrolmentException("수강신청 데이터가 없습니다.");
    	}
        
    }
	
	@PostMapping("/professor/deadline")
    public String deadlineRegistration(@RequestParam("lectureName") String lectureName, Enrolment enrolment) {
        lectureService.updateLectureDeadline(lectureName, 1); // deadline 값 1로 변경해서 수강신청 마감

        List<Enrolment> enrolmentStudentList = enrolmentService.findByEnrolmentStudentList(lectureName);
        List<Lecture> lectureList = lectureService.findByLectureName(lectureName);
        int lectureCapacity = lectureList.get(0).getLectureCapacity();
        
        System.out.println(lectureList.get(0).getLectureName());
        System.out.println("수강정원 : " + lectureList.get(0).getLectureCapacity());
        
        int enrolmentStudentListSize = enrolmentStudentList.size();
        enrolmentService.deleteStudent(lectureName, enrolmentStudentListSize, lectureCapacity);
        return "redirect:/";
    }

	
	// 추가수강신청
	@GetMapping("/secondEnrolment")
	public String showSecondEnrolment(Model model) {
		model.addAttribute("lecture", new Lecture());
		return "student/enrolment/secondEnrolment";
	}
	
	@PostMapping("/secondEnrolment/search")
	public String searchSecondLectures(
			@ModelAttribute Lecture lecture,
			Model model,
			HttpSession session) {
	    System.out.println("학년 값 : " + lecture.getLectureGrade());
	    System.out.println("전공 값 : " + lecture.getLectureMajor());
	    System.out.println("분류 : " + lecture.getLectureType());
	    
	    // 입력한 전공과 학년을 세션에 저장
	    session.setAttribute("inputLectureMajorSession", lecture.getLectureMajor());
	    session.setAttribute("inputLectureGradeSession", lecture.getLectureGrade());
	    session.setAttribute("inputLectureTypeSession", lecture.getLectureType());
	    
	    List<Lecture> lectures = lectureService.findBySecondEnrolmentLecture(lecture);
	    System.out.println("강의 갯수 : " + lectures.size());
	    model.addAttribute("lectures", lectures);
	    return "student/enrolment/secondResults :: secondResultsFragment"; // 검색 결과를 보여줄 템플릿 이름과 fragment 지정
	}

	@GetMapping("/secondEnrolment/register")
	public String registerSecondEnrolment(
	        @RequestParam("lectureId") int lectureId,
	        @RequestParam("lectureName") String lectureName,
	        @SessionAttribute("loggedInStudentEmail") String studentEmail,
	        Model model,
	        HttpSession session) {
	    // 세션 값 가져오기
	    String inputLectureMajorSession = (String) session.getAttribute("inputLectureMajorSession");
	    int inputLectureGradeSession = (int) session.getAttribute("inputLectureGradeSession");
	    String inputLectureTypeSession = (String) session.getAttribute("inputLectureTypeSession");

	    // 학생 메일로 수강신청에 필요한 학생정보 가져오기
	    List<Lecture> enrolmentLectureInfo = lectureService.findByLectureMajorAndLectureGrade(inputLectureMajorSession, inputLectureGradeSession, inputLectureTypeSession);
	    int enrolmentListSize = enrolmentLectureInfo.size();
	    System.out.println("교양 enrolmentListSize " + enrolmentListSize);
	    Optional<Student> studentOptional = studentService.findByEnrolmentStudentInfo(studentEmail);
	    Student student = studentOptional.orElseThrow(() -> new StudentNotFoundException("학생 정보를 찾을 수 없습니다: " + studentEmail));
	    
	    try {
	        System.out.println("학생메일: " + studentEmail);
	        System.out.println("학번: " + student.getStudentNumber());
	        System.out.println("학생명: " + student.getStudentName());
	        System.out.println("학과: " + student.getStudentMajor());

	        // 선택한 강의 정보 출력
	        Lecture selectedLecture = enrolmentLectureInfo.stream()
	                .filter(l -> l.getLectureName().equals(lectureName))
	                .findFirst()
	                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 강의명: " + lectureName));

	        System.out.println("강의명 : " + selectedLecture.getLectureName());
	        System.out.println("강의학기 : " + selectedLecture.getLectureSemester());
	        System.out.println("강의등록일 : " + selectedLecture.getLectureRegistrationDate());
	        System.out.println("강의학점 : " + selectedLecture.getLectureCredit());
	        System.out.println("강의분류 : " + selectedLecture.getLectureType());
	        System.out.println("강의ID : " + lectureId);

	        LocalDateTime currentDate = LocalDateTime.now();

	        // 추가 수강신청 정보 저장
        	Enrolment enrolmentSecond = new Enrolment(
	                null,  // majorEnrolmentId는 자동 생성되므로 null로 설정합니다.
	                student.getStudentNumber(),
	                student.getStudentName(),
	                student.getStudentMajor(),
	                0,
	                selectedLecture.getLectureName(),
	                selectedLecture.getLectureSemester(),
	                currentDate,  // 현재 날짜로 설정
	                selectedLecture.getLectureCredit(),
	                selectedLecture.getLectureType()
	        );

	        enrolmentService.registerEnrolment(enrolmentSecond);
	        // TODO 등록한 강의의 student_count 값 증가시키기
	        lectureService.updateLectureStudentCount(lectureName);
	        return "redirect:/"; // 성공적으로 수강신청 후 메인 페이지로 리다이렉트
	    } catch (Exception e) {
	        throw new StudentNotFoundException(e.getMessage());
	    }
	}
}