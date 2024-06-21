package com.schoolproject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.schoolproject.entity.Professor;
import com.schoolproject.entity.Student;
import com.schoolproject.exception.AlreadyExistsStudentException;
import com.schoolproject.exception.StudentNotFoundException;
import com.schoolproject.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository; // 리포지토리

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student registerStudent(Student student) {
    	
    	generateStudentNumber(student);
    	// 만약 학번이 이미 등록되어 있을 경우
        if (studentRepository.existsByStudentNumber(student.getStudentNumber())) {
            throw new AlreadyExistsStudentException("이미 존재하는 학번입니다.");
        }
        
        /** 
         * 교수가 학생 등록할 때 학생 개인정보는 등록하지 않음
         * 그래서 해당 컬럼들을 기본값으로 DB에 등록
         *  */
        if (student.getStudentName() == null) {
            student.setStudentName("");
        }
        if (student.getStudentEmail() == null) {
            student.setStudentEmail("");
        }
        if (student.getStudentPw() == null) {
            student.setStudentPw("");
        }
        if (student.getStudentBirthdate() == null) {
            student.setStudentBirthdate("");
        }
        if (student.getStudentPhone() == null) {
            student.setStudentPhone("");
        }
        if (student.getStudentPoint() == null) {
            student.setStudentPoint(15);
        }
        if (student.getStudentGender() == null) {
            student.setStudentGender("");
        }
        if (student.getStudentRegistrationDate() == null) {
            student.setStudentRegistrationDate(LocalDate.now());
        }
        studentRepository.updateAutoIncrementValue();
        return studentRepository.save(student);
    }

    private void generateStudentNumber(Student student) {
        // 현재 연도 가져오기
        int currentYear = LocalDate.now().getYear();

        // 전공에 따라 학번 생성
        String studentMajor = student.getStudentMajor();
        int sequence = studentRepository.countByStudentMajor(studentMajor) + 1;
        String studentNumberStr = String.format("%d%s%03d", currentYear, getPrefixForMajor(studentMajor), sequence);
        
        // String을 Integer로 변환
        Integer studentNumber = Integer.parseInt(studentNumberStr);
        student.setStudentNumber(studentNumber);
    }

    private String getPrefixForMajor(String studentMajor) {
        // 각 전공에 따른 학번 접두사 설정
        if ("컴퓨터소프트웨어공학".equals(studentMajor)) {
            return "07";
        } else if ("전자공학".equals(studentMajor)) {
            return "08";
        } else if ("경영학".equals(studentMajor)) {
            return "09";
        } else {
            throw new IllegalArgumentException("지원되지 않는 전공입니다.");
        }
    }
    
    public Student updateStudent(Student student) {
        Optional<Student> existingStudentOpt = studentRepository.findByStudentNumberAndStudentMajor(
                student.getStudentNumber(), student.getStudentMajor());

        if (existingStudentOpt.isPresent()) {
            Student existingStudent = existingStudentOpt.get();
            existingStudent.setStudentName(student.getStudentName());
            existingStudent.setStudentGender(student.getStudentGender());
            existingStudent.setStudentEmail(student.getStudentEmail());
            existingStudent.setStudentPw(student.getStudentPw());
            existingStudent.setStudentPhone(student.getStudentPhone());
            existingStudent.setStudentBirthdate(student.getStudentBirthdate());
            existingStudent.setStudentRegistrationDate(LocalDate.now());
            return studentRepository.save(existingStudent);
        } else {
            throw new StudentNotFoundException("학번을 찾을 수 없습니다. " + student.getStudentNumber());
        }
    }
    
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
    
    public String findBystudentName(String studentEmail) {
        Optional<Student> studentOptional = studentRepository.findByStudentEmail(studentEmail);
        System.out.println("StudentOptional의 값 : " + studentOptional.get().getStudentName());
        return studentOptional.get().getStudentName();
	}
    
    public boolean login(String email, String password) throws StudentNotFoundException {
        // 이메일을 통해 학생 정보 가져오기
        Optional<Student> studentOptional = studentRepository.findByStudentEmail(email);
        
        // 학생 정보가 없거나 비밀번호가 일치하지 않으면 로그인 실패
        if (studentOptional.isEmpty() || !studentOptional.get().getStudentPw().equals(password)) {
            throw new StudentNotFoundException("이메일 또는 비밀번호가 잘못되었습니다. " + studentOptional);
        }

        // 로그인 성공
        return true;
    }
    
    public Optional<Student> findByEnrolmentStudentInfo(String studentEmail) {
    	// 이메일을 통해 학생 정보 가져오기
        Optional<Student> studentOptional = studentRepository.findByStudentEmail(studentEmail);
    	return studentOptional;
    }

    
}