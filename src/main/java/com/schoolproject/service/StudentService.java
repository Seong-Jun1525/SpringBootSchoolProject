package com.schoolproject.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.schoolproject.entity.Student;
import com.schoolproject.exception.StudentNotFoundException;
import com.schoolproject.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository; // 리포지토리

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student registerStudent(Student student) {
        student.setStudentRegistrationDate(LocalDate.now());
        return studentRepository.save(student);
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