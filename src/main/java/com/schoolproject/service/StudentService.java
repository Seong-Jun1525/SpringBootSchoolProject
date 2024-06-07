package com.schoolproject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
        if (student.getStudentGender() == null) {
            student.setStudentGender("");
        }
        if (student.getStudentRegistrationDate() == null) {
            student.setStudentRegistrationDate(LocalDate.now());
        }

        return studentRepository.save(student);
    }

    
    public Optional<Student> login(String email, String password) {
        return studentRepository.findByStudentEmailAndStudentPw(email, password);
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
}