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
    	// Check if student_number already exists
        if (studentRepository.existsByStudentNumber(student.getStudentNumber())) {
            throw new AlreadyExistsStudentException("이미 존재하는 학번입니다.");
        }
        
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
            throw new StudentNotFoundException("Student not found with number: " + student.getStudentNumber());
        }
    }
    
    

    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}